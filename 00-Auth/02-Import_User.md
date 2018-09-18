
# Import user

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

       { 
          mutation: `{
            importUser(input: {email: "testing@fstk.io", firstName: "James", lastName: "Bond", ethereumKey: { version: 3,
            id: "ca79c08c-5c07-437d-96a4-ee718a8be813",
            address: "a0814f93d51868198b01a81c51defd649be525fb",
            crypto:
             { ciphertext:
                "ce5c241f741b84f090c3f54a5f7dafa0c2de345af74a147b01e9ff12d0b8b9e6",
               cipherparams: { iv: "01a937e2c033c8540a35587b119ce4f3" },
               cipher: "aes-128-ctr",
               kdf: "scrypt",
               kdfparams:
                { dklen: 32,
                  salt:
                   "9b668da702536f3adfa9c6c72170baee0c35c234e356a7a571e64abdcc9cebe2",
                  n: 8192,
                  r: 8,
                  p: 1 },
               mac:
                "bda9e55ddd3c51dc0b884c7b1fbaf05b3a84b1337be0428ef7b6e4d005e0929e" } }}) {
              id
              JWTId
              base64JWTId
            }
          }`
        }

    The value of `mutation` in the body is a `String`

    The `ethereumKey` in this `importUser` args can be generated via https://github.com/funderstoken/eth-key-lib-js

- Response (for example)

        {
          "data": {
            "importUser": {
              "id": "VXNlckluZm86C8OTMX3Cj08Rw6jCjlpvbMKAw4fCnMOj",
              "JWTId": "\u000bÓ1}O\u0011èZolÇã",
              "base64JWTId": "C8OTMX3Cj08Rw6jCjlpvbMKAw4fCnMOj"
            }
          }
        }

    The `id` in this `importUser` result is the `uid` required in generating access token for web access.
