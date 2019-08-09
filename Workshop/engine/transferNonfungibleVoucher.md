# Transfer Non-Fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferNonfungibleVoucher {
    erc721Transfer (input: {
      contract: "0x0000000000000000000000000000000000009805" # The address of the ledger
      to: "0x9ea175288fe328e2cc447bc406b26580caf4c703" # The address of the receiver
      tokenID: "658968545" # The unique ID of the non-fungible Voucher to be transferred
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - The response of this API will contain `data.erc721Transfer.transaction` and `data.erc721Transfer.submitToken` for signing before submitting transaction

- Permission
  - Master, Issuer and End-User are permitted to invoke this API
<!-- 
#### Method 1
- Auth: Master (TEST)
  - mintIL
- Auth: Issuer or End-User
  - transferNonfungibleToken
    - sign transaction
    - submitTx

#### Method 2
- Auth: Issuer
  - publishNonFungibleVoucher
    - signTx
    - submitTx
  - mintErc721
    - signTx
    - submitTx
- Auth: Issuer or End-User
  - transfer erc721
    - signTx
    - submitTx -->
