# Send a Transaction

## Table of Contents

 1. Prerequisite
 2. Encode a Ethereum Transaction (Token transfer)
 3. Decrypt the Ethereum Key JSON
 4. Sign the Ethereum Transaction
 5. Broadcast the Ethereum Transaction
 6. Next step

## Prerequisite

 1. 已經完成 Connect to FsTK Engine API 與 Get account information
 2. 已經確認帳戶中 (當前使用者) 擁有的資產
 3. 確認帳戶有足夠的 Ether 來付出燃料費用 (eth gas fee)

## Encode a Ethereum Transaction (Token transfer)
 > 請記得無論是哪一種呼叫手法，都記得要在 http request header 指定 `authorization`

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia)

    ```graphql
    mutation erc20Transfer($input: ERC20TransferInput!) {
      erc20Transfer(input: $input) {
        pendingTransactions
        transaction
        submitToken
      }
    }
    ```

    Variables:

    ```json
    {  
      "input":{  
        "id":"VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM=",
        "to":"0x0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f",
        "value":"123456789123456789"
      }
    }
    ```

    > `id` 為那個 token 的 `id`，在 `get me` 中可以看得到自己擁有的 token 們的 id

    > `to` 為 token 傳送的目的地，為 Ethereum 地址

    > `value` 為想要傳送的數量，切記，這裡的數字單位為 [`wei`](https://etherconverter.online)，也就是說假如想要傳送 `1` token，則 `value` 就要為 `"1000000000000000000"`

 - Using cURL

    ```sh
    curl --request POST \
         --url https://test.fstk.io/api \
         --header 'authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImlhdCI6MTU1MDAyOTQxMSwiZXhwIjoxNTUwMTE1ODExLCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6YWNjZXNzX3Rva2VuIn0.N44Ga-96NPZhBD82tLm2od9RVRIn67YIJXa-Pl9-y1UB-xfPrHpeQhq8yVDw21E6W1AQCAVgLwfOmgQn8zzxtQ' \
         --header 'content-type: application/json' \
         --cookie locale=en \
         --data '{"query":"mutation erc20Transfer($input: ERC20TransferInput!) {\n  erc20Transfer(input: $input) {\n    pendingTransactions\n    transaction\n    submitToken\n  }\n}\n","variables":{"input":{"id":"VG9rZW46wqIQOcOWHsOjEcOpwp4aD0oqw4DCtQM=","to":"0x0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f","value":"123456789123456789"}},"operationName":"erc20Transfer"}'
    ```

 - Response

    ```
    {
      "data": {
        "erc20Transfer": {
          "pendingTransactions": "0",
          "transaction": {
            "nonce": "0x10d",
            "gasPrice": "0x3b9aca00",
            "gas": "0xf30f",
            "to": "0x00E2F43299f51457935333AeF6C956b234Fa4781",
            "value": "0x0",
            "data": "0xa9059cbb0000000000000000000000000f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f00000000000000000000000000000000000000000000000001b69b4bacd05f15",
            "chainId": 42
          },
          "submitToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJtb2RlIjowLCJ1aWQiOiLDpsKIc8KdXHUwMDEzw6JcdTAwMTHDqMKCwqBje0x0w6nCsCIsImFjdGlvbiI6ImVyYzIwVHJhbnNmZXIiLCJ0eCI6IitHcUNBUTJFTzVyS0FJTHpENVFBNHZReW1mVVVWNU5UTTY3MnlWYXlOUHBIZ1lDNFJLa0ZuTHNBQUFBQUFBQUFBQUFBQUFBUER3OFBEdzhQRHc4UER3OFBEdzhQRHc4UER3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFHMm0wdXMwRjhWS29DQSIsImluZm8iOnt9LCJpYXQiOjE1NTAwNDA3MjIsImV4cCI6MTU1MDA0MTMyMiwiYXVkIjoidXJuOmZzdGs6ZW5naW5lIiwiaXNzIjoidXJuOmZzdGs6ZW5naW5lIiwic3ViIjoidXJuOmZzdGs6ZW5naW5lOnN1Ym1pdF90b2tlbiJ9.BPDyUdI8PviroRM8jX1YqKTkJqEZlK51zrNLdAPRH7_ZU2xWjk8DGm3tX2xgi52RK0GymLCTpBKQlJtKAKzZ3Q"
        }
      }
    }
    ```

## Decrypt the Ethereum Key JSON

 > 請注意 `password` 與 `passphrase` 在 FsTK 的差別，`password` 代表登入平台用的帳戶密碼，而 `passphrase` 為用來解密 Ethereum key json 用的，也就是拿來簽署交易的時候用的  
 > 因多個不同的函式庫的用詞皆不同，有些會將 `passphrase` 寫成 `password`，請避免搞混

 > 首先，從 `get me` 中取得 `ethereumKey` 欄位的資料，如:

 ```
 {
   "id": "64031d31-53a4-11e8-b00a-2b7a29c9f6b9",
   "address": "0x3a7af8b8c19c404670c1470273bca449148df4ed",
   "crypto": {
     "kdf": "scrypt",
     "mac": "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
     "cipher": "aes-128-ctr",
     "kdfparams": {
       "n": 262144,
       "p": 1,
       "r": 8,
       "salt": "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aba",
       "dklen": 32
     },
     "ciphertext": "dc1bf4ab51e55f4d063f835a44f5c172008fea7eeb4b0da7fb7e50f3a18cf9ef",
     "cipherparams": {
       "iv": "b343d847b8a72ad68c6bf10866757421"
     }
   },
   "version": 3
 }
 ```

 > 此為當前使用者之 Ethereum key json，其中包含被加密過後的私鑰 (以 passphrase 加密)，也就是說還算是可以安全地直接儲存，但也儘量不要公開  
 > 擁有私鑰等於擁有此 Ethereum Account 的所有控制權，請嚴格保密地儲存 Ethereum key json 與 passphrase
 > **也請注意，假如遺失了此 Ethereum key json 的 passphrase，則無任何恢復出私鑰的手段，因為 FsTK 不儲存使用者的 Ethereum key json passphrase**

 - Using JavaScript (Node.js)

    > 安裝 [eth-key-lib](https://github.com/fstnetwork/eth-key-lib-js)

    ```sh
    npm i --save "https://github.com/fstnetwork/eth-key-lib-js"
    # or yarn add "https://github.com/fstnetwork/eth-key-lib-js"
    ```

    ```javascript
    ```

 - Using Java

   > 請參考 [Web3j](https://web3j.io)  
   > 請注意 `WalletUtils` 中的 `loadCredentials` 方法，必須使用此多載  

   ```Java
   public static Credentials loadCredentials(String password, File source)
   ```
   > 也就是說，因為 web3j 只提供從 `File` 載入，請注意檔案系統的空間，或者也可以使用 in-memory-fs in Java

   > 也請參考 [Web3j 的簡易範例](https://docs.web3j.io/transactions.html#creating-and-working-with-wallet-files)

 - Using C#

   > 請參考 [Nethereum](https://nethereum.com)  
   > 也請參考 [Nethereum 的簡易範例](https://nethereum.readthedocs.io/en/latest/accounts/#working-with-an-account)  
   > 從 `Account` 取得私鑰請使用 `Account.PrivateKey`

## Sign the Ethereum Transaction

 - Using JavaScript

    ```javascript
    ```

 - Using Java

   > 請參考 [Web3j](https://web3j.io)  
   > 請注意 `TransactionEncoder` 中的 `signMessage` 方法，必須使用此多載，因為要簽署到 `chainId`

   ```Java
   public static byte[] signMessage(RawTransaction rawTransaction, byte chainId, Credentials credentials)
   ```

   > 也請參考 [Web3j 的簡易範例](https://docs.web3j.io/transactions.html#signing-transactions)

 - Using C#

   > 請參考 [Nethereum](https://nethereum.com)  
   > 請注意 `TransactionSigner` 中的 `SignTransaction` 方法，必須使用此多載，因為要簽署到 `chainId`

   ```csharp
   public string SignTransaction(byte[] privateKey, BigInteger chainId, string to, BigInteger amount, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, string data)
   ```

## Broadcast the Ethereum Transaction

 - Using [GraphQL](https://graphql.org/learn/) (請善用 Insomnia)

    ```graphql
    ```

 - Using cURL

    ```sh
    ```

## Next step

恭喜您，您已經完成 Quick start。  
如想要知道更多細節，請繼續從 [Guide](../Guide/01-A_short_introduction_to_Ethereum.zh.md) 開始閱讀，或者請到 [FST.Network 的 Github](https://github.com/fstnetwork) 瀏覽
