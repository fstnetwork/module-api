
# Get all Users

## GraphQL API

- Query Example
  ```javascript
  query getUser {
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
