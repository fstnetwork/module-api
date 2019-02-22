# Send a Transaction

> 在此章節中，您將學習到如何走過一次完整的 Transaction 相關 API 之基本流程

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

 - Using [GraphQL](https://graphql.org/learn/) (Recommended Insomnia for testing)

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

    > `id` is the `id` of transferring token. Token id of current user can be reviewd in `get me`. Please notice in `tokenBalances` section,

    > `to` is the Ethereum address the token will be transferred to.

    > `value` is the transferring amount. 
    > Please remember the unit here is [`wei`](https://etherconverter.online), e.g. ，也就是說假如想要傳送 `1` token，則 `value` 就要為 `"1000000000000000000"`  
    > 如 `"123456789123456789"` 為 `0.123456789123456789` Token 的意思
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

    > 請注意 `erc20Transfer` 之所以存在在此 response 中是因為我們使用了 `mutation erc20Transfer`，不同的 encode 請求會有不同的字串，但結構是一樣的
  
    > 此 response 中的 `transaction` 物件將為接下來拿來簽署的 payload，`submitToken` 也請保留，等一下將簽署後的結果送出時將需要

    > 也請記得，此 response 會隨著不同時間呼叫而有所不同，請使用當前最新的呼叫作為接下來步驟所需要用到的資料

    > 而假如收到類似  
    ```json   
    { 
      "data": {
        "erc20Transfer": null
      },
      "errors": [....]
    }
    ```  
    > 則表示此交易將會失敗，我們建議直接省略接下來的步驟，並請檢查交易相關所需資源是否足夠或有無問題

## Decrypt the Ethereum Key JSON

 > 請注意 `password` 與 `passphrase` 在 FsTK 的差別，`password` 代表登入平台用的帳戶密碼，而 `passphrase` 為用來解密 Ethereum key json 用的，也就是拿來簽署交易的時候用的

 > 因多個不同的函式庫的用詞皆不同，有些會將 `passphrase` 寫成 `password`，請避免搞混

 > 首先，從 `get me` 中取得 `ethereumKey` 欄位的資料，如:

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

 > 此為當前使用者之 Ethereum key json，其中包含被加密過後的私鑰 (以 passphrase 加密)，也就是說還算是可以安全地直接儲存，但也儘量不要公開
 
 > 擁有私鑰等於擁有此 Ethereum Account 的所有控制權，請嚴格保密地儲存 Ethereum key json 與 passphrase

 > **也請注意，如果遺失了此 Ethereum key json 的 passphrase，則無任何恢復出私鑰的手段，因為 FsTK 不儲存使用者的 Ethereum key json passphrase**

 - Using JavaScript (Node.js)

    > 安裝 [eth-key-lib](https://github.com/fstnetwork/eth-key-lib-js)

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

    > 此語法為 ES6 之 module import，假如您的 node.js 不支援此語法，請參考 [Webpack](https://webpack.js.org/configuration/target) (`target = "node"`) 及以下範例

    > 最小可用之 `webpack.config.js`

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

    > 最小可用之 `package.json`

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

    > 建置命令 (請將程式進入點放置在 `index.js`)

    ```sh
    npm i && npm start
    # or
    # yarn && yarn start
    ```

    > 如您在 windows 平台上開發，請參照 [node-gyp on windows](https://github.com/nodejs/node-gyp#on-windows)

 - Using Java

   > 請參考 [Web3j](https://web3j.io)  
   > 請注意 `WalletUtils` 中的 `loadCredentials` 方法，必須使用此多載  

   ```Java
   public static Credentials loadCredentials(String password, File source)
   ```
   > 也就是說，因為 web3j 只提供從 `File` 載入，請注意檔案系統的空間，或者也可以使用 in-memory-fs in Java

   > 也請參考 [Web3j 的簡易範例](https://docs.web3j.io/transactions.html#creating-and-working-with-wallet-files)

 - Using C#

   > 請參考 [Nethereum](https://nethereum.com)  
   > 也請參考 [Nethereum 的簡易範例](https://nethereum.readthedocs.io/en/latest/accounts/#working-with-an-account)

   ```csharp
   Nethereum.Web3.Accounts.Account.LoadFromKeyStore(keyStoreEncryptedJson, passphrase)
   ```

   > 從 `Account` 取得私鑰請使用 `Account.PrivateKey`

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

   > 請參考 [Web3j](https://web3j.io)  
   > 請注意 `TransactionEncoder` 中的 `signMessage` 方法，必須使用此多載，因為要簽署到 `chainId`

   ```java
   public static byte[] signMessage(RawTransaction rawTransaction, byte chainId, Credentials credentials)
   ```

   > 也請參考 [Web3j 的簡易範例](https://docs.web3j.io/transactions.html#signing-transactions)

 - Using C#

   > 請參考 [Nethereum](https://nethereum.com)  
   > 請注意 `TransactionSigner` 中的 `SignTransaction` 方法，必須使用此多載，因為要簽署到 `chainId`

   ```csharp
   public string SignTransaction(byte[] privateKey, BigInteger chainId, string to, BigInteger amount, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, string data)
   ```

   > 也請參考 `Nethereum.Web3.Accounts.AccountSignerTransactionManager.SignTransaction`

 ## Broadcast the Ethereum Transaction
 
  - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

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

    > `data` 為上面以當前使用者的私鑰簽署 `transaction` 物件過後的產物，也就是 `signedTransaction`，為 hex string

    > `submitToken` 為 Encode Ethereum Transaction 小章節中得到的 `submitToken`

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

    > 此 `transactionHash` 為下一步拿來確認有沒有交易驗證通過之交易代號

## Confirm the Ethereum Transaction

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

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

    > `txHash` 為想要確認之交易代號
    
    > 請注意不同鏈上的交易代號可能會重疊，但代表不同的交易

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

    > 請看 `confirmations` 中的 `remain` 成為 `0` 時，表示交易已經驗證完成

    > 延伸補充，驗證完成不一定等於交易成功，因為在區塊鏈上，交易失敗也是一種共識結果，故請善用 [Infura](https://infura.io) 搭配 [ETH-JSON-RPC](https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionreceipt) 來取得 `status` 是否為成功

## Next step

Congratulation, you have comepleted `Quick start`.  

For more detailed info, please refer to [Guide](../Guide/01-A_short_introduction_to_Ethereum.zh.md), or please redirect to [FST Network Github](https://github.com/fstnetwork).
