
# Erc721 Token List

## GraphQL API

- Query Example
  ```javascript
  query getNonfungibleTokenList {
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
