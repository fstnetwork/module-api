
# Transfer fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferFungibleVoucher {
    erc20Transfer (input: {
      contract: "0xff21850b2548b9dea8fb7a22866ece03b1be0b4e",
      to: "0x964e274de05ad33c1e240e7f02576a68daf40fb2"
      value: "1" # Transfer 1 fungible Voucher
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Parameter
  - `contract` is the address of the ledger

- Permission
  - Master, Issuer and End User have permission to use this API