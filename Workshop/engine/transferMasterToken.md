# Transfer Master Token by Master

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferMasterToken {
    transferMasterToken(input: {
      issuer: "0x001f74990fb6700262363e56cc8c917566d7c56a" # The address of the receiver (the issuer)
      value: "1000000000000000000000" # Transfer 1,000 Master Token to the receiver
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - This API is used mostly for replenishing the Issuer with Master Token, which means the Master authorises the Issuer (as a kind of certificate) and allows the Issuer can purchase Master Service Gas
  - If Master needs to hold Master Token on its own (The Master Token is locked in a Special Multi-Sig Escrow for security), Master can assign its own address into the `issuer` param 
  - The response of this API will contain `data.transferMasterToken.transaction` and `data.transferMasterToken.submitToken` for signing before submitting transaction

- Permission
  - Only Master is permitted to invoke this API
