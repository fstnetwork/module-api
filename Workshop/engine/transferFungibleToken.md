# Transfer fungible Token

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferFungibleToken {
    erc20Transfer (input: {
      contract: "0xe93e7a04a4b5273dee710f7263a4ca69b96424d3" # The address of the ledger
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8" # The address of the receiver
      value: "1000000000000000000" # Transfer 1 fungible Token to the receiver
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
