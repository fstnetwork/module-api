
# Get me

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
  query: """{
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
          url
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
                url
              }
            }
            value
          }
        }
      }
      voucherbalances {
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
  }"""
}
```

The value of `query` in the body is a `String`

- Response (for example)

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
                  "ipfs": "zBurK9DbDfqRN9zsUHmhW91ZJbsd8KdtjynRXaPa6j1SFqnQTreytmgzkyEacGrSVVmYaZW4Ph7GuwakSYsEUkVzgHUGV/image/default",
                  "url": "//test.fstk.io/file/zBurK9DbDfqRN9zsUHmhW91ZJbsd8KdtjynRXaPa6j1SFqnQTreytmgzkyEacGrSVVmYaZW4Ph7GuwakSYsEUkVzgHUGV/image/default"
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
