
# Get Token Info

## GraphQL API

- Query String
  ```
  fragment voucherInfo on Voucher {
    id
    contractAddress
    name
    description
    symbol
    decimals
    totalSupply
    proofOfContract {
      url
      ipfs
    }
    liquid
    price {
      numerator
      denominator
    }
    availableAmount
    vendible
    expiry
    consumable
    createdTime
    transfers(first: 3) {
      edges {
        cursor
        node {
          timestamp
          from
          to
          value
          transaction
        }
      }
    }
    holders(first: 3) {
      edges {
        cursor
        node {
          address
          balance
        }
      }
    }
  }

  fragment tokenInfo on Token {
    id
    issuer {
      id
      ethereumAddress
    }
    contractAddress
    name
    description
    symbol
    decimals
    totalSupply
    proofOfContract {
      url
      ipfs
    }
    liquid
    price {
      numerator
      denominator
    }
    availableAmount
    vendible
    website
    logo {
      url
      ipfs
    }
    createdTime
    transfers(first: 3) {
      edges {
        cursor
        node {
          timestamp
          from
          to
          value
          transaction
        }
      }
    }
    holders(first: 3) {
      edges {
        cursor
        node {
          address
          balance
        }
      }
    }
    vouchers(first: 3) {
      edges {
        cursor
        node {
          ...voucherInfo
        }
      }
    }
  }

  query GetTokenInfo {
    node(id: VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7) {
      ...tokenInfo
    }
  }
  ```

  The number of `first: 3` in the graphql arguments can be set in every request, and it represents the number of responsed items.

  The `node(id: "VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7")` in `query GetTokenInfo` is for assigning the `id` of the Token.

  The value of `query` in the body is a `String`.

- HTTP Headers
  ```
  {
    "authorization": "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NTYyODAyLCJleHAiOjE1Mzg2NDkyMDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.sGfxYe16aRx_vmvzlRps_gcyTeQD-zsR5HCtjXQ3hYpQYjN1lOFkdpF0m4Yrrh8uHyWBYifqYUVHmkRej4-9gA"
  }
  ```



## HTTP Request
- URL
  - For development: `http://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`
- Method: `POST`
- Headers
  - accept: `application/json;`
  - content-type: `application/json;`
  - authorization: `Bearer [JWT Server-to-Server access token or JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g      ```
- Body
  ```
  {
    "query": "fragment voucherInfo on Voucher {\n  id\n  contractAddress\n  name\n  description\n  symbol\n  decimals\n  totalSupply\n  proofOfContract {\n    url\n    ipfs\n  }\n  liquid\n  price {\n    numerator\n    denominator\n  }\n  availableAmount\n  vendible\n  expiry\n  consumable\n  createdTime\n  transfers(first: 3) {\n    edges {\n      cursor\n      node {\n        timestamp\n        from\n        to\n        value\n        transaction\n      }\n    }\n  }\n  holders(first: 3) {\n    edges {\n      cursor\n      node {\n        address\n        balance\n      }\n    }\n  }\n}\n\nfragment tokenInfo on Token {\n  id\n  issuer {\n    id\n    ethereumAddress\n  }\n  contractAddress\n  name\n  description\n  symbol\n  decimals\n  totalSupply\n  proofOfContract {\n    url\n    ipfs\n  }\n  liquid\n  price {\n    numerator\n    denominator\n  }\n  availableAmount\n  vendible\n  website\n  logo {\n    url\n    ipfs\n  }\n  createdTime\n  transfers(first: 3) {\n    edges {\n      cursor\n      node {\n        timestamp\n        from\n        to\n        value\n        transaction\n      }\n    }\n  }\n  holders(first: 3) {\n    edges {\n      cursor\n      node {\n        address\n        balance\n      }\n    }\n  }\n  vouchers(first: 3) {\n    edges {\n      cursor\n      node {\n        ...voucherInfo\n      }\n    }\n  }\n}\n\nquery GetTokenInfo {\n  node(id: \"VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7/\") {\n    ...tokenInfo\n  }\n}\n\nquery mytoken {\n  me {\n    token {\n      id\n    }\n  }\n}\n"
  }
  ```


