# Publish a Smart Voucher

> 在此章節中，您將學習到如何通過 FsTK API 發布一個智能使用券 (子帳本)

> Smart Voucher (智能使用券) 為開啟母帳本 (Smart Token) 之子應用所需的子帳本，  
> 也就是說，每個服務或產品，或甚至是身分認證都各可以發布出各自的 Smart Voucher

## Table of Contents

 1. Prerequisite
 2. Encode the Transaction (publishing smart voucher)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Confirm the Ethereum Transaction
 7. Confirm the Smart Voucher

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

 7. 確認帳戶有足夠的 FST Service Gas 來付出服務手續費 (最少 600 FST Service Gas)

 8. 已經成為 Issuer (Token 發行者)，請至 `get me` 中的 `token` 確認

## Encode the Transaction (publishing smart voucher)

 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

 - Using multipart/form-data

   > operations 裡放入 GraphQL query 以及 GraphQL variables

   - operations detail

     ```json
     {
       "query": "mutation publishVoucher($input: PublishVoucherInput!) {          publishVoucher(input: $input) {            hash         transaction             submitToken          }        }",
       "variables": {
         "input": {
           "name": "FSST 2019 Xmas Surpirse Voucher",
           "symbol": "FSST_19FXSV",
           "consumable": false,
           "totalSupply": "1000",
           "price": {
             "numerator": "1000000000000000000",
             "denominator": "1"
           },
           "expiry": "1577807999000",
           "description": "The voucher for 2019 Christmas Sales!",
           "proofOfContract": null,
           "por": "DISABLE"
         }
       }
     }
     ```

     - `name` 為新發布之 Smart Voucher 的名稱，最多為 20 個字元

     - `symbol` 為新發布之 Smart Voucher 的符號，前綴必須是母帳本之符號 (symbol)，如 Smart Token 之 symbol 為 `ABC` 則 Smart Voucher 之 symbol 可取為 `ABC_TEST11`，前綴以外最多可有六個字元

     - `consumable` 為決定此 Smart Voucher 是否可被消耗，假如為 `true` 則可以使用 Consume 功能 (亦可傳送 Voucher)，`false` 則無法消耗，但仍可與 Token 一樣傳送

     - `totalSupply` 為此 Smart Voucher 總發布量，請注意這個數字是人類數，不像 Token 相關的數字系統一樣有放大 10^18 倍

     - `price` 為一個物件，表示此 Smart Voucher 一個為多少 Smart Token (也就是與母帳本之間的單價比)，也是 Smart Voucher 的初始設定價，Smart Voucher 只能以 Smart Token 購買
     
       - `numerator` 為代表一個 Smart Voucher 是多少 wei 的 Smart Token，例如一個 Smart Voucher 的價格為 123 Smart Token，則此可為 `"123000000000000000000"`

       - `denominator` 通常設為 `"1"` 即可，但事實上也可以設定成 `numerator = "1000000000000000000"` 以及 `denominator = "3"`，來表示 3 個 Smart Voucher 價值 1 Smart Token，也就是數字系統上會精確地以分數進行運算

     - `expiry` 為此 Smart Voucher 之有效日期時間，單位為 Unix time millisecond (毫秒)，例如 UTC+8 之 2019/12/31 為 `"1577807999000"`，請注意 Unix time 沒有時區，記得對應您所在的時區進行調整

     - `description` 此 Smart Voucher 之相關描述

     - `proofOfContract` 在 operations 裡面將不放置，會放置在檔案區，故為 `null`

   > proofOfContract 裡為 pdf 檔案，用於向客戶說明此 Smart Voucher 相關的合約及權益，並會存放在 IPFS 進行保護

   > multipart/form-data 之總結為

   ```
   operations: {"query":"mutation publishVoucher($input: PublishVoucherInput!) {          publishVoucher(input: $input) {            hash            transaction            submitToken          }        }","variables":{"input":{"name":"FSST Xmas Sales","symbol":"FSST_19FXSV","consumable":false,"totalSupply":"1000","price":{"numerator":"1000000000000000000","denominator":"1"},"expiry":"1577807999000","description":"The voucher for 2019 Christmas Sales!","proofOfContract":null,"por":"DISABLE"}}}
   map: {"proofOfContract":["variables.input.proofOfContract"]}
   proofOfContract: (binary)
   ```

   

 - Using cURL

    ```sh
    curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDU1ODk4MywiZXhwIjoxNTUwNjQ1MzgzLCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.XuC2T5SXsIQ4pC-iDn5mNKN1SuFXfPBtuT0_PIgroV1VC_QU6YADK5GQRLnfLtm7NqWIsi-qP2fhUn_GZJoU5A' \
         --cookie locale=en \
         --form 'operations={"query":"mutation publishVoucher($input: PublishVoucherInput!) {          publishVoucher(input: $input) {            hash            transaction            submitToken          }        }","variables":{"input":{"name":"FSST Xmas Sales","symbol":"FSST_19FXSV","consumable":false,"totalSupply":"1000","price":{"numerator":"1000000000000000000","denominator":"1"},"expiry":"1577807999000","description":"The voucher for 2019 Christmas Sales!","proofOfContract":null,"por":"DISABLE"}}}' \
         --form 'map={"proofOfContract":["variables.input.proofOfContract"]}' \
         --form proofOfContract='@/path/to/the/pdf'
    ```

 - Response

    ```json
    {
      "data": {
        "publishVoucher": {
          "hash": "0x23401ea4495b56555440e95a3faf1c07209753896bc305f05806b8d8b3fe2e5b",
          "transaction": {
            "nonce": "0x10f",
            "gasPrice": "0x3b9aca00",
            "gas": "0x480eb3",
            "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
            "value": "0x0",
            "data": "0x4000aea0000000000000000000000000d6aebbbd0af65107a8d3dfe362f322bf4c8e1bcf0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002a4459ee93a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018000000000000000000000000000000000000000000000000000000000000001c000000000000000000000000000000000000000000000000000000000000004d20000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000005e0b707f0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c25821bf51fafd2d16801a2837d87af840446129000000000000000000000000000000000000000000000006aaf7c8516d0c00000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000065445535431310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b535449435f54455354313100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b384865537174365a656f634d50726363435654366d4357447152595535615137785453377a787645574841354746764e4752656f7564336263444675475755354c39716932533672417061526d6650314a68416333736633000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "chainId": 42
          },
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6InB1Ymxpc2hWb3VjaGVyIiwidHgiOiIrUU5zZ2dFUGhEdWF5Z0NEU0E2emxBRGk5REtaOVJSWGsxTXpydmJKVnJJMCtrZUJnTGtEUkVBQXJxQUFBQUFBQUFBQUFBQUFBQURXcnJ1OUN2WlJCNmpUMytOaTh5Sy9USTRiendBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUNwRVdlNlRvQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBWUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQndBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVRTQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFnQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFYZ3R3ZndBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBd2xnaHYxSDYvUzBXZ0Jvb045aDYrRUJFWVNrQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQWFxOThoUmJRd0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUVBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJsUkZVMVF4TVFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBdFRWRWxEWDFSRlUxUXhNUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQmpMMmx3Wm5NdmVrSjFja3M0U0dWVGNYUTJXbVZ2WTAxUWNtTmpRMVpVTm0xRFYwUnhVbGxWTldGUk4zaFVVemQ2ZUhaRlYwaEJOVWRHZGs1SFVtVnZkV1F6WW1ORVJuVkhWMVUxVERseGFUSlRObkpCY0dGU2JXWlFNVXBvUVdNemMyWXpBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBS29DQSIsImluZm8iOnsidG9rZW5JZCI6IsKiXHUwMDEwOcOWXHUwMDFlw6NcdTAwMTHDqcKeXHUwMDFhXHUwMDBmSirDgMK1XHUwMDAzIiwibmFtZSI6eyJlbiI6IlRFU1QxMSJ9LCJzeW1ib2wiOiJTVElDX1RFU1QxMSIsInRvdGFsU3VwcGx5IjoiMTIzNCIsIm1ldGFkYXRhIjoiQWJVZmVCQXFlVkJ4RTY2Z0E5VG1uRHBqQzVIQ2dFS0ZRWk9BazN0UHpJMUJoTUZRTFZDZHdQc252R3BKUTdMNmVsUkRDSlFvRVROR3hIT1Via04wbWhNNFhIcEciLCJsaXF1aWQiOnRydWUsImFwcHJvdmVDaGVja2luZyI6ZmFsc2UsImV4cGlyeSI6MTU3NzgwNzk5OTAwMCwiY29uc3VtYWJsZSI6ZmFsc2UsInByaWNlIjp7Im51bWVyYXRvciI6IjEyMzAwMDAwMDAwMDAwMDAwMDAwMCIsImRlbm9taW5hdG9yIjoiMSJ9LCJ2ZW5kaWJsZSI6dHJ1ZSwiZGVzY3JpcHRpb24iOnsiZW4iOiJmb3IgdGhlIHRlc3QifX0sImlhdCI6MTU1MDU2MjQ5NywiZXhwIjoxNTUwNTYzMDk3LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.lPsuWvPyTNcZaa_kXrLzlDKb50j7LOOtSKuvjsXWH8Bfg2YmGNEXaLclFIeBBySXIXfScTrCinbtdd7C9Y4fnQ"
        }
      },
    }
    ```

    > 目前此 API 尚未支援回傳所需 FST Service Gas 量，欲查詢可透過 `Smart Voucher 發行量 * 有效天數 * 0.00003 FST Service Gas` 的方式計算，未達 600 則算為 600 FST Service Gas
  
    > 此 response 中的 `transaction` 物件將為接下來拿來簽署的 payload，`submitToken` 也請保留，等一下將簽署後的結果送出時將需要

    > 也請記得，此 response 會隨著不同時間呼叫而有所不同，請使用當前最新的呼叫作為接下來步驟所需要用到的資料

    > 而假如收到類似  
    ```json   
    { 
      "data": {
        "publishVoucher": null
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
         --data '{"query":"mutation submitSignedTransaction($input: SubmitTransactionInput!) {  submitTransaction(input: $input) {    transactionHash  }}","variables":{"input":{"data":"0xf8aa82010d843b9aca0082f30f9400e2f43299f51457935333aef6c956b234fa478180b844a9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f1578a0b8a0eee843ab7a58f0d36ba94829c4ab0422b7f26f5e114ff1f662892fdc07e1a028dd5b5b6b0c1e39fa094a971d2614661ff18942b7db72be779b25ae0c2f0082","submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJ0eCI6IitHcUNBUTJFTzVyS0FJTHpENVFBNHZReW1mVVVWNU5UTTY3MnlWYXlOUHBIZ1lDNFJLa0ZuTHNBQUFBQUFBQUFBQUFBQUFBUER3OFBEdzhQRHc4UER3OFBEdzhQRHc4UER3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHMm0wdXMwRjhWS29DQSIsImluZm8iOnt9LCJpYXQiOjE1NTA0NzgyNzcsImV4cCI6MTU1MDQ3ODg3NywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.Qv8mA7mqsQ5RBkMcXvJ2qZOed14Vx-DJPQc3k-U1beb1mFx3Ok-MlZoYivOC-Z1IP0YmS3NJTfrJpOUxOUuVaw"}},"operationName":"submitSignedTransaction"}'
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
    query getTransactionReceipt($txHash: String!) {
      getTransactionReceipt(txHash: $txHash)
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
         --data '{"query":"query getTransactionReceipt($txHash: String!) {  getTransactionReceipt(txHash: $txHash)}","variables":{"txHash":"0x963339460f699b5d02dfd841c21992353cd441917964506ebeae06efe85f400b"},"operationName":"getTransactionReceipt"}'
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

## Confirm the Smart Voucher

 > 請查看 `get me` 中的 `token.vouchers` (詳情請參考 Quick start [第二篇章](../Quick_Start/02-Get_account_information.zh.md))
