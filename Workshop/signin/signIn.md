
# Sign in

## GraphQL API

- End point `/signin`
- Mutation Example
  ```javascript
  mutation signIn{
    signIn(input:{
      id: "master"
      password: "12345678"
    }){
      access_token
    }
  }
  ```

- Annotations for the parameters and attributes
  - `access_token` is a [JWT](https://jwt.io)

- Please attach the responded `access_token` to the HTTP Header to use FST Engine API
  ```
  {
    "Authorization": "Bearer $access_token"
  }
  ```
