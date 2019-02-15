
# Get Smart Token Holders
You are able to fetch all holders of the Smart Token and amount of the Smart Token each holder owns via this API.

## GraphQL API

- Query String
  ```
  query getSmartTokenHolders {
    me {
      token {
        id
        contractAddress
        name
        symbol
        decimals
        holders {
          totalCount
          pageInfo {
            endCursor
            startCursor
            hasNextPage
            hasPreviousPage
          }
          edges {
            cursor
            node {
              address
              balance
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
    "query":"query getSmartTokenHolders {\n     me {\n     token {\n     id\n     contractAddress\n     name\n     symbol\n     decimals\n     holders {\n     totalCount\n     pageInfo {\n     endCursor\n     startCursor\n     hasNextPage\n     hasPreviousPage\n     \n}     edges {\n     cursor\n     node {\n     address\n     balance\n     }\n     }\n     }\n     }\n     }\n     }\n"
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
        "holders": {
          "totalCount": 4,
          "pageInfo": {
            "endCursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "hasNextPage": false,
            "hasPreviousPage": false
          },
          "edges": [
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
              "node": {
                "address": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
                "balance": "12000000000000000000000000000"
              }
            },
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
              "node": {
                "address": "0xc01926f281f51ace3291a8dd680b968888f13b40",
                "balance": "901180000000000000000"
              }
            },
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
              "node": {
                "address": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
                "balance": "200123123000000000000"
              }
            },
            {
              "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
              "node": {
                "address": "0xd6b672ecf2ec687befde52a30ccc313a6add7d7c",
                "balance": "125443000000000000000"
              }
            },
          ]
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
      - Smart Token decimals.
    - **`holders`** \<TokenHolderConnection>
      - **`totalCount`** \<int>
        - Total amount of the holders.
      - **`pageInfo`** \<PageInfo>
        - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
      - **`edges`** \<TokenHolderEdge>
        - **`cursor`** \<string>
          - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
        - **`node`** \<Holder>
          - **`address`** \<string>
            - The holder's address.
          - **`balance`** \<string>
            - Amount of the Smart Token the holder owns. The format is Decimaled Number.
