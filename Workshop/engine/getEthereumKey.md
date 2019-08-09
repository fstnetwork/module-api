
# Get Ethereum key (keystore file / wallet file / key file)

## GraphQL API

- Query Example
  ```javascript
  query getEthereumKey {
    ethereumKey {
      version
      address
      crypto
    }
  }
  ```

- Annotations for the parameters and attributes
  - `ethereumKey` contains the hmac and encrypted info of the Ethereum key file (JSON)

- Permission
  - Master, Issuer and End-User are permitted to invoke this API
