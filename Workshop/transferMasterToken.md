
# Transfer Master Token by Master

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferMasterToken {
    transferMasterToken(input: {
      issuer: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      value: "100000000000000000000" # 100
    }){
      transaction
      submitToken
    }
  }
  ```


- **permission**
  - Only Master could use