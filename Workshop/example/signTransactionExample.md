## Signing Transaction Example code

JavaScript

```javascript
// This example demostrates how to sign a Transaction Object responded from FST Engine API
// Please see more details at https://github.com/fstnetwork/eth-key-lib-js

// npm i "https://github.com/fstnetwork/eth-key-lib-js"
import { DecryptEthereumKeyJson, SignTransaction } from "eth-key-lib-js";

// The transaction object in the response from FST Engine API
const transaction = {
  chainId: 42,
  data: "0x",
  gas: "0x5208",
  gasPrice: "0x3b9aca00",
  nonce: "0xed",
  to: "0x1211ef4E91607766a19e544a2F8d0CA68837cAd0",
  value: "0xc12dc63fa970000"
};

// The submitToken is also in the response from FST Engine API
// submitToken is used when submitting the signed transaction
const submitToken = "A JWT String....";

// The encrypted key-store file / wallet file / key file of the signer
const ethereumKey = {
  id: "64031d31-53a4-11e8-b00a-2b7a29c9f6b9",
  address: "0x3a7af8b8c19c404670c1470273bca449148df4ed",
  crypto: {
    kdf: "scrypt",
    mac: "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
    cipher: "aes-128-ctr",
    kdfparams: {
      n: 262144,
      p: 1,
      r: 8,
      salt: "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aba",
      dklen: 32
    },
    ciphertext:
      "dc1bfefb51e55f4d063f835a44f5c172008fea7eeb4b0da7fb7e50f3a18cf9ef",
    cipherparams: {
      iv: "b343d847b8a72ad68c6bf10866757421"
    }
  },
  version: 3
};

// Decrypt the ethereumKey and get wallet object containing the private key
// walletObj.privateKeyBuffer is the private key for signing
const walletObj = DecryptEthereumKeyJson("THE PASSPHRASE", ethereumKey);

// Sign the transaction object with the private key to get Signed Transaction
const signedTx = SignTransaction(walletObj.privateKeyBuffer, transaction);

// Assemble the signedTx and submitToken, then use it in submitTransaction API
// Please refer to the section of submitTransaction in FST Engine API Doc
SUBMIT_TRASACTION({ signedTx, submitToken }).then(resp =>
  // The hash of the transaction
  console.log(resp.data.submitTransaction.transactionHash)
);
```

Java (Please notice the critical part)

> For full working example, please refer to [jvm-example](/Workshop/example/jvm-example/src/main/java/network/fst/example/JavaExample.java)

```java
package network.fst.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// web3j library (https://github.com/web3j/web3j)
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class JavaExample implements Example {
    private static final Logger log = LoggerFactory.getLogger(JavaExample.class);

    private String bearerToken;
    private String address;

    public void runExample() {
        // send ether to receiver
        // 1 * 10e-18 ether
        BigInteger value = new BigInteger("1");
        String txHash = sendEther("RECEIVER_ADDRESS", value, "MY_PASSPHARSE");
        System.out.println(
                "Please verify your transaction result on:\n" +
                        "https://explorer.[DOMAIN].workshop.fst.network/tx/" + txHash);
    }

    private String sendEther(String to, BigInteger value, String passphrase) {
        try {
            log.info("call sendEther api...");
            
            // please do not forget to call mutation api with bearer token
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

            // CRITICAL PART STARTS HERE

            log.info("decrypt ethereum key...");
            JsonNode keyJson = data.path("ethereumKey");
            // DefaultObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper 
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

            // CRITICAL PART ENDS HERE

            // please do not forget to attach submitToken from the response of mutation
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

```
