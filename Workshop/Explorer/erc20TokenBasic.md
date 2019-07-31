
# Erc20 Token Basic

## sub Query
  - info
  - holders
  - transfers
  - transactions


## GraphQL API

- Query Example
  ```javascript
  query erc20TokenBasic {
    erc20TokenBasic(contract: "0x87e1a234a979ff839e636f077a680a2ca0342143") {
      info {
        contract
        name
        symbol
        decimals
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
            value
            age
            contract {
              symbol
              decimals
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
