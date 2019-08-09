# Purchase Master Service Gas

## GraphQL API

- Mutation Example
  ```javascript
  mutation purchaseMasterServiceGas{
    fillGas(input: {
      receiver: "0x001f74990fb6700262363e56cc8c917566d7c56a" # The address of the receiver
      value: "1000000000000000000000" # 1,000 Master Token will be consumed to purchase Master Service Gas
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- **1 Master Token = 1 Master Service Gas**

- Annotations for the parameters and attributes
  - Please make sure the requester (mostly Issuer but also can be Master) holding enough Master Token
  - The response of this API will contain `data.fillGas.transaction` and `data.fillGas.submitToken` for signing before submitting transaction

- Permission
  - Master, Issuer and End-User are permitted to invoke this API
