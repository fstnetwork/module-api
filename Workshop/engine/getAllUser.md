
# Get all Users

## GraphQL API

- Query Example
  ```javascript
  query getAllUser {
    getAllUser(first:5){
      pageInfo{
        endCursor
        hasNextPage
      }
      edges{
        node{
          id
          role
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


- Parameter
  - `role`
    - 0: Master (Master CANNOT be created by API.)
    - 1: Issuer
    - 2: End User

- Permission
  - Master and Issuer have permission to use this API