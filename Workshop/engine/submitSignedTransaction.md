# Submit signed transaction

## GraphQL API

- Mutation Example
  ```javascript
  mutation submitSignedTransaction {
    submitTransaction(
      input: {
        signedTx: "..." # A hex string starts with "0x"
        submitToken: "...." # A JWT string contains the info of this transaction
      }
    ) {
      transactionHash
    }
  }
  ```

- Annotations for the parameters and attributes
  - The expire time of `submitToken` is 10 minutes, it is good to sign and submit the transaction as soon as possible
  - `signedTx` is the production of the signing process, please refer to [signTransactionExample.md](/Workshop/example/signTransactionExample.md)
  - `submitToken` is from the response of the APIs that needs transaction signing
  - `transactionHash` is used to locate the transaction on the Explorer of the Data Network
