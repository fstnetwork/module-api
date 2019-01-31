
# Finalize Airdrop

## GraphQL API
You are able to claim the budget remaining, get the asset you sold, and close the airdrop via this API.

- Query String
  ```
  mutation claimAirdropMission($input: ClaimAirdropMissionInput!) {
    claimAirdropMission(input: $input) {
      pendingTransactions
      transaction
      submitToken
      hash
      metadata
    }
  }
  ```
- Query Variables

  ```
  {
    "input":{
      "missionId":"QWlyZHJvcE1pc3Npb246OTE=",
      "por":"DISABLE"
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
    "query":"\n      mutation claimAirdropMission($input: ClaimAirdropMissionInput!) {\n        claimAirdropMission(input: $input){\n          pendingTransactions\n          transaction\n          submitToken\n      hash\n      metadata\n        }\n      }\n    ",
    "variables":{
        "input":{
          "missionId":"QWlyZHJvcE1pc3Npb246OTE=",
          "por":"DISABLE"
        }
    }
  }
  ```

  The value of `query` in the body is a `String`.


## Response
```
{
  "data":{
    "claimAirdropMission":{
      "pendingTransactions":"0",
      "transaction":{
        "nonce":"0xef",
        "gasPrice":"0x3b9aca00",
        "gas":"0xdb02",
        "to":"0xc3a86fB0204dCB008372774960400B65Fec3a6b2",
        "value":"0x0",
        "data":"0x4677866f000000000000000000000000000000000000000000000000000000000000005b",
        "chainId":42
         },
      "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiYWN0aW9uIjoic3RvcEFpcmRyb3BNaXNzaW9uIiwiZGF0YSI6IlJuZUdid0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCYiIsImluZm8iOnsibWlzc2lvbklkIjoiOTEifSwiaWF0IjoxNTQzODIwMTI3LCJleHAiOjE1NDM4MjA3MjcsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.zyHfjPlcIzvwIbF77C4moV-kvojhlls1Hkzda23iE9eN89d2d1ONQgcZIOkcMuGIHXr1a5TulkC7C5S_t54vzQ",
      "hash":"0xab3fa519a3aefedbgryja3287eceae3c8f779cc43ee82b01d40fa1aeb5ad9f69",
      "metadata":{
        "fee": {
          "type": "ETH",
          "amount": "223624000000000"
        }
      }
    }
  }
}
```

This API responses a ABI-Encoded transaction for creating the basic voucher campaign, and the end-user (the sender, the requester) has to sign the `transaction` object in the response via [ETH Key lib JS](https://github.com/fstnetwork/eth-key-lib-js), then send the signed transaction and the `submitToken` to [SubmitSignedTransaction API]().

## Parameters
### Request
  - **`missionId`** \<string>
    - Airdrop mission ID. `missionID` is able to get by [getAirdropMissionInfo API]().
    - Required: Yes
  - **`por`** \<PORMode>
    - `ENABLE` or `DISABLE` \<enum>
    - Required: Optional. Default is `DISABLE`.

### Response
  - **`claimAirdropMission`** \<claimAirdropMissionPayload>
    - **`pendingTransactions`** \<string>
      - Amount of your transactions which are still pending.
    - **`transaction`** \<JSON>
      - UNSIGNED raw transaction format in Ethereum.
    - **`submitToken`** \<string>
      - The value for [SubmitSignedTransaction API]().
    - **`hash`** \<string>
      - PORMode `ENABLE`: Hash of the abi encode.
      - PORMode `DISABLE`: Hash of the RLP encode.
    - **`metadata`** \<JSON>
      - Metadata of the transaction.