
# Get Smart Voucher Holders
You are able to fetch all Smart Voucher holders and amount of Smart Voucher each holder owns via this API.

## GraphQL API

- Query String
  ```
  query getSmartVoucherHolders {
    me {
      token {
        vouchers {
          totalCount
          edges {
            node {
              id
              contractAddress
              name
              symbol
              decimals
              holders {
                totalCount
                pageInfo {
                  endCursor
                  startCursor
                  hasNextPage
                  hasPreviousPage
                }
                edges {
                  cursor
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
   "query":"query getSmartVoucherHolders { me { token { vouchers {totalCountedges {  node { id    contractAddress  name symbol decimals holders { totalCount pageInfo { endCursor  startCursor        hasNextPage  hasPreviousPage } edges { cursor node {  address balance } } } } } } } }"
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
          "totalCount": 3,
          "edges": [
            {
              "node": {
                "id": "Vm91Y2hlcjoBdRLCmBsBEcOpwro7w7PCiMOZEQjCqw==",
                "contractAddress": "0x72eb714d79dfe1d76c175c9376e49db8e683e385",
                "name": "floor",
                "symbol": "WHOUSE_FLOOR",
                "decimals": "0",
                "holders": {
                  "totalCount": 3,
                  "pageInfo": {
                    "endCursor": "YXJyYXljb25uZWN0aW9uOjI=",
                    "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
                    "hasNextPage": false,
                    "hasPreviousPage": false
                  },
                  "edges": [
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
                      "node": {
                        "address": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
                        "balance": "42950"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
                      "node": {
                        "address": "0x9bd46dbdd1acfa238e821b00e7842c0f93e7dc1a",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
                      "node": {
                        "address": "0xbe4f95765c7efc96bb6efdf07344131dd8e7a232",
                        "balance": "2"
                      }
                    }
                  ]
                }
              }
            },
            {
              "node": {
                "id": "Vm91Y2hlcjrCixrDosOOHsOqEcOpwro7I8OqwoPCpmUe",
                "contractAddress": "0x684cf7f33b54a377bdd22cdae471227cdd37cada",
                "name": "qwerqwer",
                "symbol": "WHOUSE_QWER",
                "decimals": "0",
                "holders": {
                  "totalCount": 1,
                  "pageInfo": {
                    "endCursor": "YXJyYXljb25uZWN0aW9uOjA=",
                    "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
                    "hasNextPage": false,
                    "hasPreviousPage": false
                  },
                  "edges": [
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
                      "node": {
                        "address": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
                        "balance": "4000"
                      }
                    }
                  ]
                }
              }
            },
            {
              "node": {
                "id": "Vm91Y2hlcjrCs8K3GEoaw78Rw6nCujtXwqfCnsKodcKT",
                "contractAddress": "0xc8ae84a07ad40f847650076f125b0f1a82193488",
                "name": "window in the side",
                "symbol": "WHOUSE_WINITS",
                "decimals": "0",
                "holders": {
                  "totalCount": 2,
                  "pageInfo": {
                    "endCursor": "YXJyYXljb25uZWN0aW9uOjk=",
                    "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
                    "hasNextPage": false,
                    "hasPreviousPage": false
                  },
                  "edges": [
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
                      "node": {
                        "address": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
                        "balance": "27719"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjk=",
                      "node": {
                        "address": "0x9391fb5e345535a2a05cf6e37c6fc0a175120817",
                        "balance": "1000"
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
  - **`vouchers`** \<Voucher>
    - **`totalCount`** \<int>
      - Amount of Smart Vouchers.
    - **`edges`**
      - **`node`**
        - **`id`** \<string>
          - Smart Voucher ID. ID is a global identifier.
        - **`contractAddress`** \<string>
          - Smart Voucher contract address.
        - **`name`** \<string>
          - Smart Voucher name.
        - **`symbol`** \<string>
          - Smart Voucher symbol.
        - **`decimals`** \<string>
          - Smart Voucher decimals.
        - **`holders`** \<VoucherHolderConnection>
          - **`totalCount`** \<int>
            - Total amount of the holders.
          - **`pageInfo`** \<PageInfo>
            - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
          - **`edges`** \<VoucherHolderEdge>
            - **`cursor`** \<string>
              - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
            - **`node`** \<Holder>
              - **`address`** \<string>
                - The holder's address.
              - **`balance`** \<string>
                - Amount of the Smart Voucher the holder owns. The format is Decimaled Number.
