
# Transfer Smart Token
You are able to transfer Smart Token via this API.

## GraphQL API

- Query String
  ```
  mutation transferFIL ($input: ERC20TransferInput!) {
    erc20Transfer(input:$input) {
      transaction
      hash
      submitToken
    }
  }
  ```
- Query Variables
  ```
  {
    "input": {
      "id": "VG9rZW46wrRGCwoaw68Rw6nCujsXbMKew7Bzwqc=",
      "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
      "value": "100000000000000000000",
      "por": "DISABLE"
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
    "query": "mutation transferFIL ($input: ERC20TransferInput!) { erc20Transfer(input:$input) { transaction hash submitToken } }",
    "variables": {
      "input": {
        "id": "VG9rZW46wrRGCwoaw68Rw6nCujsXbMKew7Bzwqc=",
        "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
        "value": "100000000000000000000",
        "por": "DISABLE"
      }
    }
  }
  ```
  
  The value of `query` in the body is a `String`. 
  
### Response
```
{
  "data": {
    "erc20Transfer": {
      "transaction": {
        "nonce": "0x9ff",
        "gasPrice": "0x3b9aca00",
        "gas": "0xac72",
        "to": "0x4711e92Ad968A6488500bC5dDe2A48eE17743AB1",
        "value": "0x0",
        "data": "0xa9059cbb0000000000000000000000004cf40da49f9d82819161c5db86fcb496defeb35d0000000000000000000000000000000000000000000000056bc75e2d63100000",
        "chainId": 42
      },
      "hash": "0xa3f076b85ab4677f815b733c9d9fd583638d2ad241b62ac798e72eff01b2f20a",
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJZw4_ChiZcdTAwMWHDrVx1MDAxMcOpwro7XHUwMDFmNlx1MDAwNVx1MDAxMMKawpoiLCJhY3Rpb24iOiJlcmMyMFRyYW5zZmVyIiwidHgiOiIrR3FDQ2YrRU81cktBSUtzY3BSSEVla3EyV2ltU0lVQXZGM2VLa2p1RjNRNnNZQzRSS2tGbkxzQUFBQUFBQUFBQUFBQUFBQk05QTJrbjUyQ2daRmh4ZHVHL0xTVzN2NnpYUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJXdkhYaTFqRUFBQUtvQ0EiLCJpbmZvIjp7fSwiaWF0IjoxNTQ4NzQ3MDA3LCJleHAiOjE1NDg3NDc2MDcsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.D3F6pzHSCfHiEe7vx6KkmdI6vZ3S8jl5McQrdbHR5xLQQm6sTYekomApIM4tJgu1XQ-DH-n-Yp9zvaklCILWMA"
    }
  }
}
```

## Parameters
### Request 
- **`id`** \<ID!>
  - ID of the Smart Token which is to be transferred. ID is a global identifier.
- **`to`** \<String!>
  - Address of the receiver.
- **`value`** \<String!>
  - Amount of Smart Token to be transferred. The format is Decimaled Number.
- **`por`** \<PORMode>
  - `ENABLE` or `DISABLE` \<enum>
  - Default is `DISABLE`.

### Response
- **`erc20Transfer`** \<ERC20TransferPayload!>
  - **`transaction`** \<JSON>
    - UNSIGNED raw transaction format in Ethereum.
  - **`hash`** \<String>
    - PORMode `ENABLE`: Hash of the abi encode.
    - PORMode `DISABLE`: Hash of the RLP encode.
  - **`submitToken`** \<String>
    - The value for [SubmitSignedTransaction API]().