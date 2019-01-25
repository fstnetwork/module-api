
# Get Smart Token Holders

You are able to fetch all your Smart Token Balance via this API.

## GraphQL API

- Query String
  ```
  query getSmartTokenBalance {
    me {
      tokenBalances {
        totalCount
        pageInfo {
          hasNextPage
          hasPreviousPage
          startCursor
          endCursor
        }
        edges {
          cursor
          node {
            token {
              id
              contractAddress
              name
              symbol
              decimals
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
    "query": "query getSmartTokenBalance { me { tokenBalances { totalCount  pageInfo { hasNextPage  hasPreviousPage  startCursor  endCursor  } edges { cursor node { token { id contractAddress name symbol decimals } value } } } } }" 
  }
  ```
  
  The value of `query` in the body is a `String`. 
  

## Response
_(sample)_
```

{
  "data": {
    "me": {
      "tokenBalances": {
        "totalCount": 2,
        "pageInfo": {
          "hasNextPage": false,
          "hasPreviousPage": false,
          "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
          "endCursor": "YXJyYXljb25uZWN0aW9uOjE="
        },
        "edges": [
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "node": {
              "token": {
                "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
                "contractAddress": "0x3830f7af866fae79e4f6b277be17593bf96bee3b",
                "name": "Funder Smart Token",
                "symbol": "FST",
                "decimals": "18"
              },
              "value": "9348655677000000000000"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
            "node": {
              "token": {
                "id": "VG9rZW46wrRGCwoaw68Rw6nCujsXbMKew7Bzwqc=",
                "contractAddress": "0x4711e92ad968a6488500bc5dde2a48ee17743ab1",
                "name": "Wood House",
                "symbol": "WHOUSE",
                "decimals": "18"
              },
              "value": "11999783843443553877000000000"
            }
          }
        ]
      }
    }
  }
}

```

## Parameters
### Response
- **`tokenBalances`** \<TokenBalanceByAddressConnection>
  - **`totalCount`** \<Int>
    - TotalCount of all data
  - **`pageInfo`** \<PageInfo>
    - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)
    - **`hasNextPage`** \<boolean>
    - **`hasPreviousPage`** \<boolean>
    - **`startCursor`** \<string>
    - **`endCursor`** \<string>
  - **`edges`** \<TokenBalanceByAddressEdge>
    - **`cursor`** \<String>
    - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
    - **`node`** \<TokenBalance>
      - **`token`** \<Token>
        - **`id`** \<ID>
        - id of the token
        - **`contractAddress`** \<string>
        - contractAddress of the token
        - **`name`** \<string>
        - name of the token
        - **`symbol`** \<string>
        - symbol of the token
        - **`decimals`** \<int>
        - decimals of the token
      - **`value`** \<string>
        - token balance of query user
