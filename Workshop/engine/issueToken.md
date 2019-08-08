# Issue Token

## GraphQL API

- Mutation Example
  ```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: "658968545",
      name: "A Token"
      symbol: "AT"
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

- Annotations for the parameters and attributes
  - An Issuer can only issue Token one time, but an Issuer can publish Voucher multiple times as long as its Master Service Gas is enough
  - The part of `numerator = 1` and `denominator = 345` indicates 1 Ether is worth 345 Token, so that the ratio between Ether and the Token will be calculated and represented in terms of this fraction
  - `tokenID` is required to consume specific IL that the Issuer holds. The tokenID can be checked on the Explorer of the Data Network
  - The response of this API will contain `data.issueToken.transaction` and `data.issueToken.submitToken` for signing before submitting transaction

- Permission
  - Only Issuer is permitted to invoke this API
  - Issue token is a one-off action for Issuer
