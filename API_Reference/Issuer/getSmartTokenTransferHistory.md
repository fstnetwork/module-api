
# Get Smart Token Transfer History
You are able to fetch all transfer history of the Smart Token(which you issued) via this API.

## GraphQL API

- Query String
  ```
  query getTokenTransferHistory {
    me {
      token {
        id
        contractAddress
        name
        symbol
        decimals
        transfers {
          pageInfo {
            endCursor
            startCursor
            hasPreviousPage
            hasNextPage
          }
          totalCount
          edges {
            cursor
            node {
              from
              to
              value
              transaction
              timestamp
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
    "query":"query getTokenTransferHistory {  me {  token { id  contractAddress name  symbol  decimals  transfers { pageInfo {  endCursor startCursor  hasPreviousPage  hasNextPage  }  totalCount  edges {  cursor  node {  from  to  value  transaction  timestamp  }  }  }  }  }  }"
  }
  ```
  The value of `query` in the body is a `String`. 


### Response
```
{
 "data": {
    "me": {
      "token": {
        "id": "VG9rZW46wrRGCwoaw68Rw6nCujsXbMKew7Bzwqc=",
        "contractAddress": "0x4711e92ad968a6488500bc5dde2a48ee17743ab1",
        "name": "Wood House",
        "symbol": "WHOUSE",
        "decimals": "18",
        "transfers": {
          "pageInfo": {
            "endCursor": "YXJyYXljb25uZWN0aW9uOjk=",
            "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "hasPreviousPage": false,
            "hasNextPage": false
          },
          "totalCount": 3,
          "edges": [
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
              "node": {
                "from": "0xc01926f281f51ace3291a8dd680b968888f13b40",
                "to": "0x430789fd8df0f6dd12fe269e025271a8aba4c27d",
                "value": "1220000000000000000",
                "transaction": "0x04860d9aa59d45d82e13d50ca5aa00c5725817fc4d2fec49dd73b2b5596fb6bf",
                "timestamp": "1547803456000"
              }
            },
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
              "node": {
                "from": "0xc01926f281f51ace3291a8dd680b968888f13b40",
                "to": "0x2534b855fed66fcac0e9c1076c75b97997bcbd57",
                "value": "1220000000000000000",
                "transaction": "0x04860d9aa59d45d82e13d50ca5aa00c5725817fc4d2fec49dd73b2b5596fb6bf",
                "timestamp": "1547803456000"
              }
            },
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
              "node": {
                "from": "0xc01926f281f51ace3291a8dd680b968888f13b40",
                "to": "0xd15bdb8f3fcd505fed25929932b7b2e03e5a057f",
                "value": "1220000000000000000",
                "transaction": "0x04860d9aa59d45d82e13d50ca5aa00c5725817fc4d2fec49dd73b2b5596fb6bf",
                "timestamp": "1547803456000"
              }
            }
        }
      }
    }
  }
}
```

## Parameters
### Response
- **`token`** \<Token>
  - **`id`** \<string>
    - Smart Token ID. ID is a global identifier.
  - **`contractAddress`** \<string>
    - Smart Token contract address.
  - **`name`** \<string>
    - Smart Token name.
  - **`symbol`** \<string>
    - Smart Token symbol.
  - **`decimals`** \<string>
    - Smart Token decimals
  - **`transfers`** \<TokenTransferConnection>
    - **`pageInfo`** \<PageInfo>
      - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
    - **`totalCount`** \<int>
      - Total amount of the Smart Token transfer.
    - **`edges`** \<TokenTransferEdge Array>
      - **`cursor`** \<string>
        - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
      - **`node`** \<Transfer>
        - **`from`** \<string>
          - Sender address.
        - **`to`** \<string>
          - Receiver address.
        - **`value`** \<string>
          - Amount of the Smart Token the sender sent.
        - **`transaction`** \<string>
          - The transaction hash of this action.
        - **`timestamp`** \<string>
          - The time that FST Network server reviced transaction. The format is Unix Timestamp in millisecond resolution.
