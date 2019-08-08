
# Create an Issuer and transfer asset

## GraphQL API

- Mutation Example
  ```javascript
  mutation createUserAndTransferAsset {
    createUserV2(
      input: {
        id: "issuer_abc"
        password: "12345678"
        userPassphrase: "12345678"
        role: 1
        etherValue: "2000000000000000" # Transfer 0.002 ether
        masterTokenValue: "1000000000000000000000" # Transfer 1000 Master Token
        tokenId: "658968545"
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




- Parameter
  - `id` and `tokenId` is a global identifier
  - `role`
    - 0: Master (Master CANNOT be created by API.)
    - 1: Issuer
    - 2: End User
  - `password` is required for sign in
  - `userPassphrase` is for Issuer to decrypt Ethereum key JSON and sign the transaction
  - `masterPassphrase` is Master's passphrase
  - `tokenId` is required to identify a particular token
  - `tokenUri` is Metadata JSON Schema

- Permission
  - Only Master can create Issuer
  - Master and Issuer can create End User