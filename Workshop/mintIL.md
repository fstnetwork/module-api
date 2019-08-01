
# Mint Initianation License

## GraphQL API

- Mutation Example
  ```javascript
  mutation mintIL {
    mintIL(input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      tokenID: "556"
      tokenUri: "556"
    }){
      transaction
      submitToken
    }
  }
  ```



- **permission**
  - Only Master could use
  - Master Address 要有 Ether
