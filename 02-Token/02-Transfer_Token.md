
# Transfer Token

## GraphQL API
- Query String
  ```
  mutation erc20Transfer($input: ERC20TransferInput) {
    erc20Transfer(input: $input) {
      pendingTransactions
      transaction
      submitToken
    }
  }
  ```

- Query Variables
  ```
  {  
    "input":{  
        id: "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
        to: "0x829BD824B016326A401d083B33D092293333A830",
        value: "1123456789123456789"
    }
  }
  ```

- HTTP Headers
  ```
  {
    "authorization": "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NTYyODAyLCJleHAiOjE1Mzg2NDkyMDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.sGfxYe16aRx_vmvzlRps_gcyTeQD-zsR5HCtjXQ3hYpQYjN1lOFkdpF0m4Yrrh8uHyWBYifqYUVHmkRej4-9gA"
  }
  ```

## HTTP Request
- URL
  - For development: `http://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`
- Method: `POST`
- Headers
  - accept: `application/json;`
  - content-type: `application/json;`
  - authorization: `Bearer [JWT Server-to-Server access token or JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
      ```

- Body:
  ```
  {  
    "query": "\n    mutation($input: ERC20TransferInput!) {\n      erc20Transfer(input: $input) {\n        pendingTransactions\n        transaction\n        submitToken\n      }\n    }\n    ",
    "variables": {  
      "input": {  
         "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
         "to": "0x829BD824B016326A401d083B33D092293333A830",
         "value": "1123456789123456789"
      }
    }
  }
  ```

  The value of `query` in the body is a `String`. 

  The `id` in `variables` is the Token `id`.  
  The `to` in `variables` is the Token recevier's address.  
  The `value` in `variables` is the Token value to be transferred. **This string in Token transfer represents the decimaled number string**. For example, because the Tokens have the decimals of 18, the value `"1123456789123456789"` means `1.123456789123456789` for human (**human number string**).


## HTTP Response
```
{
  "data": {
    "erc20Transfer": {
      "pendingTransactions": "0",
      "transaction": {
        "nonce": "0xfb",
        "gasPrice": "0x3b9aca00",
        "gas": "0xf526",
        "to": "0x3830f7Af866FAe79E4f6B277Be17593Bf96beE3b",
        "value": "0x0",
        "data": "0xa9059cbb000000000000000000000000829bd824b016326a401d083b33d092293333a8300000000000000000000000000000000000000000000000000f9751ff54345f15",
        "chainId": 42
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJkYXRhIjoicVFXY3V3QUFBQUFBQUFBQUFBQUFBSUtiMkNTd0ZqSnFRQjBJT3pQUWtpa3pNNmd3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFENWRSLzFRMFh4VT0iLCJpYXQiOjE1MzQ2OTMwNzIsImV4cCI6MTUzNDY5MzY3MiwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.nGItBL3I7HLifsa3HZUIyPnX7NiH9YTgx2OcaZtfTV5xEUbJDpDQ0DJIWKgKl5M7f29guC7428iFPkyDTKFVXQ"
    }
  }
}
``` 


This API responses a ABI-Encoded transaction for the Token transfer, and the end-user (the sender, the requester) has to sign the `transaction` object in the response via [ETH Key lib JS](https://github.com/funderstoken/eth-key-lib-js), then send the signed transaction and the `submitToken` to [SubmitSignedTransaction API](https://github.com/funderstoken/module-api/tree/master/SubmitSignedTransaction).
