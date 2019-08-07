
# Publish Fungible Voucher By Publisher

## GraphQL API

- Query Example
  ```javascript
  query PublishFungibleVoucherByPublisher {
    account(address: "0x3be8698c3de5c88991162de44f33c9dd3f52ed13") {
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
