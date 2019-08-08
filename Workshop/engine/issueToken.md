
# Issue token by issuer

## GraphQL API

- Mutation Example
  ```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: "9453",
      name: "test"
      symbol: "LT"
      supply: "25000000000000000000000" # It's a decimal Number
      vendible: true
      numerator: 1,
      denominator: 345
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```
- This part of `numerator = 1` and `denominator = 345`, this means 1 Ether is worth 345 Smart Token, so that the ratio between Ether and the Token will be calculated and represented in terms of fraction.


- **permission**
  - Only Issuer has permission to use this API.
  - Issue token is a one-off action for Issuer.
  - `tokenID` is your IL Id.


## Step

- Method0.
  - Auth: Master
    (Will give ethValue, mintIL and transferMasterToken when creating issuer)
    - createUserV2, role = 1
  - Auth: Issuer
    - issueToken
      - signTx
      - submitTx


- Method1.
  - Auth: Master
    - mintIL to issuer
  - Auth: Issuer
    - issueToken
      - signTx
      - submitTx


## Tx Example

1. Auth: Master
  - create Issuer: [createUserAndTransferAsset](/Workshop/engine/createUserAndTransferAsset.md)
  - req
```javascript
  mutation createUserAndTransferAsset {
    createUserV2(
      input: {
        id: "issuer_0802_test_1" # unique
        password: "12345678"
        userPassphrase: "12345678"
        role: 1
        etherValue: "2000000000000000" # 0.002 ether
        masterTokenValue: "1000000000000000000000" # 1000 master token
        tokenId: "8021" # unique
        tokenUri: "8021"
        masterPassphrase: "12345678"
      }
    ) {
      uid
      keystore
      etherTransaction
      masterTokenTransaction
      mintILTransaction
    }
  }
```
 - res
```javascript
{
  "data": {
    "createUserV2": {
      "uid": "dd9fa9ca-b4f5-11e9-a919-9b91d922fd44",
      "keystore": {
        "version": 3,
        "address": "5438be01404693f75b3f6b776ef0de3087819eb8",
        "crypto": {
          "ciphertext": "7420675dcae9a8b7c141b8569f1ce4a505f608f4ee280f32c55d53108bd14d44",
          "cipher": "aes-128-ctr",
          "cipherparams": {
            "iv": "fb3ff7a13eb96ba5327c7e3603c29ebe"
          },
          "kdf": "scrypt",
          "kdfparams": {
            "dklen": 32,
            "n": 262144,
            "r": 8,
            "p": 1,
            "salt": "4ee3e08bad05fca7b98af54745ce4350761718a08b085b0c4342316e5ebc28e2"
          },
          "mac": "e0ff4f3daec4d5bc8b6292387266821775729f54cb2269a0f8e75be083d601fb"
        }
      },
      "etherTransaction": "0x4821c7574aca1afd0f540c3052612cacad78f084ec3219423705be6ceb2fd3ce",
      "masterTokenTransaction": "0x06ecc0643bbd87a0aaab8b4ce76a2cabf6346a06efcfa684d9ce5fd6457638ba",
      "mintILTransaction": "0x673e60f9a67c66e2882e33ea2be9c369665c5ffabb595dfc6712733ffdcacf1a"
    }
  },
  "extensions": {
    "cacheControl": {
      "version": 1,
      "hints": [
        {
          "path": [
            "createUserV2"
          ],
          "maxAge": 0
        }
      ]
    }
  }
}
```

2. Auth: Issuer
  - Issue token => call [getSmartTokenList](/Workshop/explorer/getSmartTokenList.md) to query
  - req
```javascript
  mutation issueToken {
    issueToken(input: {
      tokenID: "8021",
      name: "test"
      symbol: "LT" 
      supply: "25000000000000000000000000"
      vendible: true
      numerator: 1,
      denominator: 1
    }){
      transaction
      submitToken
      ethereumKey
    }
  }

```
  - res
