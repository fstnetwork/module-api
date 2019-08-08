
# Transfer ether

## GraphQL API

- Mutation Example
  ```javascript
  mutation transferEther {
    sendEther (input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      value: "100000000000000000" # Transfer 0.1 ETH
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


- Permission
  - Master, Issuer and End User have permission to use this API