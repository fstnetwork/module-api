
# Create a role

## GraphQL API

- Mutation Example
  ```javascript
  mutation submit {
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