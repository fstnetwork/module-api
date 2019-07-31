
# Issue token by issuer

## GraphQL API

- Mutation Example
  ```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: 556,
      name: "bosin test"
      symbol: "BST"
      supply: "25000000000000000000000000"
      metadata: "dss"
      vendible: true
      numerator: 1,
      denominator: 1
    }){
      transaction
      submitToken
    }
  }
  ```