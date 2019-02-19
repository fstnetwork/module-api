
# Get Transfer History
You are able to fetch all your transfer history via this API.

## GraphQL API

- Query String
  ```
  query getTransferHistory($first: Int, $after: String) {
    transferHistory(first: $first, after: $after) {
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
          from
          to
          symbol
          value
          decimals
          transactionHash
          type
          time
        }
      }
    }
  }              
  ```
- Query Variables
  ```
  {
    "first": 10,
    "after": ""
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
    "query": "query getTransferHistory($first: Int, $after: String) { transferHistory(first: $first, after: $after) { totalCount pageInfo { hasNextPage hasPreviousPage startCursor endCursor } edges { cursor node { from to symbol value decimals transactionHash type time } } } }".
    "variables": {
      "first": 10,
      "after": "",
    }
  }
  ```
  
  The value of `query` in the body is a `String`. 
  

### Response
```
{
  "data": {
    "transferHistory": {
      "totalCount": 2604,
      "pageInfo": {
        "hasNextPage": true,
        "hasPreviousPage": false,
        "startCursor": "YXJyYXljb25uZWN0aW9uOjA=",
        "endCursor": "YXJyYXljb25uZWN0aW9uOjk="
      },
      "edges": [
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjA=",
          "node": {
            "from": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "to": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "symbol": "FIL",
            "value": "1",
            "decimals": "0",
            "transactionHash": "0xc7cc7d8fb67f30a2f87ce49e3e7b8d36ea6df5d84007dbd20737a0c19a4e04b5",
            "type": "voucher",
            "time": "1548405592595"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjE=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "WHOUSE",
            "value": "1000000000000000000000",
            "decimals": "18",
            "transactionHash": "0xfcf064eb77dd3bde3a6d75059469ad58c06cf011693bb2b30c80e23207395132",
            "type": "token",
            "time": "1548322320637"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjI=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "FST",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0x38902e56fccc105a0dd13ce92aeb689fa233614134516e76b717b87bdffbf02d",
            "type": "token",
            "time": "1548322320633"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjM=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "FST",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0xdad7831e076a64a0ae2f343f7a332a138b311c5afe93d7248235caf0ef6fda82",
            "type": "token",
            "time": "1548322256511"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjQ=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "WHOUSE",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0x32a2306c2c5451c317c85b59208cdd8cf217485c40784c221c198b214e2bb671",
            "type": "token",
            "time": "1548322232967"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjU=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "FST",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0x7c3bb696efd729edd5ab733ffee22532a7974d8c61b3007d6ef4957ca973fb1d",
            "type": "token",
            "time": "1548322160644"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjY=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "WHOUSE",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0x8e08016230a024e74757fe8eebc2cb29ec6cf90c888c938b9aed394df43c6b79",
            "type": "token",
            "time": "1548322140594"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjc=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "WHOUSE",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0xacd14781f0fefb130b9f675306a98b10a1482fcd2fdbfdecb3c607bc93408ce9",
            "type": "token",
            "time": "1548321960398"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjg=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "WHOUSE",
            "value": "100000000000000000000",
            "decimals": "18",
            "transactionHash": "0xd731ed2d31d562cb6ca9f63ccaeebe603c0a475b69c0eac8ada4d0f211812511",
            "type": "token",
            "time": "1548321784166"
          }
        },
        {
          "cursor": "YXJyYXljb25uZWN0aW9uOjk=",
          "node": {
            "from": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb",
            "to": "0x4cf40da49f9d82819161c5db86fcb496defeb35d",
            "symbol": "FST",
            "value": "10222200000000000000",
            "decimals": "18",
            "transactionHash": "0xc5055a60d765262eae04ae5fd702a7e4f8ac9d2ce716c927cbfe1bd08cabff9c",
            "type": "token",
            "time": "1548321764656"
          }
        }
      ]
    }
  }
}

```

## Parameters
### Request 
- **`first`** \<Int>
  - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
- **`after`** \<String>
  - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_

### Response
- **`transferHistory`** \<TransferHistoryConnection>
  - **`totalCount`** \<Int!>
    - TotalCount of all data
  - **`pageInfo`** \<PageInfo!>
    - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
  - **`edges`** \<[TransferHistoryEdge]>
    - **`cursor`** \<String!>
      - _Please refer to [document of GraphQL](https://graphql.org/learn/pagination/)_
    - **`node`** \<TransferHistory!>
      - **`from`** \<String!>
        - Sender address.
      - **`to`** \<String!>
        - Receiver address.
      - **`symbol`** \<String!>
        - Asset symbol.
      - **`value`** \<String!>
        - Amount of asset has been transferred. The format is Decimaled Number.
      - **`decimals`** \<String!>
        - Asset decimals.
      - **`transactionHash`** \<String>
        - Transaction hash of the asset transfer.
      - **`type`** \<String!>
        - Type of the asset.
      - **`time`** \<String!>
        - The time that FST Network server reviced transaction. The format is Unix Timestamp in millisecond resolution.