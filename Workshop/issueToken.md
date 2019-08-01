
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
    - Only Issuer could test


## Step

- Method0.
  - Auth: Master
    (創 issuer 的時候就直接給錢錢: mintIL, transferMasterToken)
    - createUserV2, role = 1
  - Auth: Issuer
    - fillGas 給 issuer
      - signTx
      - submitTx
    - issueToken
      - signTx
      - submitTx


- Method1.
  - Auth: Master
    - mintIL 給 issuer
    - transferMasterToken  給 issuer
  - Auth: Issuer
    - fillGas 給 issuer
      - signTx
      - submitTx
    - issueToken 
      - signTx
      - submitTx


- Method2.
  - Auth: Master
    - mintIL 給 issuer
    - transferMasterToken  給 master
    - fillGas 給 issuer
      - signTx
      - submitTx
  - Auth: Issuer
    - issueToken
      - signTx
      - submitTx