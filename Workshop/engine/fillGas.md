
# Fill gas

## GraphQL API

- Mutation Example
  ```javascript
  mutation fillGas{
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
  - Anyone can estimate

## 1 transferMasterToken = 1 fillGas