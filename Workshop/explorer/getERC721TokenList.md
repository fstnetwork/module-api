
# Erc721 Token List

## GraphQL API

- Query Example
  ```javascript
  query erc721TokenList {
    erc721TokenList(first: 5) {
      pageInfo {
        endCursor
        hasNextPage
      }
      edges {
        node {
          name
          symbol
          contract
          standard
        }
      }
    }
  }
  ```
