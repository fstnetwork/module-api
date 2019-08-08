
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


- Parameter
  - `role`
    - 0: Master (Master CANNOT be created by API.)
    - 1: Issuer
    - 2: End User

- Permission
  - Only Master has permission to use this API