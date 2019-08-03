
# Transfer erc721

## GraphQL API

- Mutation Example
  ```javascript
  mutation erc721Transfer {
    erc721Transfer (input: {
      contract: "0x0000000000000000000000000000000000009805",
      to: "0x9ea175288fe328e2cc447bc406b26580caf4c703"
      tokenID: "658968545"
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


#### S1.
- Auth: Master (TEST)
  - mintIL
- Auth: Issuer or End User
  - transfer erc721
    - signTx
    - submitTx

#### S2.
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
    - submitTx


