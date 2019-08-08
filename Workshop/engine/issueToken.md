
# Issue token by issuer

## GraphQL API

- Mutation Example
  ```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: "9453",
      name: "Test Token"
      symbol: "TT"
      supply: "25000000000000000000000" # Issue 25,000 token
      vendible: true
      numerator: 1,
      denominator: 345
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```
- This part of `numerator = 1` and `denominator = 345`, this means 1 Ether is worth 345 Smart Token, so that the ratio between Ether and the Token will be calculated and represented in terms of fraction.

- Parameter
  - `tokenID` is required to identify a particular token

- Permission
  - Only Issuer has permission to use this API
  - Issue token is a one-off action for Issuer
