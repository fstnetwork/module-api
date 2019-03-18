# Create a Campaign

> 在此章節中，您將學習到如何通過 FsTK API 建立 Smart Voucher Campaign 及讓消費者快速購入 Smart Voucher

> Campaign 為販售 Smart Token/Voucher 的販賣機，您可以設定欲販售之 Smart Token/Voucher、販售期間、販售量、以及本次販售期間的價格，讓消費者透過此管道購買 Smart Token/Voucher。

## Table of Contents

 1. Prerequisite
 2. Encode the Transaction (creating smart voucher campaign)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Confirm the Ethereum Transaction
 7. Confirm the Campaign
 8. Transfer SMart Token to the Campaign to obtain Smart Voucher
    1. Encode the Transaction (transferring smart token to voucher campaign)
 9.  Check the progress of the Campaign
 10. Finalize the Campaign
     1.  Prerequisite
     2.  Encode the Transaction (finalizing campaign)

## Prerequisite

 1. 請先於 `https://test.fstk.io` 或 `https://engine.fstk.io` 註冊帳號，並確認開通成功
    > 請注意此兩個平台之帳戶資料沒有互通

    - `test.fstk.io` 是在 [**Kovan Testnet**](https://kovan.etherscan.io) 建立的 Tokeneden 平台，是作為較快速的開發與測試與 Demo 所用  
    - `engine.fstk.io` 則在 [**Mainnet**](https://etherscan.io)，是於以太坊主公開鏈建立的 Tokeneden 平台

 2. 請檢查您的帳號中的 `ETH`、`FST`、`FIL`，及 `FST Service Gas` 餘額
    > 請記得，於 `test.fstk.io` 之資產皆在 **Kovan Testnet**，而於 `engine.fstk.io` 之資產皆在 **Mainnet**

    - `ETH` 為 `Ether`，於 `test.fstk.io` 會少量發放至新帳戶  
    - `FST` 為 `Funder Smart Token`，為 [FST Network](https://fst.network) 中的基礎 Utility Token，於 `test.fstk.io` 會發放至新帳戶  
    - `FIL` 為 `FundersToken Initialisation License`，為可發行 Token 之授權證明，於 `test.fstk.io` 會發放 `1 FIL` 至新帳戶  
    - `FST Service Gas` 為當身為 `Token 發行者 (Issuer)` ，使用 FsTK 模組時所需要的燃料，在網頁右上角個人資訊裡面可以看到餘額

 3. 請準備好您的 API 測試工具
    - [Insomnia](https://insomnia.rest) (推薦)
    - [Postman](https://www.getpostman.com)

 4. 已知如何取得 Access Web Token (JWT)
    > 詳情請參考 Quick start [第一篇章](../Quick_Start/01-Connect_to_FsTK_Engine_API.zh.md)

 5. 已完成 Quick start 之學習

 6. 確認帳戶有足夠的 Ether 來付出燃料費用 (eth gas fee)

 7. 確認帳戶有足夠的 FST Service Gas 來付出服務手續費 (建立 campaign 需 500 FST Service Gas)，建立後若取消則不退回 FST Service Gas

 8. 已經成為 Issuer (Token 發行者)，請至 `get me` 中的 `token` 確認

## Encode the Transaction (create Smart Token campaign)

 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

 > 以下繼續以 FST Sport Shop 作為範例
   
    聖誕節即將來臨，FST Sport Shop 為了讓常客能得到更多的優惠，將推出一款聖誕節特賣福袋 Smart Voucher (FSST_19FXSV)，消費者可使用所持有的 FST Sport Shop Token 購買此聖誕活動所推出的 FSST_19FXSV，此 FSST_19FXSV 將限量發行 1000 張，售價為此 FSST_19FXSV 的定價。而福袋中物品價值高於原本的定價，藉此商家可以出清未賣出的產品，亦能讓消費者以便宜的價格購得商品。

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

   - operations detail
    ```graphql
    mutation CreateCampaign($input: CreateCampaignInput!) {
      createCampaign(input: $input) {
        transaction
        submitToken
        hash
      }
    }
    ```

    Variables:

    ```json
    {
      "input": {
        "id":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
        "name":"2019 Christmas Voucher Sale.",
        "description":"This is the 2019 Christmas Voucher Sale.",
        "stages":[
          {
            "name":"2019 Christmas Voucher Sale.",
            "startTime":"1569888000000",
            "endTime":"1575072000000",
            "priceMultiplier":{
              "numerator":"1",
              "denominator":"1"
            },
            "cap":"1000",
            "isPrivate":false,
            "description":"This is the 2019 Christmas Voucher Sale."
           }
        ],
        "por":"DISABLE"
      }
    }
    ```

     - `id` 為所欲販賣之 Smart Token/Voucher 於 FsTK 系統所記錄之 ID。可於 `get me` 中的 `token` 取得

     - `name` 此 Campaign 的名稱，至少 1 字元，至多 20 字元
  
     - `description` 此 Campaign 的描述或說明

     - `stages` 此 Camapaign 的階段，目前一個 Campaign 僅有一個 stage
  
       - `name` 此 Stage 的名稱，至少 1 字元，至多 20 字元

       - `startTime` 此 Stage 的開始時間，需為 Unix Timestamp in Milliseconds 格式
    
       - `endTime` 此 Stage 的結束時間，需為 Unix Timestamp in Milliseconds 格式

       - `priceMultiplier` 此 Stage 中欲販售 Smart Token/Voucher 的價格之乘數，也就是說假如此 Ｃampaign 想要打九折，則乘數為 9/10。假如要維持原價則為 1/1
  
         - `numerator` 乘數之分子

         - `denominator` 乘數之分母
  
       - `cap` 此 Stage 中，欲販售的 Smart Token/Voucher 總數量，格式為 Decimaled Number。如欲販售 Smart Token 則要乘上 10^18，反之 Smart Voucher 維持原本的數量 (因 Voucher 不可分割，故 decimal = 0，10^0 為 1)

       - `isPrivate` 此 Stage 是否為私密販售，需要特殊簽章者才得以購買
  
       -  `description` 此 Stage 的描述或說明


 - Using cURL

    ```sh
    curl --request POST \
          --url https://dev.fstk.io/api \
          --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyNTUwMzgxLCJleHAiOjE1NTI2MzY3ODEsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.VRgydp39uLU1jNyF7bPj9yrTLJxAsoZf3xdWh7s45HCLz8HCjpWCHxJWzQg3hZbuaNptOPV2waRaHYaiEMosEQ' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"mutation CreateCampaign($input: CreateCampaignInput!) {\n  createCampaign(input: $input) {\n    transaction\n    submitToken\n    hash\n  }\n}\n","variables":{"input":{"id":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","name":"2019 Christmas Voucher Sale.","description":"This is the 2019 Christmas Voucher Sale.","stages":[{"name":"2019 Christmas Voucher Sale.","startTime":"1569888000000","endTime":"1575072000000","priceMultiplier":{"numerator":"1","denominator":"1"},"cap":"1000","isPrivate":false,"description":"This is the 2019 Christmas Voucher Sale."}],"por":"DISABLE"}},"operationName":"CreateCampaign"}'
    ```

 - Response

    ```json
    {
      "data": {
        "createCampaign": {
          "transaction": {
            "nonce": "0x5",
            "gasPrice": "0x3b9aca00",
            "gas": "0x165b83",
            "to": "0x155dea084AD150D80E56B7746dD5503fCd5dfA77",
            "value": "0x0",
            "data": "0x4000aea00000000000000000000000003a6ca60e8f13492e64e6ff1a536a77f9786c049d00000000000000000000000000000000000000000000000000000000000003e8000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000001246f40b1c90000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000082e82d99a274625c3ec061393b6c43a932d0a274000000000000000000000000000000000000000000000000000000005d929700000000000000000000000000000000000000000000000000000000005de1b10000000000000000000000000000000000000000000000000000000000000003e800000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "chainId": 42
          },
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiY3JlYXRlVG9rZW5DYW1wYWlnbiIsInR4IjoiK1FIcUJZUTdtc29BZ3haYmc1UVZYZW9JU3RGUTJBNVd0M1J0MVZBL3pWMzZkNEM1QWNSQUFLNmdBQUFBQUFBQUFBQUFBQUFBT215bURvOFRTUzVrNXY4YVUycDMrWGhzQkowQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUQ2QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBU1J2UUxISkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBSUxvTFptaWRHSmNQc0JoT1R0c1E2a3kwS0owQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUYyU2x3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFYZUd4QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUG9BQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFFQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFDcUFnQT09IiwiaW5mbyI6eyJ0b2tlbklkIjoiw4vDnGzCilx1MDAxYS5cdTAwMTHDqcK6O8OHa0PCoCDDpyIsIm5hbWUiOnsiZW4iOiIyMDE5IENocmlzdG1hcyBWb3VjaGVyIFNhbGUuIn0sImRlc2NyaXB0aW9uIjp7ImVuIjoiVGhpcyBpcyB0aGUgMjAxOSBDaHJpc3RtYXMgVm91Y2hlciBTYWxlLiJ9LCJzdGFnZXMiOlt7Im5hbWUiOnsiZW4iOiIyMDE5IENocmlzdG1hcyBWb3VjaGVyIFNhbGUuIn0sImRlc2NyaXB0aW9uIjp7ImVuIjoiVGhpcyBpcyB0aGUgMjAxOSBDaHJpc3RtYXMgVm91Y2hlciBTYWxlLiJ9LCJpc1ByaXZhdGUiOmZhbHNlLCJzdGFydFRpbWUiOiIxNTY5ODg4MDAwMDAwIiwiZW5kVGltZSI6IjE1NzUwNzIwMDAwMDAiLCJjYXAiOiIxMDAwIiwicHJpY2VNdWx0aXBsaWVyIjp7Im51bWVyYXRvciI6IjEiLCJkZW5vbWluYXRvciI6IjEifX1dfSwiaWF0IjoxNTUyNTUwNDQyLCJleHAiOjE1NTI1NTEwNDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.q4trW2Xi1lWus2Liip-Uqe9oWj5yLc-bKFOFypU0gc5Bz1Hzy7Etk7IA_AmaPxdMBAvdoqz9FQSFL5aIkavXtg",
          "hash": "0x61ae5ed106e85eead3a328cb6e287310e5e93ed939d1fade70c37e126dad9576"
        }
      }
    }
    ```

    > 目前此 API 尚未支援回傳所需 FST Service Gas 量，create a campaign 需要花費 500 FST Service Gas
  
    > 此 response 中的 `transaction` 物件將為接下來拿來簽署的 payload，`submitToken` 也請保留，等一下送出簽署後的結果時需用到

    > 也請記得，此 response 會隨著不同時間呼叫而有所不同，請使用當前最新的呼叫作為接下來步驟所需要用到的資料

    > 而假如收到類似  
    ```json   
    { 
      "data": {
        "createCampaign": null
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
      "nonce": "0x10f",
      "gasPrice": "0x3b9aca00",
      "gas": "0x480eb3",
      "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
      "value": "0x0",
      "data": "0x4000aea0000000000000000000000000d6aebbbd0af65107a8d3dfe362f322bf4c8e1bcf0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002a4459ee93a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018000000000000000000000000000000000000000000000000000000000000001c000000000000000000000000000000000000000000000000000000000000004d20000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000005e0b707f0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c25821bf51fafd2d16801a2837d87af840446129000000000000000000000000000000000000000000000006aaf7c8516d0c00000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000065445535431310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b535449435f54455354313100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b384865537174365a656f634d50726363435654366d4357447152595535615137785453377a787645574841354746764e4752656f7564336263444675475755354c39716932533672417061526d6650314a68416333736633000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
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
    mutation submitSignedTransaction(input: SubmitTransactionInput!) {
      submitTransaction(input: input) {
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
         --data '{"query":"mutation submitSignedTransaction(input: SubmitTransactionInput!) {  submitTransaction(input: input) {    transactionHash  }}","variables":{"input":{"data":"0xf8aa82010d843b9aca0082f30f9400e2f43299f51457935333aef6c956b234fa478180b844a9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f1578a0b8a0eee843ab7a58f0d36ba94829c4ab0422b7f26f5e114ff1f662892fdc07e1a028dd5b5b6b0c1e39fa094a971d2614661ff18942b7db72be779b25ae0c2f0082","submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJ0eCI6IitHcUNBUTJFTzVyS0FJTHpENVFBNHZReW1mVVVWNU5UTTY3MnlWYXlOUHBIZ1lDNFJLa0ZuTHNBQUFBQUFBQUFBQUFBQUFBUER3OFBEdzhQRHc4UER3OFBEdzhQRHc4UER3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHMm0wdXMwRjhWS29DQSIsImluZm8iOnt9LCJpYXQiOjE1NTA0NzgyNzcsImV4cCI6MTU1MDQ3ODg3NywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.Qv8mA7mqsQ5RBkMcXvJ2qZOed14Vx-DJPQc3k-U1beb1mFx3Ok-MlZoYivOC-Z1IP0YmS3NJTfrJpOUxOUuVaw"}},"operationName":"submitSignedTransaction"}'
    ```

 - Response

    ```json
    {
      "data": {
        "submitTransaction": {
          "transactionHash": "0x963339460f699b5d02dfd841c21992353cd441917964506ebeae06efe85f400b"
        }
      }
    }
    ```

    > 此 `transactionHash` 為下一步拿來確認有沒有交易驗證通過之交易代號

## Confirm the Ethereum Transaction

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

    ```graphql
    query getTransactionReceipt(txHash: String!) {
      getTransactionReceipt(txHash: txHash)
    }
    ```

    Variables:

    ```json
    {
      "txHash": "0x963339460f699b5d02dfd841c21992353cd441917964506ebeae06efe85f400b"
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
         --data '{"query":"query getTransactionReceipt(txHash: String!) {  getTransactionReceipt(txHash: txHash)}","variables":{"txHash":"0x963339460f699b5d02dfd841c21992353cd441917964506ebeae06efe85f400b"},"operationName":"getTransactionReceipt"}'
    ```

 - Response

    ```json
    {
      "data": {
        "getTransactionReceipt": {
          "tx": {
            "blockHash": "0x3b19cea19d6430d19bfbafac48e9f19cfe6eeeb702edd043bfb7949e20b575a1",
            "blockNumber": 10393839,
            "chainId": "0x2a",
            "condition": null,
            "creates": null,
            "from": "0x3e7aF8b8C19C404670C1470273bca449148Df4Ed",
            "gas": 4722355,
            "gasPrice": "1000000000",
            "hash": "0x963339460f699b5d02dfd841c21992353cd441917964506ebeae06efe85f400b",
            "input": "0x4000aea0000000000000000000000000d6aebbbd0af65107a8d3dfe362f322bf4c8e1bcf0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002a4459ee93a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018000000000000000000000000000000000000000000000000000000000000001c000000000000000000000000000000000000000000000000000000000000004d20000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000005e0b707f0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c25821bf51fafd2d16801a2837d87af840446129000000000000000000000000000000000000000000000006aaf7c8516d0c00000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000065445535431310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b535449435f54455354313100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b384865537174365a656f634d50726363435654366d4357447152595535615137785453377a787645574841354746764e4752656f7564336263444675475755354c39716932533672417061526d6650314a68416333736633000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "nonce": 271,
            "publicKey": "0x430bf21a98b0dd75d08fd4d8780cdf818f885b9c8e540fd3148123114ed987a6e226dbca1de163404cbb01f2040c4737410a6b7eacfcd8b9f3ee7a02b0eb24f0",
            "r": "0xb2e9316828e96b0f483aa681af87662d1a945effb5bf913d85de090af411e9a7",
            "raw": "0xf903ac82010f843b9aca0083480eb39400e2f43299f51457935333aef6c956b234fa478180b903444000aea0000000000000000000000000d6aebbbd0af65107a8d3dfe362f322bf4c8e1bcf0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002a4459ee93a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018000000000000000000000000000000000000000000000000000000000000001c000000000000000000000000000000000000000000000000000000000000004d20000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000005e0b707f0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c25821bf51fafd2d16801a2837d87af840446129000000000000000000000000000000000000000000000006aaf7c8516d0c00000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000065445535431310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b535449435f54455354313100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b384865537174365a656f634d50726363435654366d4357447152595535615137785453377a787645574841354746764e4752656f7564336263444675475755354c39716932533672417061526d6650314a6841633373663300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000078a0b2e9316828e96b0f483aa681af87662d1a945effb5bf913d85de090af411e9a7a036b73354513cbf935d7620cdbc22c8d94b52b65eae966125d102b9ec3d377268",
            "s": "0x36b73354513cbf935d7620cdbc22c8d94b52b65eae966125d102b9ec3d377268",
            "standardV": "0x1",
            "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
            "transactionIndex": 8,
            "v": "0x78",
            "value": "0"
          },
          "pending": false,
          "confirmations": {
            "need": 8,
            "remain": 0
          }
        }
      },
    }
    ```

    > 請看 `confirmations` 中的 `remain` 成為 `0` 時，表示交易已經驗證完成

    > 延伸補充，驗證完成不一定等於交易成功，因為在區塊鏈上，交易失敗也是一種共識結果，故請善用 [Infura](https://infura.io) 搭配 [ETH-JSON-RPC](https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionreceipt) 來取得 `status` 是否為成功

## Confirm the Campaign

 > 請查看 `get me` 中的 `campaigns` (詳情請參考 Quick start [第二篇章](../Quick_Start/02-Get_account_information.zh.md))
)

## Transfer Smart Token to the Campaign to obtain Smart Voucher
### Encode the Transaction (transferring smart token to voucher campaign)

  > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

  > 使用者購買 Smart Voucher 時，issuer 需支付 0.15 FST Service Gas per Smart Voucher 作為手續費
  
  > 以下繼續以 FST Sport Shop 作為範例：

    FST Sport Shop 開始透過 Campaign 販售 FSST_19FXSV 後，消費者可透過傳送 Smart Token (FSST) 至該 Campaign 的 address，Campaign 會回傳消費者所購得的 FSST_19FXSV 至消費者傳送 FSST 的帳戶。

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

   - operations detail
    ```graphql
    mutation transferSmartToken($input: ERC20TransferInput!) {
      erc20Transfer(input: $input) {
        transaction
        hash
        submitToken
      }
    }
    ```

    Variables:

    ```json
    {
      "input": {
        "id": "VG9rZW46wrRGCwoaw68Rw6nCujsXbMKew7Bzwqc=",
        "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
        "value": "2000000000000000000000",
        "por": "DISABLE"
      }
    }
    ```

     - `id` 為所欲傳送的 Smart Token 之 ID。可於 `getSmartTokenBalance` 中取得

     - `to` 販售 FSST_19FXSV 的 Campaign 的 address

     - `value` 欲傳送的數量，若 FSST_19FXSV 價格為 `2000` FSST，並且想要購買 1 個 FSST_19FXSV，則 FSST 之 `value = 2000000000000000000000`


  - Using cURL
    
    ```sh
    curl --request POST \
          --url https://dev.fstk.io/api \
          --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyNTUwMzgxLCJleHAiOjE1NTI2MzY3ODEsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.VRgydp39uLU1jNyF7bPj9yrTLJxAsoZf3xdWh7s45HCLz8HCjpWCHxJWzQg3hZbuaNptOPV2waRaHYaiEMosEQ' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"mutation transferSmartToken($input: ERC20TransferInput!) {\n  erc20Transfer(input: $input) {\n    transaction\n    hash\n    submitToken\n  }\n}\n","variables":{"input":{"id":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","to":"0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d","value":"2000000000000000000000","por":"DISABLE"}},"operationName":"transferSmartToken"}'
    ```

  - Response

    ```json
    {
      "data": {
        "erc20Transfer": {
          "transaction": {
            "nonce": "0x5",
            "gasPrice": "0x3b9aca00",
            "gas": "0xf2c2",
            "to": "0x155dea084AD150D80E56B7746dD5503fCd5dfA77",
            "value": "0x0",
            "data": "0xa9059cbb0000000000000000000000004cf40da49f9d82819161c5db86fcb496defeb35d00000000000000000000000000000000000000000000006c6b935b8bbd400000",
            "chainId": 42
          },
          "hash": "0x821d1ddcaa61b164c74d6b21b6a07717637c890611cac7ebc577ec8952ddd40a",
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiZXJjMjBUcmFuc2ZlciIsInR4IjoiK0dnRmhEdWF5Z0NDOHNLVUZWM3FDRXJSVU5nT1ZyZDBiZFZRUDgxZCtuZUF1RVNwQlp5N0FBQUFBQUFBQUFBQUFBQUFUUFFOcEorZGdvR1JZY1hiaHZ5MGx0NytzMTBBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHeHJrMXVMdlVBQUFDcUFnQT09IiwiaW5mbyI6e30sImlhdCI6MTU1MjYyMDgzMSwiZXhwIjoxNTUyNjIxNDMxLCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.GcyvdesjTAT3b1kw-tFYqXrFfr9SIIRlx3iTGm-PVpmWDkgXlZdyU90A956WhcmPoEIkscKq1_ykoNO0OPo4mw"
        }
      }
    }
    ```

## Check the progress of the Campaign

> 您可以從 `getAllCampaignInfo` API 中，取得所有 Campaign 的 `cap` 及 `sold`，以了解您的 Campaign 銷售進度 (請參考 [API_Reference/Campaign/getAllCampaignInfo](../API_Reference/getAllCampaignInfo.md))

## Finalize the Campaign
### Prerequisite

> 創建 Smart Voucher Campaign 後，可分為三個階段：開始販售前、販售期間、販售結束後，在此三個階段進行結束 Campaign 動作分別有不同的結果

1. 開始販售前 (now < `startTime`)：issuer 取消販售，並取回未賣出的 Smart Voucher。
2. 販售期間 (`startTime` < now < `endTime`)：若未售完，issuer 無法取消及取回剩餘的 Smart Voucher 及所賺得的 Smart Token；若在販售期間售完，issuer 則可提前取回所賺得的 Smart Token
3. 販售結束後 (`endTime` < now)：issuer 可取回未賣出的 Smart Voucher 及所賺得的 Smart Token

<!-- different result when finalize in different state  -->
### Encode the Transaction (finalizing campaign)
  
  > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

  > 以下繼續以 FST Sport Shop 作為範例：

    FST Sport Shop 所販售的 FSST_19FXSV 於販售期間熱銷一空，FST Sport Shop 欲將所賺得的 FSST 從 Campaign 販賣機中領出，可使用 `finalizeSmartVoucherCampaign` API 結束本次販售。
   
  - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

   - operations detail
    ```graphql
    mutation CloseCampaign($input: CloseCampaignInput!) {
      closeCampaign(input: $input) {
        transaction
        hash
        submitToken
      }
    }
    ```

    Variables:

    ```json
    {
      "input": {
        "id": "VG9rZW5DYW1wYWlnbjp/wqQLViLDhxHDqcK6O2/CjVgZw4ZC",
        "por": "DISABLE"
      }
    }
    ```

     - `id` 為欲關閉的 Campaign ID。可於 `getAllCampaignInfo` 中取得

  - Using cURL
    
    ```sh
    curl --request POST \
          --url https://dev.fstk.io/api \
          --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyNjMxNDA1LCJleHAiOjE1NTI3MTc4MDUsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.O7_DG_z-sMdWjkzsxXvJKPjY9N5QQccvp9sG24E8nJkxCIQNTEMyJ1R7sZvKltPhz3L-UEyHtHzXft7920pxpw' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"mutation CloseCampaign($input: CloseCampaignInput!) {\n  closeCampaign(input: $input) {\n    transaction\n    hash\n    submitToken\n  }\n}\n","variables":{"input":{"id":"VG9rZW5DYW1wYWlnbjp/wqQLViLDhxHDqcK6O2/CjVgZw4ZC","por":"DISABLE"}},"operationName":"CloseCampaign"}'
    ```

  - Response

    ```json
    {
      "data": {
        "erc20Transfer": {
          "transaction": {
            "nonce": "0x5",
            "gasPrice": "0x3b9aca00",
            "gas": "0xf2c2",
            "to": "0x155dea084AD150D80E56B7746dD5503fCd5dfA77",
            "value": "0x0",
            "data": "0xa9059cbb0000000000000000000000004cf40da49f9d82819161c5db86fcb496defeb35d00000000000000000000000000000000000000000000006c6b935b8bbd400000",
            "chainId": 42
          },
          "hash": "0x821d1ddcaa61b164c74d6b21b6a07717637c890611cac7ebc577ec8952ddd40a",
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiZXJjMjBUcmFuc2ZlciIsInR4IjoiK0dnRmhEdWF5Z0NDOHNLVUZWM3FDRXJSVU5nT1ZyZDBiZFZRUDgxZCtuZUF1RVNwQlp5N0FBQUFBQUFBQUFBQUFBQUFUUFFOcEorZGdvR1JZY1hiaHZ5MGx0NytzMTBBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHeHJrMXVMdlVBQUFDcUFnQT09IiwiaW5mbyI6e30sImlhdCI6MTU1MjYyMDgzMSwiZXhwIjoxNTUyNjIxNDMxLCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.GcyvdesjTAT3b1kw-tFYqXrFfr9SIIRlx3iTGm-PVpmWDkgXlZdyU90A956WhcmPoEIkscKq1_ykoNO0OPo4mw"
        }
      }
    }
    ```

    > 關閉 Campaign 無需花費 FST Service Gas

    > 此 API 若執行成功，請接續上方三個步驟： `3. Decrypt the Ethereum Key JSON`, `4. Sign the Ethereum Transaction`, `5. Broadcast the Ethereum Transaction`

    > 而假如收到類似  
    ```json   
    { 
      "data": {
        "createAirdropLocate": null
      },
      "errors": [....]
    }
    ```  
    > 則表示此交易將會失敗，我們建議直接省略接下來的步驟，並請檢查交易相關所需資源是否足夠或有無問題