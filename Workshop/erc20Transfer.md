
# Transfer erc20

## GraphQL API

explorer: token address
http://explorer.dev.fstk.io/token/0xe93e7a04a4b5273dee710f7263a4ca69b96424d3

- Mutation Example
  ```javascript
  mutation erc20Transfer {
    erc20Transfer (input: {
      contract: "0xe93e7a04a4b5273dee710f7263a4ca69b96424d3",
      to: "0xcb69b95f72d1b1f373d956d95f216492a7ea26c8"
      value: "1000000000000000000"
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
  ```


## Step
- Please make sure that your account has available balance can be transferred

  - id: Pluie
  - pwd: 12345678
  - address: 0xda29d213b887dd1c9196423f5c34eb82d5132b74


#### Erc20 balance
- API: query [account](/Workshop/Explorer/account.md)
```javascript
...
{
  "node": {
    "contract": {
      "decimals": "18",
      "symbol": "PRP",
      "name": "Penny Rain Pluei"
    },
    "owner": "0xda29d213b887dd1c9196423f5c34eb82d5132b74",
    "value": "850000000000000000"
  }
}
...
```
- UI: http://explorer.dev.fstk.io/address/0x223f5789fac8eef297ac1c43680876a42ac7cb19

- or Erc20 token holders ([erc20TokenBasic](/Workshop/Explorer/erc20TokenBasic.md))


#### Transfer Erc20: [erc20Transfers](/Workshop/Explorer/erc20Transfers.md)

- #### req

```javascript
  mutation erc20 {
    erc20Transfer (input: {
      contract: "0x223f5789fac8eef297ac1c43680876a42ac7cb19",
      to: "0xc58716b864936b58f1f8188f0a4c83a7cae8afa6" # id = enduser_louise_add_1, pwd = 12345678
      value: "1000000000000000" # 0.001
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
```

- #### res
```javascript
{
  "data": {
    "erc20Transfer": {
      "transaction": {
        "nonce": "0x4",
        "gasPrice": "0x3b9aca00",
        "gas": "0xf1c4",
        "to": "0x223f5789fac8eef297ac1c43680876a42ac7cb19",
        "value": "0x0",
        "data": "0xa9059cbb000000000000000000000000c58716b864936b58f1f8188f0a4c83a7cae8afa600000000000000000000000000000000000000000000000000038d7ea4c68000",
        "chainId": "0x2a"
      },
      "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJwcml2aWxlZ2UiOiJpc3N1ZXIiLCJhY3Rpb24iOiJlcmMyMFRyYW5zZmVyIiwic2VyaWFsaXplZFR4IjoiMHhmODY4MDQ4NDNiOWFjYTAwODJmMWM0OTQyMjNmNTc4OWZhYzhlZWYyOTdhYzFjNDM2ODA4NzZhNDJhYzdjYjE5ODBiODQ0YTkwNTljYmIwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDBjNTg3MTZiODY0OTM2YjU4ZjFmODE4OGYwYTRjODNhN2NhZThhZmE2MDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMzhkN2VhNGM2ODAwMDgwODA4MCIsImlhdCI6MTU2NDY3MDE3MywiZXhwIjoxNTY0NjcwNzczLCJhdWQiOiI5ZGQwNDE4OC1hMjFjLTExZTktYTkxOS01NzdkZjQzNDlkNzUiLCJpc3MiOiI4Yjk3Y2VjNC05MWE5LTExZTktYTkxNy1kN2FlMGQ3NzRhMDQiLCJzdWIiOiJTVUJNSVRfVE9LRU4ifQ.BmBp4rl8ISajIuwVGxS86vpKE_qakwQpSxaaEyxEy3SHz5lSSatI8Xtc22vHoOvb_LOCOgWZdQlzu-t7cpiPjA",
      "ethereumKey": {
        "address": "0xda29d213b887dd1c9196423f5c34eb82d5132b74",
        "crypto": {
          "kdf": "scrypt",
          "mac": "edfc0d903d8098b6d790295a4bf20d3e5939e3d3a5f8b6906e9a7611f139a647",
          "cipher": "aes-128-ctr",
          "kdfparams": {
            "n": 262144,
            "p": 1,
            "r": 8,
            "salt": "a262b8f8dcd593c0ed879051dfe012d6c38783ae22f358cc511ab09cdcab11ac",
            "dklen": 32
          },
          "ciphertext": "ba258161a00ddf0b127fe78c826341bf3f9b0c9524a8fef49fffbdcab45b991d",
          "cipherparams": {
            "iv": "0ca6eb21a43a3dabbdf0005e847c4c85"
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
            "erc20Transfer"
          ],
          "maxAge": 0
        }
      ]
    }
  }
}
```

