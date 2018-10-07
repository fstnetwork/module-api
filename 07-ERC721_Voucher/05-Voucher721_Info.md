
# Voucher721 Info

## GraphQL API

- Query String
```
query($address: String!) {
  voucher721Balance(address: $address) {
    edges {
      node {
        voucher {
          id
          token {
            id
            name
          }
          contractAddress
          transactionHash
          name
          description
          symbol
          decimals
          hardCap
          proofOfContract {
            ipfs
          }
          liquid
          approveChecking
          price {
            numerator
            denominator
          }
          availableAmount
          vendible
          expiry
          consumable
          createdTime
          transfers {
            edges {
              node {
                to
                from
                voucherId
                transaction
                timestamp
              }
            }
            totalCount
          }
          nfts {
            edges {
              node {
                id
                nftId
                ownerAddress
                contractAddress
                metadata
              }
            }
            totalCount
          }
          holders {
            edges {
              node {
                address
                balance
                nfts {
                  edges {
                    node {
                      nftId
                    }
                  }
                  totalCount
                }
              }
            }
            totalCount
          }
        }
        value
      }
    }
  }
}


```
- Query Variables

```
{
  "address": "0xa818e5f90ac4de5a15915266822d931050135cf3"
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
   "query":"query ($address: String!) {\n  voucher721Balance(address: $address) {\n    edges {\n      node {\n        voucher {\n          id\n          token {\n            id\n            name\n          }\n          contractAddress\n          transactionHash\n          name\n          description\n          symbol\n          decimals\n          hardCap\n          proofOfContract {\n            ipfs\n          }\n          liquid\n          approveChecking\n          price {\n            numerator\n            denominator\n          }\n          availableAmount\n          vendible\n          expiry\n          consumable\n          createdTime\n          transfers {\n            edges {\n              node {\n                to\n                from\n                voucherId\n                transaction\n                timestamp\n              }\n            }\n            totalCount\n          }\n          nfts {\n            edges {\n              node {\n                id\n                nftId\n                ownerAddress\n                contractAddress\n                metadata\n              }\n            }\n            totalCount\n          }\n          holders {\n            edges {\n              node {\n                address\n                balance\n                nfts {\n                  edges {\n                    node {\n                      nftId\n                    }\n                  }\n                  totalCount\n                }\n              }\n            }\n            totalCount\n          }\n        }\n        value\n      }\n    }\n  }\n}\n",
   "variables":{  
      "address":"0xa818e5f90ac4de5a15915266822d931050135cf3"
   }
}
```

