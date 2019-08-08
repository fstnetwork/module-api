
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

- Expire time of `submitToken` is 10 minutes
- Parameter
  - `signedTx` is signedTransaction which is the hex string
  - `submitToken` is from Encode Ethereum Transaction