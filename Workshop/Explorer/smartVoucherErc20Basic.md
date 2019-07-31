
# Erc20 smart voucher basic detail

## GraphQL API

- Query Example
  ```javascript
  query SmartVoucher20 {
    SmartVoucher20(contract: "0x5bb38e2165d65a036df842a5286ef04278f1533f") {
      info {
        contract
        transaction
        name
        symbol
        decimals
        totalSupply
        metadata
        website
        description
        transferability
        mintability
        consumability
        delegatability
        approvability
        secureApproval
        expiry
        age
        standard
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
