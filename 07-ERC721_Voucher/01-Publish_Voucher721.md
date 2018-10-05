
# Publish Voucher721

## GraphQL API

- Query String
```
mutation publishVoucher721($input: PublishVoucher721Input!) {
  publishVoucher721(input: $input) {
    transaction
    submitToken
    pendingTransactions
  }
}
```
- Query Variables

```
{  
   "input":{  
      "name":"Test",
      "symbol":"SAINT_TEST",
      "consumable":true,
      "totalSupply":"1000",
      "price":{  
         "numerator":"100000000000000000000",
         "denominator":"1"
      },
      "expiry":"1546271999000",
      "description":"this is test voucher",
      "proofOfContract":null
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

- Endpoint
  - For development: `https://test.fstk.io/api`
  - For production: `https://engine.fstk.io/api`

- Request Headers
  - accept: `application/json`
  - method: `POST`
  - content-type: `multipart/form-data; boundary=----WebKitFormBoundaryAf6uaPII9ots7k9B` 
  - authorization
```
Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiaWF0IjoxNTM4NzA5MDM2LCJleHAiOjE1Mzg3OTU0MzYsImF1ZCI6InVybjpmc3RrOmVuZ2luZSIsImlzcyI6InVybjpmc3RrOmVuZ2luZSIsInN1YiI6InVybjpmc3RrOmVuZ2luZTphY2Nlc3NfdG9rZW4ifQ.msJZ61FHIkKtjUpDs4sx1Kk1rb9vdhus3ntUDj6rHNmsygiHTgOEMQFJMtVqtWqkNgrtRgGpngq8Rf47xTT53g
```

- Form Data
``` 

------WebKitFormBoundaryAf6uaPII9ots7k9B
Content-Disposition: form-data; name="operations"

{"query":"mutation publishVoucher721($input: PublishVoucher721Input!) {\n             publishVoucher721(input: $input) {\n               transaction\n               submitToken\n               pendingTransactions\n             }\n           }","variables":{"input":{"name":"Test","symbol":"SAINT_TEST","consumable":true,"hardCap":"1000","price":{"numerator":"100000000000000000000","denominator":"1"},"expiry":"1546271999000","description":"this is test voucher","proofOfContract":null}}}
------WebKitFormBoundaryAf6uaPII9ots7k9B
Content-Disposition: form-data; name="map"

{"proofOfContract":["variables.input.proofOfContract"]}
------WebKitFormBoundaryAf6uaPII9ots7k9B
Content-Disposition: form-data; name="proofOfContract"; filename="proof_of_contract.pdf"
Content-Type: application/pdf


------WebKitFormBoundaryAf6uaPII9ots7k9B--


```

