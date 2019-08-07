
# get all issue token

## GraphQL API

- Query Example
  ```javascript
  query IssueTokenAll {
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
