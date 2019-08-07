
# mint IL list

## GraphQL API

- Query Example
  ```javascript
  query getILMintHistory {
    engine{
      mintIL(first: 5){
        pageInfo{
          hasNextPage
          endCursor
        }
        edges{
          node{
            id
            block
            transactionHash
            transactionLogIndex
            contract
            to
            tokenId
            tokenUri
            age
          }
        }
        totalCount
      }
    }
  }
  ```
