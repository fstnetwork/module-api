
# Get blocks details

## GraphQL API

- Query Example
  ```javascript
  query getBlocksInfo {
    blocks(first: 5) {
      pageInfo {
        endCursor
        hasNextPage
      }
      edges {
        node {
          block
          blockHash
          author
          age
          transactions {
            totalCount
          }
        }
      }
    }
  }
  ```
