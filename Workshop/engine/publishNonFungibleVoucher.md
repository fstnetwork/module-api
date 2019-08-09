# Publish Non-Fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation publishNonFungibleVoucher {
    publishNonFungibleVoucher(input: {
      name: "A NFT",
      symbol: "ANFT",
      consumable: true,
      vendible: true
    }) {
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - This ledger creation consumes 600 Master Service Gas
  - The response of this API will contain `data.publishNonFungibleVoucher.transaction` and `data.publishNonFungibleVoucher.submitToken` for signing before submitting transaction

- Permission
  - Only Issuer is permitted to invoke this API
