
# Get Smart Token Holders

You are able to transfer eth via this API.

## GraphQL API

  - Query String
  ```
  mutation transferETH ($input: EthTransferInput!) {
    ethTransfer(input: $input) {
      transaction
      hash
      metadata
      submitToken
    }
  }    
  ```
  - Query Variables

  ```
  {
    "input": {
      "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
      "value": "10000000000000000000"
    }
  }
  ```
- HTTP Headers 
  ```
  {
    "accept": "application/json",
    "content-type": "application/json",
    "authorization": "bearer [JWT Web-to-Server access token]"
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
    "query": "mutation transferETH ($input: EthTransferInput!) { ethTransfer(input: $input) { transaction hash metadata submitToken } }",
    "variables": {
      "input": {
        "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
        "value": "10000000000000000000"
      }
    }
  }
  ```
  
  The value of `query` in the body is a `String`. 
  

## Response
_(sample)_
```
{
  "data": {
    "ethTransfer": {
      "transaction": {
        "nonce": "0x9ff",
        "gasPrice": "0x3b9aca00",
        "gas": "0x5208",
        "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
        "value": "0x38d7ea4c68000",
        "data": "0x",
        "chainId": 42
      },
      "hash": "0x531b4800a2ed670dbc95519a6caa17c16d151617984ea9d6acf31d0efc2bc219",
      "metadata": {
        "fee": {
          "type": "ETH",
          "amount": "21000000000000"
        }
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJZw4_ChiZcdTAwMWHDrVx1MDAxMcOpwro7XHUwMDFmNlx1MDAwNVx1MDAxMMKawpoiLCJhY3Rpb24iOiJldGhUcmFuc2ZlciIsInR4IjoiN0lJSi80UTdtc29BZ2xJSWxFejBEYVNmbllLQmtXSEYyNGI4dEpiZS9yTmRod09OZnFUR2dBQ0FLb0NBIiwiaW5mbyI6e30sImlhdCI6MTU0ODc0NDQ5NywiZXhwIjoxNTQ4NzQ1MDk3LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.dhk8B1xYmNs-qt14p2aYLbwlJVjrG8n_aA0vVQmzo6qT2BaoSdnQsyrbiMOkuxxqCUW1LsPdNVz-_AUHKdOotA"
    }
  }
}
```

## Parameters
### Request 
- **`to`** \<string>
  - to address 
- **`value`** \<string>
  - value of 

### Response
- **`ethTransfer`** \<EthTransferPayload!>
  - **`transaction`** \<JSON>
    - **`nonce`** \<string>
    - **`gasPrice`** \<string>
    - **`gas`** \<string>
    - **`to`** \<string>
    - **`value`** \<string>
    - **`data`** \<string>
    - **`chainId`** \<Int>
  - **`hash`** \<string>
  - **`metadata`** \<JSON>
    - **`fee`** \<JSON>
      - **`type`** \<string>
      - **`amount`** \<string>
  - **`submitToken`** \<string>