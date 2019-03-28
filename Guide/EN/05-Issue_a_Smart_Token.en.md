# Issue a Smart Token

> In this chapter, you will understand how to issue Smart Token via FsTK API.

> Smart Token is the ERC-1376 Token as the main ledger for digital system and digital identity on Blockchain.

## Table of Contents

 1. Prerequisite
 2. Encode the Transaction (issuing smart token)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Confirm the Ethereum Transaction
 7. Confirm the Smart Token

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

## Encode the Transaction (issuing smart token)

 > In any of following API calls, please remember to assign access token to `authorization` in http request header.

 > Hereinafter let's take FST Sport Shop as the issuing token.

    FST Sport Shop would like to issue token as the cash back after customers purchase its products.
    Token name is `FST Sport Shop Token` (abbr. `FSST`) with total supply 100 million token.

 - Using multipart/form-data

   > Assign `operations` with GraphQL query and GraphQL variables.

   - operations detail

     ```json
     {
       "query": "mutation IssueToken($input: IssueTokenInput!) {    issueToken(input: $input) {     transaction     hash    submitToken     }    }",
       "variables": {
         "input": {
            "name":"FST Sport Shop Token",
            "symbol":"FSST",
            "totalSupply":"1000000000000000000000000000",
            "price": {
                "numerator":"1",
                "denominator":"4500"
            },
            "description":"This is the FST Sport Shop Token.",
            "website":"fst.sport.com",
            "logo":null,
            "proofOfContract":null,
            "por": "DISABLE"
         }
       }
     }
     ```

     - `name` is the name of issuing Smart Token, from 3 to 20 characters.

     - `symbol` is the symbol of issuing Smart Token, from 2 to 6 capital characters.

     - `totalSupply` is the total supply of Smart Token as Decimal Number, e.g. issuing 1234 Smart Token means `totalSupply = "1234000000000000000000"`.

     - `price` is an object showing how much Ether a Smart Token is equivalent to (i.e. the exchange ratio between Token & Voucher), also the initial price of Smart Token. Token can only be exchanged by Ether. Token.
     
       - `numerator` is `"1"` in general. In special case, say `numerator = "2"` and `denominator = "345"`, this means 2 Ether is worth 345 Smart Token, so that pricing will be calculated in terms of fraction.

       - `denominator` is showing that how much Ether a Smart Token is worth, e.g. 123 Smart Token is worth 1 Ether, then `denominator = "123"` & `numerator = 1`.

     - `description` is the description of Smart Token.
  
     - `website` is the website related to Smart Token.

     - `logo` is the logo picture of Smart Token. Logo is not required in operations, instead in form-data as another entry, is `null` here.

     - `proofOfContract` is not required in operations, instead in form-data as another entry, is `null` here.

   > logo is in image format belonging belonging to Smart Token.

   > proofOfContract is in pdf format to describe related legal agreements or rights of Smart Token, then stored and protected in IPFS.

   > the summary of multipart/form-data is:

   ```
   operations: {"query":"mutation IssueToken($input: IssueTokenInput!) {    issueToken(input: $input) {     transaction     hash    submitToken     }    }","variables":{"input":{"name":"FST Sport Shop Token","symbol":"FSST","totalSupply":"1000000000000000000000000000","price":{"numerator":"1","denominator":"4500"},"description":"This is the FST Sport Shop Token.","website":"fst.sport.com","logo":null,"proofOfContract":null,"por":"DISABLE"}}}
   map: {"logo":["variables.input.logo"],"proofOfContract":["variables.input.proofOfContract"]}
   logo: (binary)
   proofOfContract: (binary)
   ```

 - Using cURL

    ```sh
    curl --request POST \
        --url https://test.fstk.io/api \
        --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLCmcKVPsOswosmXHUwMDExw6jCjlpLwoNcdTAwMTMtwrhGIiwiaWF0IjoxNTUyNDY5NDgxLCJleHAiOjE1NTI1NTU4ODEsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.DBb1_vGP4ueHjxMbCGu9M8DZmIhaaNiqi-GjtYBOa0ApG_GZ36QWLUjDF1TIJ6BoZojOZuYADA1XPyfNNhchIQ' \
        --cookie locale=en \
        --form 'operations={"query":"mutation IssueToken($input: IssueTokenInput!) {    issueToken(input: $input) {     transaction     hash    submitToken     }    }","variables":{"input":{"name":"FST Sport Shop Token","symbol":"FSST","totalSupply":"1000000000000000000000000000","price":{"numerator":"1","denominator":"4500"},"description":"This is the FST Sport Shop Token.","website":"fst.sport.com","logo":null,"proofOfContract":null,"por":"DISABLE"}}}' \
        --form 'map={"logo":["variables.input.logo"],"proofOfContract":["variables.input.proofOfContract"]}' \
        --form logo='@/path/to/the/image'
        --form proofOfContract='@/path/to/the/pdf'
    ```

 - Response

    ```json
    {
      "data": {
        "issueToken": {
          "transaction": {
            "nonce": "0x32",
            "gasPrice": "0x3b9aca00",
            "gas": "0x595dc5",
            "to": "0x7aeCC9c7dC65d15aEbF1e2cF7eb0fBBf38F49414",
            "value": "0x0",
            "data": "0x4000aea00000000000000000000000003705ef5d9c36509faa0d4ebc7e7e09dbda0ba08e0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002448d62333600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000012000000000000000000000000000000000000000000000000000000000000001600000000000000000000000000000000000000000033b2e3c9fd0803ce800000000000000000000000000000000000000000000000000000000000000000001a000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000001194000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000144653542053706f72742053686f7020546f6b656e0000000000000000000000000000000000000000000000000000000000000000000000000000000000000004465353540000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b376745337141536570734d356b6e67773731503446544d433832466f42673350397571355a74696e67594e4d35597a4b596a745763376462755846534361525341687776445247694c4c7831787937507a684c6967347147000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            "chainId": 42
          },
          "hash": "0x4788abb3f836ab93b27995bc2173a1d67200250c330c546f727d536e11c41612",
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLCmcKVPsOswosmXHUwMDExw6jCjlpLwoNcdTAwMTMtwrhGIiwiYWN0aW9uIjoiaXNzdWVUb2tlbiIsInR4IjoiK1FNS01vUTdtc29BZzFsZHhaUjY3TW5IM0dYUld1dng0czkrc1B1L09QU1VGSUM1QXVSQUFLNmdBQUFBQUFBQUFBQUFBQUFBTndYdlhadzJVSitxRFU2OGZuNEoyOW9Mb0k0QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBa1NOWWpNMkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUVnQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFXQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQU03TGp5ZjBJQTg2QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBR2dBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFFQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVJsQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQlJHVTFRZ1UzQnZjblFnVTJodmNDQlViMnRsYmdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFFUmxOVFZBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBWXk5cGNHWnpMM3BDZFhKTE4yZEZNM0ZCVTJWd2MwMDFhMjVuZHpjeFVEUkdWRTFET0RKR2IwSm5NMUE1ZFhFMVduUnBibWRaVGswMVdYcExXV3AwVjJNM1pHSjFXRVpUUTJGU1UwRm9kM1pFVWtkcFRFeDRNWGg1TjFCNmFFeHBaelJ4UndBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFDcUFnQT09IiwiaW5mbyI6eyJpc3N1ZXJJZCI6IsKZwpU-w6zCiyZcdTAwMTHDqMKOWkvCg1x1MDAxMy3CuEYiLCJuYW1lIjp7ImVuIjoiRlNUIFNwb3J0IFNob3AgVG9rZW4ifSwic3ltYm9sIjoiRlNTVCIsImRlY2ltYWxzIjoxOCwidG90YWxTdXBwbHkiOiIxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwIiwibWV0YWRhdGEiOiJBYlVmZUJBTDdiQnhlcXFoWGg4MTJGMlh6S0ZTQys3NFJkdXBPejNZdC9Vdk5EZzZIYU1XS2MwYnlPYThzRXAwQnpUUzY0bHRFY3RLeUNORlBkcHRYWDdJaWJ0aiIsImxpcXVpZCI6dHJ1ZSwiYXBwcm92ZUNoZWNraW5nIjpmYWxzZSwicHJpY2UiOnsibnVtZXJhdG9yIjoiMSIsImRlbm9taW5hdG9yIjoiNDUwMCJ9LCJ2ZW5kaWJsZSI6dHJ1ZSwid2Vic2l0ZSI6ImZzdC5zcG9ydC5jb20iLCJkZXNjcmlwdGlvbiI6eyJlbiI6IlRoaXMgaXMgdGhlIEZTVCBTcG9ydCBTaG9wIFRva2VuLiJ9fSwiaWF0IjoxNTUyNDY5NzE3LCJleHAiOjE1NTI0NzAzMTcsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.bqXSb-kOd8x6E5KlbLUzC6Jnxy8aSSRffYul4mx4Xbcgz3OJmh2vcN3swWUw7Tzl1xEztfa1sbSTj5CKW-xw6w"
        }
      }
    }
    ```

    > Issue token requires 1 FIL as issuing service charge.
  
    > In response's `transaction`, object will be used to sign payload, `submitToken` is also required for broadcasting signed transaction.

    > Please remember that response will vary after each call, please use the latest response for next steps.

    > e.g. Response like 
    ```json   
    { 
      "data": {
        "issueToken": null
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
      "nonce": "0x32",
      "gasPrice": "0x3b9aca00",
      "gas": "0x595dc5",
      "to": "0x7aeCC9c7dC65d15aEbF1e2cF7eb0fBBf38F49414",
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

   > Please refer to [Web3j sample codes](https://docs.web3j.io/transactions.html#signing-transactions).
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

    > `data` is the object from signing `transaction` with current user's private key. In another word, `signedTransaction` is the hex string.

    > `submitToken` is `submitToken` from Encode Ethereum Transaction. 

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

## Confirm the Smart Token

 > Please refer to `token` in `get me` (More details in Quick start [Chapter 2](../../Quick_Start/EN/02-Get_account_information.en.md)).
