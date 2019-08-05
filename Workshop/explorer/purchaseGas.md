
# purchase gas list

## GraphQL API

- Query Example
  ```javascript
  query purchaseGas {
    engine {
      purchaseGas(first: 5) {
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
