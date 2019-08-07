
# Send ether

## GraphQL API

- Mutation Example
  ```javascript
  mutation sendEther {
    sendEther (input: {
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      value: "100000000000000000" # 0.1 eth
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


## Step

- Anyone can call sendEther, even without any ETH

#### Estimated

- req
```javascript
mutation sendEther {
  sendEther (input: {
    to: "0xc58716b864936b58f1f8188f0a4c83a7cae8afa6" # enduser_louise_add_1
    value: "1000000000000000" # 0.001 eth
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
    "sendEther": {
      "transaction": {
        "nonce": "0x0",
        "gasPrice": "0x3b9aca00",
        "gas": "0x5208",
        "to": "0xc58716b864936b58f1f8188f0a4c83a7cae8afa6",
        "value": "0x38d7ea4c68000",
        "data": "0x",
        "chainId": "0x2a"
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJwcml2aWxlZ2UiOiJpc3N1ZXIiLCJhY3Rpb24iOiJzZW5kRXRoZXIiLCJzZXJpYWxpemVkVHgiOiIweGVhODA4NDNiOWFjYTAwODI1MjA4OTRjNTg3MTZiODY0OTM2YjU4ZjFmODE4OGYwYTRjODNhN2NhZThhZmE2ODcwMzhkN2VhNGM2ODAwMDgwODA4MDgwIiwiaWF0IjoxNTY0Njc1NDA5LCJleHAiOjE1NjQ2NzYwMDksImF1ZCI6IjAxYzk5ZTQyLWIyOTgtMTFlOS1hOTE5LTM3NTFiODVlYjg5YSIsImlzcyI6IjgzNjViODQyLTk3ZjMtMTFlOS1hOTE5LWZiY2Q1NDk2ZmU5MSIsInN1YiI6IlNVQk1JVF9UT0tFTiJ9.vjb8PBm0ByjJiXZKVID8j_nSxXUHTymE0hicDSjKaP17Tb5-GB0ieBjgUTbgZgPmzR6ajsxHRG9zQOm86JDRJA",
      "ethereumKey": {
        "address": "0xfe788a397088898783871f20fbbbcc9e84bf34bf",
        "crypto": {
          "kdf": "scrypt",
          "mac": "e9a6a3d92fba7c21173008edcad0434c320105dc88681a61789e690bdf414e20",
          "cipher": "aes-128-ctr",
          "kdfparams": {
            "n": 262144,
            "p": 1,
            "r": 8,
            "salt": "b8de12fc23433c0b3a1273a0bbb11a3ad48bf696c31aefa0629cf1d6a4ad3527",
            "dklen": 32
          },
          "ciphertext": "7af93b13d2d0e5b657a3122a3166a2c2c0f026d0d5a050fc6bb9b0ce41a64393",
          "cipherparams": {
            "iv": "f4ed88e5ca9e2bee0f1394234ed59ec0"
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
            "sendEther"
          ],
          "maxAge": 0
        }
      ]
    }
  }
}
```

#### get private key from keystore
tool: https://www.myetherwallet.com
upload keystore(JSON) below
```javascript
{
  "address": "0xfe788a397088898783871f20fbbbcc9e84bf34bf",
  "crypto": {
    "kdf": "scrypt",
    "mac": "e9a6a3d92fba7c21173008edcad0434c320105dc88681a61789e690bdf414e20",
    "cipher": "aes-128-ctr",
    "kdfparams": {
      "n": 262144,
      "p": 1,
      "r": 8,
      "salt": "b8de12fc23433c0b3a1273a0bbb11a3ad48bf696c31aefa0629cf1d6a4ad3527",
      "dklen": 32
    },
    "ciphertext": "7af93b13d2d0e5b657a3122a3166a2c2c0f026d0d5a050fc6bb9b0ce41a64393",
    "cipherparams": {
      "iv": "f4ed88e5ca9e2bee0f1394234ed59ec0"
    }
  },
  "version": 3
}
```


- #### signTx
  - please refer to the details in `./example/signExample.js`

```javascript

const transaction = {
  nonce: "0x0",
  gasPrice: "0x3b9aca00",
  gas: "0x5208",
  to: "0xc58716b864936b58f1f8188f0a4c83a7cae8afa6",
  value: "0x38d7ea4c68000",
  data: "0x",
  chainId: "0x2a"
}

const privateKeyString = 'd82ed6e84bdeac2fc5d6ae68107f4617fbe6afd1a9a02d31a53ce614771e61c3';
const ethereumTx = new Transaction(transaction, {
  chain: Number(transaction.chainId)
});
ethereumTx.sign(Buffer.from(privateKeyString, "hex"));

console.log(ethereumTx.serialize().toString('hex'));
```

#### submitTx
```javascript
  mutation submit {
    submitTransaction(
      input: {
        signedTx: "0xf86a80843b9aca0082520894c58716b864936b58f1f8188f0a4c83a7cae8afa687038d7ea4c680008077a0aea795f92efdbff017a95dcabccbde1e10c47038c9e262cad4ec8084eca1e323a047e47c111623450cb2b53f0ed34c85977c3de8b241874a097a058718de265865"
        submitToken: "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJwcml2aWxlZ2UiOiJpc3N1ZXIiLCJhY3Rpb24iOiJzZW5kRXRoZXIiLCJzZXJpYWxpemVkVHgiOiIweGVhODA4NDNiOWFjYTAwODI1MjA4OTRjNTg3MTZiODY0OTM2YjU4ZjFmODE4OGYwYTRjODNhN2NhZThhZmE2ODcwMzhkN2VhNGM2ODAwMDgwODA4MDgwIiwiaWF0IjoxNTY0Njc1NDA5LCJleHAiOjE1NjQ2NzYwMDksImF1ZCI6IjAxYzk5ZTQyLWIyOTgtMTFlOS1hOTE5LTM3NTFiODVlYjg5YSIsImlzcyI6IjgzNjViODQyLTk3ZjMtMTFlOS1hOTE5LWZiY2Q1NDk2ZmU5MSIsInN1YiI6IlNVQk1JVF9UT0tFTiJ9.vjb8PBm0ByjJiXZKVID8j_nSxXUHTymE0hicDSjKaP17Tb5-GB0ieBjgUTbgZgPmzR6ajsxHRG9zQOm86JDRJA"
      }
    ) {
      transactionHash
    }
  }
```


- #### successfully submit transaction
Explorer UI check：{domain}/tx/0x57a4dff2d8127dc12d782953ab93c363c9a9637403a784654c564e80f9028cf8

```javascript
{
  "data": {
    "submitTransaction": {
      "transactionHash": "0x2e703877f3e3b0836171262b8eaef4427c51c90e681335b323f143afe1066feb"
    }
  },
  "extensions": {
    "cacheControl": {
      "version": 1,
      "hints": [
        {
          "path": [
            "submitTransaction"
          ],
          "maxAge": 0
        }
      ]
    }
  }
}
```