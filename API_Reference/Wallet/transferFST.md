
# Transfer FST

You are able to transfer FST via this API.

## GraphQL API

  - Query String
  ```
  mutation transferFST ($input: ERC20TransferInput!) {
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
      "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
      "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
      "value": "10000000000000000000",
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
    "query": "mutation transferFST ($input: ERC20TransferInput!) { erc20Transfer(input:$input) { transaction hash metadata submitToken } }",
    "variables": {
      "input": {
        "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
        "to": "0x4cf40da49f9d82819161C5DB86fcB496dEfeb35d",
        "value": "10000000000000000000",
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
        "gas": "0xae3d",
        "to": "0x3830f7Af866FAe79E4f6B277Be17593Bf96beE3b",
        "value": "0x0",
        "data": "0xa9059cbb0000000000000000000000004cf40da49f9d82819161c5db86fcb496defeb35d0000000000000000000000000000000000000000000000008ac7230489e80000",
        "chainId": 42
      },
      "hash": "0x6b6c263ce5591207eceea589e25120232f030f1c05d90f66d09596bb26d89e19",
      "metadata": {
        "fee": {
          "type": "ETH",
          "amount": "44605000000000"
        }
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJZw4_ChiZcdTAwMWHDrVx1MDAxMcOpwro7XHUwMDFmNlx1MDAwNVx1MDAxMMKawpoiLCJhY3Rpb24iOiJlcmMyMFRyYW5zZmVyIiwidHgiOiIrR3FDQ2YrRU81cktBSUt1UFpRNE1QZXZobSt1ZWVUMnNuZStGMWs3K1d2dU80QzRSS2tGbkxzQUFBQUFBQUFBQUFBQUFBQk05QTJrbjUyQ2daRmh4ZHVHL0xTVzN2NnpYUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFJckhJd1NKNkFBQUtvQ0EiLCJpbmZvIjp7fSwiaWF0IjoxNTQ4NzQ2MTQwLCJleHAiOjE1NDg3NDY3NDAsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.lGDF3QUEz9waIydZ5ZW0b8tWsGrjfNADfxXp4z5lQ94aVeAHm973NW0_QBeDvJEKxqFJG-5CwEfmGQRJqgSPKg"
    }
  }
}
```

## Parameters
### Request 
- **`id`** \<string>
  - ID of the FST Token which is to be transferred. ID is a global identifier.
  - Required: Yes
- **`to`** \<string>
  - Receiver address.
  - Requried: Yes
- **`value`** \<string>
  - Amount of FST token to be transferred. The format is Decimaled Number.
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