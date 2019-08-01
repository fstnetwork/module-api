
# Fill gas

## GraphQL API

- Mutation Example
  ```javascript
  mutation fillGas{
    fillGas(input: {
      receiver: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      value: "1000000000000000000"
    }){
      transaction
      submitToken
    }
  }
  ```



- **permission**
  - Anyone can estimate

