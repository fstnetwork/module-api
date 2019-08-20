package network.fst.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import network.fst.example.api.GraphQLRequest;
import network.fst.example.api.GraphqlClient;
import network.fst.example.api.http.HttpClient;
import network.fst.example.model.Input;
import network.fst.example.model.SendEther;
import network.fst.example.model.SubmitTransaction;
import network.fst.example.utils.APIUtils;
import network.fst.example.utils.DefaultObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;
import scala.Option;

import java.math.BigInteger;

public class JavaExample implements Example {
    private static final Logger log = LoggerFactory.getLogger(JavaExample.class);

    private Config config;
    private GraphqlClient signInClient;
    private GraphqlClient apiClient;
    private String bearerToken;
    private String address;

    public JavaExample(Config config) {
        this.config = config;

        HttpClient httpClient = HttpClient.apply();
        this.signInClient = new GraphqlClient(
                config.singInEndpoint(),
                httpClient
        );
        this.apiClient = new GraphqlClient(
                config.apiEndpoint(),
                httpClient
        );

        log.info("call signIn api...");
        this.bearerToken = APIUtils.signIn(signInClient, config.id(), config.password());
        if (this.bearerToken == null || this.bearerToken.isEmpty()) {
            throw new RuntimeException("login failed");
        }

        log.info("call ethereumKey api...");
        this.address = APIUtils.getAddress(apiClient, this.bearerToken);
    }

    public void runExample() {
        // send ether to myself
        // 1 * 10e-18 ether
        BigInteger value = new BigInteger("1");
        String txHash = sendEther(this.address, value, this.config.passphrase());
        System.out.println(
                "Please verify your transaction result on:\n" +
                        "https://explorer.[DOMAIN].workshop.fst.network/tx/" + txHash);
    }

    private String sendEther(String to, BigInteger value, String passphrase) {
        try {
            log.info("call sendEther api...");
            GraphQLRequest<Input<SendEther>> sendEtherRequest = GraphQLRequest.apply(
                    "mutation SendEther($input: EtherTransferInput!) {" +
                            "  sendEther(input: $input) {" +
                            "    transaction" +
                            "    submitToken" +
                            "    ethereumKey" +
                            "  }" +
                            "}",
                    new Input<>(new SendEther(to, value.toString()))
            );
            JsonNode sendEtherResponse = this.apiClient.sendGraphqlRequest(sendEtherRequest, Option.apply(this.bearerToken));
            JsonNode data = sendEtherResponse.path("data").path("sendEther");

            log.info("decrypt ethereum key...");
            JsonNode keyJson = data.path("ethereumKey");
            WalletFile walletFile = DefaultObjectMapper.treeToValue(keyJson, WalletFile.class);
            Credentials credential = Credentials.create(Wallet.decrypt(passphrase, walletFile));

            log.info("deserialize transaction...");
            JsonNode txJson = data.path("transaction");
            RawTransaction tx = RawTransaction.createTransaction(
                    Numeric.toBigInt(txJson.path("nonce").textValue()),
                    Numeric.toBigInt(txJson.path("gasPrice").textValue()),
                    Numeric.toBigInt(txJson.path("gas").textValue()),
                    txJson.path("to").textValue(),
                    Numeric.toBigInt(txJson.path("value").textValue()),
                    txJson.path("data").textValue()
            );

            log.info("signing transaction...");
            long chainId = Numeric.toBigInt(txJson.path("chainId").textValue()).longValueExact();
            byte[] signedTx = TransactionEncoder.signMessage(tx, chainId, credential);

            log.info("submit transaction...");
            String submitToken = data.path("submitToken").asText();
            GraphQLRequest submitTransactionRequest = GraphQLRequest.apply(
                    "mutation SubmitTransaction($input: SubmitTransactionInput!) {" +
                            "  submitTransaction(input: $input) {" +
                            "    transactionHash" +
                            "  }" +
                            "}",
                    new Input<>(new SubmitTransaction(Numeric.toHexString(signedTx), submitToken))
            );
            JsonNode submitTransactionResponse = apiClient.sendGraphqlRequest(submitTransactionRequest, Option.apply(this.bearerToken));
            return submitTransactionResponse.path("data").path("submitTransaction").path("transactionHash").asText();

        } catch (CipherException e) {
            throw new RuntimeException("fail to decrypt key", e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("fail to deserialize json", e);
        }
    }
}
