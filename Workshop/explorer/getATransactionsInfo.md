
# Transactions

## GraphQL API

- Query Example
  ```javascript
  query getATransactionsInfo {
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
