
# Get smart token list 

## GraphQL API

- Query Example
  ```javascript
  query getSmartTokenList {
    SmartTokenList {
      type: __typename
      pageInfo {
        endCursor
        hasNextPage
      }
      totalCount
      edges {
        cursor
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
          type: __typename
          vouchers {
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
      }
    }
  }

  ```
