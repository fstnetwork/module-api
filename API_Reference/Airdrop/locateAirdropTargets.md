
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
        distinctAccount: totalAddresses
        totalAmount: totalAirdropAmount
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
          giveTotalAccount: totalAddresses
          giveAmountAll: totalAirdropAmount
          calculateDetail: airdropDetails {
            ownerAddress: address
            value: balance
            computeResult: ruleAmount
            giveAmountResult: airdropAmount
          }
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
    "query":"\n    mutation createAirdropLocate($input: createAirdropLocateInput!) {\n      createAirdropLocate(input: $input) {\n        airdropLocate {\n          seqno: id\n          airdropItem {\n            ... on Token {\n              id\n              name\n              decimals\n            }\n          }\n          distinctAccount: totalAddresses\n          totalAmount: totalAirdropAmount\n          summary {\n            rule {\n              locateRule {\n                type\n                item {\n                  ... on Token {\n                    decimals\n                  }\n                  ... on Voucher {\n                    decimals\n                  }\n                }\n              }\n              item {\n                ... on Token {\n                  decimals\n                }\n                ... on Voucher {\n                  decimals\n                }\n              }\n              amount\n            }\n            giveTotalAccount: totalAddresses\n            giveAmountAll: totalAirdropAmount\n            calculateDetail: airdropDetails {\n              ownerAddress: address\n              value: balance\n              computeResult: ruleAmount\n              giveAmountResult: airdropAmount\n            }\n          }\n        }\n      }\n    }\n\n    ",
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
        "distinctAccount": "6",
        "totalAmount": "54000000000000000000",
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
            "giveTotalAccount": "5",
            "giveAmountAll": "24000000000000000000",
            "calculateDetail": [
              {
                "ownerAddress": "0x87a3d94465721a922c67584d6aba2d8f26822242",
                "value": "2001000000000000000000",
                "computeResult": "20",
                "giveAmountResult": "20000000000000000000"
              },
              {
                "ownerAddress": "0xc6ae6f63f9119fbc2b5d317ef74b463eff9a03dc",
                "value": "111223000000000000000",
                "computeResult": "1",
                "giveAmountResult": "1000000000000000000"
              },
              {
                "ownerAddress": "0x16cccb2d7dc6c450277e99cc3c1807d955339c29",
                "value": "100000000000000000000",
                "computeResult": "1",
                "giveAmountResult": "1000000000000000000"
              },
              {
                "ownerAddress": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
                "value": "100424242000000000000",
                "computeResult": "1",
                "giveAmountResult": "1000000000000000000"
              },
              {
                "ownerAddress": "0x1c7ba0657fc11ef8d36d99425f73777b871c9e3e",
                "value": "123123000000000000000",
                "computeResult": "1",
                "giveAmountResult": "1000000000000000000"
              },
              {
                "ownerAddress": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
                "value": "89877000000000000000",
                "computeResult": "0",
                "giveAmountResult": "0"
              }
            ]
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
            "giveTotalAccount": "6",
            "giveAmountAll": "30000000000000000000",
            "calculateDetail": [
              {
                "ownerAddress": "0x87a3d94465721a922c67584d6aba2d8f26822242",
                "value": "2001000000000000000000",
                "computeResult": "1",
                "giveAmountResult": "5000000000000000000"
              },
              {
                "ownerAddress": "0xc6ae6f63f9119fbc2b5d317ef74b463eff9a03dc",
                "value": "111223000000000000000",
                "computeResult": "1",
                "giveAmountResult": "5000000000000000000"
              },
              {
                "ownerAddress": "0x16cccb2d7dc6c450277e99cc3c1807d955339c29",
                "value": "100000000000000000000",
                "computeResult": "1",
                "giveAmountResult": "5000000000000000000"
              },
              {
                "ownerAddress": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
                "value": "100424242000000000000",
                "computeResult": "1",
                "giveAmountResult": "5000000000000000000"
              },
              {
                "ownerAddress": "0x1c7ba0657fc11ef8d36d99425f73777b871c9e3e",
                "value": "123123000000000000000",
                "computeResult": "1",
                "giveAmountResult": "5000000000000000000"
              },
              {
                "ownerAddress": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
                "value": "89877000000000000000",
                "computeResult": "1",
                "giveAmountResult": "5000000000000000000"
              }
            ]
          }
        ]
      }
    }
  }
}
```

## Parameters
### Request 
- **`rule`** \<airdropLocateRule>
  - **`type`** \<enum>
    - `EVERY` or `AT_LEAST`. `EVERY` means every condition will be counted for giveaway item calculation. _e.g. If qualification is set to be 'every' 1 FST can receive 1 FVoucher, a funder who owns 10 FST can receive 10 FVoucher through this airdrop event._ `AT_LEAST` means only the condition is greater than or equal to (&gt;=) the qualification will be counted for giveaway item calculation. _e.g. If qualification is set to be 'at least' 5 FST can receive 1 FVoucher, a funder who owns 10 FST can receive 1 FVoucher through this airdrop event._
    - Required: Yes
  - **`itemId`** \<string>
    - Item(Smart Token/Voucher) of the rule.
    - Required: Yes
  - **`amount`** \<string>
    - Amount of item(Smart Token/Voucher) of the rule.
    - Required: Yes
- **`itemID`** \<string>
  - Item(Smart Token/Voucher) to drop.
  - Required: Yes
- **`amount`** \<string>
  - Amount of item(Smart Token/Voucher) to drop if matching the condition.
  - Required: Yes

### Response
- **`airdropLocate`** \<AirdropLocate>
  - **`seqno`** \<string>
    - ID of the locate rules.
  - **`airdropItem`** \<AirdropItem>
    - **`id`** \<string>
      - ID of the item(Smart Token/Voucher) which is to drop.
    - **`name`** \<string>
      - Name of the item(Smart Token/Voucher) which is to drop.
    - **`decimals`** \<string>
      - Decimals of the item(Smart Token/Voucher) which is to drop.
  - **`distinctAccount`** \<string>
    - Amount of accounts which are match to the rules. 
  - **`totalAmount`** \<string>
    - Total amount(add each rule's `amount`) of the item(Smart Token/Voucher) to drop. The format is Decimaled Number.
  - **`summery`** \<AirdropLocateResult>
    - **`rule`** \<AirdropLocateRule>
      - **`locateRule`** \<LocateRule>
        - **`type`** \<LocateRuleEnum>
          - `EVERY` or `AT_LEAST`. \<enum>
        - **`item`** \<Token> or \<Voucher>
          - **`decimals`** \<string>
            - Decimals of the item(Smart Token/Voucher) of the rule.
      - **`item`** \<Token> or \<Voucher>
        - **`decimals`** \<string>
          - Decimals of the item(Smart Token/Voucher) which to drop.
    - **`giveTotalAccount`** \<string>
      - Total amount of account match the rule. The format is Decimaled Number.
    - **`giveAmountAll`** \<string>
      - Total amount of the item(Smart Token/Voucher) to drop. The format is Decimaled Number.
    - **`calculateDetail`** \<AirdropLocateResult Array>
      - **`ownerAddress`** \<string>
        - Address of the owner who owns the item(Smart Token/Voucher) of the rule.
      - **`value`** \<string>
        - `value`: Amount of the item of the rule the owner has. The format is Decimaled Number.
      - **`computeResult`** \<string>
        - Times of the result the address match.
      - **`giveAmountResult`** \<string>
        - Total amount of the item(Smart Token/Voucher) to drop to the address.