
# Account

## sub Query
  - erc20TokenBalance
    - pageInfo
    - edges
      - node
    - totalCount
  - erc20Transfers
    - pageInfo
    - edges
      - node
    - totalCount
  - erc721TokenBalance
    - pageInfo
    - node
    - totalCount
  - erc721Transfers
    - pageInfo
    - node
    - totalCount
  - transactions
    - pageInfo
    - node
    - totalCount

## GraphQL API

- Query Example
  ```javascript
  query account {
    account(address: "0x5c3cE06F77A54187a7758938Ee0D59582Ebd47CE"){
      erc20Transfers(first:5){
        pageInfo{
          endCursor
          hasNextPage
        }
        edges{
          node{
            block
            contract{
              name
              decimals
              symbol
            }
            from
            to
            value
            transaction
            age
          }
        }
        totalCount
      }
      erc20TokenBalance(first:5){
        pageInfo{
          endCursor
          hasNextPage
        }
        edges{
          node{
            contract{
              decimals
              symbol
              name
            }
            owner
            value
          }
        }
        totalCount
      }
      erc721Transfers(first:5){
        pageInfo{
          endCursor
          hasNextPage
        }
        edges{
          node{
            block
            contract{
              name
              symbol
            }
            from
            to
            tokenId
            transaction
            age
          }
        }
        totalCount
      }
      erc721TokenBalance(first:5){
        pageInfo{
          endCursor
          hasNextPage
        }
        edges{
          node{
            contract{
              symbol
              name
            }
            owner
            value
          }
        }
        totalCount
      }
      transactions(first:5) {
        pageInfo{
          endCursor
          hasNextPage
        }
        edges{
          node{
            transaction
            block
            age
            from
            to
            value
          }
        }
      }
    }
  }
  ```
