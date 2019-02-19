
# Create Smart Voucher Campaign

## GraphQL API

- Query String
  ```
  mutation CreateCampaign($input: CreateCampaignInput!) {
    createCampaign(input: $input) {
      transaction
      submitToken
      hash
      metadata
    }
  }
  ```
- Query Variables
  ```
  {  
    "input":{
      "id":"VG9rZW46bMKMwrsEwrHCphHDqMKLEXPDrMO5w7vCp10=",
      "name":"My first basic voucher campaign",
      "description":"This is my first basic voucher campaign",
      "stages":[
        {
          "name":"My first basic voucher campaign",
          "startTime":"1514692800000",
          "endTime":"1546228800000",
          "priceMultiplier":{
            "numerator":"1000000000000",
            "denominator":"2000000000000"
          },
          "cap":"400000000000000000000000",
          "isPrivate":false,
          "description":"This is my first basic voucher campaign"
        }
      ],
      "por":"DISABLE"
    }
  }
  ```
- HTTP Headers 
  ```
  {
    "accept": "application/json",
    "content-type": "application/json",
    "authorization": "bearer [JWT Web-to-Server access token]"
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
  ``` 
  {
    "query":"mutation CreateCampaign($input: CreateCampaignInput!) {\n      createCampaign(input: $input) {\n        transaction\n        submitToken\n      }\n    }",
    "variables":{
      "input":{
        "id":"VG9rZW46bMKMwrsEwrHCphHDqMKLEXPDrMO5w7vCp10=",
        "name":"My first basic voucher campaign",
        "description":"This is my first basic voucher campaign",
        "stages":[
          {
            "name":"My first basic voucher campaign",
            "startTime":"1514692800000",
            "endTime":"1546228800000",
            "priceMultiplier":{
              "numerator":"1000000000000",
              "denominator":"2000000000000"
            },
            "cap":"400000000000000000000000",
            "isPrivate":false,
            "description":"This is my first basic voucher campaign"
          }
        ],
        "por":"DISABLE"
      }
    }
  }
  ```
  The value of `query` in the body is a `String`. 
  
  <!-- The `id` in `variables` is the Token `id`.  
  The `startTime` and `endTime` in `variables.stages` is the sales period of the basic token campaign. The value of them is in the form of millisecond timestamp UTC+0.  
  The `priceMultiplier` in `variables.stages` is the bonus rate in fractional number form. For example, if to set a bonus rate at 30%, then `numerator` : `denominator` should be `1 : 1.3`.  
  The `cap` in `variables.stages` is the target cap of the basic token campaign. For example, if to set a cap to `123 Token`, then the `cap` should be `"123000000000000000000"`, which is a **decimaled number string**. -->

### Response
```
{
  "data":{
    "createCampaign":{
      "transaction":{
        "nonce":"0x5",
        "gasPrice":"0x3b9aca00",
        "gas":"0x161737",
        "to":"0x72E8CcAeB65b5071095fF92Ed773ccFffcc42378",
        "value":"0x0",
        "data":"0x4000aea0000000000000000000000000e23d3eae3643cefc8ffe15b9635b3ef065f7a1af0000000000000000000000000000000000000000000054b40b1f852bda000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000001246f40b1c9000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009b14fc2cd40195d68cb0b40b5c64dd1fb7c929da000000000000000000000000000000000000000000000000000000005a4860c0000000000000000000000000000000000000000000000000000000005c2994400000000000000000000000000000000000000000000054b40b1f852bda00000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
        "chainId":42
      },
      "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiImXHUwMDBmw54wwrHChFx1MDAxMcOowotcdTAwMTHDn8KNw4XDscKTRSIsImFjdGlvbiI6ImNyZWF0ZVRva2VuQ2FtcGFpZ24iLCJkYXRhIjoiUUFDdW9BQUFBQUFBQUFBQUFBQUFBT0k5UHE0MlE4NzhqLzRWdVdOYlB2Qmw5Nkd2QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFGUzBDeCtGSzlvQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBWUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBRWtiMEN4eVFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQ2JGUHdzMUFHVjFveXd0QXRjWk4wZnQ4a3AyZ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJhU0dEQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFGd3BsRUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVZMUUxINFVyMmdBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBSUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBPSIsImluZm8iOnsidG9rZW5JZCI6ImzCjMK7XHUwMDA0wrHCplx1MDAxMcOowotcdTAwMTFzw6zDucO7wqddIiwibmFtZSI6eyJlbiI6Ik9NRzEifSwiZGVzY3JpcHRpb24iOnsiZW4iOiJPTUcgMSAhISEhISJ9LCJzdGFnZXMiOlt7Im5hbWUiOnsiZW4iOiJPTUcxIn0sImRlc2NyaXB0aW9uIjp7ImVuIjoiT01HIDEgISEhISEifSwiaXNQcml2YXRlIjpmYWxzZSwic3RhcnRUaW1lIjoiMTUxNDY5MjgwMDAwMCIsImVuZFRpbWUiOiIxNTQ2MjI4ODAwMDAwIiwiY2FwIjoiNDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwIiwicHJpY2VNdWx0aXBsaWVyIjp7Im51bWVyYXRvciI6IjEiLCJkZW5vbWluYXRvciI6IjIifX1dfSwiaWF0IjoxNTQwMjAyMzM2LCJleHAiOjE1NDAyMDI5MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTpzdWJtaXRfdG9rZW4ifQ._XOTHBjgtZZdagmg0z8-k73az2c1VZ7Dq7Fv2NylzG9cFlFsdXjpD4vMrzJx3oifT2xcymf29ftxg2-4oNZzUg",
      "hash":"0xab3fa519a3aefedbgryja3287eceae3c8f779cc43ee82b01d40fa1aeb5ad9f69",
      "metadata":{
        "fee": {
          "type": "ETH",
          "amount": "223624000000000"
        }
      }
    }
  }
}
```

This API responses a ABI-Encoded transaction for creating the basic token campaign, and the end-user (the sender, the requester) has to sign the `transaction` object in the response via [ETH Key lib JS](https://github.com/fstnetwork/eth-key-lib-js), then send the signed transaction and the `submitToken` to [SubmitSignedTransaction API]().


## Parameters
### Request 
  - **`id`** \<ID!>
    - ID of the Smart Voucher to sell. ID is a global identifier.
  - **`name`** \<String!>
    - Campaigm name.
  - **`description`** \<String!>
    - Campaign description.
  - **`stages`** **(Currently only support one stage in `stages`.)** \<[CampaignStageInput]!>
    - **`name`** \<String!>
      - The campaign stage name.
    - **`startTime`** \<String!>
      - The campaign stage start time. The format is Unix Timestamp in millisecond resolution.
    - **`endTime`** \<String!>
      - The campaign stage end time. The format is Unix Timestamp in millisecond resolution.
    - **`priceMultiplier`** \<Fraction!>
      - The multiplier to the price for this campaign stage. Must be less than or equal to 1. Must be greater than 0. (_For example, assume the original Smart Voucher price is 1 YourVoucher = 100 YourToken, if you have created a 20% discount stage, the priceMultiplier you get is: {numerator: 80, denominator: 100}. So the token price is allowed to be 1 YourVoucher = 80 YourToken._) 
      - **`numerator`** \<String!>
        - The numerator of this fraction.
      - **`denominator`** \<String!>
        - The denominator of this fraction.
    - **`cap`** \<String!>
      - Total amount of token for sale during this campaign stage. The format is Decimaled Number.
    - **`isPrivate`** \<Boolean!>
      - The campaign stage is private or not.
    - **`description`** \<String!>
      - The campaign stage description.
  - **`por`** \<PORMode>
    - `ENABLE` or `DISABLE` \<enum>
    - Default is `DISABLE`.

### Response
  - **`createCampaign`** \<CreateCampaignPayload>
    - **`transaction`** \<JSON>
      - UNSIGNED raw transaction format in Ethereum.
    - **`submitToken`** \<String>
      - The value for [SubmitSignedTransaction API]().
    - **`pendingTransactions`** \<String>
      - Amount of your transactions which are still pending.
    - **`hash`** \<String>
      - PORMode `ENABLE`: Hash of the abi encode.
      - PORMode `DISABLE`: Hash of the RLP encode.
    - **`metadata`** \<JSON>
      - Metadata of the transaction.