## HTTP Response
```
{
   "data":{
      "publishVoucher721":{
         "transaction":{
            "nonce":"0xc1",
            "gasPrice":"0x3b9aca00",
            "gas":"0x47620c",
            "to":"0xfB7a710eAc15c29607Aa36F38b26F85720908799",
            "value":"0x0",
            "data":"0x50698bb40000000000000000000000000000000000000000000000000000000000000140000000000000000000000000000000000000000000000000000000000000018000000000000000000000000000000000000000000000000000000000000003e800000000000000000000000000000000000000000000000000000000000001c0000000000000000000000000000000000000000000000000000000005c2a3cff0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000ba7819f8745b0b1644eefaa4a490e3667d77da420000000000000000000000000000000000000000000000056bc75e2d631000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000045465737400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000b5341494e54325f5445535400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000632f697066732f7a4275724b38434473556d52524d756a544156316355767a625841316a6236325a42674662676e714a61573751416d644e354656526d584132646f457777374e7876544732507973317763357351524b4e7847715546355648784679670000000000000000000000000000000000000000000000000000000000",
            "chainId":42
         },
         "submitToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCIsImtpZCI6ImZzdGstZW5naW5lIn0.eyJ1aWQiOiLDr1xiw73Ch8KDSFx1MDAxMcOowo5awrvCqsOAXHUwMDAywrwmIiwiYWN0aW9uIjoicHVibGlzaFZvdWNoZXIiLCJkYXRhIjoiVUdtTHRBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUZBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFZQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFENkFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBSEFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBRndxUFA4QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUUFBQUFBQUFBQUFBQUFBQUxwNEdmaDBXd3NXUk83NnBLU1E0Mlo5ZDlwQ0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFGYThkZUxXTVFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFRQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFCQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBUlVaWE4wQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUxVMEZKVGxReVgxUkZVMVFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFZeTlwY0daekwzcENkWEpMT0VORWMxVnRVbEpOZFdwVVFWWXhZMVYyZW1KWVFURnFZall5V2tKblJtSm5ibkZLWVZjM1VVRnRaRTQxUmxaU2JWaEJNbVJ2UlhkM04wNTRkbFJITWxCNWN6RjNZelZ6VVZKTFRuaEhjVlZHTlZaSWVFWjVad0FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBIiwiaW5mbyI6eyJ0b2tlbklkIjoiwoDDssO6wrLCu1x1MDAxMVx1MDAxMcOowqd8wqsqZsKewo7DvyIsIm5hbWUiOnsiZW4iOiJUZXN0In0sInN5bWJvbCI6IlNBSU5UMl9URVNUIiwidG90YWxTdXBwbHkiOiIxMDAwIiwibWV0YWRhdGEiOiJBYlVmZUJBbHkvTkxsV0N6NmFoenRROXZqZStvSE5yVHFaSHZKM0s1eTd1WE5reUd4akR3ZmFwdnFzL05CY2YyR1lkOUI3MEIwUFl4R2RLeWoyczJ4L0hrOER6biIsImxpcXVpZCI6dHJ1ZSwiYXBwcm92ZUNoZWNraW5nIjpmYWxzZSwiZXhwaXJ5IjoxNTQ2MjcxOTk5MDAwLCJjb25zdW1hYmxlIjp0cnVlLCJwcmljZSI6eyJudW1lcmF0b3IiOiIxMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJkZW5vbWluYXRvciI6IjEifSwidmVuZGlibGUiOnRydWUsImRlc2NyaXB0aW9uIjp7ImVuIjoidGhpcyBpcyB0ZXN0IHZvdWNoZXIifX0sImlhdCI6MTUzODcwOTc3NiwiZXhwIjoxNTM4NzEwMzc2LCJhdWQiOiJ1cm46ZnN0azplbmdpbmUiLCJpc3MiOiJ1cm46ZnN0azplbmdpbmUiLCJzdWIiOiJ1cm46ZnN0azplbmdpbmU6c3VibWl0X3Rva2VuIn0.azbN3MrIeEZWNy0jYmRmBb9KEudj8QKtg9RbY4hoX8mkfGsRn3qBIJURQLAliohchP6Xj0p7uw8mG5UE9MtQpg",
         "pendingTransactions":"0"
      }
   },
   "extensions":{
      "tracing":{
         "version":1,
         "startTime":"2018-10-05T03:22:55.737Z",
         "endTime":"2018-10-05T03:22:56.578Z",
         "duration":841886358,
         "execution":{
            "resolvers":[
               {
                  "path":[
                     "publishVoucher721"
                  ],
                  "parentType":"Mutation",
                  "fieldName":"publishVoucher721",
                  "returnType":"publishVoucher721Payload",
                  "startOffset":1436802,
                  "duration":840383182
               },
               {
                  "path":[
                     "publishVoucher721",
                     "transaction"
                  ],
                  "parentType":"publishVoucher721Payload",
                  "fieldName":"transaction",
                  "returnType":"JSON!",
                  "startOffset":841847404,
                  "duration":9306
               },
               {
                  "path":[
                     "publishVoucher721",
                     "submitToken"
                  ],
                  "parentType":"publishVoucher721Payload",
                  "fieldName":"submitToken",
                  "returnType":"String!",
                  "startOffset":841861798,
                  "duration":1890
               },
               {
                  "path":[
                     "publishVoucher721",
                     "pendingTransactions"
                  ],
                  "parentType":"publishVoucher721Payload",
                  "fieldName":"pendingTransactions",
                  "returnType":"String!",
                  "startOffset":841866770,
                  "duration":2932
               }
            ]
         }
      },
      "cacheControl":{
         "version":1,
         "hints":[
            {
               "path":[
                  "publishVoucher721"
               ],
               "maxAge":0
            }
         ]
      }
   }
}
```
