# Transfer fungible Voucher

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferFungibleVoucher {
    erc20Transfer (input: {
      contract: "0xff21850b2548b9dea8fb7a22866ece03b1be0b4e" # The address of the ledger
      to: "0x964e274de05ad33c1e240e7f02576a68daf40fb2" # The address of the receiver
      value: "1" # Transfer 1 fungible Voucher to the receiver
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - The response of this API will contain `data.erc20Transfer.transaction` and `data.erc20Transfer.submitToken` for signing before submitting transaction

- Permission
  - Master, Issuer and End-User are permitted to invoke this API
