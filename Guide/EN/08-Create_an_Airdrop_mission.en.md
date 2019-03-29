# Create an Airdrop Mission

> In this chapter, you will understand how to set up Airdrop mission via FsTK APi. You can set up Airdrop Locate rule, extract corresponding address on FsTK Engine and airdrop desired assets.

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

 1. Please sign up an account on `https://test.fstk.io` or `https://engine.fstk.io`.
    >  Notice account data are NOT shared across both platform. 

    - `test.fstk.io` is Tokeneden built on [**Kovan Testnet**](https://kovan.etherscan.io) for agile software development, testing & demo.  
    - `engine.fstk.io` is official Tokeneden built on Ethereum [**Mainnet**](https://etherscan.io).

 2. Please take a look at your asset balances of `ETH`、`FST`、`FIL` and `FST Service Gas`.
    > Please remember that assets on `test.fstk.io` belongs to **Kovan Testnet**; assets on `engine.fstk.io` belongs to **Mainnet**.

    - `ETH` is `Ether`, a small amount will be given to new accounts on `test.fstk.io`. 
    - `FST` is `Funder Smart Token`, a fundamental Utility Token within [FST Network](https://fst.network) and will be given to new accounts on `test.fstk.io`.
    - `FIL` is `FundersToken Initialisation License` as Token Issuance License, 1 FIL will be given to new accounts on `test.fstk.io`.
    - `FST Service Gas` is the FsTK module usage fee for `Token Issuer`, balance is shown at User Profile on the top right corner.

 3. Please prepare your API testing tools.
    - [Insomnia](https://insomnia.rest) (recommended)
    - [Postman](https://www.getpostman.com)

 4. Understand how to retrieve Access Web Token (JWT).
    > Please refer to Quick start [Chapter 1](../../Quick_Start/EN/01-Connect_to_FsTK_Engine_API.en.md).

 5. Complete Quick start.

 6. Confirm sufficient Ether (ETH) for ETH gas fee.

 7. Confirm sufficient FST Service Gas for module service fee (at least 90 FST Service Gas), FST Service Gas will not be refunded after cancellation.

 8. Become Issuer (Token Issuer), please confirm `token` in `get me`.

## Encode the Transaction (create airdrop locate)

 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

 > Hereinafter let's take FST Sport Shop as the example.
   
  <!-- FST Sport Shop 開幕滿一週年慶祝，為回饋消費者，在每年週年慶期間，將依照消費者所擁有之 FSST 數量進行回饋，消費者每擁有 10 FSST 將額外獲得 1 FSST 作為回饋。亦即擁有 22 FSST 將額外獲得 2 FSST，擁有 35 FSST 將額外獲得 3 FSST，依此類推。 -->
    FST Sport Shop is going to give away benefits based on customers FSST holding amount. Every 10 FSST will grant customer 1 FSST as rewards. 

  - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)
    - operations detail
    ```graphql
    mutation createAirdropLocate($input: createAirdropLocateInput!) {
      createAirdropLocate(input: $input) {
        airdropLocate {
          seqno: id
          airdropItem {
            ... on Token {
              id
              name
              decimals
            }
            ... on Voucher {
              id
              name
              decimals
            }
          }
          totalAddresses
          totalAirdropAmount
          summary {
            rule {
              locateRule {
                type
                item {
                  ... on Token {
                    decimals
                  }
                  ... on Voucher {
                    decimals
                  }
                }
              }
              item {
                ... on Token {
                  decimals
                }
                ... on Voucher {
                  decimals
                }
              }
              amount
            }
            totalAddresses
            totalAirdropAmount
          }
        }
      }
    }
    ```

    Variables:
    ```json
    {
      "input": {
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
    ```

     - `rules` are Airdrop Locate rules that each rule acts independently.

       - `rule` is an individual airdrop locate rule.

         - `type` contains 2 types `EVERY` and `AT_LEAST`. `EVERY` means every condition will be counted for giveaway item calculation. For example, 'every 10 Smart Token can receive 1 Smart Voucher' means that a customer with 25 Smart Token can receive 2 Smart Voucher. `AT_LEAST` means only the condition is greater than or equal to (>=) the qualification will be counted for giveaway item calculation. For example, 'at least 10 Smart Token can receive 1 Smart Voucher' means that a customer with 25 Smart Token can receive 1 Smart Voucher.

         - `itemId` is the ID of located Smart Token/Voucher.

         - `amount` is the located amount of located Smart Token/Voucher in Decimaled Number. Please notice that Smart Token's decimal is 18 and Smart Voucher's decimal is 0.
     
       - `itemId` is the ID of giveaway Smart Token/Voucher.

       - `amount` is the giveaway amount of Smart Token/Voucher in Decimaled Number. Please notice that Smart Token's decimal is 18 and Smart Voucher's decimal is 0.

  <!-- > 補充說明，因搜尋條件所用到的資產，與所能獲得的資產之組合相當自由，請務必注意 Decimaled Number 誤判造成的問題，例如欲搜尋擁有 Smart Token 之帳戶進行空投 Smart Token，`rules.n.rule.itemId` 及 `rules.n.itemId` 因注意都為 decimal = 18 之狀態。如任一給予錯誤的值，可能會造成空頭超量的問題。 -->
  > Please pay attention to the Decimaled Number based on difference of located item & giveaway item. For example, locating Smart Token to give away Smart Token requires decimals of both `rules.n.rule.itemId` and `rules.n.itemId` to be 18. Over-giveaway could occur if given any of the incorrect amount.


 - Using cURL

    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
          --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyNjM1NzYzLCJleHAiOjE1NTI3MjIxNjMsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.d55YCLhl-_xPEk-N9WAisx8S4vLHe0p3iE8KEzg0YGbwGaqozaT85pNJbJ9EwfZiEflm9NVOjzn4lX_qT1fjOQ' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"mutation createAirdropLocate($input: createAirdropLocateInput!) {\n  createAirdropLocate(input: $input) {\n    airdropLocate {\n      seqno: id\n      airdropItem {\n        ... on Token {\n          id\n          name\n          decimals\n        }\n        ... on Voucher {\n          id\n          name\n          decimals\n        }\n      }\n      totalAddresses\n      totalAirdropAmount\n      summary {\n        rule {\n          locateRule {\n            type\n            item {\n              ... on Token {\n                decimals\n              }\n              ... on Voucher {\n                decimals\n              }\n            }\n          }\n          item {\n            ... on Token {\n              decimals\n            }\n            ... on Voucher {\n              decimals\n            }\n          }\n          amount\n        }\n        totalAddresses\n        totalAirdropAmount\n      }\n    }\n  }\n}\n","variables":{"input":{"rules":[{"rule":{"type":"EVERY","itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","amount":"10000000000000000000"},"itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","amount":"1000000000000000000"}]}},"operationName":"createAirdropLocate"}'
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
            "totalAddresses": "500",
            "totalAirdropAmount": "100000000000000000000",
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
                "totalAddresses": "500",
                "totalAirdropAmount": "100000000000000000000"
              }
            ]
          }
        }
      }
    }
    ```

    > Setting up Airdrop Locate will not consume FST Service Gas.

    > If API succeeds, then your Airdrop Locate rule (here referenced as `seqno`) is also stored in systems of FST Network. You may use the reference to start Airdrop in the future. **Please take down your Airdrop Locate rule `seqno` and FST Network does not provide this value**.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "createAirdropLocate": null
      },
      "errors": [....]
    }
    ```  
    > means the transaction will fail. We suggest to skip the following steps and check related resources of Transaction are correct first.
    > e.g. ETH balance, FST Service Gas balance, Token balance, Voucher balance, ... etc..

