
# 

## GraphQL API

- Query String
  ```
{
  me {
    id
    campaigns {
      edges {
        node {
          id
          name
          description
          contractAddress
          isOpen
          stages {
            name
            description
            startTime
            endTime
            priceMultiplier {
              numerator
              denominator
            }
            cap
            isPrivate
            sold
          }
          transactionHash
          createdTime
        }
      }
    }
  }
}
  ```
- Query Variables

  ```
  
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
  _(sample)_
  ``` 
  {  
   "query":"{\n  me {\n    id\n    campaigns {\n      edges {\n        node {\n          id\n          name\n          description\n          contractAddress\n          isOpen\n          stages {\n            name\n            description\n            startTime\n            endTime\n            priceMultiplier {\n              numerator\n              denominator\n            }\n            cap\n            isPrivate\n            sold\n          }\n          transactionHash\n          createdTime\n        }\n      }\n    }\n  }\n}\n"
}
  ```
  The value of `query` in the body is a `String`. 


### Response
```
{
  "data": {
    "me": {
      "id": "VXNlckluZm86ZMKySsOYGioRw6nCujvCqyRfw65wAw==",
      "campaigns": {
        "edges": [
          {
            "node": {
              "id": "VG9rZW5DYW1wYWlnbjp/wqQLViLDhxHDqcK6O2/CjVgZw4ZC",
              "name": "This is my first bas",
              "description": "My first basic token campaign",
              "contractAddress": "0xc1149f52f0f1b690e2f20fddc897b015de878e38",
              "isOpen": false,
              "stages": [
                {
                  "name": "This is my first bas",
                  "description": "My first basic token campaign",
                  "startTime": "1548734400000",
                  "endTime": "1548907200000",
                  "priceMultiplier": {
                    "numerator": "1",
                    "denominator": "1"
                  },
                  "cap": "131452000000000000000000000",
                  "isPrivate": false,
                  "sold": "0"
                }
              ],
              "transactionHash": "0x547e1bfa9b4dfb98db141da0afe5aa980ff3c344de0fb6ead4081204694a284d",
              "createdTime": "1548657592000"
            }
          },
          {
            "node": {
              "id": "VG9rZW5DYW1wYWlnbjpwwq/DlRQiw4oRw6nCujtXw4xow4Nnwp0=",
              "name": "My first basic token",
              "description": "This is my first basic token campaign",
              "contractAddress": "0x62bfa3cb1d38d8739a042d2386b94cea12e2da6a",
              "isOpen": true,
              "stages": [
                {
                  "name": "My first basic token",
                  "description": "This is my first basic token campaign",
                  "startTime": "1548734400000",
                  "endTime": "1548907200000",
                  "priceMultiplier": {
                    "numerator": "1",
                    "denominator": "1"
                  },
                  "cap": "13145200000000000000000000",
                  "isPrivate": false,
                  "sold": "0"
                }
              ],
              "transactionHash": "0xa52beaf9b2f4666a4308ecf89fc3933c5dbb78a3edc05c0439871f1d1053f1ea",
              "createdTime": "1548658852000"
            }
          }
        ]
      }
    }
  }
}
```



## Parameters
### Request 


### Response
