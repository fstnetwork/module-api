
# Submit the Signed Transaction
You are able to submit the SIGNED transaction via this API and FST Network will broadcast your transaction to the Ethereum Network.

## GraphQL API

  - Query String
  ```
  mutation submitSignedTransaction($input: SubmitTransactionInput!) {
    submitTransaction(input: $input) {
      transactionHash
    }
  } 
  ```
  - Query Variables

  ```
  {
    "input": {
      "data": "0x00",
      "submitToken": "0x00"
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
    "query": "mutation submitSignedTransaction($input: SubmitTransactionInput!) { submitTransaction(input: $input) { transactionHash } }",
    "variables": {
      "input": {
        "data": "",
        "submitToken": ""
      }
    }
  }
  ```
  
  The value of `query` in the body is a `String`. 
  

### Response
```
{
  "data": {
    "submitTransaction": {
      "transactionHash": ""
    }
  }
}
```

## Parameters
### Request 
- **`data`** \<string>
  - data after signed 
- **`submitToken`** \<string>
  - submitToken from the previous api

### Response
- **`submitTransaction`** \<SubmitTransactionPayload!>
  - **`transactionHash`** \<string>
    - transaction hash of the submit transaction
