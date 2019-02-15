
# Get Smart Voucher Information
You are able to fetch your Smart Voucher Information via this API.

## GraphQL API

- Query String
  ```
  query getSmartVoucherInfo {
    me {
      token {
        vouchers {
          edges {
            node {
              id
              token {
                id
              }
              contractAddress
              transactionHash
              name
              description
              symbol
              decimals
              totalSupply
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
   "query":"{\n  me {\n    token {\n      vouchers {\n        edges {\n          node {\n            id\n            token {\n              id\n            }\n            contractAddress\n            transactionHash\n            name\n            description\n            symbol\n            decimals\n            totalSupply\n            proofOfContract {\n              ipfs\n            }\n            liquid\n            approveChecking\n            price {\n              numerator\n              denominator\n            }\n            availableAmount\n            vendible\n            expiry\n            consumable\n            createdTime\n          }\n        }\n      }\n    }\n  }\n}\n"
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
                "token": {
                  "id": "VG9rZW46w4vDnGzCihouEcOpwro7w4drQ8KgIMOn"
                },
                "contractAddress": "0xc9961776dc4cc72422a551dd616f5b53fd1bdd78",
                "transactionHash": "0xe0cf672420248b3af5ff34cfb80763c50689f82e50783ce7a186fd9dc73bd4d8",
                "name": "Love River",
                "description": "love love",
                "symbol": "HAN_LOVE",
                "decimals": "0",
                "totalSupply": "9487",
                "proofOfContract": {
                  "ipfs": "zBurKC5xBDHi2TTEYyY51S9p5qqGV2DqcaC2eYmpmh57Kxi6brpz3QH2h3X97xjKcuPDBZasPBsAHjpjzGuBbNzWhNRCL/proofOfContract/default"
                },
                "liquid": true,
                "approveChecking": false,
                "price": {
                  "numerator": "75000000000000000000",
                  "denominator": "1"
                },
                "availableAmount": "9467",
                "vendible": true,
                "expiry": "1704038399000",
                "consumable": true,
                "createdTime": "1547796216000"
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
    - **`edges`** \<VoucherEdge>
      - **`node`** \<Voucher>
        - **`id`** \<string>
          - Smart Voucher ID. ID is a global identifier.
        - **`token`**\<Token>
          - **`id`** \<string>
            - ID of the Smart Token which the Smart Voucher . ID is a global identifier.
        - **`contractAddress`** \<string>
          - The Smart Voucher contract address.
        - **`transactionHash`** \<string>
          - Transaction hash of the Smart Voucher publishment.
        - **`name`** \<string>
          - Smart Voucher name.
        - **`description`** \<string>
          - Description of the Smart Voucher.
        - **`symbol`** \<string>
          - Smart Voucher symbol.
        - **`decimals`** \<string>
          - Smart Voucher decimals
        - **`totalSupply`** \<string>
          - Total supply of the Smart Voucher. The format is Decimaled Number.
        - **`proofOfContract`** \<File>
          - The PDF file of the Smart Voucher contract.
          - **`ipfs`** \<string>
            - Information of the contract's ipfs link.
        - **`liquid`** \<boolean>
          - The Smart Voucher is liquid or not.
        - **`approveChecking`** \<boolean>
        - **`price`** \<Fraction>
          - Smart Voucher price.
          - **`numerator`** \<string>
            - The numerator of this fraction.
          - **`denominator`** \<string>
            - The denominator of this fraction.
        - **`availableAmount`** \<string>
          - Remaining amount of the Smart Voucher. The format is Decimaled Number.
        - **`vendible`** \<boolean>
          - The Smart Voucher is vendible or not.
        - **`expiry`** \<string>
          - Expiry date of the Smart Voucher. The format is Unix Timestamp in millisecond resolution.
        - **`consumable`** \<boolean>
          - The Smart Voucher is consumalbe or not.
        - **`createdTime`** \<string>
          - The Smart Voucher created time. The format is Unix Timestamp in millisecond resolution.
