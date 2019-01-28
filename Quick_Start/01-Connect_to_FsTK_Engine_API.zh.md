# Quick Start

## Table of Contents

 1. Prerequisite
 2. Connect to API
 3. Next step

## Prerequisite

 1. 請先於 `https://test.fstk.io` 或 `https://engine.fstk.io` 註冊帳號，並確認開通成功
    > (此兩個平台之帳戶資料沒有互通)

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

## Connect to API

 1. 取得 Access Web Token (JWT)
    > 請使用帳號與密碼，也請注意是連線至 `https://test.fstk.io` 或 `https://engine.fstk.io`，並且僅用 `https` 而請勿使用 `http`
  
    > 以下皆以 `https://test.fstk.io` 做為端點進行示範
    
    - Send a request via cURL

    ```sh
    curl -X POST \
      https://test.fstk.io/signin \
      -H 'Content-Type: application/json' \
      -H 'cache-control: no-cache' \
      -d '{ "identity": "your@email.com",
            "password": "yourpassword" }'
    ```

    - Send a request in JavaScript

    ```javascript
    var request = require("request");
    
    var options = { method: 'POST',
      url: 'https://test.fstk.io/signin',
      headers: 
       { 'cache-control': 'no-cache',
         'Content-Type': 'application/json' },
      body: { identity: 'your@email.com', password: 'yourpassword' },
      json: true };
    
    request(options, function (error, response, body) {
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

 2. 記錄並使用剛取得之 `access_token`
    > `access_token` 的格式為 `JSON Web Token` ，請見 [jwt.io](https://jwt.io)

    > `access_token` 有效期間為 24 hr

    接下來，除了登入的任一 API 請求，此 `access_token` 需要被夾帶在 http request header 中的 `authorization` header，例如:

    ```
    authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU0ODY0OTM4NiwiZXhwIjoxNTQ4NzM1Nzg2LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.gEKFuVuz4LOtGg_dughy7i2uzgNeKb1iS0LjM8IfyHkLFpsczTo9Wd4QQwiUfltErsFxf3k1UtdyLWX2z9QQ8w
    ```

## Next step

[Next step](./02-Get_account_information.zh.md)
