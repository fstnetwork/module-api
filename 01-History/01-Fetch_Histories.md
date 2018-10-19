
# Fetch Transaction Histories

## GraphQL API
- Query String
  ```
  query fetchHistory($accountAddress: String, $pagingPagesize: Int!, pagingPageNumber: Int!){
    ValueTransactionHistory(accountAddress: $accountAddress, pagingPagesize: $pagingPagesize, pagingPageNumber: $pagingPageNumber) {
      txhash
      from
      to
      value
      symbol
      created_time
    }
  }
  ```


- Query Variables
  ```
  {
    "accountAddress": "0x3e7aF8b8C19C404670C1470273bca449148Df4Ed",
    "pagingPagesize": "10",
    "pagingPageNumber": "1"
  }
  ```

- HTTP Headers
  ```
  {
    "authorization": "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NTYyODAyLCJleHAiOjE1Mzg2NDkyMDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.sGfxYe16aRx_vmvzlRps_gcyTeQD-zsR5HCtjXQ3hYpQYjN1lOFkdpF0m4Yrrh8uHyWBYifqYUVHmkRej4-9gA"
  }
  ```

## HTTP Request
- URL
  - For development: `http://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`
- Method: `POST`
- Headers
  - accept: `application/json;`
  - content-type: `application/json;`
  - authorization: `Bearer [JWT Server-to-Server access token or JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g      ```
- Body
  ```
  {  
    "variables":{  
      "accountAddress":"0x3e7aF8b8C19C404670C1470273bca449148Df4Ed",
      "pagingPagesize":10,
      "pagingPageNumber":1
    },
    "query":"fragment voucherInfo on Voucher {\n  id\n  contractAddress\n  name\n  description\n  symbol\n  decimals\n  totalSupply\n  proofOfContract {\n    url\n    ipfs\n  }\n  liquid\n  price {\n    numerator\n    denominator\n  }\n  availableAmount\n  vendible\n  expiry\n  consumable\n  createdTime\n  transfers(first: 3) {\n    edges {\n      cursor\n      node {\n        timestamp\n        from\n        to\n        value\n        transaction\n      }\n    }\n  }\n  holders(first: 3) {\n    edges {\n      cursor\n      node {\n        address\n        balance\n      }\n    }\n  }\n}\n\nfragment tokenInfo on Token {\n  id\n  issuer {\n    id\n    ethereumAddress\n  }\n  contractAddress\n  name\n  description\n  symbol\n  decimals\n  totalSupply\n  proofOfContract {\n    url\n    ipfs\n  }\n  liquid\n  price {\n    numerator\n    denominator\n  }\n  availableAmount\n  vendible\n  website\n  logo {\n    url\n    ipfs\n  }\n  createdTime\n  transfers(first: 3) {\n    edges {\n      cursor\n      node {\n        timestamp\n        from\n        to\n        value\n        transaction\n      }\n    }\n  }\n  holders(first: 3) {\n    edges {\n      cursor\n      node {\n        address\n        balance\n      }\n    }\n  }\n  vouchers(first: 3) {\n    edges {\n      cursor\n      node {\n        ...voucherInfo\n      }\n    }\n  }\n}\n\nquery GetTokenInfo {\n  node(id: \"VG9rZW46woDDssO6wrLCuxERw6jCp3zCqypmwp7CjsO/\") {\n    ...tokenInfo\n  }\n}\n\nquery fetchHistory($accountAddress: String, $pagingPagesize: Int!, $pagingPageNumber: Int!) {\n  ValueTransactionHistory(accountAddress: $accountAddress, pagingPagesize: $pagingPagesize, pagingPageNumber: $pagingPageNumber) {\n    txhash\n    from\n    to\n    value\n    symbol\n    created_time\n  }\n}\n"
  }
  ```
  The value of `query` in the body is a `String`

## HTTP Response
```
{
  "data": {
    "ValueTransactionHistory": [
      {
        "txhash": "0xb21ff4d48fab0f51cc141a912c4ad8ddbbb8913a1a2f2c93f70153b7c90eedc3",
        "from": "0xc1cd2d775c304e8e5a28049c7ebd3d56e3723c02",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "1000000000000000800",
        "symbol": "FST",
        "created_time": "1534398168554"
      },
      {
        "txhash": "0xb21ff4d48fab0f51cc141a912c4ad8ddbbb8913a1a2f2c93f70153b7c90eedc3",
        "from": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "to": "0x0575445130876a2ff230430491d64201efe41559",
        "value": "277777777777778",
        "symbol": "ETH",
        "created_time": "1534398168547"
      },
      {
        "txhash": "0x7d989e56fc31a4ebb5a5e9a2539d4bf10a800ead1c4dbf5e0c1faf3f022ab886",
        "from": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "1000000000000000000000",
        "symbol": "FST",
        "created_time": "1533476176480"
      },
      {
        "txhash": "0x6c58b19f4c6e8019eb14692ee2d2b0fc39c903ea3e877b9a7098f93d286d4f40",
        "from": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "100000000000000000000",
        "symbol": "FST",
        "created_time": "1533476124405"
      },
      {
        "txhash": "0x2dbead84383d436513e883574512ce1329abdecde932970a333a8ebeea839497",
        "from": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "100000000000000000000",
        "symbol": "FST",
        "created_time": "1533476052276"
      },
      {
        "txhash": "0x9b49398877f6da4e634aaee7829402535ac496d4eac18fbc26e05e8aa7a0d805",
        "from": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "to": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "value": "30000000000000000000000",
        "symbol": "FST",
        "created_time": "1532571408257"
      },
      {
        "txhash": "0xbdb84e4de0cf93e48c0a2a713c831123526f31257cf4902cc6ecb13b3aeb8566",
        "from": "0x004822c92365add3923ae07ea8d640639438158e",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "300000000000000000000000",
        "symbol": "FST",
        "created_time": "1532279112352"
      },
      {
        "txhash": "0x51d24b1fa25dd6697816ab19fefdef2b27650e0e8afadc946af4aacba9c75abf",
        "from": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "2300000000000000000",
        "symbol": "ETH",
        "created_time": "1531993376467"
      },
      {
        "txhash": "0xf6006ad8709d3e145548e9b5d5eafd911316b5a9285c6352014bb360ea82cd4d",
        "from": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "5540000000000000",
        "symbol": "ETH",
        "created_time": "1531992260618"
      },
      {
        "txhash": "0x233a07b85e37eb258129403443848451e8bda711ccb5bbdf2fb3e12597b5e12d",
        "from": "0x7bfbdd00de4fa8a4b5cf9466eb6a16d70a8dfd88",
        "to": "0x3e7af8b8c19c404670c1470273bca449148df4ed",
        "value": "44300000000000000",
        "symbol": "ETH",
        "created_time": "1531992116400"
      }
    ]
  }
}
```

