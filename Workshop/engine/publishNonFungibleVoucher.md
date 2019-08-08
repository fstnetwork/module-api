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
  - Only Issuer has permission to use this API.
