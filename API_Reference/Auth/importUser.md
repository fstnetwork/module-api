
# Import user
You can import your end user via this API.

## GraphQL API

- Query String
  ```
  mutation importUser($input: ImportUserInput!){
    importUser(input: $input) {
      id
      JWTId
      base64JWTId
    }
  }
  ```
- Query Variables
  ```
  {
    "input": {
      "email": "testing2@fstk.io",
      "firstName": "Testing",
      "lastName": "2",
      "ethereumKey": {
        "address": "0x12347cb0c4d400c65095c9ddefc6084bbec21e76",
        "crypto": {
          "kdf": "pbkdf2",
          "kdfparams": {
            "c": 262144,
            "dklen": 32,
            "prf": "hmac-sha256",
            "salt": "710f8facc94fa7543c95951dbe0539412d766603cf0de66accb952b95825d7f1"
          },
          "cipher": "aes-128-ctr",
          "ciphertext": "19432eb7fef101ad4235c73420c36295bb16bf288227036e8821b86ec0249775",
          "cipherparams": {
            "iv": "ab462eea30c3c80bac2d255f58ae1710"
          },
          "mac": "a1b1401feb3bea34bd4638d82c7dfbf0a3ae35a162756f32c7452b50d2e060c4"
        },
        "id": "92304227-a393-4a22-8ef8-041c3740aa01",
        "version": 3
      }
    }
  }
  ```
  The `ethereumKey` in this `importUser` args can be generated via https://github.com/funderstoken/eth-key-lib-js

- HTTP Headers
  ```
  {
    "accept": "application/json",
    "content-type": "application/json",
    "authorization": "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjdiZWM2YWExLWE0ZTEtNGU1Zi04ZTc0LTAxZThjYmNkZDFjMSJ9.eyJpYXQiOjE1Mzk4NDU5NzEsImV4cCI6MTUzOTg0NjAzMSwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmplaSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzMnNfdG9rZW4ifQ.FZ2qeOHRFA2qJaqFf7ZFl8EpJi35XAdYuBHSlQHxdbKTWr2tUW9eaT-Y7zrRbdnVMrGTxQnK8xFuPQuPX37sAxclJbvMakVqqkQOX9N-pkuB0_p8VK319kbGmd-CYV05rw-eo-T6At5T0pV0IaZHntMCTCPt1n9bipndn4eDLwW8ohGjd4WKVXothJ7HLkkH4ujOHUXTCSxvj5aiFhQCznep59k6te-uRLX2tau7unkP_ahoD7w8j838FNUaHylfiNIliDEBhLvn4CPx9GwTtEYrqx6YAmZQONOTsOnUp8sDuDecvJ8dLEqrnQeyF2CiVznDwhHX19nDibiOsFd34A"
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
  - authorization: `Bearer [JWT Server-to-Server access token]`
    - (for example)
      ```
      Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjdiZWM2YWExLWE0ZTEtNGU1Zi04ZTc0LTAxZThjYmNkZDFjMSJ9.eyJpYXQiOjE1Mzk4NDU5NzEsImV4cCI6MTUzOTg0NjAzMSwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmplaSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzMnNfdG9rZW4ifQ.FZ2qeOHRFA2qJaqFf7ZFl8EpJi35XAdYuBHSlQHxdbKTWr2tUW9eaT-Y7zrRbdnVMrGTxQnK8xFuPQuPX37sAxclJbvMakVqqkQOX9N-pkuB0_p8VK319kbGmd-CYV05rw-eo-T6At5T0pV0IaZHntMCTCPt1n9bipndn4eDLwW8ohGjd4WKVXothJ7HLkkH4ujOHUXTCSxvj5aiFhQCznep59k6te-uRLX2tau7unkP_ahoD7w8j838FNUaHylfiNIliDEBhLvn4CPx9GwTtEYrqx6YAmZQONOTsOnUp8sDuDecvJ8dLEqrnQeyF2CiVznDwhHX19nDibiOsFd34A
      ```
- Body (for example)
  ```
  {
    "variables": {
      "input": {
        "email": "testing2@fstk.io",
        "firstName": "Testing",
        "lastName": "2",
        "ethereumKey": {
          "address": "0x12347cb0c4d400c65095c9ddefc6084bbec21e76",
          "crypto": {
            "kdf": "pbkdf2",
            "kdfparams": {
              "c": 262144,
              "dklen": 32,
              "prf": "hmac-sha256",
              "salt": "710f8facc94fa7543c95951dbe0539412d766603cf0de66accb952b95825d7f1"
            },
            "cipher": "aes-128-ctr",
            "ciphertext": "19432eb7fef101ad4235c73420c36295bb16bf288227036e8821b86ec0249775",
            "cipherparams": {
              "iv": "ab462eea30c3c80bac2d255f58ae1710"
            },
            "mac": "a1b1401feb3bea34bd4638d82c7dfbf0a3ae35a162756f32c7452b50d2e060c4"
          },
          "id": "92304227-a393-4a22-8ef8-041c3740aa01",
          "version": 3
        }
      }
    },
    "query": "mutation importUser($input: ImportUserInput!) {\n  importUser(input: $input) {\n    id\n    JWTId\n    base64JWTId\n  }\n}\n"
  }
  ```
  The value of `mutation` in the body is a `String`
  
  The `ethereumKey` in this `importUser` args can be generated via https://github.com/funderstoken/eth-key-lib-js



### Response
```
{
  "data": {
    "importUser": {
      "id": "VXNlckluZm86acKOYT3DksKjEcOowqfCgMOnTMKBTMKpwrE=",
      "JWTId": "ia=Ò£\u0011è§çLL©±",
      "base64JWTId": "acKOYT3DksKjEcOowqfCgMOnTMKBTMKpwrE="
    }
  }
}
```
The `base64JWTId` in this `importUser` result is the `uid` required in generating access token for web access.



    
