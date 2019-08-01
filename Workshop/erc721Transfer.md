
# Transfer erc721

## GraphQL API

- Mutation Example
  ```javascript
  mutation erc721Transfer {
    erc721Transfer (input: {
      contract: "0xd57265a096c0b1de7e92db8f59072083048477d9",
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      tokenID: 554
    }){
      transaction
      submitToken
    }
  }
  ```