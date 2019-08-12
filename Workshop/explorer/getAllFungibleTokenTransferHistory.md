
# Get all fungible Token transfer history

## GraphQL API

- Query Example
  ```javascript
  query getAllFungibleTokenTransferHistory {
    erc20Transfers(first: 5) {
      pageInfo {
        endCursor
        hasNextPage
      }
      edges {
        node {
          transaction
          from
          to
          value
          age
          contract {
            symbol
            decimals
            contract
          }
        }
      }
    }
  }
  ```
