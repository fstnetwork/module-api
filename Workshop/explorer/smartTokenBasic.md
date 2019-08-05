
# Get smart token basic details

## GraphQL API

- Query Example
  ```javascript
  query SmartTokenBasic {
    SmartToken(contract: "0x50585ba341da2dda160a5679bdd69193cd20aca0"){
			info {
        contract
        transaction
        name
        symbol
        totalSupply
        metadata
        website
        description
        transferability
        mintability
        consumability
        delegatability
        approvability
        age
        standard
        vouchers {
          pageInfo {
            endCursor
            hasNextPage
          }
          totalCount
        }
        type: __typename
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
              totalSupply
              standard
              type: __typename
            }
            from
            to
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