## HTTP Response
```
{  
   "data":{  
      "voucher721Balance":{  
         "edges":[  
            {  
               "node":{  
                  "voucher":{  
                     "id":"Vm91Y2hlcjcyMTpQw4HDhMOkw4LDrBHDqMKnfD9IBsOgwpvDvA==",
                     "token":{  
                        "id":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                        "name":"SAINT MKII"
                     },
                     "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                     "transactionHash":"0x327d11e272bdb5db257104dd61d84acb07615f897973200f11bb626cc49eb609",
                     "name":"TKK",
                     "description":"tkk is good good eat",
                     "symbol":"SAINT2_TKK",
                     "decimals":"0",
                     "hardCap":"5000",
                     "proofOfContract":{  
                        "ipfs":"zBurK96UrUiNkVSAtziRwDsmmFpy84YCpMWagvdCvEXbs7di228YmBsaPXz99idr6QeehsTS8Qra5Yhu9xoCZg6RqgMK9/proofOfContract/default"
                     },
                     "liquid":true,
                     "approveChecking":false,
                     "price":{  
                        "numerator":"12000000000000000000",
                        "denominator":"1"
                     },
                     "availableAmount":"3",
                     "vendible":true,
                     "expiry":"1549094399000",
                     "consumable":true,
                     "createdTime":"1538118088000",
                     "transfers":{  
                        "edges":[  
                           {  
                              "node":{  
                                 "to":"0x00a0a24b9f0e5ec7aa4c7389b8302fd0123194de",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"87394256817236557687648236852355643521345231",
                                 "transaction":"0x5273aa6b60667dae8c2b11b0a73e654948bc3f04d4734f2480951eb04ee226d2",
                                 "timestamp":"1538534088000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0x003bbce1eac59b406dd0e143e856542df3659075",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"94876666",
                                 "transaction":"0x3daaf5bddc573d5ab68b2093755694f7af048677129f26ea2ea7c3b31692e335",
                                 "timestamp":"1538532647000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"745465465464656546545465",
                                 "transaction":"0x10e09f3ca47cf4648aaceada30a2b230b4862dcf886e31dc9289ef46a2825087",
                                 "timestamp":"1538429712000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "from":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "voucherId":"1",
                                 "transaction":"0x63923db67cd3c6f523d2dbc619a2785360b9d7e58ccb1185b238655e6a32098a",
                                 "timestamp":"1538120464000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"6",
                                 "transaction":"0x8fefa81ba609dbf2302d03e95cd9db6457a2ade7e0291d30419ddbea7becb199",
                                 "timestamp":"1538120136000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"5",
                                 "transaction":"0x8d9c90d6ed34f97a2c23287eb384bca013a37c889adee5ea598900a537e45f7e",
                                 "timestamp":"1538120040000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"4",
                                 "transaction":"0x005bf95e616696da63585b5154d9c7c9b1181977cf3ca9af54d772899f56075b",
                                 "timestamp":"1538119848000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xe51ddaa1b650c26b62fca2520cdc2c60ce205f75",
                                 "from":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "voucherId":"3",
                                 "transaction":"0x5602c5ebb7e2a2005b229a053d5185556544a588249f56e68e51323f90633a01",
                                 "timestamp":"1538119796000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"3",
                                 "transaction":"0x96910bebf2ed8a45e3af7f1ea99767d2d690628ed64c862167fd9bb712299132",
                                 "timestamp":"1538119616000"
                              }
                           },
                           {  
                              "node":{  
                                 "to":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "voucherId":"77777",
                                 "transaction":"0xf5b77e3e2e168c132fb1af32e429de927be782eb1b942663a2b4069bc3a3a1bb",
                                 "timestamp":"1538119356000"
                              }
                           }
                        ],
                        "totalCount":13
                     },
                     "nfts":{  
                        "edges":[  
                           {  
                              "node":{  
                                 "id":"TmZ0OsKHwpU5woTDhHYRw6jCp3wHDGjCgh0G",
                                 "nftId":"2",
                                 "ownerAddress":"0xc6ec42e1f0e971864d7236945d91664ec17f5b9f",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"atkins how bun bun"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKHwpU5woXDhHYRw6jCp3x/woUGNQ8u",
                                 "nftId":"77777",
                                 "ownerAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"test mint voucher to contract self"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKHwpU5wobDhHYRw6jCp3xzEsOFwoLDrQo=",
                                 "nftId":"3",
                                 "ownerAddress":"0xe51ddaa1b650c26b62fca2520cdc2c60ce205f75",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"Teachers Day"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKHwpU5wofDhHYRw6jCp3zCg8O5w5dmw6/CqA==",
                                 "nftId":"4",
                                 "ownerAddress":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"oh my god"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKHwpU5wojDhHYRw6jCp3xDeMOJR3cE",
                                 "nftId":"5",
                                 "ownerAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"no, thanks"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKIwqDCoMKEw4R2EcOowqd8wps+DcOGWsOU",
                                 "nftId":"6",
                                 "ownerAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"No Pei, No FundersToken"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKIwqDCoMKFw4R2EcOowqd8X1Btw47CvcOl",
                                 "nftId":"1",
                                 "ownerAddress":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"noel 6666, you 9487"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsO/aG44w4YEEcOowqd8w5tJLMKqP3g=",
                                 "nftId":"745465465464656546545465",
                                 "ownerAddress":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"No Pei, No FundersToken"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OsKswqjDncO+w4bDtBHDqMKnfMOTZMKLNlc+",
                                 "nftId":"94876666",
                                 "ownerAddress":"0x003bbce1eac59b406dd0e143e856542df3659075",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"kovan sucks"
                              }
                           },
                           {  
                              "node":{  
                                 "id":"TmZ0OgbDh8KhwozDhsO4EcOowqd8w4vDhMK7cm/CsQ==",
                                 "nftId":"87394256817236557687648236852355643521345231",
                                 "ownerAddress":"0x00a0a24b9f0e5ec7aa4c7389b8302fd0123194de",
                                 "contractAddress":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "metadata":"kovan sucks"
                              }
                           }
                        ],
                        "totalCount":10
                     },
                     "holders":{  
                        "edges":[  
                           {  
                              "node":{  
                                 "address":"0xa818e5f90ac4de5a15915266822d931050135cf3",
                                 "balance":"3",
                                 "nfts":{  
                                    "edges":[  
                                       {  
                                          "node":{  
                                             "nftId":"5"
                                          }
                                       },
                                       {  
                                          "node":{  
                                             "nftId":"6"
                                          }
                                       },
                                       {  
                                          "node":{  
                                             "nftId":"77777"
                                          }
                                       }
                                    ],
                                    "totalCount":3
                                 }
                              }
                           },
                           {  
                              "node":{  
                                 "address":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "balance":"3",
                                 "nfts":{  
                                    "edges":[  
                                       {  
                                          "node":{  
                                             "nftId":"1"
                                          }
                                       },
                                       {  
                                          "node":{  
                                             "nftId":"745465465464656546545465"
                                          }
                                       },
                                       {  
                                          "node":{  
                                             "nftId":"4"
                                          }
                                       }
                                    ],
                                    "totalCount":3
                                 }
                              }
                           },
                           {  
                              "node":{  
                                 "address":"0x003bbce1eac59b406dd0e143e856542df3659075",
                                 "balance":"1",
                                 "nfts":{  
                                    "edges":[  
                                       {  
                                          "node":{  
                                             "nftId":"94876666"
                                          }
                                       }
                                    ],
                                    "totalCount":1
                                 }
                              }
                           },
                           {  
                              "node":{  
                                 "address":"0x00a0a24b9f0e5ec7aa4c7389b8302fd0123194de",
                                 "balance":"1",
                                 "nfts":{  
                                    "edges":[  
                                       {  
                                          "node":{  
                                             "nftId":"87394256817236557687648236852355643521345231"
                                          }
                                       }
                                    ],
                                    "totalCount":1
                                 }
                              }
                           },
                           {  
                              "node":{  
                                 "address":"0xc6ec42e1f0e971864d7236945d91664ec17f5b9f",
                                 "balance":"1",
                                 "nfts":{  
                                    "edges":[  
                                       {  
                                          "node":{  
                                             "nftId":"2"
                                          }
                                       }
                                    ],
                                    "totalCount":1
                                 }
                              }
                           },
                           {  
                              "node":{  
                                 "address":"0xe51ddaa1b650c26b62fca2520cdc2c60ce205f75",
                                 "balance":"1",
                                 "nfts":{  
                                    "edges":[  
                                       {  
                                          "node":{  
                                             "nftId":"3"
                                          }
                                       }
                                    ],
                                    "totalCount":1
                                 }
                              }
                           }
                        ],
                        "totalCount":6
                     }
                  },
                  "value":"3"
               }
            }
         ]
      }
   }
}
```
