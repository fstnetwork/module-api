# Get account information

## Table of Contents

 1. Prerequisite
 2. Get current user information via `get me`
 3. Next step

## Prerequisite

 1. 已經完成 Connect to FsTK Engine API 的流程 (上一篇)
 2. 已經安裝完成 Insomnia 或 Postman 以幫助學習與測試
 3. 已經知道如何在 http request 中指定 `authorization` header

## Get current user information via `get me`
   > 請記得加入 `` 

```graphql
{
  me {
    id
    emails {
      email
      verified
      primary
    }
    mobilePhones {
      mobilePhone
      verified
      primary
    }
    concurrencyStamp
    firstName
    lastName
    ethereumKey
    token {
      id
      name
      symbol
      decimals
      contractAddress
      totalSupply
      price {
        numerator
        denominator
      }
      logo {
        ipfs
      }
      availableAmount
      vouchers {
        edges {
          node {
            id
            name
            symbol
            decimals
            description
            contractAddress
            totalSupply
            expiry
            consumable
            availableAmount
            price {
              numerator
              denominator
            }
          }
        }
      }
    }
    campaigns {
      edges {
        node {
          id
          name
          isOpen
          description
          stages {
            name
            description
            startTime
            endTime
            cap
            sold
            priceMultiplier {
              numerator
              denominator
            }
          }
          contractAddress
          transactionHash
          type: __typename
          ... on TokenCampaign {
            token {
              id
              name
              symbol
              decimals
              totalSupply
              price {
                numerator
                denominator
              }
            }
          }
          ... on VoucherCampaign {
            voucher {
              id
              name
              symbol
              decimals
              totalSupply
              price {
                numerator
                denominator
              }
            }
          }
        }
      }
    }
    etherBalance
    gasTankBalance
    tokenBalances {
      edges {
        node {
          token {
            id
            symbol
            name
            decimals
            description
            contractAddress
            website
            logo {
              ipfs
            }
          }
          value
        }
      }
    }
    voucherBalances {
      edges {
        node {
          voucher {
            id
            name
            symbol
            decimals
            description
            contractAddress
            token {
              id
            }
          }
          value
        }
      }
    }
  }
}
```

## Next step

[Next step](./03-Send_a_Transaction.zh.md)
