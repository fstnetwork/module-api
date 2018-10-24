
# Create Basic Voucher Campaign

## GraphQL API

- Query String
  ```
  mutation CreateCampaign($input: CreateCampaignInput!) {
    createCampaign(input: $input) {
      transaction
      submitToken
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
            "numerator":"70000000000000000000",
            "denominator":"100000000000000000000"
          },
          "cap":"123",
          "isPrivate":false,
          "description":"This is my first basic voucher campaign"
        }
      ]
    }
  }
  ```
- HTTP Headers 
  ```
  {
    "authorization": "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NTYyODAyLCJleHAiOjE1Mzg2NDkyMDIsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.sGfxYe16aRx_vmvzlRps_gcyTeQD-zsR5HCtjXQ3hYpQYjN1lOFkdpF0m4Yrrh8uHyWBYifqYUVHmkRej4-9gA"
  }
  ```
## HTTP Request

- URL
  - For development: `https://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`

- Method: `POST`

- Headers
  - accept: `application/json`
  - content-type: `application/json` 
  - authorization
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
              "numerator":"70000000000000000000",
              "denominator":"100000000000000000000"
            },
            "cap":"123",
            "isPrivate":false,
            "description":"This is my first basic voucher campaign"
          }
        ]
      }
    }
  }
  ```

  The value of `query` in the body is a `String`. 
  
  The `id` in `variables` is the Token `id`.  
  The `startTime` and `endTime` in `variables.stages` is the sales period of the basic voucher campaign. The value of them is in the form of millisecond timestamp UTC+0.  
  The `priceMultiplier` in `variables.stages` is the bonus rate in fractional number form. For example, if to set a bonus rate at 30%, then `numerator` : `denominator` should be `0.7 : 1`.  
  The `cap` in `variables.stages` is the target cap of the basic voucher campaign. For example, if to set a cap to `123 Voucher`, then the `cap` should be `"123"`, which is a **decimaled number string**.

## HTTP Response
```
{
  "data":{
    "publishVoucher":{
      "transaction":{
        "nonce":"0x7",
        "gasPrice":"0x3b9aca00",
        "gas":"0x47600b",
        "to":"0xfB7a710eAc15c29607Aa36F38b26F85720908799",
        "value":"0x0",
        "data":"0x50698bb400000000000000000000000000000000000000000000000000000000000001400000000000000000000000000000000000000000000000000000000000000180000000000000000000000000000000000000000000000000000000000000303900000000000000000000000000000000000000000000000000000000000001c0000000000000000000000000000000000000000000000000000000005c2a3cff0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000218dc7d90ea84bded83234aa23a520f1cfb43ac10000000000000000000000000000000000000000000000000de0b6b3a764000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000356563100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007454f535f5656310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b4261486337594a794c33707873586d7638796e73787041634a6e6e356d666746795a4732727a725834427a4a637362645a625a4e77644c5a556f54756962756546427a376b65656d7179517a4156566a6d7a34476e55716b0000000000000000000000000000000000000000000000000000000000",
        "chainId":42
      },
      "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDvSBcbsOgwrHCg1x1MDAxMcOowotcdTAwMTHDh8OJV8Oaw71cdTAwMWMiLCJhY3Rpb24iOiJwdWJsaXNoVm91Y2hlciIsImRhdGEiOiJVR21MdEFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBRkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVlBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQXdPUUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFIQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFGd3FQUDhBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFRQUFBQUFBQUFBQUFBQUFBQ0dOeDlrT3FFdmUyREkwcWlPbElQSFB0RHJCQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFEZUMyczZka0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUJBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFOV1ZqRUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBSFJVOVRYMVpXTVFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQVl5OXBjR1p6TDNwQ2RYSkxRbUZJWXpkWlNubE1NM0I0YzFodGRqaDVibk40Y0VGalNtNXVOVzFtWjBaNVdrY3ljbnB5V0RSQ2VrcGpjMkprV21KYVRuZGtURnBWYjFSMWFXSjFaVVpDZWpkclpXVnRjWGxSZWtGV1ZtcHRlalJIYmxWeGF3QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUEiLCJpbmZvIjp7InRva2VuSWQiOiLDu8K6ZMKswrHCplx1MDAxMcOowotcdTAwMTFcdTAwMWZ-wq1cdTAwMTnCtMO2IiwibmFtZSI6eyJlbiI6IlZWMSJ9LCJzeW1ib2wiOiJFT1NfVlYxIiwidG90YWxTdXBwbHkiOiIxMjM0NSIsIm1ldGFkYXRhIjoiQWJVZmVCRE80QXQrUUlEWUVuOGhnT05EZjhtQ2tyTWJKR0g3OENhL1F0SW01RWtaeE5WamptcC9RM2VXeXVUbWJSR0tFOTk5ZnlOVkdUaDQ2c1M5WjNSZzhRZnYiLCJsaXF1aWQiOnRydWUsImFwcHJvdmVDaGVja2luZyI6ZmFsc2UsImV4cGlyeSI6MTU0NjI3MTk5OTAwMCwiY29uc3VtYWJsZSI6dHJ1ZSwicHJpY2UiOnsibnVtZXJhdG9yIjoiMTAwMDAwMDAwMDAwMDAwMDAwMCIsImRlbm9taW5hdG9yIjoiMSJ9LCJ2ZW5kaWJsZSI6dHJ1ZSwiZGVzY3JpcHRpb24iOnsiZW4iOiIxMjM0XG4xXG5WVjEifX0sImlhdCI6MTU0MDM5MDc4OCwiZXhwIjoxNTQwMzkxMzg4LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.zRb1XUZcasR5rEwO4O_A3Zkskfu6HVyZRt-iJhiEzQuZ0DW_Kw02ft5lwrmXzVk6kcOZLyiTiZbH_t3azj_LTA",
      "pendingTransactions":"0"
    }
  }
}
```

This API responses a ABI-Encoded transaction for creating the basic voucher campaign, and the end-user (the sender, the requester) has to sign the `transaction` object in the response via [ETH Key lib JS](https://github.com/funderstoken/eth-key-lib-js), then send the signed transaction and the `submitToken` to [SubmitSignedTransaction API](https://github.com/funderstoken/module-api/tree/master/SubmitSignedTransaction).
