
# Get Airdrop Mission Infomation

You are able to fetch all your airdrop history via this API.

## GraphQL API

- Query String
  ```
  query {
    airdropHistory {
      edges {
        node {
          id
          method {
            listType
            listId
            targets
          }
          item {
            ... on Token {
              id
              name
            }
            ... on Voucher {
              id
              name
            }
          }
          budget
          status
          totalAirdropAmount
          totalAddresses
          isClaimed
          usedBudget
          createTime
          invokeTime
          executeTime
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
    "query":"query{       airdropHistory{         edges{           node{             id             method{               listType               listId               targets             }           item{             ... on Token{               id               name             }              ... on Voucher{               id          name             }           }           budget           status           totalAirdropAmount           totalAddresses           isClaimed           usedBudget           createTime           invokeTime           executeTime           }         }       }     }"
  }
  ```

  The value of `query` in the body is a `String`. 
  

### Response
```
{
  "data": {
    "airdropHistory": {
      "edges": [
        {
          "node": {
            "id": "QWlyZHJvcE1pc3Npb246Mw==",
            "method": {
              "listType": "AirdropLocate",
              "listId": "132",
              "targets": {
                "filters": [
                  {
                    "giveItem": "VG9rZW46OSLCrAgFMhHDqcK6M8Ozw7RFwoXCgB0=",
                    "ruleItem": "VG9rZW46OSLCrAgFMhHDqcK6M8Ozw7RFwoXCgB0=",
                    "ruletype": "AT_LEAST",
                    "giveAmount": "10333000000000000000",
                    "ruleAmount": "1000000000000000000"
                  }
                ]
              }
            },
            "item": {
              "id": "VG9rZW46OSLCrAgFMhHDqcK6M8Ozw7RFwoXCgB0=",
              "name": "wood clock"
            },
            "budget": "1000000000000000000000",
            "status": "FAILURE",
            "totalAirdropAmount": "10333000000000000000",
            "totalAddresses": "1",
            "isClaimed": false,
            "usedBudget": "0",
            "createTime": "1545405455",
            "invokeTime": "1545405483",
            "executeTime": "1545405540"
          }
        },
        {
          "node": {
            "id": "QWlyZHJvcE1pc3Npb246NA==",
            "method": {
              "listType": "AirdropLocate",
              "listId": "133",
              "targets": {
                "filters": [
                  {
                    "giveItem": "VG9rZW46OSLCrAgFMhHDqcK6M8Ozw7RFwoXCgB0=",
                    "ruleItem": "VG9rZW46OSLCrAgFMhHDqcK6M8Ozw7RFwoXCgB0=",
                    "ruletype": "AT_LEAST",
                    "giveAmount": "1000000000000000000",
                    "ruleAmount": "1000000000000000000"
                  }
                ]
              }
            },
            "item": {
              "id": "VG9rZW46OSLCrAgFMhHDqcK6M8Ozw7RFwoXCgB0=",
              "name": "wood clock"
            },
            "budget": "100000000000000000000",
            "status": "SUCCESS",
            "totalAirdropAmount": "1000000000000000000",
            "totalAddresses": "1",
            "isClaimed": true,
            "usedBudget": "1000000000000000000",
            "createTime": "1545894652",
            "invokeTime": "1545894681",
            "executeTime": "1545894732"
          }
        }
      ]
    }
  }
}
```

## Parameters
### Response
  - **`id`** \<ID!>
    - Airdrop mission ID.
  - **`method`** \<AirdropMethod!>
    - **`listType`** \<String>
      - Type of the list. `AirdropLocate` or `AirdropManual`.
    - **`listId`** \<String!>
      - The ID of the list in each type.
    - **`targets`** \<JSON>
      - **`filters`** \<[JSON]!>
        - **`giveItem`** \<ID!>
          - ID of the item(Smart Token/Voucher) to drop.
        - **`ruleItem`** \<ID!>
          - Item(Smart Token/Voucher) ID of the rule.
        - **`ruletype`** \<enum RuleType!>
          - `EVERY` or `AT_LEAST`.  \<enum>
        - **`giveAmount`** \<String!>
          - Total amount of item(Smart Token/Voucher) to drop. The format is Decimaled Number.
        - **`ruleAmount`** \<String!>
          - Amount of item(Smart Token/Voucher) to drop if match the rule. The format is Decimaled Number.
  - **`item`** \<Token!> or \<Voucher!>
    - **`id`** \<ID!>
      - ID of the item(Smart Token/Voucher) to drop.
    - **`name`** \<String!>
      - Name of the item(Smart Token/Voucher) to drop.
  - **`budget`** \<String!>
    - Budget for the airdrop. The format is Decimaled Number.
  - **`status`** \<enum AirdropMissionStatus!>
    - `PENDING`, `PROCESSING`, `SUCCESS`, `FAILURE`, `CANCEL` or `CLAIMED`. \<enum>
  - **`totalAirdropAmount`** \<String>
    - Total amount of item(Smart Token/Voucher) to drop. The format is Decimaled Number.
  - **`totalAddresses`** \<String>
    - Total amount of addresses to drop.
  - **`isClaimed`** \<Boolean>
    - The airdrop is claimed or not.
  - **`usedBudget`** \<String>
    - Total budget the airdrop used. The format is Decimaled Number.
  - **`createTime`** \<String!>
    - The airdrop created time. The format is Unix Timestamp.
  - **`invokeTime`** \<String!>
    - The airdrop invoked time. The format is Unix Timestamp.
  - **`executeTime`** \<String>
    - The airdrop executed time. The format is Unix Timestamp