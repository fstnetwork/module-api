
# Get consume gas list

## GraphQL API

- Query Example
  ```javascript
  query getGasConsumeHistory {
    engine {
      consumeGas(first: 5) {
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
