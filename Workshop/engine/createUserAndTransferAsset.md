
# Create an issuer and transfer Asset

## GraphQL API

- Mutation Example
  ```javascript
  mutation createUserAndTransferAsset {
    createUserV2(
      input: {
        id: "issuer_abc" # unique
        password: "12345678"
        userPassphrase: "12345678"
        role: 1
        etherValue: "2000000000000000" # 0.002 ether
        masterTokenValue: "1000000000000000000000" # 1000 master token
        tokenId: "658968545" # unique
        tokenUri: "658968545"
        masterPassphrase: "12345678"
      }
    ) {
      uid
      keystore
      etherTransaction
      masterTokenTransaction
      mintILTransaction
    }
  }
  ```

- **permission**
  - `role`
    - 0: Master (Master CANNOT be created by API.)
    - 1: Issuer
    - 2: End User
  - Only **Master** can create Issuer
  - **Master** and **Issuer** can create End User
  - Unique param: `id`, `tokenId`