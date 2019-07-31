
# Erc721 Token Basic Details

## GraphQL API

- Query Example
  ```javascript
  query erc721TokenBasic {
    erc721TokenBasic(contract: "0xd57265a096c0b1de7e92db8f59072083048477d9") {
      info {
        contract
        name
        symbol
        totalSupply
      }
      holders(first: 5) {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          node {
            owner
            value
            contract
          }
        }
      }
      transfers(first: 5) {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          node {
            transaction
            from
            to
            tokenId
            age
            contract {
              symbol
              contract
            }
          }
        }
      }
      transactions(first: 5) {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          node {
            transaction
            block
            age
            from
            to
            transactionIndex
          }
        }
      }
    }
  }
  ```
