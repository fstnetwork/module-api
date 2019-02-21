
# Cancel Smart Voucher Campaign

## GraphQL API

- Query String
  ```
  mutation CloseCampaign($input: CloseCampaignInput!) {
    closeCampaign(input: $input) {
      transaction 
      hash          
      submitToken        
    }      
  }
  ```
- Query Variables

  ```
  {  
      "input":{  
         "id":"Vm91Y2hlckNhbXBhaWduOsOaK8OWw64iw4wRw6nCujsfw6rDlR/Cu8Of",
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
   "query":"      mutation CloseCampaign($input: CloseCampaignInput!) {        closeCampaign(input: $input) {          hash          submitToken        }      }",
   "variables":{  
      "input":{  
         "id":"Vm91Y2hlckNhbXBhaWduOsOaK8OWw64iw4wRw6nCujsfw6rDlR/Cu8Of",
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
        "nonce": "0x6",
        "gasPrice": "0x3b9aca00",
        "gas": "0x11da8",
        "to": "0xc9961776DC4Cc72422a551dd616F5b53fD1BDD78",
        "value": "0x0",
        "data": "0x4000aea00000000000000000000000007bdfada0608449e36f812c861ab19670286e1b1f000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000600000000000000000000000000000000000000000000000000000000000000044d3d202fd0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
        "chainId": 42
      },
      "hash": "0x6109a523400ceb436c0072365d8f5bdf5fe89be50aedf9d84702b3e302ab71d6",
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiJkwrJKw5hcdTAwMWEqXHUwMDExw6nCujvCqyRfw65wXHUwMDAzIiwiYWN0aW9uIjoiY2xvc2VWb3VjaGVyQ2FtcGFpZ24iLCJ0eCI6IitRRUpCWVE3bXNvQWd3RWRxSlRKbGhkMjNFekhKQ0tsVWQxaGIxdFQvUnZkZUlDNDVFQUFycUFBQUFBQUFBQUFBQUFBQUFCNzM2MmdZSVJKNDIrQkxJWWFzWlp3S0c0Ykh3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUk5QU0F2MEFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQ3FBZ0E9PSIsImluZm8iOnt9LCJpYXQiOjE1NDg2NjAwMjksImV4cCI6MTU0ODY2MDYyOSwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.q9qbUpFYPXWHxcxvv0EYAOdd95FXJsGp3wa4uxFnjZGH5JrYSkL6AeZLb1n4dwZF98iVX5FTWXP-RHpJ5PPL2Q"
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

