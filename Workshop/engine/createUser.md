# Create an Issuer or an End-User

## GraphQL API

- Mutation Example
  ```javascript
  mutation createUser {
    createUser(
      input: {
        id: "issuer_abc"
        password: "A PASSWORD"
        passphrase: "A PASSPHARSE"
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

- Annotations for the parameters and attributes
  - `id` is a global identifier, it must be a unique string
  - `role`
    - ~~0: Master (Master CANNOT be created by this API)~~
    - 1 : Issuer
    - 2 : End-User
  - `password` is the credential in sign-in for this user
  - `passphrase` is the passphrase for encrypting randomly created private key for this user
  - `keystore` is the wallet file that contains the encrypyed private key

- Permission
  - Only Master can create Issuer (role = 1)
  - Master and Issuer can create End-User (role = 2)
