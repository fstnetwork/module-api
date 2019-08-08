
# Create an issuer or an enduser

## GraphQL API

- Mutation Example
  ```javascript
  mutation createUser {
    createUser(
      input: {
        id: "issuer_abc"
        password: "12345678"
        passphrase: "12345678"
        firstName: "first"
        lastName: "last"
        role: 1
      }
    ) {
      uid
      keystore
    }
  }
  ```



- Parameter
  - `id` is a global identifier
  - `role`
    - 0: Master (Master CANNOT be created by API)
    - 1: Issuer
    - 2: End User
  - `password` is required for sign in
  - `passphrase` is required to decrypt Ethereum key JSON and sign the transaction
  - `keystore` is an encrypted wallet private key

- Permission
  - Only Master can create Issuer
  - Master and Issuer can create End User