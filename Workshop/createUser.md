
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

- **role**
  - 0 = Master (could not be created by APIs)
  - 1 = Issuer
  - 2 = End User

- **permission**
  - Only Master could create role = 1
  - Master and Issuer could create role = 2