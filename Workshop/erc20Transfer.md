
# Transfer erc20

## GraphQL API

explorer: token address
http://explorer.dev.fstk.io/token/0xe93e7a04a4b5273dee710f7263a4ca69b96424d3

- Mutation Example
  ```javascript
  mutation erc20 {
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
- 請確認該帳戶有足夠的 token 餘額

id: Pluie
pwd: 12345678
address: 0xda29d213b887dd1c9196423f5c34eb82d5132b74

1. 看 Account 是否有 token 餘額
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

- 或是看 Erc20 token holders ([erc20TokenBasic](/Workshop/Explorer/erc20TokenBasic.md)) 來做 transfer


2. transfer Erc20: [erc20Transfers](/Workshop/Explorer/erc20Transfers.md)

#### req

```javascript
  mutation erc20 {
    erc20Transfer (input: {
      contract: "0x223f5789fac8eef297ac1c43680876a42ac7cb19",
      to: "0xc58716b864936b58f1f8188f0a4c83a7cae8afa6" # id = enduser_louise_add_1, pwd = 12345678
      value: "10000000000000000" # 0.01
    }){
      transaction
      submitToken
      ethereumKey
    }
  }
```

#### res
```javascript

```

#### signTx
- change signExample.js details

```javascript

```

#### submitTx
```javascript
  mutation submit {
    submitTransaction(
      input: {
        signedTx: "..."
        submitToken: "...."
      }
    ) {
      transactionHash
    }
  }
```
