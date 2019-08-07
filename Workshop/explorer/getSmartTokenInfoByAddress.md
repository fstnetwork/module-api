
# Get smart token info by contract address

## GraphQL API

- Query Example
  ```javascript
  query getSmartTokenInfoByAddress {
    SmartToken(contract: "0xec51bbfb1c77459e73aacbf217b17a9775068d57") {
      info {
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
      holders {
        pageInfo {
          endCursor
          hasNextPage
        }
        totalCount
        edges {
          cursor
          node {
            contract
            owner
            value
            type: __typename
          }
        }
      }
      transfers {
        pageInfo {
          endCursor
          hasNextPage
        }
        totalCount
        edges {
          cursor
          node {
            id
            block
            contract {
              contract
              name
              symbol
              decimals
              totalSupply
              standard
              type: __typename
            }
            from
            to
            value
            transaction
            transactionIndex
            transactionLogIndex
            age
            consume
            type: __typename
          }
        }
      }
      transactions {
        pageInfo {
          endCursor
          hasNextPage
        }
        edges {
          cursor
          node {
            id
            transaction
            nonce
            block
            blockHash
            transactionIndex
            age
            from
            to
            value
            gasPrice
            gas
            gasUsed
            cumulativeGasUsed
            input
            creates
            publicKey
            v
            r
            s
            root
            status
            type: __typename
          }
        }
      }
    }
  }
  ```
