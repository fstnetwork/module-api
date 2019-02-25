# Fill up FST Service Gas tank

> In this chapter, you will understand how to top up FST Service Gas via FsTK API.

> FST Service Gas is the service charge of FsTK modules. Each user has one Gas tank for top-up and service fee will be deducted directly from it.

## Table of Contents

 1. Prerequisite
 2. Encode the Transaction (filling up FST Service Gas tank)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Confirm the Ethereum Transaction
 7. Confirm the FST Service Gas amount in the tank

## Prerequisite

 1. Please sign up an account on `https://test.fstk.io` or `https://engine.fstk.io`.
    >  Notice account data are NOT shared across both platform 

    - `test.fstk.io` is Tokeneden built on [**Kovan Testnet**](https://kovan.etherscan.io) for agile software development, testing & demo.  
    - `engine.fstk.io` is official Tokeneden built on Ethereum [**Mainnet**](https://etherscan.io).

 2. Please take a look at your asset balances of `ETH`、`FST`、`FIL` and `FST Service Gas`.
    > Please remember that assets on `test.fstk.io` belongs to **Kovan Testnet**; assets on `engine.fstk.io` belongs to **Mainnet**

    - `ETH` is `Ether`, a small amount will be given to new accounts on `test.fstk.io`. 
    - `FST` is `Funder Smart Token`, a fundamental Utility Token within [FST Network](https://fst.network) and will be given to new accounts on `test.fstk.io`.
    - `FIL` is `FundersToken Initialisation License` as Token Issuance License, 1 FIL will be given to new accounts on `test.fstk.io`.
    - `FST Service Gas` is the FsTK module usage fee for `Token Issuer`, balance is shown at User Profile on the top right corner.

 3. Please prepare your API testing tools
    - [Insomnia](https://insomnia.rest) (recommended)
    - [Postman](https://www.getpostman.com)

 4. Understand how to retrieve Access Web Token (JWT)
    > Please refer to Quick start [Chapter 1](../../Quick_Start/EN/01-Connect_to_FsTK_Engine_API.en.md)

 5. Complete Quick start

 6. Confirm sufficient Ether (ETH) for ETH gas fee.

## Encode the Transaction (filling up FST Service Gas tank)

 > In any of following API calls, please remember to assign the access token to `authorization` in http request header.

 - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

    ```graphql
    mutation fillGasTank($input: FillGasTankInput!) {
      fillGasTank(input: $input) {
        transaction
        hash
        metadata
        submitToken
        pendingTransactions
      }
    }
    ```

    Variables:

    ```json
    {
      "input": {
        "por": "DISABLE",
        "amount": "100000000000000000000"
      }
    }
    ```

    > `por` is the switch of PoR mode. PoR mode requires users for further installation . Please contact us for more info: tech-support@fstk.io.

    > `amount` is the top-up amount of FST. FST : FST Service Gas is 1:1.
    > Please remember the unit is [`wei`](https://etherconverter.online).
    > e.g. In order to transfer `1` token, `amount` should be `"1000000000000000000"`.
    > In another words, `amount` of `"100000000000000000000"` means topping up `100` FST.
 - Using cURL

    ```sh
    curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDU1NDYwNiwiZXhwIjoxNTUwNjQxMDA2LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.VRkWKX5zsru9R6xqw-sI8NCBTYDTNs0VXMPx7oact-wm4Znf37O5ywi7CCL41KzOzCnjic31Q5jwcGMNCBbx1A' \
         --header 'content-type: application/json' \
         --cookie locale=en \
         --data '{"query":"mutation fillGasTank($input: FillGasTankInput!) {\n  fillGasTank(input: $input) {\n    transaction\n    hash\n    metadata\n    submitToken\n    pendingTransactions\n  }\n}\n","variables":{"input":{"por":"DISABLE","amount":"100000000000000000000"}},"operationName":"fillGasTank"}'
    ```

 - Response

    ```json
    {
      "data": {
        "fillGasTank": {
          "transaction": {
            "nonce": "0x10e",
            "gasPrice": "0x3b9aca00",
            "gas": "0x17743",
            "to": "0x3830f7Af866FAe79E4f6B277Be17593Bf96beE3b",
            "value": "0x0",
            "data": "0x4000aea000000000000000000000000056533b3052dd2bc92d2d11372427b9a7f3256eaa0000000000000000000000000000000000000000000000056bc75e2d631000000000000000000000000000000000000000000000000000000000000000000060000000000000000000000000000000000000000000000000000000000000004447d5f0be0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "chainId": 42
          },
          "hash": "0x8dfb883dc3b40f319f3960448ffe96a0aad69ff58a7800b191e04d58efdea12c",
          "metadata": {
            "fee": {
              "type": "ETH",
              "amount": "96067000000000"
            }
          },
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImZpbGxHYXNUYW5rIiwidHgiOiIrUUVMZ2dFT2hEdWF5Z0NEQVhkRGxEZ3c5NitHYjY1NTVQYXlkNzRYV1R2NWErNDdnTGprUUFDdW9BQUFBQUFBQUFBQUFBQUFBRlpUT3pCUzNTdkpMUzBSTnlRbnVhZnpKVzZxQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUZhOGRlTFdNUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBWUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQkVSOVh3dmdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUtvQ0EiLCJpbmZvIjp7fSwiaWF0IjoxNTUwNTU0NzQ1LCJleHAiOjE1NTA1NTUzNDUsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.Sb3SbSUK_1GtQl3FEmt2S-2_oAi9lcTmYY9g0GyK_z9BD2yKJLxArTlO7--Lvp-qdN6LroSoUzaEaVWcmzDv7g",
          "pendingTransactions": "0"
        }
      }
    }
    ```
  
    > In response's `transaction`, object will be used to sign payload, `submitToken` is also required for broadcasting signed transaction.

    > Please remember that  response will vary after each call, please use the latest response for next steps.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "fillGasTank": null
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

## Confirm the FST Service Gas amount in the tank

 > Please refer to `gasTankBalance` in `get me`. (More details in Quick start [Chapter 2](../../Quick_Start/EN/02-Get_account_information.en.md))
