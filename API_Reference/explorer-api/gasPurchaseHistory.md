# Gas Purchase History
- Endpoint
  - For development: `http://api.dev.fstk.io/explorer`
  - Customer: *`CUSTOMER_URL`*
- Method: `POST`
- Header:
  - accept: `application/json`
  - content-type: `application/json`
- API Query
  - [getAllGasPurchaseHistory](#getAllGasPurchaseHistory)
  - [getAllGasPurchaseHistoryByAddress](#getAllGasPurchaseHistoryByAddress)

## getAllGasPurchaseHistory
### GraphQL Query
``` js
query purchaseGas {
  engine {
    purchaseGas(first: 2) {
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
          from
          receiver
          amount
          age
        }
        cursor
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
      "purchaseGas": {
        "pageInfo": {
          "hasNextPage": true,
          "endCursor": "MTIxNjc4OTQsMCwz"
        },
        "edges": [
          {
            "node": {
              "id": "UHVyY2hhc2VHYXNUeXBlOuwDQPx0Q5AsPNjt0ZGTU_0OGx7vZCoa92qA0xah27SVLDM",
              "block": "12260664",
              "transactionHash": "0xec0340fc7443902c3cd8edd1919353fd0e1b1eef642a1af76a80d316a1dbb495",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "from": "0xf9d1f584748ab74cb23905d46a3e9bb0fa805b9d",
              "receiver": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "amount": "20000000000000000000000",
              "age": "1563351096000"
            },
            "cursor": "MTIyNjA2NjQsMSwz"
          },
          {
            "node": {
              "id": "UHVyY2hhc2VHYXNUeXBlOtbGz-ZH8FmiVIBeRyqg74-8J7acTFJqt_vWqBkIMzIdLDM",
              "block": "12259379",
              "transactionHash": "0xd6c6cfe647f059a254805e472aa0ef8fbc27b69c4c526ab7fbd6a8190833321d",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "from": "0xf9d1f584748ab74cb23905d46a3e9bb0fa805b9d",
              "receiver": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "amount": "100000000000000000000",
              "age": "1563345952000"
            },
            "cursor": "MTIyNTkzNzksMCwz"
          }
        ],
        "totalCount": 18
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
- `PurchaseGas` <PurchaseGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[PurchaseGasEdge]>
    - `node` <PurchaseGasType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `from` <String!>
      - `receiver` <String!>
      - `amount` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>

---

## getAllGasPurchaseHistoryByAddress

### GraphQL Query
```js
query purchaseGasByAddress {
  engine {
    purchaseGasByAddress(
      address: "0xf9d1f584748ab74cb23905d46a3e9bb0fa805b9d"
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
          from
          receiver
          amount
          age
        }
        cursor
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
      "purchaseGasByAddress": {
        "pageInfo": {
          "hasNextPage": true,
          "endCursor": "MTIyNTkzNzksMCwz"
        },
        "edges": [
          {
            "node": {
              "id": "UHVyY2hhc2VHYXNUeXBlOuwDQPx0Q5AsPNjt0ZGTU_0OGx7vZCoa92qA0xah27SVLDM",
              "block": "12260664",
              "transactionHash": "0xec0340fc7443902c3cd8edd1919353fd0e1b1eef642a1af76a80d316a1dbb495",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "from": "0xf9d1f584748ab74cb23905d46a3e9bb0fa805b9d",
              "receiver": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "amount": "20000000000000000000000",
              "age": "1563351096000"
            },
            "cursor": "MTIyNjA2NjQsMSwz"
          },
          {
            "node": {
              "id": "UHVyY2hhc2VHYXNUeXBlOtbGz-ZH8FmiVIBeRyqg74-8J7acTFJqt_vWqBkIMzIdLDM",
              "block": "12259379",
              "transactionHash": "0xd6c6cfe647f059a254805e472aa0ef8fbc27b69c4c526ab7fbd6a8190833321d",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "from": "0xf9d1f584748ab74cb23905d46a3e9bb0fa805b9d",
              "receiver": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "amount": "100000000000000000000",
              "age": "1563345952000"
            },
            "cursor": "MTIyNTkzNzksMCwz"
          }
        ],
        "totalCount": 7
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
- `PurchaseGas` <PurchaseGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[PurchaseGasEdge]>
    - `node` <PurchaseGasType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `from` <String!>
      - `receiver` <String!>
      - `amount` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>