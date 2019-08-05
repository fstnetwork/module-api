
# Erc20 Token Basic detail

## GraphQL API

- Query Example
  ```javascript
  query erc20TokenList {
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
