
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

#### more detail =>  [erc20Transfer](/Workshop/erc20Transfer.md)