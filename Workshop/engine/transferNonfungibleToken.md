
# Transfer Nonfungible Token and Nonfungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferNonfungibleToken {
    erc721Transfer (input: {
      contract: "0x0000000000000000000000000000000000009805",
      to: "0x9ea175288fe328e2cc447bc406b26580caf4c703"
      tokenID: "658968545" # non-fungible token id
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```
- Parameter
  - `contract` is the address of the ledger
  - `tokenID` is required to identify a particular token

- Permission
  - Master, Issuer and End User have permission to use this API
<!-- 
#### Method 1
- Auth: Master (TEST)
  - mintIL
- Auth: Issuer or End User
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
- Auth: Issuer or End User
  - transfer erc721
    - signTx
    - submitTx -->


