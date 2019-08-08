
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


- **permission**
  - Master, Issuer and End User have permission to use this API.