
# Get Airdrop Mission Infomation

You are able th fetch all your airdrop history via this API.

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
    "authorization": "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NTYyODAyLCJleHAiOjE1Mzg2NDkyMDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.sGfxYe16aRx_vmvzlRps_gcyTeQD-zsR5HCtjXQ3hYpQYjN1lOFkdpF0m4Yrrh8uHyWBYifqYUVHmkRej4-9gA"
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
    "query":"query{\n       airdropHistory{\n         edges{\n           node{\n             id\n             method{\n               listType\n               listId\n               targets\n             }\n           item{\n             ... on Token{\n               id\n               name\n             }\n              ... on Voucher{\n               id\n          name\n             }\n           }\n           budget\n           status\n           totalAirdropAmount\n           totalAddresses\n           isClaimed\n           usedBudget\n           createTime\n           invokeTime\n           executeTime\n           }\n         }\n       }\n     }\n\n"
  }
  ```

  The value of `query` in the body is a `String`. 
  

## Response
_(sample)_
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
  - **`id`** \<string>
    - Airdrop mission ID.
  - **`method`** \<AirdropMethod>
    - **`listType`** \<string>
      - Type of the list. `AirdropLocate` or `AirdropManual`.
    - **`listId`** \<string>
      - The ID of the list in each type.
    - **`targets`** \<JSON>
      - **`filters`** \<JSON Array>
        - **`giveItem`** \<string>
          - ID of the item(token/voucher) to drop.
        - **`ruleItem`** \<string>
          - Item(token/voucher) ID of the rule.
        - **`ruletype`** \<string>
          - `EVERY` or `AT_LEAST`
        - **`giveAmount`** \<string>
          - Total amount of item(token/voucher) to drop. The format is Decimaled Number.
        - **`ruleAmount`** \<string>
          - Amount of item(token/voucher) to drop if match the rule. The format is Decimaled Number.
  - **`item`** \<Token> or \<Voucher>
    - **`id`** \<string>
      - ID of the item(token/voucher) to drop.
    - **`name`** \<string>
      - Name of the item(token/voucher) to drop.
  - **`budget`** \<string>
    - Budget for the airdrop. The format is Decimaled Number.
  - **`status`** \<AirdropMissionStatus>
    - `PENDING`, `PROCESSING`, `SUCCESS`, `FAILURE`, `CANCEL` or `CLAIMED`. \<enum>
  - **`totalAirdropAmount`** \<string>
    - Total amount of item(token/voucher) to drop. The format is Decimaled Number.
  - **`totalAddresses`** \<string>
    - Total amount of addresses to drop.
  - **`isClaimed`** \<Boolean>
    - The airdrop is claimed or not.
  - **`usedBudget`** \<string>
    - Total budget the airdrop used. The format is Decimaled Number.
  - **`createTime`** \<string>
    - The airdrop created time. The format is Unix Timestamp.
  - **`invokeTime`** \<string>
    - The airdrop invoked time. The format is Unix Timestamp.
  - **`executeTime`** \<string>
    - The airdrop executed time. The format is Unix Timestamp