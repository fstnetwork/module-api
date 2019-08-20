package network.fst.example

import network.fst.example.api.http.HttpClient
import network.fst.example.api.{GraphQLRequest, GraphqlClient}
import network.fst.example.model.{Input, SendEther, SubmitTransaction}
import network.fst.example.utils.{APIUtils, DefaultObjectMapper, Logging}
import org.web3j.crypto._
import org.web3j.utils.{Numeric => NumericUtils}

class ScalaExample(config: Config) extends Logging with Example {

  private val httpClient = HttpClient();
  private val signInClient = new GraphqlClient(
    config.singInEndpoint,
    httpClient
  )
  private val apiClient = new GraphqlClient(
    config.apiEndpoint,
    httpClient
  )

  log.info("call signIn api...")
  private val bearerToken = APIUtils.signIn(signInClient, config.id, config.password)
  if (bearerToken == null || bearerToken.isEmpty) {
    throw new RuntimeException("login failed")
  }

  log.info("call ethereumKey api...")
  private val address = APIUtils.getAddress(apiClient, this.bearerToken)

  override def runExample(): Unit = {
    // send ether to myself
    // 1 * 10e-18 ether
    val value = BigInt(1)
    val txHash = sendEther(this.address, value, config.passphrase)
    println(
      s"""Please verify your transaction result on:
         |https://explorer.[DOMAIN].workshop.fst.network/tx/${txHash}
         |""".stripMargin)
  }

  def sendEther(to: String, value: BigInt, passphrase: String): String = {
    log.info("call sendEther api...")
    val sendEtherRequest = GraphQLRequest(
      query =
        """mutation SendEther($input: EtherTransferInput!) {
          |  sendEther(input: $input) {
          |    transaction
          |    submitToken
          |    ethereumKey
          |  }
          |}""".stripMargin,
      variables = Input(SendEther(to, value.toString))
    )
    val sendEtherResponse = apiClient.sendGraphqlRequest(sendEtherRequest, Some(this.bearerToken))
    val data = sendEtherResponse.path("data").path("sendEther")

    log.info("decrypt ethereum key...")
    val keyJson = data.path("ethereumKey")
    val walletFile = DefaultObjectMapper.treeToValue(keyJson, classOf[WalletFile])
    val credential = Credentials.create(Wallet.decrypt(passphrase, walletFile))

    log.info("deserialize transaction...")
    val txJson = data.path("transaction")
    val tx = RawTransaction.createTransaction(
      NumericUtils.toBigInt(txJson.path("nonce").textValue()),
      NumericUtils.toBigInt(txJson.path("gasPrice").textValue()),
      NumericUtils.toBigInt(txJson.path("gas").textValue()),
      txJson.path("to").textValue(),
      NumericUtils.toBigInt(txJson.path("value").textValue()),
      txJson.path("data").textValue()
    )

    log.info("signing transaction...")
    val chainId = NumericUtils.toBigInt(txJson.path("chainId").textValue()).longValueExact()
    val signedTx = TransactionEncoder.signMessage(tx, chainId, credential)

    log.info("submit transaction...")
    val submitToken = data.path("submitToken").asText()
    val submitTransactionRequest = GraphQLRequest(
      query =
        """mutation SubmitTransaction($input: SubmitTransactionInput!) {
          |  submitTransaction(input: $input) {
          |    transactionHash
          |  }
          |}""".stripMargin,
      variables = Input(SubmitTransaction(NumericUtils.toHexString(signedTx), submitToken))
    )
    val submitTransactionResponse = apiClient.sendGraphqlRequest(submitTransactionRequest, Some(this.bearerToken))
    submitTransactionResponse.path("data").path("submitTransaction").path("transactionHash").asText()
  }
}
