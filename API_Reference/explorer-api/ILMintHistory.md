# IL Mint History
- Endpoint
  - For development: `http://api.dev.fstk.io/explorer`
  - Customer: *`CUSTOMER_URL`*
- Method: `POST`
- Header:
  - accept: `application/json`
  - content-type: `application/json`
- API Query
  - [getILMintHistory](#getILMintHistory)
  - [getILMintHistoryByAddress](#getILMintHistoryByAddress)

## getAllGasConsumeHistory
### GraphQL Query
```js
query mintIL {
  engine{
    mintIL(first: 2){
      pageInfo{
        hasNextPage
        endCursor
      }
      edges{
        node{
          id
          block
          transactionHash
          transactionLogIndex
          contract
          to
          tokenId
          tokenUri
          age
        }
      }
      totalCount
    }
  }
}
```

### Response
```json
{
  "data": {
    "engine": {
      "mintIL": {
        "pageInfo": {
          "hasNextPage": false,
          "endCursor": "MTI2NjY2NTQsMCwx"
        },
        "edges": [
          {
            "node": {
              "id": "TWludElMVHlwZTqmdl7a8MiuGH2tmo0LtYxkLYodpXbzaWqKIYXbS8zT4ywx",
              "block": "12666654",
              "transactionHash": "0xa6765edaf0c8ae187dad9a8d0bb58c642d8a1da576f3696a8a2185db4bccd3e3",
              "transactionLogIndex": "1",
              "contract": "0xa3234e55904890522b71cb061d92328aa3c111be",
              "to": "0x0748ffdd7c1601c3b25922086cd29a1f83de6b4f",
              "tokenId": "999123123123",
              "tokenUri": "2222222222220000aaaaaaaacccccc",
              "age": "1564976636000"
            }
          }
        ],
        "totalCount": 1
      }
    }
  }
}
```

### Parameters
#### Request
- `first` \<String>
- `after` \<Int>

#### Response
- `mintIL` <MintILConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[MintILEdge]>
    - `node` <MintILType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `to` <String!>
      - `tokenId` <String!>
      - `tokenUri` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>

---

## getILMintHistoryByAddress

### GraphQL Query
```js
query mint {
  engine {
    mintILByAddress(
      address: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      first: 2
    ) {
      pageInfo {
        hasNextPage
        endCursor
      }
      edges {
        node {
          id
          block
          transactionHash
          transactionLogIndex
          contract
          to
          tokenId
          tokenUri
          age
        }
      }
      totalCount
    }
  }
}
```
### Response
```json
{
  "data": {
    "engine": {
      "mintILByAddress": {
        "pageInfo": {
          "hasNextPage": false,
          "endCursor": null
        },
        "edges": [],
        "totalCount": 0
      }
    }
  }
}
```
### Parameters
#### Request
- `address` <String!>
- `first` \<String>
- `after` \<Int>

#### Response
- `mintIL` <MintILConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[MintILEdge]>
    - `node` <MintILType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `to` <String!>
      - `tokenId` <String!>
      - `tokenUri` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>