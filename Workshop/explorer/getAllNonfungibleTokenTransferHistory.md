
# Get all Nonfungible Token transfer history

## GraphQL API

- Query Example
  ```javascript
  query getAllNonfungibleTokenTransferHistory {
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
