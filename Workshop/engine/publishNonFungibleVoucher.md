# Publish NonFungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation publishNonFungibleVoucher {
    publishNonFungibleVoucher(input: {
      name: "Blind NFT",
      symbol: "BNFT",
      consumable: true,
      vendible: true
    }) {
      transaction
      submitToken
      ethereumKey
    }
  }

  ```



- **permission**
  - Only Issuer can use
