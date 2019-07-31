
# purchase gas by address

## GraphQL API

- Query Example
  ```javascript
  query purchaseGasByAddress {
    engine {
      purchaseGasByAddress(
        address: "0xf9d1f584748ab74cb23905d46a3e9bb0fa805b9d"
        first: 5
      ) {
        pageInfo {
          hasNextPage
          endCursor
        }
        edges {
          node {
            id
            block
            transactionHash
            transactionLogIndex
            contract
            from
            receiver
            amount
            age
          }
          cursor
        }
        totalCount
      }
    }
  }
  ```
