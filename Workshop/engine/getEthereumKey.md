
# Get ethereum key

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


- Parameter
  - `ethereumKey` decrypt Ethereum key JSON
- Permission
  - Master, Issuer and End User have permission to use this API