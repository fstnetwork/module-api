# Create an Airdrop Mission

> 在此章節中，您將學習如何透過 FsTK API 建立 Airdrop，您可以建立規則卡，擷取出在 FsTK Engine 中所有符合規則的帳戶進行空投。

## Table of Contents

 1. Prerequisite
 2. Encode the Transaction (creating airdrop)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Confirm the Ethereum Transaction
 7. Confirm the Airdrop mission
 8. Check the progress of Airdrop mission
 9. Finalize the Airdrop mission
    1.  Prerequisite
    2.  Encode the Transaction (finalizing airdrop mission)

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

 7. 確認帳戶有足夠的 FST Service Gas 來付出服務手續費 (最少 90 FST Service Gas)

 8. 已經成為 Issuer (Token 發行者)，請至 `get me` 中的 `token` 確認

## Encode the Transaction
### Create Airdrop Locate

 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

 > 以下繼續以 FST Sport Shop 作為範例：
   
    FST Sport Shop 開幕滿一週年慶祝，為回饋消費者，在每年週年慶期間，將依照消費者所擁有之 FSST 數量進行回饋，消費者每擁有 10 FFST 將額外獲得 1 FFST 作為回饋，擁有 22 FFST 將額外獲得 2 FFST，擁有 35 FFST 將額外獲得 3 FFST，依此類推。

 - Using multipart/form-data

   > operations 裡放入 GraphQL query 以及 GraphQL variables

   - operations detail

     ```json
     {
       "query": "mutation createAirdropLocate($input: createAirdropLocateInput!) {      createAirdropLocate(input: $input) {        airdropLocate {          seqno: id         airdropItem {         ... on Token {          id          name          decimals            }          ... on Voucher {          id          name           decimals           }          }          totalAddresses          totalAirdropAmount          summary {            rule {          locateRule {                type         item {                  ... on Token {                    decimals           }            ... on Voucher {         decimals         }        }        }         item {         ... on Token {            decimals        }        ... on Voucher {          decimals        }          }          amount         }            totalAddresses         totalAirdropAmount       }       }      }     }",
       "variables": {
         "input":{  
          "rules":[
            {  
              "rule":{
                "type":"EVERY",
                "itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
                "amount":"10000000000000000000"
              },
              "itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
              "amount":"1000000000000000000"
            }
          ]
         }
       }
     }
     ```

     - `rules` 為本次活動的所有空投規則，可有多個規則，每個空投規則之間無關聯。

       - `rule` 空投規則

         - `type` 有 `EVERY` 及 `AT_LEAST` 兩種。`EVERY` 代表「每...可得...」，例如：每 10 Smart Token 可得 1 Smart Voucher，則有一消費者有 25 Smart Token，該消費者可得 2 Smart Voucher。 `AT_LEAST` 代表「至少...可得...」，例如：至少擁有 10 Smart Token 可獲得 1 Smart Voucher，則有一消費者擁有 25 Smart Token，該消費者可得 1 Smart Voucher。

         - `itemId` 所訂定之規則的資產 ID

         - `amount` 所訂定之規則的資產數量。格式為 Decimaled Number
     
       - `itemId` 符合規則所能獲得的資產之 ID

       - `amount` 符合規則所能獲得的資產之數量。格式為 Decimaled Number

   > multipart/form-data 之總結為

   ```
   operations: {"query": "mutation createAirdropLocate($input: createAirdropLocateInput!) {      createAirdropLocate(input: $input) {        airdropLocate {          seqno: id          airdropItem {            ... on Token {              id              name              decimals            }            ... on Voucher {              id              name              decimals            }          }          totalAddresses          totalAirdropAmount          summary {            rule {              locateRule {                type                item {                  ... on Token {                    decimals                  }                  ... on Voucher {                    decimals                  }                }              }              item {                ... on Token {                  decimals                }                ... on Voucher {                  decimals                }              }              amount            }            totalAddresses            totalAirdropAmount                      }        }      }    }","variables":{"input":{"rules":[{"rule":{"type":"EVERY","itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/","amount":"10000000000000000000"},"itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/","amount":"100000000000000000"}]}}}
   ```

 - Using cURL

    ```sh
    curl --request POST \
         --url https://dev.fstk.io/api \
         --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyNjM1NzYzLCJleHAiOjE1NTI3MjIxNjMsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.d55YCLhl-_xPEk-N9WAisx8S4vLHe0p3iE8KEzg0YGbwGaqozaT85pNJbJ9EwfZiEflm9NVOjzn4lX_qT1fjOQ' \
         --header 'content-type: application/json' \
         --cookie locale=en \
         --data '{"query":"mutation createAirdropLocate(input: createAirdropLocateInput!) {\n  createAirdropLocate(input: input) {\n    airdropLocate {\n      seqno: id\n      airdropItem {\n        ... on Token {\n          id\n          name\n          decimals\n        }\n        ... on Voucher {\n          id\n          name\n          decimals\n        }\n      }\n      totalAddresses\n      totalAirdropAmount\n      summary {\n        rule {\n          locateRule {\n            type\n            item {\n              ... on Token {\n                decimals\n              }\n              ... on Voucher {\n                decimals\n              }\n            }\n          }\n          item {\n            ... on Token {\n              decimals\n            }\n            ... on Voucher {\n              decimals\n            }\n          }\n          amount\n        }\n        totalAddresses\n        totalAirdropAmount\n      }\n    }\n  }\n}\n","variables":{"input":{"rules":[{"rule":{"type":"EVERY","itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","amount":"10000000000000000000"},"itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","amount":"1000000000000000000"}]}},"operationName":"createAirdropLocate"}'\
    ```

 - Response

    ```json
    {
      "data": {
        "createAirdropLocate": {
          "airdropLocate": {
            "seqno": "QWlyZHJvcExvY2F0ZToxNA==",
            "airdropItem": {
              "id": "VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
              "name": "FST Sport Shop Token",
              "decimals": "18"
            },
            "totalAddresses": "0",
            "totalAirdropAmount": "0",
            "summary": [
              {
                "rule": {
                  "locateRule": {
                    "type": "EVERY",
                    "item": {
                      "decimals": "18"
                    }
                  },
                  "item": {
                    "decimals": "18"
                  },
                  "amount": "1000000000000000000"
                },
                "totalAddresses": "0",
                "totalAirdropAmount": "0"
              }
            ]
          }
        }
      }
    }
    ```

    > 建立規則卡無需花費 FST Service Gas

    > 此 API 若執行成功，表示您所建立的規則已經被記錄於 FST Network 的資料庫中，您可重複使用此 Locate 來進行 Airdrop。**請務必記錄您自訂的規則之 `seqno`，目前 FST Network 暫不提供查詢此值。**

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

