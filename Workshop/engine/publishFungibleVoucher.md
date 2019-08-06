
# Publish Fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation publishFungibleVoucher {
    publishFungibleVoucher(input: {
      name: "SB Voucher"
      symbol: "SB"
      supply: "1000"
      vendible: true
      numerator: "2000000000000000000000"
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



- **permission**
  - Only Issuer can use


## Step

  - Auth: Master
    - transferMasterToken to issuer
      - signTx
      - submitTx
    - sendEther to issuer
      - signTx
      - submitTx
  - Auth: Issuer
    - fillGas to issuer
      - signTx
      - submitTx
    - publishFungibleVoucher
      - signTx
      - submitTx
