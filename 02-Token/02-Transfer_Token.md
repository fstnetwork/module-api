
# Get Token info

## Definition

 - Endpoint
   - For development: `https://test.fstk.io/api`
   - For production: `https://engine.fstk.io/api`
- HTTP Method
  - `POST`
- Content type in header
  - `application/json; charset=utf-8`
- Authorization in header
  - `Bearer [JWT Server-to-Server access token]`
  - (for example) `Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTUzMjYwNTMxOCwiZXhwIjoxNTMyNjkxNzE4LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.TBwaCVLn77M70wR2fv86ADssg8F5aqsMPklGSnerl9H0qUIAmJWQZYzBYRbXsHisoXTq4pu4n2hBMIXExOy23A`
- Body (for example)

```
{
  query: """
    mutation erc20Transfer($input: ERC20TransferInput!) {
      erc20Transfer(input: $input) {
        pendingTransactions
        transaction
        submitToken
      }
    }
  """,
  variables: {
    clientMutationId: "VXNlckluZm86w6bCiHPCnRPDohHDqMKCwqBje0x0w6nCsA==",
    id: "VG9rZW46wpAkwq7CgMKfBxHDp8KAAQAAAAAAAA==",
    to: "0x829BD824B016326A401d083B33D092293333A830",
    value: "1123456789123456789"
  }
}
```

The value of `query` in the body is a `String`.  
The `clientMutationId` in `variables` is the end-user's (requester) `id`.  
The `id` in `variables` is the Token `id`.  
The `to` in `variables` is the Token recevier's address.  
The `value` in `variables` is the Token value to be transferred. **This string in Token transfer represents the decimaled number string**. For example, because the Tokens have the decimals of 18, the value `"1123456789123456789"` means `1.123456789123456789` for human (**human number string**).

- Response (for example)

```
{
  "data": {
    "erc20Transfer": {
      "pendingTransactions": "0",
      "transaction": {
        "nonce": "0xfb",
        "gasPrice": "0x3b9aca00",
        "gas": "0xf526",
        "to": "0x3830f7Af866FAe79E4f6B277Be17593Bf96beE3b",
        "value": "0x0",
        "data": "0xa9059cbb000000000000000000000000829bd824b016326a401d083b33d092293333a8300000000000000000000000000000000000000000000000000f9751ff54345f15",
        "chainId": 42
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJkYXRhIjoicVFXY3V3QUFBQUFBQUFBQUFBQUFBSUtiMkNTd0ZqSnFRQjBJT3pQUWtpa3pNNmd3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFENWRSLzFRMFh4VT0iLCJpYXQiOjE1MzQ2OTMwNzIsImV4cCI6MTUzNDY5MzY3MiwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.nGItBL3I7HLifsa3HZUIyPnX7NiH9YTgx2OcaZtfTV5xEUbJDpDQ0DJIWKgKl5M7f29guC7428iFPkyDTKFVXQ"
    }
  }
}
```

This API responses a ABI-Encoded transaction for the Token transfer, and the end-user (the sender, the requester) has to sign the `transaction` object in the response via [ETH Key lib JS](https://github.com/funderstoken/eth-key-lib-js), then send the signed transaction and the `submitToken` to [SubmitSignedTransaction API](https://github.com/funderstoken/module-api/tree/master/SubmitSignedTransaction).
