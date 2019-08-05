
# Transaction

## GraphQL API

- Query Example
  ```javascript
  query transaction {
    transaction(
      transaction: "0x12ff31b7344787cec983e0c00921bda0a29a4a525f2d8e97885fa76f3f146a42"
    ) {
      transaction
      block
      transactionIndex
      age
      from
      to
      nonce
      gas
      gasUsed
      gasPrice
      value
      input
      status
      creates
      logs(first: 100) {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          node {
            address
            topic0
            topic1
            topic2
            topic3
            data
          }
        }
      }
      erc20Transfers(first: 100) {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          node {
            from
            to
            value
            contract {
              symbol
              contract
              decimals
            }
          }
        }
      }
      erc721Transfers {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          node {
            from
            to
            tokenId
            contract {
              symbol
              contract
            }
          }
        }
      }
    }
  }
  ```
