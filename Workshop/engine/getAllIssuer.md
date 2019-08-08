
# Get all Issuers

## GraphQL API

- Query Example
  ```javascript
  query getAllIssuer {
    getAllIssuer(first:5){
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
  - Only Master has permission to use this API.