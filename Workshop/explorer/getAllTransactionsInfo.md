
# Get transactions

## GraphQL API

- Query Example
  ```javascript
  query getAllTransactionsInfo {
    transactions(first: 5) {
      pageInfo {
        endCursor
        hasNextPage
      }
      edges {
        node {
          transaction
          from
          to
          block
          status
          age
          creates
        }
      }
    }
  }
  ```
