
# get all issue token

## GraphQL API

- Query Example
  ```javascript
  query getTokenIssuenceHistory {
    issueTokens {
      pageInfo {
        endCursor
        hasNextPage
      }
      totalCount
      edges {
        node {
          block
          blockHash
          transaction
          transactionIndex
          transactionLogIndex
          contract
          issuer
          auditor
          ce
          token
          age
        }
      }
    }
  }

  ```
