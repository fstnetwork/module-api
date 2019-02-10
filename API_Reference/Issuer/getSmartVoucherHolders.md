
# Get Smart Voucher Holders

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
          "totalCount": 5,
          "edges": [
            {
              "node": {
                "id": "Vm91Y2hlcjoBdRLCmBsBEcOpwro7w7PCiMOZEQjCqw==",
                "contractAddress": "0x72eb714d79dfe1d76c175c9376e49db8e683e385",
                "name": "floor",
                "symbol": "WHOUSE_FLOOR",
                "decimals": "0",
                "holders": {
                  "totalCount": 1051,
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
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
                      "node": {
                        "address": "0x1fb733deb6f95e6dd22e64d212b648a46094b58a",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
                      "node": {
                        "address": "0x27a17b59e93a05999cc2829f31d1481667e98f33",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjU=",
                      "node": {
                        "address": "0x890cc1ba126158ae84921edfaeddd67d856376a8",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjY=",
                      "node": {
                        "address": "0xdffd566caf3cc00502d28bf4de810afc081daf2b",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjc=",
                      "node": {
                        "address": "0xe92594420a89b675ce13b0ef7ca738210631939c",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjg=",
                      "node": {
                        "address": "0xe570159ad28085813de89a2e2c6a30daa07ed04c",
                        "balance": "2"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjk=",
                      "node": {
                        "address": "0xe0c61136e04107a54c4af0a4f8137265238869f6",
                        "balance": "2"
                      }
                    }
                  ]
                }
              }
            },
            {
              "node": {
                "id": "Vm91Y2hlcjouwoLCqkAaw7ARw6nCujsTWATDlcK8DQ==",
                "contractAddress": "0xf1ead68d94f78d83234d96e45ee1034441b05e35",
                "name": "Green plant",
                "symbol": "WHOUSE_GPLANT",
                "decimals": "0",
                "holders": {
                  "totalCount": 82,
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
                        "balance": "1919"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
                      "node": {
                        "address": "0xec4abaa710773b869337ff1d8a51339b945a7421",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
                      "node": {
                        "address": "0x72ac7b5ca22ae7796653e9b2b207e10a08d229e0",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
                      "node": {
                        "address": "0xfaac0e7d41744070f7e0c0d3329adfd2faaaa80b",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
                      "node": {
                        "address": "0xdaf6cf774af380fe9d718bff0cf3a015f9bb7b73",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjU=",
                      "node": {
                        "address": "0x3a0635bc1d59ae6f41286de4162d824e2b017844",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjY=",
                      "node": {
                        "address": "0x413108cb39a9930b0d3c5415afe0882bc969880f",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjc=",
                      "node": {
                        "address": "0x8fd503c276b342793d858aabacd71a242e9291af",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjg=",
                      "node": {
                        "address": "0xecdab6942e80fe4bfea9a31959965bb58a4a765f",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjk=",
                      "node": {
                        "address": "0x5048bd28e5d47ad6e11295f0b0578993542c6112",
                        "balance": "1"
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
                  "totalCount": 166,
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
                      "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
                      "node": {
                        "address": "0x9391fb5e345535a2a05cf6e37c6fc0a175120817",
                        "balance": "1000"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
                      "node": {
                        "address": "0xc01926f281f51ace3291a8dd680b968888f13b40",
                        "balance": "552"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
                      "node": {
                        "address": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
                        "balance": "162"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
                      "node": {
                        "address": "0x6e2de947d765da388143d36bbdfbb3314af6decb",
                        "balance": "4"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjU=",
                      "node": {
                        "address": "0x3c89407549e0fc1914ba899f30d45979b6b0c3c6",
                        "balance": "4"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjY=",
                      "node": {
                        "address": "0xec4abaa710773b869337ff1d8a51339b945a7421",
                        "balance": "4"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjc=",
                      "node": {
                        "address": "0x4e964e92d1aeda31fc999fea4c114c09f9e512b9",
                        "balance": "4"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjg=",
                      "node": {
                        "address": "0x987d8fa9a36cc2cf9259fa49c85e52b64890b0d6",
                        "balance": "4"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjk=",
                      "node": {
                        "address": "0x35f52c4dee52533d45f2575e83dd28a02cc49288",
                        "balance": "4"
                      }
                    }
                  ]
                }
              }
            },
            {
              "node": {
                "id": "Vm91Y2hlcjrDoHo8wqIaw7IRw6nCujtLwr/DkzDDvCI=",
                "contractAddress": "0x4b468dfb1223dfbfb2c639076fd9fd04517463a1",
                "name": "Light",
                "symbol": "WHOUSE_LIGHT",
                "decimals": "0",
                "holders": {
                  "totalCount": 166,
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
                        "balance": "3295"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
                      "node": {
                        "address": "0x0d2b20f3360395e3138c5e596c0fca3d06fe9b80",
                        "balance": "25"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
                      "node": {
                        "address": "0x7ec33626217c6b59edf0decdab0831f0373981e3",
                        "balance": "17"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
                      "node": {
                        "address": "0x8d3e126fb79936cd39a52bd505b8288ec6f630e6",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
                      "node": {
                        "address": "0x5ac7467bbb9ff35f0233cdb70eaae2657bebaa42",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjU=",
                      "node": {
                        "address": "0x7bc68726a8486aa50c2b0c8caf7c8948ba7c1291",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjY=",
                      "node": {
                        "address": "0x6f9743a81015572cc755d0bf3bdd28eecaaf04ca",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjc=",
                      "node": {
                        "address": "0xaed00394bd74c9d641c2f57beb373b7521509963",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjg=",
                      "node": {
                        "address": "0xe73cae93bf15345249c9f983938a016a4965d2b2",
                        "balance": "1"
                      }
                    },
                    {
                      "cursor": "YXJyYXljb25uZWN0aW9uOjk=",
                      "node": {
                        "address": "0xf3b0846c2cc327039200a3dab5fd431790a65b8b",
                        "balance": "1"
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
    - **`edges`**
      - **`node`**
        - **`id`** \<string>
          - Token ID. ID is a global identifier.
        - **`contractAddress`** \<string>
          - Token contract address.
        - **`name`** \<string>
          - Token name.
        - **`symbol`** \<string>
          - Token symbol.
        - **`decimals`** \<string>
          - Token decimals.
        - **`holders`** \<TokenHolderConnection>
          - **`totalCount`** \<int>
            - Total amount of holders.
          - **`pageInfo`** \<PageInfo>
            - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
          - **`edges`** \<TokenHolderEdge>
            - **`cursor`** \<string>
              - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
            - **`node`** \<Holder>
              - **`address`** \<string>
                - The holder's address.
              - **`balance`** \<string>
                - Amount of the token the holder owns. The format is Decimaled Number.
