# Transfer Ether

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferEther {
    sendEther (input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8" # The address of the receiver
      value: "100000000000000000" # Transfer 0.1 Ether to the receiver
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - The response of this API will contain `data.sendEther.transaction` and `data.sendEther.submitToken` for signing before submitting transaction

- Permission
  - Master, Issuer and End-User are permitted to invoke this API
