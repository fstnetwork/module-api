
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
  
- HTTP Headers 
  ```
  {
    "authorization": "bearer $access_token"
  }
  ```