## HTTP Response
For example: 
```
{  
   "data":{  
      "node":{  
         "id":"VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7",
         "issuer":{  
            "id":"VXNlckluZm86w68Iw73Ch8KDSBHDqMKOWsK7wqrDgALCvCY=",
            "ethereumAddress":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
         },
         "contractAddress":"0x6514acc34a4ae4ffc894860f9413deca138c0d0d",
         "name":"Hello World",
         "description":"Hello World is good.",
         "symbol":"HW",
         "decimals":"18",
         "totalSupply":"10000000000000000000000000",
         "proofOfContract":{  
            "url":"//test.fstk.io/file/zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/proofOfContract/default",
            "ipfs":"zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/proofOfContract/default"
         },
         "liquid":true,
         "price":{  
            "numerator":"1",
            "denominator":"1000"
         },
         "availableAmount":"9174450000000000000000000",
         "vendible":true,
         "website":"www.helloworld.com",
         "logo":{  
            "url":"//test.fstk.io/file/zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/image/default",
            "ipfs":"zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/image/default"
         },
         "createdTime":"1533797592000",
         "transfers":{  
            "edges":[  
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjA=",
                  "node":{  
                     "timestamp":"1534489052000",
                     "from":"0x6d24af23b9a762a21f15cdffec366e4d83a5958f",
                     "to":"0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
                     "value":"10000000000000000000",
                     "transaction":"0x87c7b321b8eb454e45489b25ef28f1f07c7e9a0e22fad1d6f5409793f8e6b077"
                  }
               },
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjE=",
                  "node":{  
                     "timestamp":"1534402708000",
                     "from":"0x1dacf8ffa468b4f95b0466de16a3337400f57b37",
                     "to":"0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
                     "value":"20000000000000000000000",
                     "transaction":"0x7e2c69138e20804cc487858f4f891c3c977f151210fe103b3781ad18f9d91a59"
                  }
               },
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjI=",
                  "node":{  
                     "timestamp":"1534342212000",
                     "from":"0x02a8ff731ac7464b79d6cabbe5c292019829bf15",
                     "to":"0x10e3c5bcdecc884b574f8aa5432be56e79e526c2",
                     "value":"1110000000000000000000",
                     "transaction":"0x6805c193cca19e3793d260dd2adf269637894d545b0e59de926fb5fc4da84caa"
                  }
               }
            ]
         },
         "holders":{  
            "edges":[  
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjA=",
                  "node":{  
                     "address":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                     "balance":"9174450000000000000000000"
                  }
               },
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjE=",
                  "node":{  
                     "address":"0xc99c5784f1a5fc453553a9fa13fe108651a95a94",
                     "balance":"400000000000000000000000"
                  }
               },
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjI=",
                  "node":{  
                     "address":"0x6d24af23b9a762a21f15cdffec366e4d83a5958f",
                     "balance":"296089999998889000000000"
                  }
               }
            ]
         },
         "vouchers":{  
            "edges":[  
               {  
                  "cursor":"YXJyYXljb25uZWN0aW9uOjA=",
                  "node":{  
                     "id":"Vm91Y2hlcjrDucOywpZqwpvCpRHDqMKOWn/DuXFxUMON",
                     "contractAddress":"0xca9eded32b35365cfb352eab714fcd9e5027bfb8",
                     "name":"Hello Earch",
                     "description":"this is Hello Earch voucher",
                     "symbol":"HW_HE",
                     "decimals":"0",
                     "totalSupply":"400",
                     "proofOfContract":{  
                        "url":"//test.fstk.io/file/zBurKBkN9YbkHQC752vS481fdDAaiYZ7eoxTBSn1mFNFHMMBK1r9SsVcvg8EwXXN39Y46reSXjQKEp1uiXhhuHqNjYgZQ/proofOfContract/default",
                        "ipfs":"zBurKBkN9YbkHQC752vS481fdDAaiYZ7eoxTBSn1mFNFHMMBK1r9SsVcvg8EwXXN39Y46reSXjQKEp1uiXhhuHqNjYgZQ/proofOfContract/default"
                     },
                     "liquid":true,
                     "price":{  
                        "numerator":"100000000000000000000",
                        "denominator":"1"
                     },
                     "availableAmount":"390",
                     "vendible":true,
                     "expiry":"Sun Dec 09 2018 15:59:59 GMT+0000 (Coordinated Universal Time)",
                     "consumable":true,
                     "createdTime":"1533799788000",
                     "transfers":{  
                        "edges":[  
                           {  
                              "cursor":"YXJyYXljb25uZWN0aW9uOjA=",
                              "node":{  
                                 "timestamp":"1533799868000",
                                 "from":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "to":"0x6d24af23b9a762a21f15cdffec366e4d83a5958f",
                                 "value":"10",
                                 "transaction":"0x4886830fda9e2df59a50ca2c1912b15ea2596f87d5d891ad2b1afb59e0a906be"
                              }
                           },
                           {  
                              "cursor":"YXJyYXljb25uZWN0aW9uOjE=",
                              "node":{  
                                 "timestamp":"1533799788000",
                                 "from":"0x0000000000000000000000000000000000000000",
                                 "to":"0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                                 "value":"400",
                                 "transaction":"0xc8bcdc394a1e4355a4bb01eb5ee9258a2d97806507817b40e1b22cc0624ff212"
                              }
                           }
                        ]
                     },
                     "holders":{  
                        "edges":[  

                        ]
                     }
                  }
               }
            ]
         }
      }
   }
}
```

