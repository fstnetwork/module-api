
# Cancel Smart Token Campaign

## GraphQL API

- Query String
  ```
  mutation CloseCampaign($input: CloseCampaignInput!) {
    closeCampaign(input: $input) {
      transaction
      metadata          
      hash          
      submitToken        
    }      
  }
  ```
- Query Variables
  ```
  {  
      "input":{  
         "id":"VG9rZW5DYW1wYWlnbjpwwq/DlRQiw4oRw6nCujtXw4xow4Nnwp0=",
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
   "query":"\n      mutation CloseCampaign($input: CloseCampaignInput!) {\n        closeCampaign(input: $input) {\n          metadata\n          hash          submitToken        }      }",
   "variables":{  
      "input":{  
         "id":"VG9rZW5DYW1wYWlnbjp/wqQLViLDhxHDqcK6O2/CjVgZw4ZC",
         "por":"DISABLE"
      }
   }
  }
  ```
  The value of `query` in the body is a `String`. 


### Response
```
{
  "data": {
    "closeCampaign": {
      "transaction": {
        "nonce": "0x5",
        "gasPrice": "0x3b9aca00",
        "gas": "0x10ef6",
        "to": "0x155dea084AD150D80E56B7746dD5503fCd5dfA77",
        "value": "0x0",
        "data": "0x4000aea000000000000000000000000062bfa3cb1d38d8739a042d2386b94cea12e2da6a000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000600000000000000000000000000000000000000000000000000000000000000044d3d202fd0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
        "chainId": 42
      },
      "metadata": {
        "fee": {
          "type": "ETH",
          "amount": "69366000000000"
        }
      },
      "hash": "0x5f69c608ccae0ccd9c60d58124e39f1719afa61ef9786b13e5fb2c19f5c6e5e8",
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiY2xvc2VUb2tlbkNhbXBhaWduIiwidHgiOiIrUUVKQllRN21zb0Fnd0VPOXBRVlhlb0lTdEZRMkE1V3QzUnQxVkEvelYzNmQ0QzQ1RUFBcnFBQUFBQUFBQUFBQUFBQUFBQml2NlBMSFRqWWM1b0VMU09HdVV6cUV1TGFhZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBR0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVJOUFNBdjBBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUNxQWdBPT0iLCJpbmZvIjp7fSwiaWF0IjoxNTQ4NjU5MjA3LCJleHAiOjE1NDg2NTk4MDcsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.kN917XiPHMGjka_Z-fi46mSytVRYAJFM3DkG_Zb_NxaC_vWnNZjooN61Vw1cFt5BVotRJ5DjJrmWCAYVluoFPQ"
    }
  }
}
```

## Parameters
### Request 
  - **`id`** \<ID!>
    - Smart Token campaign ID.
  - **`por`** \<PORMode>
    - `ENABLE` or `DISABLE` \<enum>
    - Default is `DISABLE`.

### Response
  - **`transaction`** \<JSON>
    - UNSIGNED raw transaction format in Ethereum.
  - **`submitToken`** \<String!>
    - The value for [SubmitSignedTransaction API]().
  - **`hash`** \<String>
    - PORMode `ENABLE`: Hash of the abi encode.
    - PORMode `DISABLE`: Hash of the RLP encode.
  - **`metadata`** \<JSON>
    - Metadata of the transaction.
