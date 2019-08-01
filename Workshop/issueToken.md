
# Issue token by issuer

## GraphQL API

- Mutation Example
  ```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: "556",
      name: "bosin test"
      symbol: "BST"
      supply: "25000000000000000000000000"
      vendible: true
      numerator: 1,
      denominator: 1
    }){
      transaction
      submitToken
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
    - fillGas to issuer
      - signTx
      - submitTx
    - issueToken
      - signTx
      - submitTx


- Method1.
  - Auth: Master
    - mintIL to issuer
    - transferMasterToken  to issuer
  - Auth: Issuer
    - fillGas to issuer
      - signTx
      - submitTx
    - issueToken
      - signTx
      - submitTx


- Method2.
  - Auth: Master
    - mintIL to issuer
    - transferMasterToken to master
    - fillGas to issuer
      - signTx
      - submitTx
  - Auth: Issuer
    - issueToken
      - signTx
      - submitTx