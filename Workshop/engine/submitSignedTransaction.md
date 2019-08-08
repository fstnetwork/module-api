
# Submit signed transaction

## GraphQL API

- Mutation Example
  ```javascript
  mutation submitSignedTransaction {
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

- Expiry time of `submitToken` is 10 minutes.
