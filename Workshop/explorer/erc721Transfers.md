
# Erc721 Transfer Details

## GraphQL API

- Query Example
  ```javascript
  query erc721Transfers {
    erc721Transfers(first: 5) {
      pageInfo {
        endCursor
        hasNextPage
      }
      edges {
        node {
          transaction
          from
          to
          age
          tokenId
          contract {
            symbol
            contract
          }
        }
      }
    }
  }
  ```
