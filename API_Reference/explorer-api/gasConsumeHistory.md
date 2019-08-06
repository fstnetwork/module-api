# Gas Consume History
- Endpoint
  - For development: `http://api.dev.fstk.io/explorer`
  - Customer: *`CUSTOMER_URL`*
- Method: `POST`
- Header:
  - accept: `application/json`
  - content-type: `application/json`
- API Query
  - [getAllGasConsumeHistory](#getAllGasConsumeHistory)
  - [getAllGasConsumeHistoryByAddress](#getAllGasConsumeHistoryByAddress)

## getAllGasConsumeHistory
### GraphQL Query
``` js
query consumeGas {
  engine {
    consumeGas(first: 2) {
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
          corporation
          initiator
          key
          price {
            denominator
            numerator
          }
          amount {
            denominator
            numerator
          }
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
      "consumeGas": {
        "pageInfo": {
          "hasNextPage": true,
          "endCursor": "MTIyNjA3MDgsMSwz"
        },
        "edges": [
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6W3DJifY9kihOtm9S33bOkSVQmIMZlqKJJXW1PQJrLjIsMw",
              "block": "12302943",
              "transactionHash": "0x5b70c989f63d92284eb66f52df76ce91255098831996a2892575b53d026b2e32",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "corporation": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "initiator": "0x501fee0c8eaf69ef3dd200dc9298be03d1b42c04",
              "key": "0x83e06c1200634b3e8264119df9f596b1c22779c2d529d10b156968d7d130f5b0",
              "price": {
                "denominator": "9",
                "numerator": "3125000000"
              },
              "amount": {
                "denominator": "1",
                "numerator": "1728000000000"
              },
              "age": "1563520352000"
            },
            "cursor": "MTIzMDI5NDMsMCwz"
          },
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6m32CqnDnlFcgso0gnYRsLoRf7ebN300kNg7w6lakim8sMw",
              "block": "12260708",
              "transactionHash": "0x9b7d82aa70e7945720b28d209d846c2e845fede6cddf4d24360ef0ea56a48a6f",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "corporation": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "initiator": "0x501fee0c8eaf69ef3dd200dc9298be03d1b42c04",
              "key": "0x83e06c1200634b3e8264119df9f596b1c22779c2d529d10b156968d7d130f5b0",
              "price": {
                "denominator": "9",
                "numerator": "3125000000"
              },
              "amount": {
                "denominator": "1",
                "numerator": "1728000000000"
              },
              "age": "1563351272000"
            },
            "cursor": "MTIyNjA3MDgsMSwz"
          }
        ],
        "totalCount": 59
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
- `consumeGas` <ＣonsumeGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[ＣonsumeGasEdge]>
    - `node` <ＣonsumeGasType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `corporation` <String!>
      - `initiator` <String!>
      - `key` <String!>
      - `price` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `amount` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>

---

## getAllGasConsumeHistoryByAddress

### GraphQL Query
```js
query purchase {
  engine {
    consumeGasByAddress(
      address: "0x3be8698c3de5c88991162de44f33c9dd3f52ed13"
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
          corporation
          initiator
          key
          price {
            denominator
            numerator
          }
          amount {
            denominator
            numerator
          }
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
      "consumeGasByAddress": {
        "pageInfo": {
          "hasNextPage": true,
          "endCursor": "MTIyNjA3MDgsMSwz"
        },
        "edges": [
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6W3DJifY9kihOtm9S33bOkSVQmIMZlqKJJXW1PQJrLjIsMw",
              "block": "12302943",
              "transactionHash": "0x5b70c989f63d92284eb66f52df76ce91255098831996a2892575b53d026b2e32",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "corporation": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "initiator": "0x501fee0c8eaf69ef3dd200dc9298be03d1b42c04",
              "key": "0x83e06c1200634b3e8264119df9f596b1c22779c2d529d10b156968d7d130f5b0",
              "price": {
                "denominator": "9",
                "numerator": "3125000000"
              },
              "amount": {
                "denominator": "1",
                "numerator": "1728000000000"
              },
              "age": "1563520352000"
            },
            "cursor": "MTIzMDI5NDMsMCwz"
          },
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6m32CqnDnlFcgso0gnYRsLoRf7ebN300kNg7w6lakim8sMw",
              "block": "12260708",
              "transactionHash": "0x9b7d82aa70e7945720b28d209d846c2e845fede6cddf4d24360ef0ea56a48a6f",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "corporation": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "initiator": "0x501fee0c8eaf69ef3dd200dc9298be03d1b42c04",
              "key": "0x83e06c1200634b3e8264119df9f596b1c22779c2d529d10b156968d7d130f5b0",
              "price": {
                "denominator": "9",
                "numerator": "3125000000"
              },
              "amount": {
                "denominator": "1",
                "numerator": "1728000000000"
              },
              "age": "1563351272000"
            },
            "cursor": "MTIyNjA3MDgsMSwz"
          }
        ],
        "totalCount": 5
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
- `consumeGas` <ＣonsumeGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[ＣonsumeGasEdge]>
    - `node` <ＣonsumeGasType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `corporation` <String!>
      - `initiator` <String!>
      - `key` <String!>
      - `price` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `amount` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>