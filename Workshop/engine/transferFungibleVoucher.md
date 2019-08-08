
# Transfer fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferFungibleVoucher {
    erc20Transfer (input: {
      contract: "0xff21850b2548b9dea8fb7a22866ece03b1be0b4e",
      to: "0x964e274de05ad33c1e240e7f02576a68daf40fb2"
      value: "1"
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


#### After publishFungibleVoucher
- Auth: Issuer
  - publishFungibleVoucher
    - signTx
    - submitTx
  - transferFungibleVoucher to issuer or enduser
    - signTx
    - submitTx
