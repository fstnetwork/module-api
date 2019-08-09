# Mint Ledger Initialisation License

## GraphQL API

- Mutation Example
  ```javascript
  mutation mintIL {
    mintIL(input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8" # The address of the receiver
      tokenId: "658968545" # Random unique string
      tokenUri: "98db50f47eea00e8090d" # Random unique string
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```

- Annotations for the parameters and attributes
  - Master mints an IL to Issuer to authorise the Issuer for ledger creation
  - `tokenId` is a global identifier, it must be a unique string (This will be the unique Id of the newly-minted Ledger Initiation License)
  - `tokenUri` is a global identifier, it must be a unique string (This will be the Metadata URI of the newly-minted Ledger Initiation License)
  - The response of this API will contain `data.mintIL.transaction` and `data.mintIL.submitToken` for signing before submitting transaction

- Permission
  - Only Master is permitted to invoke this API
