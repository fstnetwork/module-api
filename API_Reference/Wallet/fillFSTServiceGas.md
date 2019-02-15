
# 

## GraphQL API

- Query String
  ```
  mutation fillGasTank($input: FillGasTankInput!) {
    fillGasTank(input: $input) {
      transaction
      hash
      metadata
      submitToken
      pendingTransactions
    }
  }
  ```
- Query Variables

  ```
  {
    "input":{
      "por":"DISABLE",
      "amount":"100000000000000000000"
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

## HTTP Request and Response
### Request

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
  ``` 
  {  
    "query":"\n      mutation fillGasTank($input: FillGasTankInput!) {\n        fillGasTank(input: $input) {\n          transaction\n          hash\n          metadata\n          submitToken\n        }\n      }\n    ",
    "variables":{  
        "input":{  
          "por":"DISABLE",
          "amount":"100000000000000000000"
        }
    }
  }
  ```
  The value of `query` in the body is a `String`. 


### Response
(for example)
```
{  
  "data":{  
    "fillGasTank":{  
      "transaction": {
        "nonce": "0x5",
        "gasPrice": "0x3b9aca00",
        "gas": "0x16068",
        "to": "0x3830f7Af866FAe79E4f6B277Be17593Bf96beE3b",
        "value": "0x0",
        "data": "0x4000aea000000000000000000000000056533b3052dd2bc92d2d11372427b9a7f3256eaa0000000000000000000000000000000000000000000000056bc75e2d631000000000000000000000000000000000000000000000000000000000000000000060000000000000000000000000000000000000000000000000000000000000004447d5f0be0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
        "chainId": 42
      },
      "hash":"0xb101bfd9560ded2c5f403aeb4b1a21ac4e86864608a666ca36c8144dec44c11e",
      "metadata":{  
        "fee":{  
          "type":"0x3830f7Af866FAe79E4f6B277Be17593Bf96beE3b",
          "amount":"0x0"
        }
      },
      "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjoxLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiZmlsbEdhc1RhbmsiLCJ0eCI6eyJ0b2tlbiI6IjB4MzgzMGY3QWY4NjZGQWU3OUU0ZjZCMjc3QmUxNzU5M0JmOTZiZUUzYiIsIm5vbmNlIjoiMHgwIiwiZmVlIjoiMHgwIiwiZ2FzQW1vdW50IjoiMHgxNjg4MSIsInRvIjoiMHg1NjUzM2IzMDUyZEQyQmM5MkQyRDExMzcyNDI3QjlhN0YzMjU2ZWFhIiwidmFsdWUiOiIweDU2YmM3NWUyZDYzMTAwMDAwIiwiZGF0YSI6IjB4NDdkNWYwYmUwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDU2YmM3NWUyZDYzMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwYjAyMzZmOWE2YTFjZDhjZjE3MjUxYTEzMDY1MWUwYmU4ZmIwMGUyNyIsIm1vZGUiOiIweDAiLCJyZWxheWVyIjoiMHgwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwIn0sImluZm8iOnt9LCJpYXQiOjE1NDc3OTY4NDMsImV4cCI6MTU0Nzc5NzQ0MywiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.xh3Xf49LdBgywBdNPX0xVmQcSVDARUHHgc0V9vMtFjnEWMXL0jOrAR0Cab-r2mpo0OKCKCtsYLbyN-WrymUETA",
      "pendingTransactions": "0"
    }
  }
}
```



## Parameters
### Request 
- **`por`** \<PORMode>
  - `Enable` or `Disable` \<enum>
  - Reuqired: Optional. Default is `DISABLE`.
- **`amount`** \<string>
  - Amount of FST to refill. The format is Decimaled Number.
  - Required: Yes.

### Response
- **`fillGasTank`** \<FillGasTankPayload>
  - **`transaction`** \<JSON>
    - UNSIGNED raw transaction format in Ethereum.
  - **`hash`** \<string>
    - PORMode `ENABLE`: Hash of the abi encode.
    - PORMode `DISABLE`: Hash of the RLP encode.
  - **`metadata`** \<JSON>
    - Metadata of the transaction.
  - **`submitToken`** \<string>
    - The value for [SubmitSignedTransaction API]().
  - **`pendingTransactions`** \<string>
    - Amount of your transactions which are still pending.
