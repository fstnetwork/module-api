
# Get Smart Voucher(which you published) Transfer History

## GraphQL API

- Query String
  ```
  {
    me {
      token {
        vouchers {
          edges {
            node {
              id
              transfers {
                edges {
                  node {
                    from
                    to
                    value
                    transaction
                    timestamp
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
   "query":"{\n  me {\n    token {\n      vouchers {\n        edges {\n          node {\n            id\n            transfers {\n              edges {\n                node {\n                  from\n                  to\n                  value\n                  transaction\n                  timestamp\n                }\n              }\n            }\n          }\n        }\n      }\n    }\n  }\n}\n"
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
                "id": "Vm91Y2hlcjrDq30Kw7Qaw7ERw6nCujvCh2sfw5NbNg==",
                "transfers": {
                  "edges": [
                    {
                      "node": {
                        "from": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "to": "0x802bc18573bfa1322c829f24fe171ba44fd97a4c",
                        "value": "20",
                        "transaction": "0xe2c1c97eb124238a7fcc1d629fef9a6242830a29f3ac7a9bb62d868ccc6eaede",
                        "timestamp": "1548659896000"
                      }
                    },
                    {
                      "node": {
                        "from": "0x802bc18573bfa1322c829f24fe171ba44fd97a4c",
                        "to": "0x7bdfada0608449e36f812c861ab19670286e1b1f",
                        "value": "20",
                        "transaction": "0xe2c1c97eb124238a7fcc1d629fef9a6242830a29f3ac7a9bb62d868ccc6eaede",
                        "timestamp": "1548659896000"
                      }
                    },
                    {
                      "node": {
                        "from": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "to": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "value": "600",
                        "transaction": "0xc564d2a9965a0cf0521e727ea4e924aa4bc212861351183eb480907d611f7240",
                        "timestamp": "1548052256000"
                      }
                    },
                    {
                      "node": {
                        "from": "0x0000000000000000000000000000000000000000",
                        "to": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "value": "9487",
                        "transaction": "0xe0cf672420248b3af5ff34cfb80763c50689f82e50783ce7a186fd9dc73bd4d8",
                        "timestamp": "1547796216000"
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
