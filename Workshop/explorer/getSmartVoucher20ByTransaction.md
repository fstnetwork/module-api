
# Publish Fungible Voucher By Transaction

## GraphQL API

- Query Example
  ```javascript
  query PublishFungibleVoucherByTransaction {
    transaction(
      transaction: "0x9b7d82aa70e7945720b28d209d846c2e845fede6cddf4d24360ef0ea56a48a6f"
    ) {
      publishFungibleVoucher {
        pageInfo {
          endCursor
          hasNextPage
        }
        totalCount
        edges {
          node {
            id
            block
            blockHash
            transaction
            transactionIndex
            transactionLogIndex
            contract
            publisher
            voucher
            age
            type: __typename
          }
        }
      }
    }
  }

  ```
