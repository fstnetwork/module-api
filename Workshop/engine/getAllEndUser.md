
# Get all End-Users

## GraphQL API

- Query Example
  ```javascript
  query getAllEndUser {
    getAllUser(first:5){
      pageInfo{
        endCursor
        hasNextPage
      }
      edges{
        node{
          id
          importedBy
          firstName
          lastName
          email
          phone
        }
      }
      totalCount
    }
  }
  ```

- Permission
  - Only Master and Issuer are permitted to invoke this API
