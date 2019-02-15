
# Transfer FIL

You are able to transfer FIL via this API.

## GraphQL API

- Query String
  ```
  mutation transferFIL ($input: ERC20TransferInput!) {
    erc20Transfer(input:$input) {
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
      "id": "Vm91Y2hlcjrCkCTCrsKAwp8HEcOnwoACAAAAAAAA",
      "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
      "value": "1",
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
    "query": "mutation transferFIL ($input: ERC20TransferInput!) { erc20Transfer(input:$input) { transaction hash metadata submitToken } }",
    "variables": {
      "input": {
        "id": "Vm91Y2hlcjrCkCTCrsKAwp8HEcOnwoACAAAAAAAA",
        "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
        "value": "1",
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
        "gas": "0xf376",
        "to": "0x7aeCC9c7dC65d15aEbF1e2cF7eb0fBBf38F49414",
        "value": "0x0",
        "data": "0xa9059cbb0000000000000000000000004cf40da49f9d82819161c5db86fcb496defeb35d0000000000000000000000000000000000000000000000000000000000000001",
        "chainId": 42
      },
      "hash": "0x1991e1c0331df5f30313132c7d4cff7835fce123d7984659a180642c250a6a5d",
      "metadata": {
        "fee": {
          "type": "ETH",
          "amount": "62326000000000"
        }
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJZw4_ChiZcdTAwMWHDrVx1MDAxMcOpwro7XHUwMDFmNlx1MDAwNVx1MDAxMMKawpoiLCJhY3Rpb24iOiJlcmMyMFRyYW5zZmVyIiwidHgiOiIrR3FDQ2YrRU81cktBSUx6ZHBSNjdNbkgzR1hSV3V2eDRzOStzUHUvT1BTVUZJQzRSS2tGbkxzQUFBQUFBQUFBQUFBQUFBQk05QTJrbjUyQ2daRmh4ZHVHL0xTVzN2NnpYUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQktvQ0EiLCJpbmZvIjp7fSwiaWF0IjoxNTQ4NzQ2MzEyLCJleHAiOjE1NDg3NDY5MTIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.wwdWE00GJ1fyCYzBEFrgSy0xKvK8BWSO7-YAXV2HuX9Q0aU2Xoywo7dzClYfBPPyrZYWiARgmmtd5FGiz9LaGQ"
    }
  }
}
```

## Parameters
### Request 
- **`id`** \<string>
  - ID of the FIL Token which is to be transferred. ID is a global identifier.
  - Required: Yes
- **`to`** \<string>
  - Address of the receiver.
  - Required: Yes
- **`value`** \<string>
  - Amount of FIL token to be transferred. The format is Decimaled Number.
  - Required: Yes
- **`por`** \<PORMode>
  - `ENABLE` or `DISABLE` \<enum>
  - Required: Optional. Default is `DISABLE`.

### Response
- **`erc20Transfer`** \<ERC20TransferPayload>
  - **`transaction`** \<JSON>
    - UNSIGNED raw transaction format in Ethereum.
  - **`hash`** \<string>
    - PORMode `ENABLE`: Hash of the abi encode.
    - PORMode `DISABLE`: Hash of the RLP encode.
  - **`metadata`** \<JSON>
    - Metadata of the transaction.
  - **`submitToken`** \<string>
    - The value for [SubmitSignedTransaction API]().