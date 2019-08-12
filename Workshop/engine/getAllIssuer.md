
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
          address
        }
      }
      totalCount
    }
  }
  ```

- Annotations for the parameters and attributes
  - `role`
    - 1 : Issuer

- Permission
  - Only Master is permitted to invoke this API
