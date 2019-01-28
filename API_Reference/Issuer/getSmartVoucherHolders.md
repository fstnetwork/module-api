
# 

## GraphQL API

- Query String
  ```
  {
    me {
      token {
        vouchers {
         edges {
           node {
             holders {
                edges {
                  node {
                    address
                   balance
                 }
                }
              }
            }
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
   "query":"{\n  me {\n    token {\n      vouchers {\n        edges {\n          node {\n            holders {\n              edges {\n                node {\n                  address\n                  balance\n                }\n              }\n            }\n          }\n        }\n      }\n    }\n  }\n}\n"
  }
  ```
  The value of `query` in the body is a `String`. 


### Response
(for example)
```
{
  "data": {
    "me": {
      "token": {
        "vouchers": {
          "edges": [
            {
              "node": {
                "holders": {
                  "edges": [
                    {
                      "node": {
                        "address": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "balance": "9467"
                      }
                    },
                    {
                      "node": {
                        "address": "0x7bdfada0608449e36f812c861ab19670286e1b1f",
                        "balance": "20"
                      }
                    }
                  ]
                }
              }
            }
          ]
        }
      }
    }
  }
}
```



## Parameters
### Request 


### Response
