
# Get me

Get your account info.

## GraphQL API
- Query String
  ```
  query {
    me {
      id
      emails {
        email
        verified
        primary
      }
      mobilePhones {
        mobilePhone
        verified
        primary
      }
      concurrencyStamp
      firstName
      lastName
      ethereumKey
      token {
        id
        name
        symbol
        decimals
        contractAddress
        totalSupply
        price {
          numerator
          denominator
        }
        logo {
          ipfs
        }
        availableAmount
        vouchers {
          edges {
            node {
              id
              name
              symbol
              decimals
              description
              contractAddress
              totalSupply
              expiry
              consumable
              availableAmount
              price {
                numerator
                denominator
              }
            }
          }
        }
      }
      campaigns {
        edges {
          node {
            id
            name
            isOpen
            description
            stages {
              name
              description
              startTime
              endTime
              cap
              sold
              priceMultiplier {
                numerator
                denominator
              }
            }
            contractAddress
            transactionHash
            type: __typename
            ... on TokenCampaign {
              token {
                id
                name
                symbol
                decimals
                totalSupply
                price {
                  numerator
                  denominator
                }
              }
            }
            ... on VoucherCampaign {
              voucher {
                id
                name
                symbol
                decimals
                totalSupply
                price {
                  numerator
                  denominator
                }
              }
            }
          }
        }
      }
      etherBalance
      gasTankBalance
      tokenBalances {
        edges {
          node {
            token {
              id
              symbol
              name
              decimals
              description
              contractAddress
              website
              logo {
                ipfs
              }
            }
            value
          }
        }
      }
      voucherBalances {
        edges {
          node {
            voucher {
              id
              name
              symbol
              decimals
              description
              contractAddress
              token {
                id
              }
            }
            value
          }
        }
      }
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
  - authorization: `Bearer [JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
      ```

- Body
  ``` 
  {"query":"\n    {\n      me {\n        id\n        emails {\n          email\n          verified\n          primary\n        }\n        mobilePhones {\n          mobilePhone\n          verified\n          primary\n        }\n        concurrencyStamp\n        firstName\n        lastName\n        ethereumKey\n        token {\n          id\n          name\n          symbol\n          decimals\n          contractAddress\n          totalSupply\n          price {\n            numerator\n            denominator\n          }\n          logo {\n          ipfs\n          }\n          availableAmount\n          vouchers {\n            edges {\n              node {\n                id\n                name\n                symbol\n                decimals\n                description\n                contractAddress\n                totalSupply\n                expiry\n                consumable\n                availableAmount\n                price {\n                  numerator\n                  denominator\n                }\n              }\n            }\n          }\n        }\n        campaigns {\n          edges {\n            node {\n              id\n              name\n              isOpen\n              description\n              stages {\n                name\n                description\n                startTime\n                endTime\n                cap\n                sold\n                priceMultiplier {\n                  numerator\n                  denominator\n                }\n              }\n              contractAddress\n              transactionHash\n              type: __typename\n              ... on TokenCampaign {\n                token {\n                  id\n                  name\n                  symbol\n                  decimals\n                  totalSupply\n                  price {\n                    numerator\n                    denominator\n                  }\n                }\n              }\n              ... on VoucherCampaign {\n                voucher {\n                  id\n                  name\n                  symbol\n                  decimals\n                  totalSupply\n                  price {\n                    numerator\n                    denominator\n                  }\n                }\n              }\n            }\n          }\n        }\n        etherBalance\n        gasTankBalance\n        tokenBalances {\n          edges {\n            node {\n              token {\n                id\n                symbol\n                name\n                decimals\n                description\n                contractAddress\n                website\n                logo {\n                  ipfs\n                }\n              }\n              value\n            }\n          }\n        }\n        voucherBalances {\n          edges {\n            node {\n              voucher {\n                id\n                name\n                symbol\n                decimals\n                description\n                contractAddress\n                token {\n                  id\n                }\n              }\n              value\n            }\n          }\n        }\n      }\n    }"}
  ```

  The value of `query` in the body is a `String`. 

## HTTP Response
(for example)
```
{
  "data": {
    "me": {
      "id": "VXNlckluZm86w6bCiHPCnRPDohHDqMKCwqBje0x0w6nCbA==",
      "emails": [
        {
          "email": "hello@fstk.io",
          "verified": true,
          "primary": true
        }
      ],
      "mobilePhones": [
        {
          "mobilePhone": "+886988888888",
          "verified": true,
          "primary": true
        }
      ],
      "concurrencyStamp": "200c7c81-5188-42fd-9a06-51e06ea5fec0",
      "firstName": "Hello",
      "lastName": "World",
      "ethereumKey": {
        "id": "51f81139-53a2-11e8-b00a-b33bf21ccb88",
        "address": "0x3e7af8b8c19c404670c1670273bca449148df4ed",
        "crypto": {
          "kdf": "scrypt",
          "mac": "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
          "cipher": "aes-128-ctr",
          "kdfparams": {
            "n": 262144,
            "p": 1,
            "r": 8,
            "salt": "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aca",
            "dklen": 32
          },
          "ciphertext": "dc1bf4ab51e55f4d063f835a44f5c172008feb7eeb4b0da7fb7e50f3a18cf9ef",
          "cipherparams": {
            "iv": "b343d847b8a72ad68c6bf10866757421"
          }
        },
        "version": 3
      },
      "token": null,
      "campaigns": {
        "edges": []
      },
      "etherBalance": "9509917598777777768",
      "gasTankBalance": "0",
      "tokenBalances": {
        "edges": [
          {
            "node": {
              "token": {
                "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
                "symbol": "FST",
                "name": "Funder Smart Token",
                "decimals": "18",
                "description": "FundersToken offers PaaS for business to issue Tokens and use built-in CRM Tools without programmings. FST is the service charge supporting platform features.",
                "contractAddress": "0x3830f7af866fae79e4f6b277be17593bf96bee3b",
                "website": "https://www.fstk.io",
                "logo": {
                  "ipfs": "zBurK9DbDfqRN9zsUHmhW91ZJbsd8KdtjynRXaPa6j1SFqnQTreytmgzkyEacGrSVVmYaZW4Ph7GuwakSYsEUkVzgHUGV/image/default"
                }
              },
              "value": "271201000000000000000800"
            }
          }
        ]
      },
      "voucherbalances": {
        "edges": [
          {
            "node": {
              "voucher": {
                "id": "Vm91Y2hlcjrCkCTCrsKAwp8HEcOnwoACAAAAAAAA",
                "name": "FundersToken Initialisation License",
                "symbol": "FIL",
                "decimals": "0",
                "description": "This is also known as the one-off licensing fee of Token issuance. One Funder Account can only issue 1 type of Smart Tokens, which means you only need 1 of FIL per account.",
                "contractAddress": "0x7aecc9c7dc65d15aebf1e2cf7eb0fbbf38f49414",
                "token": {
                  "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA=="
                }
              },
              "value": "1"
            }
          }
        ]
      }
    }
  }
}
```

The `campaigns` is only showed if the end-user is an issuer, and the end-user has created at least one Campaign.