### Create Airdrop Mission

 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

 > Hereinafter we take FST Sport Shop as the example.

    FST Sport Shop's annual sale is approaching, an Airdrop to customers is under preparation with budget of 200,000 FSST.

 - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

   - operations detail
    ```graphql
    mutation createAirdropMission($input: CreateAirdropMissionInput!) {
      createAirdropMission(input: $input) {
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
        "listId":"QWlyZHJvcExvY2F0ZToxNA==",
        "itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn",
        "budget":"200000000000000000000000",
        "invokeTime":"1569888000000",
        "por":"DISABLE"
      }
    }
    ```

     - `listId` is the `seqno` from previous step, i.e. the Airdrop Locate rule ID.

     - `itemId` is the ID of giveaway Smart Token/Voucher during this Airdrop.

     - `budget` is the budget of Airdrop. Insufficient budget will cause failure of Airdrop.

     - `invokeTime` is the Airdrop time in Unix time millisecond, e.g. UTC+8 2019/12/31 means `"1577807999000"`. Please notice that Unix time has no time zone, please adjust it according to your local time.


 - Using cURL

    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
          --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyNjM1NzYzLCJleHAiOjE1NTI3MjIxNjMsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.d55YCLhl-_xPEk-N9WAisx8S4vLHe0p3iE8KEzg0YGbwGaqozaT85pNJbJ9EwfZiEflm9NVOjzn4lX_qT1fjOQ' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"mutation createAirdropMission($input: CreateAirdropMissionInput!) {\n  createAirdropMission(input: $input) {\n    transaction\n    submitToken\n    hash\n  }\n}\n","variables":{"input":{"listId":"QWlyZHJvcExvY2F0ZToxNA==","itemId":"VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn","budget":"200000000000000000000000","invokeTime":"1569888000000","por":"DISABLE"}},"operationName":"createAirdropMission"}'
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

    > Create Airdrop Mission will consume 90 FST Service Gas. Cancelling an Airdrop will not refund FST Service Gas.

    > Issuer will consume 0.3 FST Service Gas per airdrop receiver with 90 FST Service Gas as minimum. To airdrop over 300 receivers, each will charge 0.3 FST Service Gas per person.
  
    > In response's `transaction`, object will be used to sign payload, `submitToken` is also required for broadcasting signed transaction.

    > Please remember that response will vary after each call, please use the latest response for next steps.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "createAirdropMission": null
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

 > **WARNING: If the passphrase of Ethereum key JSON is lost, the private key is lost and FsTK does not have users' Ethereum key JSON passphrase.**

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

    > Install command line (please let `index.js` be the program entry point).

    ```sh
    npm i && npm start
    # or
    # yarn && yarn start
    ```

    > If working on windows, please refer to [node-gyp on windows](https://github.com/nodejs/node-gyp#on-windows).

 - Using Java

   > Please refer to [Web3j](https://web3j.io).
   > Notice that `loadCredentials` in `WalletUtils` method with this overload:

   ```Java
   public static Credentials loadCredentials(String password, File source)
   ```
   > In another way, as web3j only provides `File` import, please pay attention to OS storage or use in-memory-fs in Java.

   > Please to refer to [Web3j sample codes](https://docs.web3j.io/transactions.html#creating-and-working-with-wallet-files).

 - Using C#

   > Please refer to [Nethereum](https://nethereum.com). 
   > Please refer to [Nethereum sample codes](https://nethereum.readthedocs.io/en/latest/accounts/#working-with-an-account).

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

   > Please refer to [Web3j](https://web3j.io).
   > Notice that `signMessage` in `TransactionEncoder`, and please use the overload below since the `chainId` must be included in the signature process.

   ```java
   public static byte[] signMessage(RawTransaction rawTransaction, byte chainId, Credentials credentials)
   ```

   > Please refer to [Web3j sample codes](https://docs.web3j.io/transactions.html#signing-transactions)

 - Using C#

   > Please refer to [Nethereum](https://nethereum.com). 
   > Please refer to `SignTransaction` in `TransactionSigner`, and please use the overload below since the `chainId` must be included in the signature process.

   ```csharp
   public string SignTransaction(byte[] privateKey, BigInteger chainId, string to, BigInteger amount, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, string data)
   ```

   > Please refer to the section `Nethereum.Web3.Accounts.AccountSignerTransactionManager.SignTransaction`.

 ## Broadcast the Ethereum Transaction
 
  - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

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

    > `data` is the object from signing `transaction` with current user's private key. In another word, `signedTransaction` is the hex string.

    > `submitToken` is `submitToken` from Encode Ethereum Transaction. 

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
      "txHash": "0x963339460f699b5d02dfd841c21992353cd441917964506ebeae06efe85f400b"
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
      }
    }
    ```

    > When `remain` in `confirmations`  becomes `0`, the transaction is confirmed.

    > Notice that a confirmed transaction may not succeed. As on Blockchain, failed transaction is also a consensus. Please use [Infura](https://infura.io) with [ETH-JSON-RPC](https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionreceipt) to fetch `status` (success/failure of transaction).

## Confirm the Airdrop mission

 > Please refer to `getAirdropMissionInfo` to retrieve Airdrop info (More details in [API_Reference/Airdrop/getAirdropMissionInfo](../API_Reference/Airdrop/getAirdropMissionInfo.md)).
       
## Check the progress of Airdrop mission
 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

 - Using [GraphQL](https://graphql.org/learn/) (Recommended Insomnia)

   - operations detail
    ```graphql
    {
      airdropHistory {
        edges {
          node {
            id
            method {
              listType
              listId
              targets
            }
            item {
              ... on Token {
                id
                name
              }
              ... on Voucher {
                id
                name
              }
            }
            budget
            status
            totalAirdropAmount
            totalAddresses
            isClaimed
            usedBudget
            createTime
            invokeTime
            executeTime
          }
        }
      }
    }
    ```

 - Using cURL

    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
          --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyODc5OTAwLCJleHAiOjE1NTI5NjYzMDAsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.fUqX8JI99T1s5dMQINm-npd4ZeJxJfSj-DRr02h6L3Fk69wBgX2ttOxiLffIuLbh9E3qXUZ6k9fEpQjIJZpLrw' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"{\n  airdropHistory {\n    edges {\n      node {\n        id\n        method {\n          listType\n          listId\n          targets\n        }\n        item {\n          ... on Token {\n            id\n            name\n          }\n          ... on Voucher {\n            id\n            name\n          }\n        }\n        budget\n        status\n        totalAirdropAmount\n        totalAddresses\n        isClaimed\n        usedBudget\n        createTime\n        invokeTime\n        executeTime\n      }\n    }\n  }\n}\n"}'
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

  > An airdrop mission has several phases as below.

  ```
         Pending                 Locked(1 hr)           Activated (1 hr)          Distributed
  
  ------------------------|------------------------|------------------------|------------------------>
  
                    Locked Time             Airdrop Activation        Airdrop Ending

  Locked Time: an hour BEFORE set-up time of Airdrop. 
  Airdrop Activation: set-up time of Airdrop which can be configured.
  Airdrop Ending: an hour AFTER set-up time of Airdrop.
  
  <!-- 可取消期：在此期間 issuer 可隨時取消該次空投，取消不退回 FST Service Gas。
  鎖定期：為開始空投前一小時，issuer 在此期間便無法取消空頭，且 box 資產會被鎖定無法使用。
  空投期：開始空投，若提前空投結束且成功，issuer 可立即取回剩餘預算，若總預算不足則空投失敗。若空坄失敗，issuer 需於一小時後才可取回全部預算。
  釋放期：可取回預算之時期。 -->
  Pending: time when issuer can cancel the Airdrop and FST Service Gas will not be returned.
  Locked: 1-hour period when issuer cannot cancel Airdrop and budget of Smart Token/Voucher will be locked.
  Activated: 1-hour period when Airdrop starts and issuer can claim back the remaining budget as soon as Airdrop is complete; issuer will not be able to claim back budget when Airdrop fails (e.g. insufficient budget) until 1 hour later.
  Distributed: time when Airdrop is complete and (remaining) budget could be claimed back.
  ```

### Encode the Transaction
 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

  > Hereinafter we take FST Sport Shop as the example.
   
    FST Sport Shop would like to clear up the Airdrop mission and claim back the remaining Smart Token/Voucher, which requires finalizing the Airdrop.

 - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

   - operations detail
    ```graphql
    mutation claimAirdropMission($input: claimAirdropMissionInput!) {
      claimAirdropMission(input: $input) {
        pendingTransactions
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
        "missionId":"QWlyZHJvcE1pc3Npb246OTE=",
        "por":"DISABLE"
      }
    }
    ```

     - `missionId` is the ID of finalizing Airdrop.


 - Using cURL

    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
          --header 'authorization: bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiaWF0IjoxNTUyODc5OTAwLCJleHAiOjE1NTI5NjYzMDAsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.fUqX8JI99T1s5dMQINm-npd4ZeJxJfSj-DRr02h6L3Fk69wBgX2ttOxiLffIuLbh9E3qXUZ6k9fEpQjIJZpLrw' \
          --header 'content-type: application/json' \
          --cookie locale=en \
          --data '{"query":"mutation claimAirdropMission($input: claimAirdropMissionInput!) {\n  claimAirdropMission(input: $input) {\n    pendingTransactions\n    transaction\n    submitToken\n    hash\n  }\n}\n","variables":{"input":{"missionId":"QWlyZHJvcE1pc3Npb246","por":"DISABLE"}},"operationName":"claimAirdropMission"}'
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

    > Finalizing Airdrop will not consume FST Service Gas.

    > Is API succeeds, please follow 3 previous steps： `3. Decrypt the Ethereum Key JSON`, `4. Sign the Ethereum Transaction`, `5. Broadcast the Ethereum Transaction`.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "claimAirdropMission": null
      },
      "errors": [....]
    }
    ```  
    > means the transaction will fail. We suggest to skip the following steps and check related resources of Transaction are correct first.
    > e.g. ETH balance, FST Service Gas balance, Token balance, Voucher balance, ... etc..