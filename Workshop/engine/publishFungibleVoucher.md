
# Publish Fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation publishFungibleVoucher {
    publishFungibleVoucher(input: {
      name: "SB Voucher"
      symbol: "SB"
      supply: "1000" # It's a natural number
      vendible: true
      numerator: "2000000000000000000"
      denominator: "1"
      isConsumable: true
      expiry: "1596844800"
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- The part of `numerator = "2000000000000000000"` (2 * (10^18)) and `denominator = "1"` indicates this 1 fungible Voucher is worth 2 Token (the parent ledger of the Voucher), so that the ratio between this fungible Voucher and the Token will be calculated and represented in terms of fraction.


- **permission**
  - Only Issuer has permission to use this API.


## Step

  - Auth: Master
    - transferMasterToken to issuer
      - signTx
      - submitTx
    - transferEther to issuer
      - signTx
      - submitTx
  - Auth: Issuer
    - fillGas to issuer
      - signTx
      - submitTx
    - publishFungibleVoucher
      - signTx
      - submitTx
