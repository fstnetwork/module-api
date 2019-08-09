# Create an Issuer and transfer essential assets to the Issuser

## GraphQL API

- Mutation Example
  ```javascript
  mutation createUserAndTransferAsset {
    createUserV2(
      input: {
        id: "issuer_abc"
        password: "A PASSWORD"
        userPassphrase: "A PASSPHARSE"
        role: 1
        etherValue: "2000000000000000" # Transfer 0.002 Ether to the Issuer
        masterTokenValue: "1000000000000000000000" # Transfer 1,000 Master Token to the Issuer
        masterPassphrase: "A PASSPHARSE"
        tokenId: "658968545" # Random unique string
        tokenUri: "98db50f47eea00e8090d" # Random unique string
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

- Annotations for the parameters and attributes
  - `id` is a global identifier, it must be a unique string
  - `role`
    - 1 : Issuer
  - `password` is required for sign in for this user
  - `userPassphrase` is the passphrase for encrypting randomly created private key for this user
  - `masterPassphrase` is Master's EthereumKey passphrase
  - `tokenId` is a global identifier, it must be a unique string (This will be the unique Id of the newly-minted Ledger Initiation License)
  - `tokenUri` is a global identifier, it must be a unique string (This will be the Metadata URI of the newly-minted Ledger Initiation License)

- Permission
  - Only Master is permitted to invoke this API
