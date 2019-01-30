
# 

## GraphQL API

- Query String
  ```
  mutation IssueToken($input: IssueTokenInput!) {
    issueToken(input: $input) {
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
    "input": { 
      "name":"Green and life",
      "symbol":"GALIFE",
      "totalSupply":"4800000000000000000000000000",
      "price": {
        "numerator":"1",
        "denominator":"12000"
      },
      "description":"qwerqwerqwerqwer",
      "website":"green.com",
      "logo":null,
      "proofOfContract":null
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
  _(sample)_
  ``` 
  ------WebKitFormBoundarya1KBKIx6Q3mGrSEU
  Content-Disposition: form-data; name="operations"

  {
    "query":"mutation IssueToken($input: IssueTokenInput!) { issueToken(input: $input) {                     transaction hash metadata submitToken } }",
    "variables": {
      "input": { 
        "name":"Green and life",
        "symbol":"GALIFE",
        "totalSupply":"4800000000000000000000000000",
        "price": {
          "numerator":"1",
          "denominator":"12000"
        },
        "description":"qwerqwerqwerqwer",
        "website":"green.com",
        "logo":null,
        "proofOfContract":null
      }
    }
  }
  ------WebKitFormBoundarya1KBKIx6Q3mGrSEU
  Content-Disposition: form-data; name="map"

  {"logo":["variables.input.logo"],"proofOfContract":["variables.input.proofOfContract"]}
  ------WebKitFormBoundarya1KBKIx6Q3mGrSEU
  Content-Disposition: form-data; name="logo"; filename="blob"
  Content-Type: image/png

  logo file content

  ------WebKitFormBoundarya1KBKIx6Q3mGrSEU
  Content-Disposition: form-data; name="proofOfContract"; filename="test.pdf"
  Content-Type: application/pdf

  proofOfContract file content

  ------WebKitFormBoundarya1KBKIx6Q3mGrSEU--
  ```

  The value of `query` in the body is a `String`. 

### Response
(for example)
```
{
  "issueToken": {
    "transaction": {
      "nonce": "0x0",
      "gasPrice": "0x3b9aca00",
      "gas": "0x595c92",
      "to": "0x7aeCC9c7dC65d15aEbF1e2cF7eb0fBBf38F49414",
      "value": "0x0",
      "data": "0x4000aea00000000000000000000000003705ef5d9c36509faa0d4ebc7e7e09dbda0ba08e0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002448d623336000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000120000000000000000000000000000000000000000000000000000000000000016000000000000000000000000000000000000000000f8277896582678ac000000000000000000000000000000000000000000000000000000000000000000001a000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000002ee00000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000e477265656e20616e64206c696665000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000647414c494645000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b415371445a625574443368784a7a5a68334c514e6f61614361777765387444435559524b5a6948437a594d3632396b6556457a5551626d527064547a73355067366570543352507279414b4451535371656e323542644a38000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
      "chainId": 42
    },
    "hash": "0xec7c47c4fb5b87f75b99d7637c254d8fc6a0a95fe57fbd558c444f292910a62f",
    "metadata": {
      "fee": {
        "type": "ETH",
        "amount": "5856402000000000"
      }
    },
    "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDvsOcw6UqXCLDmVx1MDAxMcOpwro7fzfClX_Cqk0iLCJhY3Rpb24iOiJpc3N1ZVRva2VuIiwidHgiOiIrUU1LZ0lRN21zb0FnMWxja3BSNjdNbkgzR1hSV3V2eDRzOStzUHUvT1BTVUZJQzVBdVJBQUs2Z0FBQUFBQUFBQUFBQUFBQUFOd1h2WFp3MlVKK3FEVTY4Zm40SjI5b0xvSTRBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFRQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJnQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFrU05Zak0yQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBRWdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBK0NkNGxsZ21lS3dBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUVBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBdTRBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBNUhjbVZsYmlCaGJtUWdiR2xtWlFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUdSMEZNU1VaRkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFZeTlwY0daekwzcENkWEpMUVZOeFJGcGlWWFJFTTJoNFNucGFhRE5NVVU1dllXRkRZWGQzWlRoMFJFTlZXVkpMV21sSVEzcFpUVFl5T1d0bFZrVjZWVkZpYlZKd1pGUjZjelZRWnpabGNGUXpVbEJ5ZVVGTFJGRlRVM0ZsYmpJMVFtUktPQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUNxQWdBPT0iLCJpbmZvIjp7Imlzc3VlcklkIjoiw77DnMOlKlwiw5lcdTAwMTHDqcK6O383wpV_wqpNIiwibmFtZSI6eyJlbiI6IkdyZWVuIGFuZCBsaWZlIn0sInN5bWJvbCI6IkdBTElGRSIsImRlY2ltYWxzIjoxOCwidG90YWxTdXBwbHkiOiI0ODAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwIiwibWV0YWRhdGEiOiJBYlVmZUJDV2JlV3JMcndXVU9XdUtxTmZ3bW8zWm44RDVpTFVkTUx6RVRXSkxJZ0k5VjYvSFBPQ2RCSmJReVBWaFQ5ZW01NElOdlp5M1p0Z3hDbGRZYTdmNStBaCIsImxpcXVpZCI6dHJ1ZSwiYXBwcm92ZUNoZWNraW5nIjpmYWxzZSwicHJpY2UiOnsibnVtZXJhdG9yIjoiMSIsImRlbm9taW5hdG9yIjoiMTIwMDAifSwidmVuZGlibGUiOnRydWUsIndlYnNpdGUiOiJncmVlbi5jb20iLCJkZXNjcmlwdGlvbiI6eyJlbiI6InF3ZXJxd2VycXdlcnF3ZXIifX0sImlhdCI6MTU0ODY2NjE4NSwiZXhwIjoxNTQ4NjY2Nzg1LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.RcqBWtK_K5CdztQZfxMzDn_09lfgKvmkS1NYIIZcoprljeQVmPD9-xOkfwsHTy4_p1ZOLri7V_PJPBltkhn1RA"
  }
}
```



## Parameters
### Request 
- **`name`** \<string>
  - name of the token
- **`symbol`** \<string>
  - symbol of the token.
- **`totalSupply`** \<string>
  - totalSupply of the token (decimal)
- **`price`** \<string>
 - price
  - **`numerator`** \<string>
  - numerator
  - **`denominator`** \<string>
  - denominator
- **`description`** \<string>
- **`website`** \<string>
- **`logo`** \<string>
- **`proofOfContract`** \<string>

### Response
  - **`issueToken`** \<IssueTokenPayload>
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