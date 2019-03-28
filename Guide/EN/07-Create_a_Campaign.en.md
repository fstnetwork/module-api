# Create a Campaign

> In ths chapter, you will understand how to create Smart Voucher Campaign via FsTK API and effectively distribute Smart Voucher.

> Campaign is the vending machine of Smart Token/Voucher. You can set up the type of selling Smart Token/Voucher, activated selling period, selling amount and selling price in this campaign. This allows customers to automatically receive Smart Token/Voucher after purchase.
<!-- 感覺整篇是 Create_a_Voucher_Campaign -->

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

 7. Confirm sufficient FST Service Gas for module service fee (at least 500 FST Service Gas), FST Service Gas will not be refunded after cancellation.

 8. Become Issuer (Token Issuer), please confirm `token` in `get me`.

## Encode the Transaction (create smart voucher campaign)

 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

 > Hereinafter let's take FST Sport Shop as the example.

    As Christmas is coming, in order to promote seasonal sales, FST Sport Shop is going to release a special lucky bag with Smart Voucher (FSST_19FXSV). Customers can spend FST Sport Shop Token and receive FSST_19FXSV (which can be further distributed and exchanged for lucky bag). FSST_19FXSV has a fixed total supply of 1,000 with original pricing (set-up when publishing FSST_19FXSV). 
    <!-- 而福袋中物品價值高於原本的定價，藉此商家可以出清未賣出的產品，亦能讓消費者以便宜的價格購得商品。 -->

  - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

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

     - `id` is the registered ID on FsTK system of selling Smart Token/Voucher, which can be retrieved from `token` in `get me`.

     - `name` is the name of Campaign, between 1 character to 20 characters.
  
     - `description` is the description of Campaign.

     - `stages` are Campaign stages. Currently Campaign only allows 1 stage.
  
       - `name` is the name of Stage, between 1 character to 20 characters.

       - `startTime` is the activated selling time of Stage, in Unix Timestamp in Milliseconds.
    
       - `endTime` is the ending time of Stage, in Unix Timestamp in Milliseconds.

       - `priceMultiplier` is the price multiplier (discount/markup) of Smart Token/Voucher at this Stage, e.g., a 10% off means 9/10; no discount means 1/1.
  
         - `numerator` is the numerator of price multiplier.

         - `denominator` is the denominator of price multiplier.
  
       - `cap` is the maximal selling amount of Smart Token/Voucher at this Stage in Decimaled Number. If selling Smart Voucher, this is the selling amount, otherwise selling Smart token, this requires a multiplier of 10^18. As Voucher is an integer with decimal = 0, 10^0 = 1.

       - `isPrivate` means whether this Stage is limited to customer identity (having special signature).
  
     - `description` is the description of Stage.


 - Using cURL

    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
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

    > At the moment, API does not respond the value of consuming FST Service Gas. `create a campaign` will consume 500 FST Service Gas.
  
    > In response's `transaction`, object will be used to sign payload, `submitToken` is also required for broadcasting signed transaction.

    > Please remember that response will vary after each call, please use the latest response for next steps.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "createCampaign": null
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

    > `data` is the object from signing `transaction` with current user's private key. In another word, `signedTransaction` is the hex string

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
      },
    }
    ```

    > When `remain` in `confirmations`  becomes `0`, the transaction is confirmed.

    > Notice that a confirmed transaction may not succeed. As on Blockchain, failed transaction is also a consensus. Please use [Infura](https://infura.io) with [ETH-JSON-RPC](https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_gettransactionreceipt) to fetch `status` (success/failure of transaction).

## Confirm the Campaign

 > Please refer to `token.vouchers` in `get me` (More details in Quick start [Chapter 2](../../Quick_Start/EN/02-Get_account_information.en.md))

## Transfer Smart Token to the Campaign to obtain Smart Voucher
### Encode the Transaction (transferring smart token to voucher campaign)

  > In any of following API calls, please remember to assign access token to `authorization` in http request header.

  > Voucher Issuer will consume 0.15 FST Service Gas after customer purchases each Smart Voucher.
  
  > Hereinafter let's take FST Sport Shop as the example.

    After FST Sport Shop sets up Campaign of FSST_19FXSV, customers can transfer 消費者可透過傳送 Smart Token (FSST) to Campaign address, the Campaign smart contract will automatically transfer FSST_19FXSV to the customer address corresponding voucher pricing.

 - Using [GraphQL](https://graphql.org/learn/) (Recommended Insomnia)

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

     - `id` is the registered ID on FsTK system of Smart Token, which can be retrieved from `getSmartTokenBalance`.

     - `to` is the Campaign smart contract address of FSST_19FXSV.

     - `value` is the transferring amount. If the price of FSST_19FXSV is `2000` FSST, to purchase 1 FSST_19FXSV requires `value = 2000000000000000000000`.


  - Using cURL
    
    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
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

> Information of Campaign, e.g. `cap` and `sold`, can be retrieved from `getAllCampaignInfo` API and shows the sales progress of current Campaign. (Please refer to [API_Reference/Campaign/getAllCampaignInfo](../API_Reference/getAllCampaignInfo.md))

## Finalize the Campaign
### Prerequisite

> There are 3 phases of Smart Voucher Campaign: pre sales, launch period and post sales. When finalizing the sales, there are different outcomes in each phase.

1. Pre sales (now < `startTime`): Campaign is cancelled by the issuer and returns the unsold Smart Voucher.
2. Launch Period (`startTime` < now < `endTime`): If Smart Voucher is sold out during Launch, issuer can claim back the Smart Token before `endTime`, otherwise issuer cannot cancel the Campaign or claim back remaining Smart Voucher and earned Smart Token.
3. Post sales (`endTime` < now)：issuer can claim back unsold Smart Voucher and earned Smart Token.

<!-- different result when finalize in different state  -->
### Encode the Transaction (finalizing campaign)
  
 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

  > Hereinafter we take FST Sport Shop as the example.

    FST Sport Shop's FSST_19FXSV is sold out during launch period. FST Sport Shop would like to receive FSST from Campaign vending machine, and `finalizeSmartVoucherCampaign` API can finalize the cmapaign.
   
  - Using [GraphQL](https://graphql.org/learn/) (Insomnia recommended)

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

     - `id` is the ID of finalizing Campaign, which can be retrieved from `getAllCampaignInfo`.

  - Using cURL
    
    ```sh
    curl --request POST \
          --url https://test.fstk.io/api \
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

    > Finalizing Campaign will not consume FST Service Gas.

    > Is API succeeds, please follow 3 previous steps： `3. Decrypt the Ethereum Key JSON`, `4. Sign the Ethereum Transaction`, `5. Broadcast the Ethereum Transaction`.

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