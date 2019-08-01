
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


- **permission**
  - Only Master and Issuer could use