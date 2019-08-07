
# Get mint IL history by address

## GraphQL API

- Query Example
  ```javascript
  query getILMintHistoryByAddress {
    engine{
      mintILByAddress(address: "0xfe788a397088898783871f20fbbbcc9e84bf34bf"){
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
