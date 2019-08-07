
# Erc20 Transfer Details

## GraphQL API

- Query Example
  ```javascript
  query erc20Transfers {
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