- #### get private key from keystore
tool: https://www.myetherwallet.com
upload keystore(JSON) below
```javascript
{
  "address": "0xda29d213b887dd1c9196423f5c34eb82d5132b74",
  "crypto": {
    "kdf": "scrypt",
    "mac": "edfc0d903d8098b6d790295a4bf20d3e5939e3d3a5f8b6906e9a7611f139a647",
    "cipher": "aes-128-ctr",
    "kdfparams": {
      "n": 262144,
      "p": 1,
      "r": 8,
      "salt": "a262b8f8dcd593c0ed879051dfe012d6c38783ae22f358cc511ab09cdcab11ac",
      "dklen": 32
    },
    "ciphertext": "ba258161a00ddf0b127fe78c826341bf3f9b0c9524a8fef49fffbdcab45b991d",
    "cipherparams": {
      "iv": "0ca6eb21a43a3dabbdf0005e847c4c85"
    }
  },
  "version": 3
}
```


- #### signTx
  - change signExample.js details

```javascript
const transaction = {
  nonce: "0x4",
  gasPrice: "0x3b9aca00",
  gas: "0xf1c4",
  to: "0x223f5789fac8eef297ac1c43680876a42ac7cb19",
  value: "0x0",
  data: "0xa9059cbb000000000000000000000000c58716b864936b58f1f8188f0a4c83a7cae8afa600000000000000000000000000000000000000000000000000038d7ea4c68000",
  chainId: "0x2a"
}

const privateKeyString = '82b9eb291520fd05e3b3f996e25cc052d62d6e2e8301dc543b064cef06706f43';
const ethereumTx = new Transaction(transaction, {
  chain: Number(transaction.chainId)
});
ethereumTx.sign(Buffer.from(privateKeyString, "hex"));

console.log(ethereumTx.serialize().toString('hex'));
```

- #### submitTx
```javascript
  mutation submit {
    submitTransaction(
      input: {
        signedTx: "0xf8a804843b9aca0082f1c494223f5789fac8eef297ac1c43680876a42ac7cb1980b844a9059cbb000000000000000000000000c58716b864936b58f1f8188f0a4c83a7cae8afa600000000000000000000000000000000000000000000000000038d7ea4c6800077a09c8523f5457b62f9a38eb2c5a518bbf6800b511a57e91bcd5b964565e7257330a0129f2ede430b0ede9c8e3ca7349875234768dbfe1f3f14ef41fa0d60250b9353"
        submitToken: "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJwcml2aWxlZ2UiOiJpc3N1ZXIiLCJhY3Rpb24iOiJlcmMyMFRyYW5zZmVyIiwic2VyaWFsaXplZFR4IjoiMHhmODY4MDQ4NDNiOWFjYTAwODJmMWM0OTQyMjNmNTc4OWZhYzhlZWYyOTdhYzFjNDM2ODA4NzZhNDJhYzdjYjE5ODBiODQ0YTkwNTljYmIwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDBjNTg3MTZiODY0OTM2YjU4ZjFmODE4OGYwYTRjODNhN2NhZThhZmE2MDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMzhkN2VhNGM2ODAwMDgwODA4MCIsImlhdCI6MTU2NDY3MDE3MywiZXhwIjoxNTY0NjcwNzczLCJhdWQiOiI5ZGQwNDE4OC1hMjFjLTExZTktYTkxOS01NzdkZjQzNDlkNzUiLCJpc3MiOiI4Yjk3Y2VjNC05MWE5LTExZTktYTkxNy1kN2FlMGQ3NzRhMDQiLCJzdWIiOiJTVUJNSVRfVE9LRU4ifQ.BmBp4rl8ISajIuwVGxS86vpKE_qakwQpSxaaEyxEy3SHz5lSSatI8Xtc22vHoOvb_LOCOgWZdQlzu-t7cpiPjA"
      }
    ) {
      transactionHash
    }
  }
```

- #### successfully submit transaction

UI check：http://explorer.dev.fstk.io/tx/0x57a4dff2d8127dc12d782953ab93c363c9a9637403a784654c564e80f9028cf8

```javascript
{
  "data": {
    "submitTransaction": {
      "transactionHash": "0x57a4dff2d8127dc12d782953ab93c363c9a9637403a784654c564e80f9028cf8"
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