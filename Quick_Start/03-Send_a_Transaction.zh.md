# Send a Transaction

## Table of Contents

 1. Prerequisite
 2. Encode a Ethereum Transaction (Token transfer)
 3. Sign the Ethereum Transaction
 4. Broadcast the Ethereum Transaction
 5. Next step

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
    ```

## Sign the Ethereum Transaction

 - Using JavaScript

    ```javascript
    ```

 - Using Java

    ```java
    ```

 - Using C#

   ```csharp
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
