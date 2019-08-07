
# SmartVoucher20List and SmartVoucher721List

## GraphQL API

- Query Example
  ```javascript
  query SmartVoucherList {
    SmartVoucherList {
      pageInfo {
        endCursor
        hasNextPage
      }
      totalCount
      edges {
        node {
          contract
          transaction
          name
          symbol
          metadata
          website
          description
          totalSupply
          transferability
          mintability
          consumability
          delegatability
          approvability
          age
          standard
          ... on SmartVoucher20InfoField {
            type: __typename
            secureApproval
            decimals
            expiry
          }
          ... on SmartVoucher721InfoField {
            type: __typename
          }
        }
      }
    }
  }

  ```
