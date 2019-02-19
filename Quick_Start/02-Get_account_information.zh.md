# Get account information

> 在此章節中，您將學習到如何得到當前使用者之完整個人資料

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

 > 此操作可以得到當前使用者之所有資訊，包含身分、加密過的 Ethereum Key JSON、各虛擬財產餘額

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia 進行測試)

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
 
 - Using cURL

   ```sh
   curl --request POST \
        --url https://test.fstk.io/api \
        --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU0OTg1OTExNCwiZXhwIjoxNTQ5OTQ1NTE0LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.pseazhbfsFLRV2KqaBlO_tgZHzm3OlGhXpNlVziVEx-MiITJRF-fYzE_UEenPNY6I287Umv5p98I_9MvTE92Ug' \
        --header 'content-type: application/json' \
        --cookie locale=en \
        --data '{"query":"{\n  me {\n    id\n    emails {\n      email\n      verified\n      primary\n    }\n    mobilePhones {\n      mobilePhone\n      verified\n      primary\n    }\n    concurrencyStamp\n    firstName\n    lastName\n    ethereumKey\n    token {\n      id\n      name\n      symbol\n      decimals\n      contractAddress\n      totalSupply\n      price {\n        numerator\n        denominator\n      }\n      logo {\n        ipfs\n      }\n      availableAmount\n      vouchers {\n        edges {\n          node {\n            id\n            name\n            symbol\n            decimals\n            description\n            contractAddress\n            totalSupply\n            expiry\n            consumable\n            availableAmount\n            price {\n              numerator\n              denominator\n            }\n          }\n        }\n      }\n    }\n    campaigns {\n      edges {\n        node {\n          id\n          name\n          isOpen\n          description\n          stages {\n            name\n            description\n            startTime\n            endTime\n            cap\n            sold\n            priceMultiplier {\n              numerator\n              denominator\n            }\n          }\n          contractAddress\n          transactionHash\n          type: __typename\n          ... on TokenCampaign {\n            token {\n              id\n              name\n              symbol\n              decimals\n              totalSupply\n              price {\n                numerator\n                denominator\n              }\n            }\n          }\n          ... on VoucherCampaign {\n            voucher {\n              id\n              name\n              symbol\n              decimals\n              totalSupply\n              price {\n                numerator\n                denominator\n              }\n            }\n          }\n        }\n      }\n    }\n    etherBalance\n    gasTankBalance\n    tokenBalances {\n      edges {\n        node {\n          token {\n            id\n            symbol\n            name\n            decimals\n            description\n            contractAddress\n            website\n            logo {\n              ipfs\n            }\n          }\n          value\n        }\n      }\n    }\n    voucherBalances {\n      edges {\n        node {\n          voucher {\n            id\n            name\n            symbol\n            decimals\n            description\n            contractAddress\n            token {\n              id\n            }\n          }\n          value\n        }\n      }\n    }\n  }\n}\n"}'
   ```

 - Response

   ```json
   {
     "data": {
       "me": {
         "id": "VXNlckluZm86w6bCiHPCnRPDohHDqMKCwqBje0x0w6nCsA==",
         "emails": [
           {
             "email": "quick-start@fstk.io",
             "verified": true,
             "primary": true
           }
         ],
         "mobilePhones": [
           {
             "mobilePhone": "+886912345678",
             "verified": true,
             "primary": true
           }
         ],
         "concurrencyStamp": "9a244baf-f7aa-4d59-b945-b466bd77ed73",
         "firstName": "Quick",
         "lastName": "Start",
         "ethereumKey": {
           "id": "64031d31-53a4-11e8-b00a-2b7a29c9f6b9",
           "address": "0x3a7af8b8c19c404670c1470273bca449148df4ed",
           "crypto": {
             "kdf": "scrypt",
             "mac": "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
             "cipher": "aes-128-ctr",
             "kdfparams": {
               "n": 262144,
               "p": 1,
               "r": 8,
               "salt": "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aba",
               "dklen": 32
             },
             "ciphertext": "dc1bfefb51e55f4d063f835a44f5c172008fea7eeb4b0da7fb7e50f3a18cf9ef",
             "cipherparams": {
               "iv": "b343d847b8a72ad68c6bf10866757421"
             }
           },
           "version": 3
         },
         "token": {
           "id": "VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM=",
           "name": "STIC Coin",
           "symbol": "STIC",
           "decimals": "18",
           "contractAddress": "0x00e2f43299f51457935333aef6c956b234fa4781",
           "totalSupply": "1000000000000000000000000",
           "price": {
             "numerator": "1",
             "denominator": "123"
           },
           "logo": {
             "ipfs": "zBurKAxJnaLD98yrGydZCL5XQd2cem6QPjBbcBVPX2Fh2cS8BZuGMo9sJgFxRPTGu4Zq94jL9duLonbC4zwjhLtngEuHs/image/default"
           },
           "availableAmount": "999000000000000000000000",
           "vouchers": {
             "edges": [
               {
                 "node": {
                   "id": "Vm91Y2hlcjoWNTXCuB7DpRHDqcKeGnvDhARFfsO3",
                   "name": "Partner Korea",
                   "symbol": "STIC_PARTKR",
                   "decimals": "0",
                   "description": "This is the voucher for Korea partners.",
                   "contractAddress": "0xa76bd4114ed111b2432e00a6697188b622b643e9",
                   "totalSupply": "10000",
                   "expiry": "1577807999000",
                   "consumable": true,
                   "availableAmount": "9000",
                   "price": {
                     "numerator": "1000000000000000000",
                     "denominator": "1"
                   }
                 }
               }
             ]
           }
         },
         "campaigns": {
           "edges": [
             {
               "node": {
                 "id": "Vm91Y2hlckNhbXBhaWduOsOXw43CuMOGHsOlEcOpwp4ae1LCnjEgwoY=",
                 "name": "Campaign 1",
                 "isOpen": true,
                 "description": "2018/12/31 ~ 2019/12/31\n0.9\n900",
                 "stages": [
                   {
                     "name": "Campaign 1",
                     "description": "2018/12/31 ~ 2019/12/31\n0.9\n900",
                     "startTime": "1546228800000",
                     "endTime": "1577764800000",
                     "cap": "1000",
                     "sold": "100",
                     "priceMultiplier": {
                       "numerator": "9",
                       "denominator": "10"
                     }
                   }
                 ],
                 "contractAddress": "0x45bcb73f151070e57167beffa94e1b619547a52a",
                 "transactionHash": "0xf34f3a5a475e90a5252a32348c1fa06e4ce1ce5ade3205dee3f9a1cdbdd2f324",
                 "type": "VoucherCampaign",
                 "voucher": {
                   "id": "Vm91Y2hlcjoWNTXCuB7DpRHDqcKeGnvDhARFfsO3",
                   "name": "Partner Korea",
                   "symbol": "STIC_PARTKR",
                   "decimals": "0",
                   "totalSupply": "10000",
                   "price": {
                     "numerator": "1000000000000000000",
                     "denominator": "1"
                   }
                 }
               }
             }
           ]
         },
         "etherBalance": "10842142158111111101",
         "gasTankBalance": "8885000000000000000000",
         "tokenBalances": {
           "edges": [
             {
               "node": {
                 "token": {
                   "id": "VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM=",
                   "symbol": "STIC",
                   "name": "STIC Coin",
                   "decimals": "18",
                   "description": "This is STIC Coin.",
                   "contractAddress": "0x00e2f43299f51457935333aef6c956b234fa4781",
                   "website": "stic.stic",
                   "logo": {
                     "ipfs": "zBurKAxJnaLD98yrGydZCL5XQd2cem6QPjBbcBVPX2Fh2cS8BZuGMo9sJgFxRPTGu4Zq94jL9duLonbC4zwjhLtngEuHs/image/default"
                   }
                 },
                 "value": "999000000000000000000000"
               }
             },
             {
               "node": {
                 "token": {
                   "id": "VG9rZW46ViLDr24lw40Rw6nCnhoHwoDCumxETA==",
                   "symbol": "ITIC",
                   "name": "ITIC Coin",
                   "decimals": "18",
                   "description": "This is ITIC Coin.",
                   "contractAddress": "0x70b43c75b7a6167fbd727c79fed0e7ec234eadd1",
                   "website": "itic.com",
                   "logo": {
                     "ipfs": "zBurKA3sHWRTf9BdezRAsXvYv65ZZqW1q3QpcbbgKfGax2y1zK79bcPUog4Z87THAw4QaQjMPdxUN4CcEnmbUXGu6w7xB/image/default"
                   }
                 },
                 "value": "9900000000000000000000"
               }
             },
             {
               "node": {
                 "token": {
                   "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
                   "symbol": "FST",
                   "name": "Funder Smart Token",
                   "decimals": "18",
                   "description": "FundersToken offers PaaS for business to issue Tokens and use built-in CRM Tools without programmings. FST is the service charge supporting platform features.",
                   "contractAddress": "0x3830f7af866fae79e4f6b277be17593bf96bee3b",
                   "website": "https://www.fstk.io",
                   "logo": {
                     "ipfs": "zBurK9DbDfqRN9zsUHmhW91ZJbsd8KdtjynRXaPa6j1SFqnQTreytmgzkyEacGrSVVmYaZW4Ph7GuwakSYsEUkVzgHUGV/image/default"
                   }
                 },
                 "value": "19086765432099641977312"
               }
             },
             {
               "node": {
                 "token": {
                   "id": "VG9rZW46CMOfKEYew5IRw6nCnhrCv0fDi8ORwpcr",
                   "symbol": "NBA",
                   "name": "NBA",
                   "decimals": "18",
                   "description": "The National Basketball Association (NBA) is a men's professional basketball league in North America; composed of 30 teams.",
                   "contractAddress": "0xe14d1e4d6645e1c1feb7c9190f5294601b4905ea",
                   "website": "https://nba.com",
                   "logo": {
                     "ipfs": "zBurK7Vkwy8c3LqF1iL2sUSJxGzxjVyiNEjwEZS98QPsgpzmi5AvCw38Q5jda6fNVB4W37E42SzUjV8FhHLf4N4DXpczu/image/default"
                   }
                 },
                 "value": "2536000000000000000000"
               }
             }
           ]
         },
         "voucherBalances": {
           "edges": [
             {
               "node": {
                 "voucher": {
                   "id": "Vm91Y2hlcjrCkCTCrsKAwp8HEcOnwoACAAAAAAAA",
                   "name": "FundersToken Initialisation License",
                   "symbol": "FIL",
                   "decimals": "0",
                   "description": "This is also known as the one-off licensing fee of Token issuance. One Funder Account can only issue 1 type of Smart Tokens, which means you only need 1 of FIL per account.",
                   "contractAddress": "0x7aecc9c7dc65d15aebf1e2cf7eb0fbbf38f49414",
                   "token": {
                     "id": "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA=="
                   }
                 },
                 "value": "1"
               }
             },
             {
               "node": {
                 "voucher": {
                   "id": "Vm91Y2hlcjoWNTXCuB7DpRHDqcKeGnvDhARFfsO3",
                   "name": "Partner Korea",
                   "symbol": "STIC_PARTKR",
                   "decimals": "0",
                   "description": "This is the voucher for Korea partners.",
                   "contractAddress": "0xa76bd4114ed111b2432e00a6697188b622b643e9",
                   "token": {
                     "id": "VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM="
                   }
                 },
                 "value": "9000"
               }
             },
             {
               "node": {
                 "voucher": {
                   "id": "Vm91Y2hlcjrDoAvDkQYew6cRw6nCnhpbScOdwpdnOQ==",
                   "name": "Dallas",
                   "symbol": "NBA_DALLAS",
                   "decimals": "0",
                   "description": "Dallas Mavericks",
                   "contractAddress": "0x94c8fbff2c2d22331162785aafb117ad9ce148b8",
                   "token": {
                     "id": "VG9rZW46CMOfKEYew5IRw6nCnhrCv0fDi8ORwpcr"
                   }
                 },
                 "value": "25"
               }
             },
             {
               "node": {
                 "voucher": {
                   "id": "Vm91Y2hlcjrDhcKEw4AGJcOPEcOpwp4aD8OCUQpjNg==",
                   "name": "VIP",
                   "symbol": "ITIC_VIP",
                   "decimals": "0",
                   "description": "This is VIP Voucher",
                   "contractAddress": "0x7b55015f35988ddc232c77e256dc2e3fb6a11cba",
                   "token": {
                     "id": "VG9rZW46ViLDr24lw40Rw6nCnhoHwoDCumxETA=="
                   }
                 },
                 "value": "1"
               }
             }
           ]
         }
       }
     }
   }
   ```

## Next step

[Next step](./03-Send_a_Transaction.zh.md)
