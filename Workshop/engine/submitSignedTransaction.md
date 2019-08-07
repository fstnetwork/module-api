
# Submit signed transaction

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
