
# Get Non-Fungible Smart Token list

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
