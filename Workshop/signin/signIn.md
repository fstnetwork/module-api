
# Sign in

## GraphQL API

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

- Please make the Authorization HTTP Header from the response
  ```
  {
    "authorization": "bearer $access_token"
  }
  ```