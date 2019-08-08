
# Transfer Master Token by Master

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferMasterToken {
    transferMasterToken(input: {
      issuer: "0x001f74990fb6700262363e56cc8c917566d7c56a" # The receiver of Master Token
      value: "1000000000000000000000" # Transfer 1000 Master Token
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


- Permission
  - Only Master has permission to use this API