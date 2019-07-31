
# Create a role

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