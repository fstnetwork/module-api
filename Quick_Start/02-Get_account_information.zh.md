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
 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`，詳情請見上一步

 > 此操作可以得到當前使用者之所有資訊，包含身分、加密過的鑰匙 JSON、各虛擬財產餘額

 - 使用 [GraphQL](https://graphql.org/learn/) (請善用 Insomnia)

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
 
 - 使用 cURL

   ```sh
   curl --request POST \
     --url https://test.fstk.io/api \
     --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU0OTg1OTExNCwiZXhwIjoxNTQ5OTQ1NTE0LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.pseazhbfsFLRV2KqaBlO_tgZHzm3OlGhXpNlVziVEx-MiITJRF-fYzE_UEenPNY6I287Umv5p98I_9MvTE92Ug' \
     --header 'content-type: application/json' \
     --cookie locale=en \
     --data '{"query":"{\n  me {\n    id\n    emails {\n      email\n      verified\n      primary\n    }\n    mobilePhones {\n      mobilePhone\n      verified\n      primary\n    }\n    concurrencyStamp\n    firstName\n    lastName\n    ethereumKey\n    token {\n      id\n      name\n      symbol\n      decimals\n      contractAddress\n      totalSupply\n      price {\n        numerator\n        denominator\n      }\n      logo {\n        ipfs\n      }\n      availableAmount\n      vouchers {\n        edges {\n          node {\n            id\n            name\n            symbol\n            decimals\n            description\n            contractAddress\n            totalSupply\n            expiry\n            consumable\n            availableAmount\n            price {\n              numerator\n              denominator\n            }\n          }\n        }\n      }\n    }\n    campaigns {\n      edges {\n        node {\n          id\n          name\n          isOpen\n          description\n          stages {\n            name\n            description\n            startTime\n            endTime\n            cap\n            sold\n            priceMultiplier {\n              numerator\n              denominator\n            }\n          }\n          contractAddress\n          transactionHash\n          type: __typename\n          ... on TokenCampaign {\n            token {\n              id\n              name\n              symbol\n              decimals\n              totalSupply\n              price {\n                numerator\n                denominator\n              }\n            }\n          }\n          ... on VoucherCampaign {\n            voucher {\n              id\n              name\n              symbol\n              decimals\n              totalSupply\n              price {\n                numerator\n                denominator\n              }\n            }\n          }\n        }\n      }\n    }\n    etherBalance\n    gasTankBalance\n    tokenBalances {\n      edges {\n        node {\n          token {\n            id\n            symbol\n            name\n            decimals\n            description\n            contractAddress\n            website\n            logo {\n              ipfs\n            }\n          }\n          value\n        }\n      }\n    }\n    voucherBalances {\n      edges {\n        node {\n          voucher {\n            id\n            name\n            symbol\n            decimals\n            description\n            contractAddress\n            token {\n              id\n            }\n          }\n          value\n        }\n      }\n    }\n  }\n}\n"}'
   ```

 - Response

## Next step

[Next step](./03-Send_a_Transaction.zh.md)
