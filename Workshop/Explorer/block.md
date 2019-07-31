
# Get a block number details

## GraphQL API

- Query Example
  ```javascript
  query block {
    block(blockNumber: "1000000") {
      blockHash
      block
      parentHash
      author
      stateRoot
      transactionRoot
      receiptsRoot
      gasUsed
      gasLimit
      extraData
      logsBloom
      difficulty
      totalDifficulty
      size
      age
      transactions {
        edges {
          node {
            transaction
          }
        }
      }
    }
  }
  ```
