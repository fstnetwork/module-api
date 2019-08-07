
# Erc721 smart voucher basic detail

## GraphQL API

- Query Example
  ```javascript
  query getNonfungibleSmartVoucherBasic {
    SmartVoucher721(contract: "0x5ffb6669cef6f8b61ee3a61395fe43416b875e9c") {
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
            tokenId
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
