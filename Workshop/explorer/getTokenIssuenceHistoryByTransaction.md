
# Issue Token by Transaction

## GraphQL API

- Query Example
  ```javascript
  query getTokenIssuenceHistoryByTransaction {
    transaction(
      transaction: "0x2dc861824059ed164e95b8304a68090c0d0672e5ade8c211d4d5f7664d6dd9b7"
    ) {
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
  }

  ```
