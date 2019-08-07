
# Get Fungible Token List

## GraphQL API

- Query Example
  ```javascript
  query getFungibleTokenList {
    erc20TokenList(first: 5) {
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
