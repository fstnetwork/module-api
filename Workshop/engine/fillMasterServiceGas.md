
# Fill gas

## GraphQL API

- Mutation Example
  ```javascript
  mutation fillMasterServiceGas{
    fillGas(input: {
      receiver: "0x001f74990fb6700262363e56cc8c917566d7c56a"
      value: "100000000000000000000"
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```



- **permission**
  - Master, Issuer and End User have permission to use this API.

- **1 Master Token = 1 Master Service Gas**