### Create Airdrop Mission

 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

 > 下方以 FST Sport Shop 作為範例：

    FST Sport Shop 的週年慶即將來臨，預備進行每年週年慶的大撒幣活動，本次活動預算為 100,000 FSST。

 - Using multipart/form-data

   > operations 裡放入 GraphQL query 以及 GraphQL variables

   - operations detail

    ```json
     {
       "query":"mutation createAirdropMission($input: CreateAirdropMissionInput!) {      createAirdropMission(input: $input) {        transaction        submitToken      hash      }    }",
       "variables":{  
        "input":{  
          "listId":"QWlyZHJvcExvY2F0ZToxNA==",
          "itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
          "budget":"100000000000000000000000",
          "invokeTime":"1569888000000",
          "por":"DISABLE"
        }
      }
    }
    ```

     - `listId` 於上一步驟您所獲得的 `seqno` 值，也就是您所要使用的規則卡 ID

     - `itemId` 您所要空投的 Smart Token/Voucher ID

     - `budget` 您所要空投的預算，若空投執行時，預算不足，則會空投失敗

     - `invokeTime` 開始空投的時間


   > multipart/form-data 之總結為

    ```
    operations: {"query":"    mutation createAirdropMission($input: CreateAirdropMissionInput!) {      createAirdropMission(input: $input) {        transaction        submitToken      hash      }    }","variables":{"input":{  "listId":"QWlyZHJvcExvY2F0ZToxNA==","itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","budget":"100000000000000000000000","invokeTime":"1569888000000","por":"DISABLE"}}}
    ```

 - Using cURL

    ```sh
    curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDU1ODk4MywiZXhwIjoxNTUwNjQ1MzgzLCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.XuC2T5SXsIQ4pC-iDn5mNKN1SuFXfPBtuT0_PIgroV1VC_QU6YADK5GQRLnfLtm7NqWIsi-qP2fhUn_GZJoU5A' \
         --cookie locale=en \
         --form 'operations={"query":"    mutation createAirdropMission($input: CreateAirdropMissionInput!) {      createAirdropMission(input: $input) {        transaction        submitToken      hash      }    }","variables":{"input":{  "listId":"QWlyZHJvcExvY2F0ZToxNA==","itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","budget":"100000000000000000000000","invokeTime":"1569888000000","por":"DISABLE"}}}' \
    ```

 - Response

    ```json
    {
      "data": {
        "createAirdropMission": {
          "transaction": {
            "nonce": "0x5",
            "gasPrice": "0x3b9aca00",
            "gas": "0x3bcdd",
            "to": "0x155dea084AD150D80E56B7746dD5503fCd5dfA77",
            "value": "0x0",
            "data": "0x4000aea0000000000000000000000000c01926f281f51ace3291a8dd680b968888f13b4000000000000000000000000000000000000000000000152d02c7e14af680000000000000000000000000000000000000000000000000000000000000000000600000000000000000000000000000000000000000000000000000000000000064117d113c00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000005d92970000000000000000000000000000000000000000000000000000000000",
            "chainId": 42
          },
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiY3JlYXRlQWlyZHJvcE1pc3Npb24iLCJ0eCI6IitRRXFCWVE3bXNvQWd3TzgzWlFWWGVvSVN0RlEyQTVXdDNSdDFWQS96VjM2ZDRDNUFRUkFBSzZnQUFBQUFBQUFBQUFBQUFBQXdCa204b0gxR3M0eWthamRhQXVXaUlqeE8wQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBRlMwQ3grRks5b0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQmdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUdRUmZSRThBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJka3BjQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFDcUFnQT09IiwiaW5mbyI6eyJsaXN0VHlwZSI6IkFpcmRyb3BMb2NhdGUiLCJsaXN0SWQiOiIxNCIsImVzY3Jvd2JveEFkZHJlc3MiOiIweGMwMTkyNkYyODFmNTFhY0UzMjkxQThERDY4MGI5Njg4ODhmMTNiNDAiLCJ1c2VyQWRkcmVzcyI6IjB4YjAyMzZmOWE2YTFjZDhjZjE3MjUxYTEzMDY1MWUwYmU4ZmIwMGUyNyIsIml0ZW1JZCI6IsOLw5xswopcdTAwMWEuXHUwMDExw6nCujvDh2tDwqAgw6ciLCJpdGVtVHlwZSI6IlRva2VuIiwiaXRlbUFkZHJlc3MiOiIweDE1NWRlYTA4NGFkMTUwZDgwZTU2Yjc3NDZkZDU1MDNmY2Q1ZGZhNzciLCJidWRnZXQiOiIxNTJkMDJjN2UxNGFmNjgwMDAwMCIsImludm9rZVRpbWUiOjE1Njk4ODgwMDAwMDB9LCJpYXQiOjE1NTI2NDMzNDcsImV4cCI6MTU1MjY0Mzk0NywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.sNiKKRzd3dlhiwUtWGtKMwljp9SK3t45jv4SfNmYrnK0E4cwO3cQEkAG44tzDInwUHYsy5IRC9b2r1UCCnKcwA",
          "hash": "0xb5793d750f840d76a8de0800a3a913dd59da56d05f51f0f15c41bc0cd554eab1"
        }
      }
    }
    ```

    > Create Airdrop Mission 需消耗 90 FST Service Gas，若取消 Airdrop 則不退回 FST Service Gas。

    > issuer 需花費 0.3 FST Service Gas / per funder。前 300 人可由 Create Airdrop Mission 時所支付的 90 FST Service Gas 抵掉，一次空投超過 300 人，每多一人收取 0.3 FST Service Gas。
  
    > 此 response 中的 `transaction` 物件將為接下來拿來簽署的 payload，也請保留 `submitToken`，將在下一步送出簽署結果時使用

    > 也請記得，此 response 會隨著不同時間呼叫而有所不同，請使用當前最新的呼叫作為接下來步驟所需要用到的資料

    > 而假如收到類似  
    ```json   
    { 
      "data": {
        "createAirdropMission": null
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

## Confirm the Airdrop mission

 > 可從 `getAirdropMissionInfo` 中取得您所有 Airdrop 的資訊 (詳情請參考 [API_Reference/Airdrop/getAirdropMissionInfo](../API_Reference/Airdrop/getAirdropMissionInfo.md))

       
## Check the progress of Airdrop mission
> 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

 - Using multipart/form-data

   > operations 裡放入 GraphQL query 以及 GraphQL variables

   - operations detail

     ```json
     {
       "query": "query{       airdropHistory{         edges{           node{             id             method{               listType               listId               targets             }           item{             ... on Token{               id               name             }              ... on Voucher{               id          name             }           }           budget           status           totalAirdropAmount           totalAddresses           isClaimed           usedBudget           createTime           invokeTime           }         }       }     }"
     }
     ```

   > multipart/form-data 之總結為

   ```
   operations: {"query": "query{       airdropHistory{         edges{           node{             id             method{               listType               listId               targets             }           item{             ... on Token{               id               name             }              ... on Voucher{               id          name             }           }           budget           status           totalAirdropAmount           totalAddresses           isClaimed           usedBudget           createTime           invokeTime           }         }       }     }"}
   ```

 - Using cURL

    ```sh
    curl --request POST \
        --url https://dev.fstk.io/api \
        --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyODc5OTAwLCJleHAiOjE1NTI5NjYzMDAsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.fUqX8JI99T1s5dMQINm-npd4ZeJxJfSj-DRr02h6L3Fk69wBgX2ttOxiLffIuLbh9E3qXUZ6k9fEpQjIJZpLrw' \
        --header 'content-type: application/json' \
        --cookie locale=en \
        --data '{"query":"{\n  airdropHistory {\n    edges {\n      node {\n        id\n        method {\n          listType\n          listId\n          targets\n        }\n        item {\n          ... on Token {\n            id\n            name\n          }\n          ... on Voucher {\n            id\n            name\n          }\n        }\n        budget\n        status\n        totalAirdropAmount\n        totalAddresses\n        isClaimed\n        usedBudget\n        createTime\n        invokeTime\n      }\n    }\n  }\n}\n"}'
    ```

 - Response

    ```json
    {
      "data": {
        "airdropHistory": {
          "edges": [
            {
              "node": {
                "id": "QWlyZHJvcE1pc3Npb246Mw==",
                "method": {
                  "listType": "AirdropLocate",
                  "listId": "1",
                  "targets": {
                    "filters": [
                      {
                        "giveItem": "VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
                        "ruleItem": "VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
                        "ruletype": "EVERY",
                        "giveAmount": "1000000000000000000",
                        "ruleAmount": "10000000000000000000"
                      }
                    ]
                  }
                },
                "item": {
                  "id": "VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
                  "name": "FST Sport Shop Token"
                },
                "budget": "100000000000000000000000",
                "status": "PENDING",
                "totalAirdropAmount": "3000000000000000000",
                "totalAddresses": "3",
                "isClaimed": false,
                "usedBudget": "3000000000000000000",
                "createTime": "1545405455",
                "invokeTime": "1545405483"
              }
            }
          ]
        }
      }
    }
    ```

## Finalize the Airdrop mission
### Prerequisite

  > 建立 Airdrop 後可分成不同時期，可參考下圖時間軸：


         可取消期                 鎖定期(1 hour)            空投期(1 hour)              釋放期
  ------------------------|------------------------|------------------------|------------------------>
                      鎖定時間點               空投執行時間點               空投結束時間點


鎖定時間點：為您創建 Airdrop 時，所設定之執行時間點的前一小時
空投執行時間點：為您創建 Airdrop 時，所設定之執行時間。僅此時間參數您可自行設定。
空投結束時間點：為您創建 Airdrop 時，所設定之執行時間點的後一小時

可取消期：在此期間 issuer 可隨時取消該次空投，取消不退回 FST Service Gas。
鎖定期：為開始空投前一小時，issuer 在此期間便無法取消空頭，且資產會被鎖定無法使用。
空投期：開始空投，若提前空投結束且成功，issuer 可立即取回剩餘預算，若總預算不足則空投失敗。若空坄失敗，issuer 需於一小時後才可取回全部預算。
釋放期：可取回預算之時期。


### Encode the Transaction
 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`  

 > 以下繼續以 FST Sport Shop 作為範例：
   
    空投結束後，FST Sport Shop 於此次空頭有剩餘預算，需進行

 - Using multipart/form-data

   > operations 裡放入 GraphQL query 以及 GraphQL variables

   - operations detail

     ```json
     {
       "query": "mutation claimAirdropMission($input: claimAirdropMissionInput!) {        claimAirdropMission(input: $input){          transaction          submitToken      hash        }      }",
       "variables": {
         "input":{  
          "missionId":"QWlyZHJvcE1pc3Npb246OTE=",
          "por":"DISABLE"
         }
       }
     }
     ```

     - `missionId` 為您欲關閉之 Airdrop 之 ID

   > multipart/form-data 之總結為

   ```
   operations: {"query": "mutation claimAirdropMission($input: claimAirdropMissionInput!) {        claimAirdropMission(input: $input){          transaction          submitToken      hash        }      }","variables":{"input":{"missionId":"QWlyZHJvcE1pc3Npb246OTE=","por":"DISABLE"}}}
   ```

 - Using cURL

    ```sh
    curl --request POST \
        --url https://dev.fstk.io/api \
        --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyODc5OTAwLCJleHAiOjE1NTI5NjYzMDAsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.fUqX8JI99T1s5dMQINm-npd4ZeJxJfSj-DRr02h6L3Fk69wBgX2ttOxiLffIuLbh9E3qXUZ6k9fEpQjIJZpLrw' \
        --header 'content-type: application/json' \
        --cookie locale=en \
        --data '{"query":"mutation claimAirdropMission($input: claimAirdropMissionInput!) {\n  claimAirdropMission(input: $input) {\n    transaction\n    submitToken\n    hash\n  }\n}\n","variables":{"input":{"missionId":"QWlyZHJvcE1pc3Npb246","por":"DISABLE"}},"operationName":"claimAirdropMission"}'
    ```

 - Response

    ```json
    {
      "data":{
        "claimAirdropMission":{
          "transaction":{
            "nonce":"0xef",
            "gasPrice":"0x3b9aca00",
            "gas":"0xdb02",
            "to":"0xc3a86fB0204dCB008372774960400B65Fec3a6b2",
            "value":"0x0",
            "data":"0x4677866f000000000000000000000000000000000000000000000000000000000000005b",
            "chainId":42
            },
          "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiYWN0aW9uIjoic3RvcEFpcmRyb3BNaXNzaW9uIiwiZGF0YSI6IlJuZUdid0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCYiIsImluZm8iOnsibWlzc2lvbklkIjoiOTEifSwiaWF0IjoxNTQzODIwMTI3LCJleHAiOjE1NDM4MjA3MjcsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.zyHfjPlcIzvwIbF77C4moV-kvojhlls1Hkzda23iE9eN89d2d1ONQgcZIOkcMuGIHXr1a5TulkC7C5S_t54vzQ",
          "hash":"0xab3fa519a3aefedbgryja3287eceae3c8f779cc43ee82b01d40fa1aeb5ad9f69"
        }
      }
    }
    ```


    > 關閉 Airdrop 無需花費 FST Service Gas

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