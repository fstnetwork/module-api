
# Locate Airdrop Targets
Set the rule and get the rusult of the rule for airdrop.

## GraphQL API

- Query String
  ```
  mutation createAirdropLocate($input: createAirdropLocateInput!) {
    createAirdropLocate(input: $input) {
      airdropLocate {
        seqno: id
        airdropItem {
          ... on Token {
            id
            name
            decimals
          }
        }
        totalAddresses
        totalAirdropAmount
        summary {
          rule {
            locateRule {
              type
              item {
                ... on Token {
                  decimals
                }
                ... on Voucher {
                  decimals
                }
              }
            }
            item {
              ... on Token {
                decimals
              }
              ... on Voucher {
                decimals
              }
            }
            amount
          }
          totalAddresses
          totalAirdropAmount
        }
      }
    }
  }
  ```
- Query Variables
  ```
  {  
    "input":{  
        "rules":[  
          {  
              "rule":{  
                "type":"EVERY",
                "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                "amount":"100000000000000000000"
              },
              "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "amount":"1000000000000000000"
          },
          {  
              "rule":{  
                "type":"AT_LEAST",
                "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                "amount":"3000000000000000000"
              },
              "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "amount":"5000000000000000000"
          }
        ]
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
    - _(for example)_
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
      ```

- Body
  ``` 
  {  
    "query":"\n    mutation createAirdropLocate($input: createAirdropLocateInput!) {\n      createAirdropLocate(input: $input) {\n        airdropLocate {\n          seqno: id\n          airdropItem {\n            ... on Token {\n              id\n              name\n              decimals\n            }\n          }\n          totalAddresses\n          totalAirdropAmount\n          summary {\n            rule {\n              locateRule {\n                type\n                item {\n                  ... on Token {\n                    decimals\n                  }\n                  ... on Voucher {\n                    decimals\n                  }\n                }\n              }\n              item {\n                ... on Token {\n                  decimals\n                }\n                ... on Voucher {\n                  decimals\n                }\n              }\n              amount\n            }\n            totalAddresses\n            totalAirdropAmount\n            \n          }\n        }\n      }\n    }\n\n    ",
    "variables":{  
      "input":{  
        "rules":[  
          {  
            "rule":{  
              "type":"EVERY",
              "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "amount":"100000000000000000000"
            },
            "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
            "amount":"1000000000000000000"
          },
          {  
            "rule":{  
              "type":"AT_LEAST",
                "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                "amount":"3000000000000000000"
            },
            "itemId":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
            "amount":"5000000000000000000"
          }
        ]
      }
    }
  }
  ```

  The value of `query` in the body is a `String`.
  

### Response
```
{
  "data": {
    "createAirdropLocate": {
      "airdropLocate": {
        "seqno": "QWlyZHJvcExvY2F0ZToxMDc=",
        "airdropItem": {
          "id": "VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
          "name": "SAINT MKII",
          "decimals": "18"
        },
        "totalAddresses": "6",
        "totalAirdropAmount": "54000000000000000000",
        "summary": [
          {
            "rule": {
              "locateRule": {
                "type": "EVERY",
                "item": {
                  "decimals": "18"
                }
              },
              "item": {
                "decimals": "18"
              },
              "amount": "1000000000000000000"
            },
            "totalAddresses": "5",
            "totalAirdropAmount": "24000000000000000000"
          },
          {
            "rule": {
              "locateRule": {
                "type": "AT_LEAST",
                "item": {
                  "decimals": "18"
                }
              },
              "item": {
                "decimals": "18"
              },
              "amount": "5000000000000000000"
            },
            "totalAddresses": "6",
            "totalAirdropAmount": "30000000000000000000"
          }
        ]
      }
    }
  }
}
```

## Parameters
### Request 
- **`rule`** \<[airdropLocateRule]!>
  - **`type`** \<enum LocateRuleEnum!>
    - `EVERY` or `AT_LEAST`. `EVERY` means every condition will be counted for giveaway item calculation. _e.g. If qualification is set to be 'every' 1 FST can receive 1 FVoucher, a funder who owns 10 FST can receive 10 FVoucher through this airdrop event._ `AT_LEAST` means only the condition is greater than or equal to (&gt;=) the qualification will be counted for giveaway item calculation. _e.g. If qualification is set to be 'at least' 5 FST can receive 1 FVoucher, a funder who owns 10 FST can receive 1 FVoucher through this airdrop event._
  - **`itemId`** \<ID!>
    - Item(Smart Token/Voucher) of the rule.
  - **`amount`** \<string!>
    - Amount of item(Smart Token/Voucher) of the rule.
- **`itemID`** \<string>
  - Item(Smart Token/Voucher) to drop.
- **`amount`** \<string>
  - Amount of item(Smart Token/Voucher) to drop if matching the condition.

### Response
- **`airdropLocate`** \<AirdropLocate!>
  - **`id`** \<ID!>
    - ID of the locate rules.
  - **`airdropItem`** \<AirdropItem!>
    - **`id`** \<ID!>
      - ID of the item(Smart Token/Voucher) which is to drop.
    - **`name`** \<String!>
      - Name of the item(Smart Token/Voucher) which is to drop.
    - **`decimals`** \<String!>
      - Decimals of the item(Smart Token/Voucher) which is to drop.
  - **`totalAddresses`** \<String>
    - Amount of accounts which are match to the rules. 
  - **`totalAirdropAmount`** \<String>
    - Total amount(add each rule's `amount`) of the item(Smart Token/Voucher) to drop. The format is Decimaled Number.
  - **`summery`** \<[AirdropLocateResult]!>
    - **`rule`** \<AirdropLocateRule!>
      - **`locateRule`** \<LocateRule>
        - **`type`** \<LocateRuleEnum>
          - `EVERY` or `AT_LEAST`. \<enum>
        - **`item`** \<Token> or \<Voucher>
          - **`decimals`** \<String>
            - Decimals of the item(Smart Token/Voucher) of the rule.
      - **`item`** \<Token> or \<Voucher>
        - **`decimals`** \<String>
          - Decimals of the item(Smart Token/Voucher) which to drop.
    - **`totalAddresses`** \<String>
      - Total amount of account match the rule. The format is Decimaled Number.
    - **`totalAirdropAmount`** \<String>
      - Total amount of the item(Smart Token/Voucher) to drop. The format is Decimaled Number.