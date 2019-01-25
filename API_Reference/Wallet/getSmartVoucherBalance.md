
# Get Smart Token Holders

You are able to fetch all your Smart Voucher Balance via this API.

## GraphQL API

- Query String
  ```
  query getSmartVoucherBalance {
    me {
      voucherBalances {
        totalCount
        pageInfo {
          hasNextPage
          hasPreviousPage
          startCursor
          endCursor
        }
        edges {
          cursor
          node {
            voucher {
              id
              contractAddress
              name
              symbol
              decimals
            }
            value
          }
        }
      }
    }
  }
  ```
  
- HTTP Headers 
  ```
  {
    "accept": "application/json",
    "content-type": "application/json",
    "authorization": "bearer [JWT Web-to-Server access token]"
  }
  ```
## HTTP Request and Response
### Request

- URL
  - For development: `https://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`

- Method: `POST`

- Headers
  - accept: `application/json`
  - content-type: `application/json` 
  - authorization: `Bearer [JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
      ```

- Body
  ``` 
  { 
    "query": "query getSmartVoucherBalance { me { voucherBalances { totalCount  pageInfo { hasNextPage  hasPreviousPage  startCursor  endCursor  } edges { cursor node { voucher { id contractAddress name symbol decimals } value } } } } }" 
  }
  ```
  
  The value of `query` in the body is a `String`. 
  

## Response
_(sample)_
```

{
  "data": {
    "me": {
      "voucherBalances": {
        "totalCount": 5,
        "pageInfo": {
          "hasNextPage": false,
          "hasPreviousPage": false,
          "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
          "endCursor": "YXJyYXljb25uZWN0aW9uOjQ="
        },
        "edges": [
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
            "node": {
              "voucher": {
                "id": "Vm91Y2hlcjouwoLCqkAaw7ARw6nCujsTWATDlcK8DQ==",
                "contractAddress": "0xf1ead68d94f78d83234d96e45ee1034441b05e35",
                "name": "Green plant",
                "symbol": "WHOUSE_GPLANT",
                "decimals": "0"
              },
              "value": "1919"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
            "node": {
              "voucher": {
                "id": "Vm91Y2hlcjrDoHo8wqIaw7IRw6nCujtLwr/DkzDDvCI=",
                "contractAddress": "0x4b468dfb1223dfbfb2c639076fd9fd04517463a1",
                "name": "Light",
                "symbol": "WHOUSE_LIGHT",
                "decimals": "0"
              },
              "value": "3295"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
            "node": {
              "voucher": {
                "id": "Vm91Y2hlcjrCs8K3GEoaw78Rw6nCujtXwqfCnsKodcKT",
                "contractAddress": "0xc8ae84a07ad40f847650076f125b0f1a82193488",
                "name": "window in the side",
                "symbol": "WHOUSE_WINITS",
                "decimals": "0"
              },
              "value": "27719"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
            "node": {
              "voucher": {
                "id": "Vm91Y2hlcjoBdRLCmBsBEcOpwro7w7PCiMOZEQjCqw==",
                "contractAddress": "0x72eb714d79dfe1d76c175c9376e49db8e683e385",
                "name": "floor",
                "symbol": "WHOUSE_FLOOR",
                "decimals": "0"
              },
              "value": "42950"
            }
          },
          {
            "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
            "node": {
              "voucher": {
                "id": "Vm91Y2hlcjrCixrDosOOHsOqEcOpwro7I8OqwoPCpmUe",
                "contractAddress": "0x684cf7f33b54a377bdd22cdae471227cdd37cada",
                "name": "qwerqwer",
                "symbol": "WHOUSE_QWER",
                "decimals": "0"
              },
              "value": "4000"
            }
          }
        ]
      }
    }
  }
}

```

## Parameters
### Response
- **`voucherBalance`** \<VoucherBalance1Connection>
  - **`totalCount`** \<Int>
    - TotalCount of all data
  - **`pageInfo`** \<PageInfo>
    - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)
    - **`hasNextPage`** \<boolean>
    - **`hasPreviousPage`** \<boolean>
    - **`startCursor`** \<string>
    - **`endCursor`** \<string>
  - **`edges`** \<VoucherBalance1Edge>
    - **`cursor`** \<String>
    - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
    - **`node`** \<VoucherBalance>
      - **`voucher`** \<Voucher>
        - **`id`** \<ID>
        - id of the voucher
        - **`contractAddress`** \<string>
        - contractAddress of the voucher
        - **`name`** \<string>
        - name of the voucher
        - **`symbol`** \<string>
        - symbol of the voucher
        - **`decimals`** \<int>
        - decimals of the voucher
      - **`value`** \<string>
        - voucher balance of query user
