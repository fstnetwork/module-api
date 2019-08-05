
# consume gas by address

## GraphQL API

- Query Example
  ```javascript
  query purchase {
    engine {
      consumeGasByAddress(
        address: "0x3be8698c3de5c88991162de44f33c9dd3f52ed13"
        first: 5
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
