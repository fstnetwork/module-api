
# Mint Initianation License

## GraphQL API

- Mutation Example
  ```javascript
  mutation mintIL {
    mintIL(input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      tokenID: "9453"
      tokenUri: "9453"
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```



- **permission**
  - Only Master can use
  - Master will be consumed ETH gasprice
