
# Publish Smart Voucher
You are able to publish Smart Voucher via this API.

## GraphQL API

- Query String
  ```
  mutation publishVoucher($input: PublishVoucherInput!) {
    publishVoucher(input: $input) {
      transaction
      hash
      metadata
      submitToken
    }
  }

  ```
- Query Variables

  ```
  {
    "input":{
      "name":"Glass",
      "symbol":"GALIFE_GLASS",
      "consumable":true,
      "totalSupply":"5000",
      "price": {
        "numerator":"2000000000000000000000",
        "denominator":"1"
      },
      "expiry":"1577807999000",
      "description":"this is description",
      "proofOfContract":null
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
  ------WebKitFormBoundaryZ8JmbMyPiS7ZuVmj
  Content-Disposition: form-data; name="operations"

  {
    "query":"mutation publishVoucher($input: PublishVoucherInput!) { publishVoucher(input: $input) { transaction hash metadata submitToken } }",
    "variables": {
      "input":{
        "name":"Glass",
        "symbol":"GALIFE_GLASS",
        "consumable":true,
        "totalSupply":"5000",
        "price": {
          "numerator":"2000000000000000000000",
          "denominator":"1"
        },
        "expiry":"1577807999000",
        "description":"this is description",
        "proofOfContract":null
      }
    }
  }
  ------WebKitFormBoundaryZ8JmbMyPiS7ZuVmj
  Content-Disposition: form-data; name="map"

  {"proofOfContract":["variables.input.proofOfContract"]}
  ------WebKitFormBoundaryZ8JmbMyPiS7ZuVmj
  Content-Disposition: form-data; name="proofOfContract"; filename="test.pdf"
  Content-Type: application/pdf

  proofOfContract file content

  ------WebKitFormBoundaryZ8JmbMyPiS7ZuVmj--

  ```
  The value of `query` in the body is a `String`. Please refer to [GraphQL multipart request specification](https://github.com/jaydenseric/graphql-multipart-request-spec) for files upload.


### Response
```
{  
  "publishVoucher":{  
    "transaction":{  
      "nonce":"0x1",
      "gasPrice":"0x3b9aca00",
      "gas":"0x4828d4",
      "to":"0x6D5ECA9bC2b73c5722195F026D6b6b8e462750a5",
      "value":"0x0",
      "data":"0x4000aea0000000000000000000000000d6aebbbd0af65107a8d3dfe362f322bf4c8e1bcf0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002a4459ee93a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018000000000000000000000000000000000000000000000000000000000000001c000000000000000000000000000000000000000000000000000000000000013880000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000005e0b707f0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000ad67ca385a827814cb00ca0033bf48033889cc0f00000000000000000000000000000000000000000000006c6b935b8bbd400000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000005476c617373000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c47414c4946455f474c415353000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b4169784d466671324137456a4b4655464e345546676e57743254346a5775575651614855704331505456445136584b775635544d714b7138344b61546946436577654676397a543875684d78557863644e51586871487436000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
      "chainId":42
    },
    "hash":"0xfa01cc289233bc32085158d1ddb5ad2bf38718815101afd3a6392d328ec9cf06",
    "metadata":{  
      "fee":{  
        "type":"ETH",
        "amount":"4729044000000000"
      }
    },
    "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDvsOcw6UqXCLDmVx1MDAxMcOpwro7fzfClX_Cqk0iLCJhY3Rpb24iOiJwdWJsaXNoVm91Y2hlciIsInR4IjoiK1FOcUFZUTdtc29BZzBnbzFKUnRYc3Fid3JjOFZ5SVpYd0p0YTJ1T1JpZFFwWUM1QTBSQUFLNmdBQUFBQUFBQUFBQUFBQUFBMXE2N3ZRcjJVUWVvMDkvall2TWl2MHlPRzg4QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBcVJGbnVrNkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFjQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFUaUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBSUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBRjRMY0g4QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUUFBQUFBQUFBQUFBQUFBQUsxbnlqaGFnbmdVeXdES0FETy9TQU00aWN3UEFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJzYTVOYmk3MUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFRQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBVkhiR0Z6Y3dBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQU1SMEZNU1VaRlgwZE1RVk5UQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFZeTlwY0daekwzcENkWEpMUVdsNFRVWm1jVEpCTjBWcVMwWlZSazQwVlVabmJsZDBNbFEwYWxkMVYxWlJZVWhWY0VNeFVGUldSRkUyV0V0M1ZqVlVUWEZMY1RnMFMyRlVhVVpEWlhkbFJuWTVlbFE0ZFdoTmVGVjRZMlJPVVZob2NVaDBOZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUNxQWdBPT0iLCJpbmZvIjp7InRva2VuSWQiOiLChsKtwpBcdTAwMTZcIsObXHUwMDExw6nCujvCh8OUwrHCmMKVciIsIm5hbWUiOnsiZW4iOiJHbGFzcyJ9LCJzeW1ib2wiOiJHQUxJRkVfR0xBU1MiLCJ0b3RhbFN1cHBseSI6IjUwMDAiLCJtZXRhZGF0YSI6IkFiVWZlQkNrVlVmaWljeE4rY2x2N0dkVndLSVMzVmV4RGEzamRMcWwxUUtGZWVNSE5nalpXR015bmpQV2REeG84SE1BRThXbDJXLzVYU3ZsYnF2aDNkV0VzenJUIiwibGlxdWlkIjp0cnVlLCJhcHByb3ZlQ2hlY2tpbmciOmZhbHNlLCJleHBpcnkiOjE1Nzc4MDc5OTkwMDAsImNvbnN1bWFibGUiOnRydWUsInByaWNlIjp7Im51bWVyYXRvciI6IjIwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJkZW5vbWluYXRvciI6IjEifSwidmVuZGlibGUiOnRydWUsImRlc2NyaXB0aW9uIjp7ImVuIjoidGhpcyBpcyBkZXNjcmlwdGlvbiJ9fSwiaWF0IjoxNTQ4NjY3MzA5LCJleHAiOjE1NDg2Njc5MDksImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ.Yd3q2AGbAynwqQwtApjDzePQwbvDxeoVRqXsz8ctkxVsYBGd56bGnjnmrN3l-kfrSCOd5WjChQEK2ZEHr-BI7A"
  }
}
```

## Parameters
### Request 
- **`name`** \<string>
  - Smart Voucher name.
  - Reuqired: Yes
- **`consumable`** \<boolean>
  - The Smart Voucher is consumable or not.
  - Reuqired: Yes
- **`symbol`** \<string>
  - Smart Voucher symbol.
  - Reuqired: Yes
- **`totalSupply`** \<string>
  - Total supply of the Smart Voucher. The format is Decimaled Number.
  - Reuqired: Yes
- **`price`** \<string>
 - Smart Voucher price.
  - **`numerator`** \<string>
    - The numerator of this fraction.
    - Reuqired: Yes
  - **`denominator`** \<string>
    - The denominator of this fraction.
    - Reuqired: Yes
- **`expiry`** \<string>
  - Expiry date of the Smart Voucher. The format is Unix Timestamp in millisecond resolution.
  - Reuqired: Yes
- **`description`** \<string>
  - Description of the Smart Voucher.
  - Reuqired: Yes
- **`proofOfContract`** \<string>
  - The PDF file of the Smart Voucher contract.
  - Reuqired: Yes

### Response
- **`publishVoucher`** \<PublishVoucherPayload>
  - **`transaction`** \<JSON>
    - UNSIGNED raw transaction format in Ethereum.
  - **`submitToken`** \<string>
    - The value for [SubmitSignedTransaction API]().
  - **`pendingTransactions`** \<string>
    - Amount of your transactions which are still pending.
  - **`hash`** \<string>
    - PORMode `ENABLE`: Hash of the abi encode.
    - PORMode `DISABLE`: Hash of the RLP encode.
  - **`metadata`** \<JSON>
    - Metadata of the transaction.
