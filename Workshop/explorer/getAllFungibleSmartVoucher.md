
# Get all fungible Voucher

## GraphQL API

- Query Example
  ```javascript
  query PublishFungibleVoucherAll {
    publishFungibleVouchers {
      pageInfo {
        endCursor
        hasNextPage
      }
      totalCount
      edges {
        cursor
        node {
          id
          block
          blockHash
          transaction
          transactionIndex
          transactionLogIndex
          contract
          publisher
          voucher
          age
          type: __typename
        }
      }
    }
  }

  ```
