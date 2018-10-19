
# ERC721 Mint

## GraphQL API

- Query String
  ```
  mutation($input: ERC721MintInput!){
    erc721Mint(input: $input){
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
      "contractAddress" : "0xa818e5f90ac4de5a15915266822d931050135cf3",
      "to" : "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
      "voucherId": "101",
      "metadata": "this is voucher metadata"
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
  - For development: `https://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`
- Method: `POST`
- Headers
  - accept: `application/json`
  - content-type: `application/json` 
  - authorization
    ```
    Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
    ```

- Body
  ``` 
  {  
    "query":"\n    mutation($input: ERC721MintInput!) {\n      erc721Mint(input: $input) {\n        pendingTransactions\n        transaction\n        submitToken\n      }\n    }\n    ",
    "variables":{  
        "input":{  
          "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
          "to":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
          "voucherId":"101",
          "metadata":"this is voucher metadata"
        }
    }
  }
  ```

## HTTP Response
```
{  
   "data":{  
      "erc721Mint":{  
         "pendingTransactions":"0",
         "transaction":{  
            "nonce":"0xc2",
            "gasPrice":"0x3b9aca00",
            "gas":"0x1e11b",
            "to":"0xa818E5F90aC4de5A15915266822d931050135cf3",
            "value":"0x0",
            "data":"0xd3fc9864000000000000000000000000cb69b95f72d1b1f373d956d95f216492a7ea26c8000000000000000000000000000000000000000000000000000000000007fd0c000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000000174e6f205065692c204e6f2046756e64657273546f6b656e000000000000000000",
            "chainId":42
         },
         "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiYWN0aW9uIjoiZXJjNzIxTWludCIsImRhdGEiOiIwL3lZWkFBQUFBQUFBQUFBQUFBQUFNdHB1Vjl5MGJIemM5bFcyVjhoWkpLbjZpYklBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFIL1F3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFZQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBWFRtOGdVR1ZwTENCT2J5QkdkVzVrWlhKelZHOXJaVzRBQUFBQUFBQUFBQUE9IiwiaWF0IjoxNTM4NzE5MTQyLCJleHAiOjE1Mzg3MTk3NDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.vQBHZpLkkiq3VvdAHVlAsfcEvuuVwgtY3cxx4iaqowrtSTPmmnAn2UL5S9ME7w7WjPta0zG95maGMcJbxZ5Vyg"
      }
   }
}
```
