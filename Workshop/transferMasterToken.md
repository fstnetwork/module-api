
# Transfer Master Token by Master

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferMasterToken {
    transferMasterToken(input: {
      issuer: "0x001f74990fb6700262363e56cc8c917566d7c56a"
      value: "100000000000000000000" # 100
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


- **permission**
  - Only Master can use