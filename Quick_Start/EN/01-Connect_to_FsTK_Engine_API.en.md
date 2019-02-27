# Connect to FsTK Engine API

> In this chapter, you will understand how to connect FsTK APIs with right authentication.

## Table of Contents

 1. Prerequisite
 2. Authentication / Authorization
 3. Next step

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

## Authentication / Authorization

 1. Retrieve Access Web Token (JWT)
    > Please use accounts & passwords on `https://test.fstk.io` or `https://engine.fstk.io`. Notice that it is `https`, not `http`.
  
    > Hereinafter we will use `https://test.fstk.io` as endpoints.
    
    - Using cURL

    ```sh
    curl -X POST \
      https://test.fstk.io/signin \
      -H 'Content-Type: application/json' \
      -H 'cache-control: no-cache' \
      -d '{ "identity": "your@email.com",
            "password": "yourpassword" }'
    ```

    - Using JavaScript

    ```javascript
    var request = require("request");
    
    var options = {
      method: "POST",
      url: "https://test.fstk.io/signin",
      headers: { "cache-control": "no-cache", "Content-Type": "application/json" },
      body: { identity: "your@email.com", password: "yourpassword" },
      json: true
    };
    
    request(options, function(error, response, body) {
      if (error) throw new Error(error);
    
      console.log(body);
    });
    ```

    - Response

    ```json
    {
        "status": "success",
        "result": {
            "access_token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU0ODY0OTM4NiwiZXhwIjoxNTQ4NzM1Nzg2LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.gEKFuVuz4LOtGg_dughy7i2uzgNeKb1iS0LjM8IfyHkLFpsczTo9Wd4QQwiUfltErsFxf3k1UtdyLWX2z9QQ8w"
        }
    }
    ```

 2. Store and use `access_token` from Response
    > Format of `access_token` is `JSON Web Token`, please refer to [jwt.io](https://jwt.io)

    > `access_token` will expire after 24 hours.

    In the future, except API for sign-ins, please assign `access_token` in `authorization` within http request header. 

    ```http
    authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU0ODY0OTM4NiwiZXhwIjoxNTQ4NzM1Nzg2LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.gEKFuVuz4LOtGg_dughy7i2uzgNeKb1iS0LjM8IfyHkLFpsczTo9Wd4QQwiUfltErsFxf3k1UtdyLWX2z9QQ8w
    ```

    > Notice the string, `Bearer `, must be added before `access_token` then authorization header will work.

## Next step

[Next step](./02-Get_account_information.en.md)
