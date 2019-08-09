# Publish Fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation publishFungibleVoucher {
    publishFungibleVoucher(input: {
      name: "A Voucher"
      symbol: "AVC"
      supply: "1000" # Publish 1,000 voucher
      vendible: true
      numerator: "2000000000000000000"
      denominator: "1"
      isConsumable: true
      expiry: "1596917394" # The expire date time of this Voucher ledger in UNIX Epoch Time format (seconds, but not milliseconds)
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - The higher supply with longer expiry increases the consumption of Master Service Gas
  - 1 Voucher/day costs 0.00003 Master Service Gas, and if the cost does not exceed 600 Master Service Gas, the actual cost will be still 600 Master Service Gas to protect the Data Network from resource creation abusing
  - The part of `numerator = "2000000000000000000"` (2 * (10^18)) and `denominator = "1"` indicates this 1 fungible Voucher is worth 2 Token (the parent ledger of the Voucher), so that the ratio between this fungible Voucher and the Token will be calculated and represented in terms of fraction
  - The response of this API will contain `data.publishFungibleVoucher.transaction` and `data.publishFungibleVoucher.submitToken` for signing before submitting transaction

- Permission
  - Only Issuer is permitted to invoke this API
