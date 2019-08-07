
# Get Fungible Token by contract address

## GraphQL API

- Query Example
  ```javascript
  query getFungibleTokenBasic {
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
