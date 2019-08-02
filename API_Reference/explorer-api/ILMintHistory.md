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
  ``` js
  
  ```

### Response
```json

```

### Parameters
#### Request
- `first` \<String>
- `after` \<Int>

#### Response
- `consumeGas` <consumeGasConnection!>
  - `pageInfo` <PageInfo!>``
  - `edges` <[consumeGasEdge]>
    - `node` <consumeGasType>
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

## getAllGasConsumeHistoryByAddress

### GraphQL Query
```js
query consumeGasByAddress {
  engine {
    consumeGasByAddress(
      address: "0x3be8698c3de5c88991162de44f33c9dd3f52ed13"
      first: 2
    ) {gas
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
          "hasNextPage": false,
          "endCursor": "MTIxNjc5MzAsMSwz"
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
          },
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6fWMFhCjXR1dyK5RMNhJejc3fu1-ht8ZgdqatJ5Q24yosMw",
              "block": "12260677",
              "transactionHash": "0x7d63058428d74757722b944c36125e8dcddfbb5fa1b7c66076a6ad279436e32a",
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
              "age": "1563351148000"
            },
            "cursor": "MTIyNjA2NzcsMSwz"
          },
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6VfraFDTw3m-QbCMkqZnrpEs1Md-uWMcBHrt4K36rYN4sMw",
              "block": "12240517",
              "transactionHash": "0x55fada1434f0de6f906c2324a999eba44b3531dfae58c7011ebb782b7eab60de",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "corporation": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "initiator": "0x501fee0c8eaf69ef3dd200dc9298be03d1b42c04",
              "key": "0x95fe0ea45cb20d05c33eba57112209523d66fc1a78b12ac25d5ab730eaf75bf2",
              "price": {
                "denominator": "1",
                "numerator": "87"
              },
              "amount": {
                "denominator": "1",
                "numerator": "1"
              },
              "age": "1563270432000"
            },
            "cursor": "MTIyNDA1MTcsMSwz"
          },
          {
            "node": {
              "id": "Q29uc3VtZUdhc1R5cGU6OxzObImiwnleSVJBW2xgvTwOa8Vfc6l6rge7Ck7lnHgsMw",
              "block": "12167930",
              "transactionHash": "0x3b1cce6c89a2c2795e4952415b6c60bd3c0e6bc55f73a97aae07bb0a4ee59c78",
              "transactionLogIndex": "3",
              "contract": "0x6a34be0f4916e9855a0025f63614b239238efaca",
              "corporation": "0x3be8698c3de5c88991162de44f33c9dd3f52ed13",
              "initiator": "0x501fee0c8eaf69ef3dd200dc9298be03d1b42c04",
              "key": "0x95fe0ea45cb20d05c33eba57112209523d66fc1a78b12ac25d5ab730eaf75bf2",
              "price": {
                "denominator": "1",
                "numerator": "87"
              },
              "amount": {
                "denominator": "1",
                "numerator": "1"
              },
              "age": "1562911240000"
            },
            "cursor": "MTIxNjc5MzAsMSwz"
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
- `consumeGas` <consumeGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[consumeGasEdge]>
    - `node` <consumeGasType>
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