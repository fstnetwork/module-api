
# Get all Issuers

## GraphQL API

- Query Example
  ```javascript
  query getIssuer {
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
  - Only Master could test