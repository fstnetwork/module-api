
# Create a role

## GraphQL API

- Mutation Example
  ```javascript
  mutation submitTransaction {
    submitTransaction(
      input: {
        signedTx: "..."
        submitToken: "...."
      }
    ) {
      transactionHash
    }
  }
  ```

- expiryTime is 10 mins

#### more detail =>  [erc20Transfer](/Workshop/engine/erc20Transfer.md)
