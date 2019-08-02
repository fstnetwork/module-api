
# Issue token by issuer

## GraphQL API

- Mutation Example
  ```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: "9453",
      name: "test"
      symbol: "LT"
      supply: "25000000000000000000000000"
      vendible: true
      numerator: 1,
      denominator: 1
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


  - **permission**
    - Only Issuer can use

## Step

- Method0.
  - Auth: Master
    (Will give ethValue, mintIL and transferMasterToken when creating issuer)
    - createUserV2, role = 1
  - Auth: Issuer
    - issueToken
      - signTx
      - submitTx


- Method1. (TEST)
  - Auth: Master
    - mintIL to issuer
  - Auth: Issuer
    - issueToken
      - signTx
      - submitTx
