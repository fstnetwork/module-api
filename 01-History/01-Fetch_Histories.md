
# Fetch Transaction Histories

Get all transaction histories of the `address`.

## GraphQL API
- Query String
  ```
  query getTransferHistory {
    transferHistory {
      edges {
        node {
          transactionHash
          from
          to
          value
          symbol
          decimals
          type
          time
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
  - authorization: `Bearer [JWT Web-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g 
      ```

- Body
  ```
  {"query":"\n    query getTransferHistory{\n      transferHistory{\n        edges {\n          node {\n            transactionHash\n            from\n            to\n            value\n            symbol\n            decimals\n            type\n            time\n          }\n        }\n      }\n    }\n    "}
  ```
  The value of `query` in the body is a `String`

## HTTP Response
```
{
  "data": {
    "transferHistory": {
      "edges": [
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x4d6bb4ed029b33cf25d0810b029bd8b1a6bcab7b",
            "transactionHash": "0x584b003830a12a6801462834c969f82d88cb829db80309d942e94f163a025f61",
            "decimals": "18",
            "symbol": "ETH",
            "type": "ETH",
            "time": "1544714588292"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0xd95448553bbdcc57260dde4e51bdf81710718e25",
            "transactionHash": "0x50b05d3d0b2cc8ed777f5eac0ffadc79b5f676532f2eed808da146ea1894432b",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544701592356"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0xd95448553bbdcc57260dde4e51bdf81710718e25",
            "transactionHash": "0x5f234e8174399450a8b47a8898fe55848ccbb4da77c4df498c4fc37646140bbb",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544701340440"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
            "transactionHash": "0x5f234e8174399450a8b47a8898fe55848ccbb4da77c4df498c4fc37646140bbb",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544701340403"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
            "transactionHash": "0xa175fb3406186ecfd9c763407018d2141af32df1673b3e0c6be4350a5a11eed7",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544701052376"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
            "transactionHash": "0x081a169d862f4b6a64bb38b42e7c1dd2a03e12f33111f701d2cacef88e0ca1af",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544700976688"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0xd95448553bbdcc57260dde4e51bdf81710718e25",
            "transactionHash": "0x6474ce0e345550b93efcb97191a353b876799bd110241aab16917aef94a8750e",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544700888276"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
            "transactionHash": "0x49512e92b50ca70ebeaa4795cd28acdb5595ababe05459f2a828df7999a32801",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544700508218"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
            "transactionHash": "0x272019e9d0a1be4fca00f00cb3d3f190abf54ed00cf0511f107f3c91da3c3900",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544700184328"
          }
        },
        {
          "node": {
            "to": "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8",
            "from": "0x8d1ddd4f3d17c637a8fef1de650add5ed48dbdc5",
            "transactionHash": "0xc2a251cf795c4477b88d1d414ffff567b7d9a95b6cd0cff7103070c556678ccd",
            "decimals": "18",
            "symbol": "SUMMER",
            "type": "token",
            "time": "1544700144466"
          }
        }
      ]
    }
  }
}
```

## Parameters
### Response
  - `transferHistory`
    - `edge`
      - `node`
        - `to`:Address of the receiver.
        - `from`: Address of the sender.
        - `transactionHash`: Transaction hash.
        - `decimals`: Decimals of the asset.
        - `symbol`: Symbol of the asset.
        - `type`: `token`, `voucher` or `ETH`.
        - `time`: FST Network server reviced transaction's time. The format is Unix Timestamp in millisecond resolution.