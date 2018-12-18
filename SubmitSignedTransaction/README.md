

# Submit signed transaction

## GraphQL API

- Query String
  ```
  mutation SubmitTransaction($input: SubmitTransactionInput!) {
    submitTransaction(input: $input) {
      transactionHash
    }
  }
  ```
- Query Variables

  ```
  {  
    input: {
      data: "0xf8a981fb843b9aca0082f526943830f7af866fae79e4f6b277be17593bf96bee3b80b844a9059cbb000000000000000000000000829bd824b016326a401d083b33d092293333a8300000000000000000000000000000000000000000000000000f9751ff54345f1577a0a97d10f0b8aaf216482f9e0131f9da7ba508ed644f2b1b2a8f414e1f55fb1c47a03ec08d1592e6d86afe7def1a00f20ae3c8642ea5543cfed9cbd4a322ff22b987"
      submitToken: "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJkYXRhIjoicVFXY3V3QUFBQUFBQUFBQUFBQUFBSUtiMkNTd0ZqSnFRQjBJT3pQUWtpa3pNNmd3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFENWRSLzFRMFh4VT0iLCJpYXQiOjE1MzQ2OTMwNzIsImV4cCI6MTUzNDY5MzY3MiwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.nGItBL3I7HLifsa3HZUIyPnX7NiH9YTgx2OcaZtfTV5xEUbJDpDQ0DJIWKgKl5M7f29guC7428iFPkyDTKFVXQ"
    }
  }
  ```
- HTTP Headers 
  ```
  {
    "accept": "application/json",
    "content-type": "application/json",
    "authorization": "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NTYyODAyLCJleHAiOjE1Mzg2NDkyMDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.sGfxYe16aRx_vmvzlRps_gcyTeQD-zsR5HCtjXQ3hYpQYjN1lOFkdpF0m4Yrrh8uHyWBYifqYUVHmkRej4-9gA"
  }
  ```
## HTTP Request

- URL
  - For development: `https://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`

- Method: `POST`

- Headers
  - accept: `application/json`
  - content-type: `application/json` 
  - authorization: `Bearer [JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
      ```

- Body

  (for example)
  ``` 
  {  
    "query":"mutation SubmitTransaction($input: SubmitTransactionInput!) {\n      submitTransaction(input: $input) {\n        transactionHash\n        }\n    }",
    "variables":{
      "input":{
        "data":"0xf8a981fb843b9aca0082f526943830f7af866fae79e4f6b277be17593bf96bee3b80b844a9059cbb000000000000000000000000829bd824b016326a401d083b33d092293333a8300000000000000000000000000000000000000000000000000f9751ff54345f1577a0a97d10f0b8aaf216482f9e0131f9da7ba508ed644f2b1b2a8f414e1f55fb1c47a03ec08d1592e6d86afe7def1a00f20ae3c8642ea5543cfed9cbd4a322ff22b987",
        "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJkYXRhIjoicVFXY3V3QUFBQUFBQUFBQUFBQUFBSUtiMkNTd0ZqSnFRQjBJT3pQUWtpa3pNNmd3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFENWRSLzFRMFh4VT0iLCJpYXQiOjE1MzQ2OTMwNzIsImV4cCI6MTUzNDY5MzY3MiwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.nGItBL3I7HLifsa3HZUIyPnX7NiH9YTgx2OcaZtfTV5xEUbJDpDQ0DJIWKgKl5M7f29guC7428iFPkyDTKFVXQ"
      }
    }
  }
  ```
  The value of `query` in the body is a `String`. 
  
  The `data` in `variables.input` is the **hex string** of signed transaction buffer. The `submitToken` in `variables.input` must be identical to the `submitToken` in previous ABI-Encoding API's response.
  

## HTTP Response
(for example)
```
{
  "data": {
    "submitTransaction": {
      "transactionHash": "0xa93f56fe55b4f7b011ef66f3ca3fed6d611bf5a780e0b8310cf56c1114cb8cc0"
    }
  }
}
```
The `transactionHash` is the txhash of the transaction, and can be checked and verified in any Ethereum explorer such as [etherscan.io](https://etherscan.io).






