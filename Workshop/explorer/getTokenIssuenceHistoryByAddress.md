
# Issue Token by Issuer Account

## GraphQL API

- Query Example
  ```javascript
  query getTokenIssuenceHistoryByAddress {
    account(address: "0x3be8698c3de5c88991162de44f33c9dd3f52ed13") {
      issueTokens {
        pageInfo {
          endCursor
          hasNextPage
        }
        totalCount
        edges {
          cursor
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