```javascript
{
  "data": {
    "issueToken": {
      "transaction": {
        "nonce": "0x0",
        "gasPrice": "0x0",
        "gas": "0x6f3224",
        "to": "0x0000000000000000000000000000000000009805",
        "value": "0x0",
        "data": "0xb88d4fde0000000000000000000000005438be01404693f75b3f6b776ef0de3087819eb8000000000000000000000000000000000000000000000000000000000000980b0000000000000000000000000000000000000000000000000000000000001f55000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000001a000000000000000000000000000000000000000000000000000000000000000e0000000000000000000000000000000000000000000000000000000000000012000000000000000000000000000000000000000000014adf4b7320334b900000000000000000000000000000000000000000000000000000000000000000001600000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000004746573740000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000024c5400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000066130623163320000000000000000000000000000000000000000000000000000",
        "chainId": "0xab23"
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJwcml2aWxlZ2UiOiJpc3N1ZXIiLCJhY3Rpb24iOiJpc3N1ZVRva2VuIiwic2VyaWFsaXplZFR4IjoiMHhmOTAyNjY4MDgwODM2ZjMyMjQ5NDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDk4MDU4MGI5MDI0NGI4OGQ0ZmRlMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwNTQzOGJlMDE0MDQ2OTNmNzViM2Y2Yjc3NmVmMGRlMzA4NzgxOWViODAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDk4MGIwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAxZjU1MDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDA4MDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAxYTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMGUwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDEyMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDE0YWRmNGI3MzIwMzM0YjkwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTYwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDEwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwNDc0NjU3Mzc0MDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAyNGM1NDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDY2MTMwNjIzMTYzMzIwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwODA4MDgwIiwiaWF0IjoxNTY0NzMwNzE1LCJleHAiOjE1NjQ3MzEzMTUsImF1ZCI6ImRkOWZhOWNhLWI0ZjUtMTFlOS1hOTE5LTliOTFkOTIyZmQ0NCIsImlzcyI6IjgzNjViODQyLTk3ZjMtMTFlOS1hOTE5LWZiY2Q1NDk2ZmU5MSIsInN1YiI6IlNVQk1JVF9UT0tFTiJ9.-G_f68yOKUXV57C8WwyTtD_rIAdZB2I3J6ijl7tM9P_gbltbe6OaWcCw4YVJTvg6IBGwopQEYPViWTHfXQ77Hw",
      "ethereumKey": {
        "address": "0x5438be01404693f75b3f6b776ef0de3087819eb8",
        "crypto": {
          "kdf": "scrypt",
          "mac": "e0ff4f3daec4d5bc8b6292387266821775729f54cb2269a0f8e75be083d601fb",
          "cipher": "aes-128-ctr",
          "kdfparams": {
            "n": 262144,
            "p": 1,
            "r": 8,
            "salt": "4ee3e08bad05fca7b98af54745ce4350761718a08b085b0c4342316e5ebc28e2",
            "dklen": 32
          },
          "ciphertext": "7420675dcae9a8b7c141b8569f1ce4a505f608f4ee280f32c55d53108bd14d44",
          "cipherparams": {
            "iv": "fb3ff7a13eb96ba5327c7e3603c29ebe"
          }
        },
        "version": 3
      }
    }
  },
  "extensions": {
    "cacheControl": {
      "version": 1,
      "hints": [
        {
          "path": [
            "issueToken"
          ],
          "maxAge": 0
        }
      ]
    }
  }
}
```
- sign
https://github.com/fstnetwork/eth-key-lib-js

```javascript
const pp = EthKeyLibBrowser.DecryptEthereumKeyJson({passpharse}, {keystore});
const sign = EthKeyLibBrowser.SignTransaction(pp.privateKeyBuffer, {transaction});

"0xf902a98080836f322494000000000000000000000000000000000000980580b90244b88d4fde0000000000000000000000005438be01404693f75b3f6b776ef0de3087819eb8000000000000000000000000000000000000000000000000000000000000980b0000000000000000000000000000000000000000000000000000000000001f55000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000001a000000000000000000000000000000000000000000000000000000000000000e0000000000000000000000000000000000000000000000000000000000000012000000000000000000000000000000000000000000014adf4b7320334b900000000000000000000000000000000000000000000000000000000000000000001600000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000004746573740000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000024c540000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006613062316332000000000000000000000000000000000000000000000000000083015669a0eafe7d467d265381d57d9ba1b91d35384720b435f8f40c4c6df77c2176a4f031a039f707c4fcdd36d396dc07ec9bff690558a5ca0cb71696e9d04c34520a5ca51d"
```

- submit
```javascript

mutation submitTransaction {
    submitTransaction(
      input: {
        signedTx: <sign_string>
        submitToken: <submitToken_string>
      }
    ) {
      transactionHash
    }
  }
```