
# Send ether

## GraphQL API

- Mutation Example
  ```javascript
  mutation sendEther {
    sendEther (input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      value: "100000000000000000" # 0.1 eth
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```