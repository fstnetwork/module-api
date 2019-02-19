
# Get Smart Voucher Transfer History
You are able to fetch all transfer history of the Smart Voucher(which you published) via this API.

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
                "contractAddress": "0x4711e92ad968a6488500bc5dde2a48ee17743ab1",
                "name": "Test Voucher",
                "symbol": "TTV",
                "decimals": "0",
                "transfers": {
                  "pageInfo": {
                    "endCursor": "YXJyYXljb25uZWN0aW9uOjk=",
                    "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
                    "hasPreviousPage": false,
                    "hasNextPage": false
                  },
                  "totalCount": 3,
                  "edges": [
                    {
                      "cursor": "YXJyYXljb25uZWN0a8J9RGy=",
                      "node": {
                        "from": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "to": "0x802bc18573bfa1322c829f24fe171ba44fd97a4c",
                        "value": "20",
                        "transaction": "0xe2c1c97eb124238a7fcc1d629fef9a6242830a29f3ac7a9bb62d868ccc6eaede",
                        "timestamp": "1548659896000"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uIFd=",
                      "node": {
                        "from": "0x802bc18573bfa1322c829f24fe171ba44fd97a4c",
                        "to": "0x7bdfada0608449e36f812c861ab19670286e1b1f",
                        "value": "20",
                        "transaction": "0xe2c1c97eb124238a7fcc1d629fef9a6242830a29f3ac7a9bb62d868ccc6eaede",
                        "timestamp": "1548659896000"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
                      "node": {
                        "from": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "to": "0xb0236f9a6a1cd8cf17251a130651e0be8fb00e27",
                        "value": "600",
                        "transaction": "0xc564d2a9965a0cf0521e727ea4e924aa4bc212861351183eb480907d611f7240",
                        "timestamp": "1548052256000"
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
### Response
- **`vouchers`** \<VoucherConnection>
  - **`pageInfo`** \<PageInfo!>
    - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
  - **`totalCount`** \<Int!>
    - Total amount of the Smart Voucher.
  - **`edges`** \<VoucherEdge>
    - **`cursor`** \<String!>
      - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
    - **`node`** \<Voucher>
      - **`id`** \<String!>
        - Smart Voucher ID. ID is a global identifier.
      - **`contractAddress`** \<String!>
        - Smart Voucher contract address.
      - **`name`** \<String!>
        - Smart Voucher name.
      - **`symbol`** \<String!>
        - Smart Voucher symbol.
      - **`decimals`** \<String!>
        - Smart Voucher decimals.
      - **`transfers`** \<VoucherTransferConnection>
        - **`pageInfo`** \<PageInfo!>
          - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
        - **`totalCount`** \<Int!>
          - Total amount of the Smart Voucher transfer.
        - **`edge`** \<VouhcerTransferEdge>
          - **`cursor`** \<String>
            - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
          - **`node`** \<Transfer>
            - **`from`** \<String!>
              - Sender address.
            - **`to`** \<String>
              - Receiver address.
            - **`value`** \<String!>
              - Amount of the Smart Voucher the sender sent. The format is Decimaled Number.
            - **`transaction`** \<String!>
              - The transaction hash of this action.
            - **`timestamp`** \<String!>
              - The time that FST Network server reviced transaction. The format is Unix Timestamp in millisecond resolution.
