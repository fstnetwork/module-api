# Send a Transaction

> In this chapter, you will understand the whole process of API calls related to Transaction.

## Table of Contents

 1. Prerequisite
 2. Encode an Ethereum Transaction (e.g token transfer)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Confirm the Ethereum Transaction
 7. Next step

## Prerequisite

 1. Complete `Connect to FsTK Engine API` & `Get account information` (last 2 chapters)
 2. Confirm existed crypto assets (owned by current user)
 3. Confirm sufficient Ether (ETH) for ETH gas fee.

## Encode a Ethereum Transaction (e.g. token transfer)

 > In any of following API calls, please remember to direct to `authorization` in http request header.

 - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

    ```graphql
    mutation erc20Transfer($input: ERC20TransferInput!) {
      erc20Transfer(input: $input) {
        pendingTransactions
        transaction
        submitToken
      }
    }
    ```

    Variables:

    ```json
    {  
      "input":{  
        "id": "VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM=",
        "to": "0x0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f",
        "value": "123456789123456789"
      }
    }
    ```

    > `id` is the `id` of transferring token. Token id of current user can be reviewed in `get me` (in `tokenBalances` section).

    > `to` is the Ethereum address the token will be transferred to.

    > `value` is the transferring amount. 
    > Please remember the unit is [`wei`](https://etherconverter.online).
    > e.g. In order to transfer `1` token, `value` should be `"1000000000000000000"`.
    > and in another way, `value` of `"123456789123456789"` means `0.123456789123456789` Token.
 - Using cURL

    ```sh
    curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDAyOTQxMSwiZXhwIjoxNTUwMTE1ODExLCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.N44Ga-96NPZhBD82tLm2od9RVRIn67YIJXa-Pl9-y1UB-xfPrHpeQhq8yVDw21E6W1AQCAVgLwfOmgQn8zzxtQ' \
         --header 'content-type: application/json' \
         --cookie locale=en \
         --data '{"query":"mutation erc20Transfer($input: ERC20TransferInput!) {\n  erc20Transfer(input: $input) {\n    pendingTransactions\n    transaction\n    submitToken\n  }\n}\n","variables":{"input":{"id":"VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM=","to":"0x0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f","value":"123456789123456789"}},"operationName":"erc20Transfer"}'
    ```

 - Response

    ```json
    {
      "data": {
        "erc20Transfer": {
          "pendingTransactions": "0",
          "transaction": {
            "nonce": "0x10d",
            "gasPrice": "0x3b9aca00",
            "gas": "0xf30f",
            "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
            "value": "0x0",
            "data": "0xa9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f15",
            "chainId": 42
          },
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJ0eCI6IitHcUNBUTJFTzVyS0FJTHpENVFBNHZReW1mVVVWNU5UTTY3MnlWYXlOUHBIZ1lDNFJLa0ZuTHNBQUFBQUFBQUFBQUFBQUFBUER3OFBEdzhQRHc4UER3OFBEdzhQRHc4UER3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHMm0wdXMwRjhWS29DQSIsImluZm8iOnt9LCJpYXQiOjE1NTA0NzgyNzcsImV4cCI6MTU1MDQ3ODg3NywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.Qv8mA7mqsQ5RBkMcXvJ2qZOed14Vx-DJPQc3k-U1beb1mFx3Ok-MlZoYivOC-Z1IP0YmS3NJTfrJpOUxOUuVaw"
        }
      }
    }
    ```

    > Please notice that `erc20Transfer` is for `mutation erc20Transfer` in response. Different encode requests will have different strings but structure of response object remains identical.
  
    > In response's `transaction`, object will be used to sign payload, `submitToken` is also required for broadcasting signed transaction.

    > Please remember that  response will vary after each call, please use the latest response for next steps.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "erc20Transfer": null
      },
      "errors": [....]
    }
    ```  
    > means the transaction will fail. We suggest to skip the following steps and check related resources of Transaction are correct first.
    > e.g. ETH balance, FST Service Gas balance, Token balance, Voucher balance, ... etc..

## Decrypt the Ethereum Key JSON

 > Please notice the difference between `password` and `passphrase` in FsTK system. `password` is required to sign in Tokeneden;  `passphrase` is required to decrypt Ethereum key JSON and sign the transaction. 

 > Word usage may be different in other libraries, i.e. `passphrase` means `password`.

 > To start with, use `get me` to fetch `ethereumKey` like the following:

 ```json
 {
   "id": "64031d31-53a4-11e8-b00a-2b7a29c9f6b9",
   "address": "0x3a7af8b8c19c404670c1470273bca449148df4ed",
   "crypto": {
     "kdf": "scrypt",
     "mac": "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
     "cipher": "aes-128-ctr",
     "kdfparams": {
       "n": 262144,
       "p": 1,
       "r": 8,
       "salt": "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aba",
       "dklen": 32
     },
     "ciphertext": "dc1bfefb51e55f4d063f835a44f5c172008fea7eeb4b0da7fb7e50f3a18cf9ef",
     "cipherparams": {
       "iv": "b343d847b8a72ad68c6bf10866757421"
     }
   },
   "version": 3
 }
 ```

 > This is current user's Ethereum key JSON, which includes encrypted private key (by passphrase). This can be safely stored but remain private unless necessary.
 
 > Owning private key means owning the Ethereum Account. Please securely store Ethereum key JSON and passphrase.

 > **WARNING: If the passphrase of Ethereum key JSON is lost, the private key is lost and FsTK does not have users' Ethereum key JSON passphrase**

 - Using JavaScript (Node.js)

    > Install [eth-key-lib](https://github.com/fstnetwork/eth-key-lib-js)

    ```sh
    npm i --save "https://github.com/fstnetwork/eth-key-lib-js"
    # or
    # yarn add "https://github.com/fstnetwork/eth-key-lib-js"
    ```

    ```javascript
    import { DecryptEthereumKeyJson } from "eth-key-lib-js";

    const walletObj = DecryptEthereumKeyJson('the passphrase', {
      "id": "64031d31-53a4-11e8-b00a-2b7a29c9f6b9",
      "address": "0x3a7af8b8c19c404670c1470273bca449148df4ed",
      "crypto": {
        "kdf": "scrypt",
        "mac": "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
        "cipher": "aes-128-ctr",
        "kdfparams": {
          "n": 262144,
          "p": 1,
          "r": 8,
          "salt": "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aba",
          "dklen": 32
        },
        "ciphertext": "dc1bfefb51e55f4d063f835a44f5c172008fea7eeb4b0da7fb7e50f3a18cf9ef",
        "cipherparams": {
          "iv": "b343d847b8a72ad68c6bf10866757421"
        }
      },
      "version": 3
    })

    console.log(walletObj) // walletObj.privateKeyBuffer is the private key for signing
    ```

    > This is the module import of ES6. If your node.js does not support it, please refer to [Webpack](https://webpack.js.org/configuration/target) (`target = "node"`) and the followings:

    > Minimal `webpack.config.js`

    ```javascript
    const path = require("path");

    const CleanWebpackPlugin = require("clean-webpack-plugin");
    
    module.exports = {
      entry: "./index.js",
      plugins: [new CleanWebpackPlugin(["dist"])],
      output: {
        path: path.resolve(__dirname, "dist"),
        filename: "index.js"
      },
      mode: "none",
      target: "node",
      externals: {
        scrypt: "scrypt"
      }
    };
    ```

    > Minimal `package.json`

    ```json
    {
      "main": "index.js",
      "dependencies": {
        "eth-key-lib-js": "https://github.com/fstnetwork/eth-key-lib-js"
      },
      "scripts": {
        "start": "webpack && node dist"
      },
      "devDependencies": {
        "clean-webpack-plugin": "^1.0.1",
        "webpack": "^4.29.4",
        "webpack-cli": "^3.2.3"
      }
    }
    ```

    > Install command line (please let `index.js` be the program entry point)

    ```sh
    npm i && npm start
    # or
    # yarn && yarn start
    ```

    > If working on windows, please refer to [node-gyp on windows](https://github.com/nodejs/node-gyp#on-windows).

 - Using Java

   > Please refer to [Web3j](https://web3j.io)  
   > Notice that `loadCredentials` in `WalletUtils` method with this overload:

   ```Java
   public static Credentials loadCredentials(String password, File source)
   ```
   > In another way, as web3j only provides `File` import, please pay attention to OS storage or use in-memory-fs in Java

   > Please to refer to [Web3j sample codes](https://docs.web3j.io/transactions.html#creating-and-working-with-wallet-files)

 - Using C#

   > Please refer to [Nethereum](https://nethereum.com)  
   > Please refer to [Nethereum sample codes](https://nethereum.readthedocs.io/en/latest/accounts/#working-with-an-account)

   ```csharp
   Nethereum.Web3.Accounts.Account.LoadFromKeyStore(keyStoreEncryptedJson, passphrase)
   ```

   > Please use `Account.PrivateKey` to fetch private key from `Account`.

## Sign the Ethereum Transaction

 - Using JavaScript

    ```javascript
    import { SignTransaction } from "eth-key-lib-js";

    const privateKeyBuffer = walletObj.privateKeyBuffer

    const signedTransaction = SignTransaction(privateKeyBuffer, {
      "nonce": "0x10d",
      "gasPrice": "0x3b9aca00",
      "gas": "0xf30f",
      "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
      "value": "0x0",
      "data": "0xa9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f15",
      "chainId": 42
    })

    console.log(signedTransaction)
    ```

 - Using Java

   > Please refer to [Web3j](https://web3j.io)  
   > Notice that `signMessage` in `TransactionEncoder`, and please use the overload below since the `chainId` must be included in the signature process

   ```java
   public static byte[] signMessage(RawTransaction rawTransaction, byte chainId, Credentials credentials)
   ```

   > Please refer to [Web3j sample codes](https://docs.web3j.io/transactions.html#signing-transactions)

 - Using C#

   > Please refer to [Nethereum](https://nethereum.com)  
   > Please refer to `SignTransaction` in `TransactionSigner`, and please use the overload below since the `chainId` must be included in the signature process

   ```csharp
   public string SignTransaction(byte[] privateKey, BigInteger chainId, string to, BigInteger amount, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, string data)
   ```

   > Please refer to the section `Nethereum.Web3.Accounts.AccountSignerTransactionManager.SignTransaction`

 ## Broadcast the Ethereum Transaction
 
  - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

    ```graphql
    mutation submitSignedTransaction($input: SubmitTransactionInput!) {
      submitTransaction(input: $input) {
        transactionHash
      }
    }
    ```

    Variables:

    ```json
    {
      "input": {
        "data": "0xf8aa82010d843b9aca0082f30f9400e2f43299f51457935333aef6c956b234fa478180b844a9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f1578a0b8a0eee843ab7a58f0d36ba94829c4ab0422b7f26f5e114ff1f662892fdc07e1a028dd5b5b6b0c1e39fa094a971d2614661ff18942b7db72be779b25ae0c2f0082",
        "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJ0eCI6IitHcUNBUTJFTzVyS0FJTHpENVFBNHZReW1mVVVWNU5UTTY3MnlWYXlOUHBIZ1lDNFJLa0ZuTHNBQUFBQUFBQUFBQUFBQUFBUER3OFBEdzhQRHc4UER3OFBEdzhQRHc4UER3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHMm0wdXMwRjhWS29DQSIsImluZm8iOnt9LCJpYXQiOjE1NTA0NzgyNzcsImV4cCI6MTU1MDQ3ODg3NywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.Qv8mA7mqsQ5RBkMcXvJ2qZOed14Vx-DJPQc3k-U1beb1mFx3Ok-MlZoYivOC-Z1IP0YmS3NJTfrJpOUxOUuVaw"
      }
    }
    ```

    > `data` is the object from signing `transaction` with current user's private key. In another word, `signedTransaction` is the hex string

    > `submitToken` is `submitToken` from Encode Ethereum Transaction. 

 - Using cURL

    ```sh
        curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDQ2MTM4OCwiZXhwIjoxNTUwNTQ3Nzg4LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.ssflLmh8waTKjtOJ9R4kNwmPUHQozKC7xzsiiZRPW4cfLiP88QnK2R5qN2M32wr4h7mPHSEFf7Ov3koDC866hQ' \
         --header 'content-type: application/json' \
         --cookie locale=en \
         --data '{"query":"mutation submitSignedTransaction($input: SubmitTransactionInput!) {\n  submitTransaction(input: $input) {\n    transactionHash\n  }\n}\n","variables":{"input":{"data":"0xf8aa82010d843b9aca0082f30f9400e2f43299f51457935333aef6c956b234fa478180b844a9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f1578a0b8a0eee843ab7a58f0d36ba94829c4ab0422b7f26f5e114ff1f662892fdc07e1a028dd5b5b6b0c1e39fa094a971d2614661ff18942b7db72be779b25ae0c2f0082","submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJ0eCI6IitHcUNBUTJFTzVyS0FJTHpENVFBNHZReW1mVVVWNU5UTTY3MnlWYXlOUHBIZ1lDNFJLa0ZuTHNBQUFBQUFBQUFBQUFBQUFBUER3OFBEdzhQRHc4UER3OFBEdzhQRHc4UER3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHMm0wdXMwRjhWS29DQSIsImluZm8iOnt9LCJpYXQiOjE1NTA0NzgyNzcsImV4cCI6MTU1MDQ3ODg3NywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.Qv8mA7mqsQ5RBkMcXvJ2qZOed14Vx-DJPQc3k-U1beb1mFx3Ok-MlZoYivOC-Z1IP0YmS3NJTfrJpOUxOUuVaw"}},"operationName":"submitSignedTransaction"}'
    ```

 - Response

    ```json
    {
      "data": {
        "submitTransaction": {
          "transactionHash": "0xde943e19f69e43b9a6fd889c94a1dde9b017484c8b8b489e14dbb37a48ee1961"
        }
      }
    }
    ```

    > `transactionHash` can be used to check whether transaction is confirmed in the next steps.

## Confirm the Ethereum Transaction

 - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

    ```graphql
    query getTransactionReceipt($txHash: String!) {
      getTransactionReceipt(txHash: $txHash)
    }
    ```

    Variables:

    ```json
    {
      "txHash": "0xde943e19f69e43b9a6fd889c94a1dde9b017484c8b8b489e14dbb37a48ee1961"
    }
    ```

    > `txHash` is the transaction hash.
    
    > Notice that transaction hash is unique on chain, but it may repeat when representing different transactions on different chain. 

 - Using cURL

    ```sh
    curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDQ2MTM4OCwiZXhwIjoxNTUwNTQ3Nzg4LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.ssflLmh8waTKjtOJ9R4kNwmPUHQozKC7xzsiiZRPW4cfLiP88QnK2R5qN2M32wr4h7mPHSEFf7Ov3koDC866hQ' \
         --header 'content-type: application/json' \
         --cookie locale=en \
         --data '{"query":"query getTransactionReceipt($txHash: String!) {\n  getTransactionReceipt(txHash: $txHash)\n}\n","variables":{"txHash":"0xde943e19f69e43b9a6fd889c94a1dde9b017484c8b8b489e14dbb37a48ee1961"},"operationName":"getTransactionReceipt"}'
    ```

 - Response

    ```json
    {
      "data": {
        "getTransactionReceipt": {
          "tx": {
            "blockHash": "0x820ea831f44d2cacdb19c10ead2a3eb691c43db8a3466b1cdb11f0f384bbee77",
            "blockNumber": 10386928,
            "chainId": "0x2a",
            "condition": null,
            "creates": null,
            "from": "0x3e7aF8b8C19C404670C1470273bca449148Df4Ed",
            "gas": 62223,
            "gasPrice": "1000000000",
            "hash": "0xde943e19f69e43b9a6fd889c94a1dde9b017484c8b8b489e14dbb37a48ee1961",
            "input": "0xa9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f15",
            "nonce": 269,
            "publicKey": "0x430bf21a98b0dd75d08fd4d8780cdf818f885b9c8e540fd3148123114ed987a6e226dbca1de163404cbb01f2040c4737410a6b7eacfcd8b9f3ee7a02b0eb24f0",
            "r": "0xb8a0eee843ab7a58f0d36ba94829c4ab0422b7f26f5e114ff1f662892fdc07e1",
            "raw": "0xf8aa82010d843b9aca0082f30f9400e2f43299f51457935333aef6c956b234fa478180b844a9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f1578a0b8a0eee843ab7a58f0d36ba94829c4ab0422b7f26f5e114ff1f662892fdc07e1a028dd5b5b6b0c1e39fa094a971d2614661ff18942b7db72be779b25ae0c2f0082",
            "s": "0x28dd5b5b6b0c1e39fa094a971d2614661ff18942b7db72be779b25ae0c2f0082",
            "standardV": "0x1",
            "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
            "transactionIndex": 24,
            "v": "0x78",
            "value": "0"
          },
          "pending": false,
          "confirmations": {
            "need": 8,
            "remain": 0
          }
        }
      }
    }
    ```

    > When `remain` in `confirmations`  becomes `0`, the transaction is confirmed.

    > Notice that a confirmed transaction may not succeed. As on Blockchain, failed transaction is also a consensus. Please use [Infura](https://infura.io) with [ETH-JSON-RPC](https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionreceipt) to fetch `status` (success/failure of transaction).

## Next step

Congratulation, you have completed `Quick start`.  

For more detailed info, please refer to [Guide](../Guide/01-A_short_introduction_to_Ethereum.zh.md), or please redirect to [FST Network Github](https://github.com/fstnetwork).
