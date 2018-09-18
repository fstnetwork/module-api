
# Get Token info

## Definition

 - Endpoint
   - For development: `https://test.fstk.io/api`
   - For production: `https://engine.fstk.io/api`
- HTTP Method
  - `POST`
- Content type in header
  - `application/json; charset=utf-8`
- Authorization in header
  - `Bearer [JWT Server-to-Server access token or JWT Web-to-Server access token]`
  - (for example) `Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTUzMjYwNTMxOCwiZXhwIjoxNTMyNjkxNzE4LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.TBwaCVLn77M70wR2fv86ADssg8F5aqsMPklGSnerl9H0qUIAmJWQZYzBYRbXsHisoXTq4pu4n2hBMIXExOy23A`
- Body (for example)

```
{ 
  query: `
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
      node(id: "VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7") {
        ...tokenInfo
      }
    }
  `
}
```

The value of `query` in the body is a `String`.

The number of `first: 3` in the graphql arguments can be set in every request, and it represents the number of responsed items.

The `node(id: "VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7")` in `query GetTokenInfo` is for assigning the `id` of the Token.

- Response (for example)

```
{
  "data": {
    "node": {
      "id": "VG9rZW46w5w5L07Cm8KgEcOowo5awq8gwqx6wpM7",
      "issuer": {
        "id": "VXNlckluZm86w68Iw73Ch8KDSBHDqMKOWsK7wqrDgALCvCY=",
        "ethereumAddress": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      },
      "contractAddress": "0x6514acc34a4ae4ffc894860f9413deca138c0d0d",
      "name": "Hello World",
      "description": "Hello World is good.",
      "symbol": "HW",
      "decimals": "18",
      "totalSupply": "10000000000000000000000000",
      "proofOfContract": {
        "url": "//test.fstk.io/file/zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/proofOfContract/default",
        "ipfs": "zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/proofOfContract/default"
      },
      "liquid": true,
      "price": {
        "numerator": "1",
        "denominator": "1000"
      },
      "availableAmount": "9174450000000000000000000",
      "vendible": true,
      "website": "www.helloworld.com",
      "logo": {
        "url": "//test.fstk.io/file/zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/image/default",
        "ipfs": "zBurK84ay7oabevzCMDNvYR8yrqRm8X2LZzojNMj341rLxZ52frUzg8vhE5zkK4hbtVe77vqyz7BFB8Rog8NwRvsuRw97/image/default"
      },
      "createdTime": "1533797592000",
      "transfers": {
        "edges": [
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "node": {
              "timestamp": "1534489052000",
              "from": "0x6d24af23b9a762a21f15cdffec366e4d83a5958f",
              "to": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
              "value": "10000000000000000000",
              "transaction": "0x87c7b321b8eb454e45489b25ef28f1f07c7e9a0e22fad1d6f5409793f8e6b077"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
            "node": {
              "timestamp": "1534402708000",
              "from": "0x1dacf8ffa468b4f95b0466de16a3337400f57b37",
              "to": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
              "value": "20000000000000000000000",
              "transaction": "0x7e2c69138e20804cc487858f4f891c3c977f151210fe103b3781ad18f9d91a59"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
            "node": {
              "timestamp": "1534342212000",
              "from": "0x02a8ff731ac7464b79d6cabbe5c292019829bf15",
              "to": "0x10e3c5bcdecc884b574f8aa5432be56e79e526c2",
              "value": "1110000000000000000000",
              "transaction": "0x6805c193cca19e3793d260dd2adf269637894d545b0e59de926fb5fc4da84caa"
            }
          }
        ]
      },
      "holders": {
        "edges": [
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "node": {
              "address": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
              "balance": "9174450000000000000000000"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
            "node": {
              "address": "0xc99c5784f1a5fc453553a9fa13fe108651a95a94",
              "balance": "400000000000000000000000"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
            "node": {
              "address": "0x6d24af23b9a762a21f15cdffec366e4d83a5958f",
              "balance": "296089999998889000000000"
            }
          }
        ]
      },
      "vouchers": {
        "edges": [
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "node": {
              "id": "Vm91Y2hlcjrDucOywpZqwpvCpRHDqMKOWn/DuXFxUMON",
              "contractAddress": "0xca9eded32b35365cfb352eab714fcd9e5027bfb8",
              "name": "Hello Earch",
              "description": "this is Hello Earch voucher",
              "symbol": "HW_HE",
              "decimals": "0",
              "totalSupply": "400",
              "proofOfContract": {
                "url": "//test.fstk.io/file/zBurKBkN9YbkHQC752vS481fdDAaiYZ7eoxTBSn1mFNFHMMBK1r9SsVcvg8EwXXN39Y46reSXjQKEp1uiXhhuHqNjYgZQ/proofOfContract/default",
                "ipfs": "zBurKBkN9YbkHQC752vS481fdDAaiYZ7eoxTBSn1mFNFHMMBK1r9SsVcvg8EwXXN39Y46reSXjQKEp1uiXhhuHqNjYgZQ/proofOfContract/default"
              },
              "liquid": true,
              "price": {
                "numerator": "100000000000000000000",
                "denominator": "1"
              },
              "availableAmount": "390",
              "vendible": true,
              "expiry": "Sun Dec 09 2018 15:59:59 GMT+0000 (Coordinated Universal Time)",
              "consumable": true,
              "createdTime": "1533799788000",
              "transfers": {
                "edges": [
                  {
                    "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
                    "node": {
                      "timestamp": "1533799868000",
                      "from": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                      "to": "0x6d24af23b9a762a21f15cdffec366e4d83a5958f",
                      "value": "10",
                      "transaction": "0x4886830fda9e2df59a50ca2c1912b15ea2596f87d5d891ad2b1afb59e0a906be"
                    }
                  },
                  {
                    "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
                    "node": {
                      "timestamp": "1533799788000",
                      "from": "0x0000000000000000000000000000000000000000",
                      "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
                      "value": "400",
                      "transaction": "0xc8bcdc394a1e4355a4bb01eb5ee9258a2d97806507817b40e1b22cc0624ff212"
                    }
                  }
                ]
              },
              "holders": {
                "edges": []
              }
            }
          }
        ]
      }
    }
  }
}
```
