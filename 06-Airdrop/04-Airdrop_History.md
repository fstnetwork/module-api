
# Airdrop History

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
  {  
    "query":"query{\n       airdropHistory{\n         edges{\n           node{\n             id\n             method{\n               listType\n               listId\n               targets\n             }\n           item{\n             ... on Token{\n               id\n               name\n             }\n              ... on Voucher{\n               id\n          name\n             }\n           }\n           }\n         }\n       }\n     }\n\n"
  }
  ```

  The value of `query` in the body is a `String`. 
  

## HTTP Response

(for example)
```
{  
  "data":{  
    "airdropHistory":{  
      "edges":[  
        {  
          "node":{  
            "id":"QWlyZHJvcE1pc3Npb246Mzc=",
            "method":{  
              "listType":"AirdropLocate",
              "listId":"9",
              "targets":{  
                "filters":[  
                  {  
                    "giveItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruleItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruletype":"AT_LEAST",
                    "giveAmount":"1000000000000000000",
                    "ruleAmount":"1000000000000000000"
                  }
                ]
              }
            },
            "item":{  
              "id":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "name":"SAINT MKII"
            }
          }
        },
        {  
          "node":{  
            "id":"QWlyZHJvcE1pc3Npb246NTM=",
            "method":{  
              "listType":"AirdropLocate",
              "listId":"73",
              "targets":{  
                "filters":[  
                  {  
                    "giveItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruleItem":"Vm91Y2hlcjoZPcOJPMK7EhHDqMKnfHsTwqI/woUA",
                    "ruletype":"AT_LEAST",
                    "giveAmount":"1223000000000000000",
                    "ruleAmount":"1"
                  }
                ]
              }
            },
            "item":{  
              "id":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "name":"SAINT MKII"
            }
          }
        },
        {  
          "node":{  
            "id":"QWlyZHJvcE1pc3Npb246NjE=",
            "method":{  
              "listType":"AirdropLocate",
              "listId":"89",
              "targets":{  
                "filters":[  
                  {  
                    "giveItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruleItem":"Vm91Y2hlcjoZPcOJPMK7EhHDqMKnfHsTwqI/woUA",
                    "ruletype":"AT_LEAST",
                    "giveAmount":"10000000000000000000",
                    "ruleAmount":"1"
                  }
                ]
              }
            },
            "item":{  
              "id":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "name":"SAINT MKII"
            }
          }
        },
        {  
          "node":{  
            "id":"QWlyZHJvcE1pc3Npb246NjA=",
            "method":{  
              "listType":"AirdropLocate",
              "listId":"88",
              "targets":{  
                "filters":[  
                  {  
                    "giveItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruleItem":"Vm91Y2hlcjoiwrZFw5bDhUcRw6jCp3zCu1vCnsOQw7zDrw==",
                    "ruletype":"AT_LEAST",
                    "giveAmount":"1230000000000000000",
                    "ruleAmount":"1"
                  }
                ]
              }
            },
            "item":{  
              "id":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "name":"SAINT MKII"
            }
          }
        },
        {  
          "node":{  
            "id":"QWlyZHJvcE1pc3Npb246OTE=",
            "method":{  
              "listType":"AirdropLocate",
              "listId":"108",
              "targets":{  
                "filters":[  
                  {  
                    "giveItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruleItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruletype":"EVERY",
                    "giveAmount":"1000000000000000000",
                    "ruleAmount":"100000000000000000000"
                  },
                  {  
                    "giveItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruleItem":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
                    "ruletype":"AT_LEAST",
                    "giveAmount":"5000000000000000000",
                    "ruleAmount":"3000000000000000000"
                  }
                ]
              }
            },
            "item":{  
              "id":"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/",
              "name":"SAINT MKII"
            }
          }
        }
      ]
    }
  }
}
```

## Parameters
### Response
  - `id`: Airdrop mission ID.
  - `method`
    - `listType`: Type of the list. `AirdropLocate` or `AirdropManual`.
    - `listId`: The ID of the list in each type.
    - `targets`
      - `filters`
        - `giveItem`: ID of the item(token/voucher) to drop.
        - `ruleItem`: Item(token/voucher) ID of the rule.
        - `ruletype`: `EVERY` or `AT_LEAST`
        - `giveAmount`: Total amount of item(token/voucher) to drop.
        - `ruleAmount`: Amount of item(token/voucher) to drop if match the rule.
  - `item`
    - `id`: ID of the item(token/voucher) to drop.
    - `name`: Name of the item(token/voucher) to drop.