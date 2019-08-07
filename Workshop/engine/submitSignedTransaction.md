
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

- expiryTime is 10 mins
