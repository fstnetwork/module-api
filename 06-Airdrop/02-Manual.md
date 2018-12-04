
# Create Airdrop

## GraphQL API

- Query String
  ```
  mutation {
    createAirdropManual(input: {targets: [{address: "0x00417ed5049c1aaaaf765dc6058309d557beb3b2", amount: "1000000000000000000"}, {address: "0x3b43743ca6bb824Be079c96521338aCb44a94AE3", amount: "2000000000000000000"}, {address: "0x003BBCE1eAC59b406dd0e143e856542Df3659075", amount: "3000000000000000000"}, {address: "0xc6FAf247f4aF84670A1b2Cfb439805fD4eD665D5", amount: "4000000000000000000"}, {address: "0x403dAcf48EE82Fe50cd93d2141D49DBe9F252fE6", amount: "5000000000000000000"}, {address: "0x0D30b2307540B4808a7867337BA5343E48EcD2Cb", amount: "6000000000000000000"}]}) {
      airdropManual {
        id
        totalAddresses
        totalAirdropAmount
      }
    }
  }
  ```
- Query Variables

  ```
  {  
    "input":[  
      {  
        "address":"0x00417ed5049c1aaaaf765dc6058309d557beb3b2",
        "amount":"1000000000000000000"
      },
      {  
        "address":"0x3b43743ca6bb824Be079c96521338aCb44a94AE3",
        "amount":"2000000000000000000"
      },
      {  
        "address":"0x003BBCE1eAC59b406dd0e143e856542Df3659075",
        "amount":"3000000000000000000"
      },
      {  
        "address":"0xc6FAf247f4aF84670A1b2Cfb439805fD4eD665D5",
        "amount":"4000000000000000000"
      },
      {  
        "address":"0x403dAcf48EE82Fe50cd93d2141D49DBe9F252fE6",
        "amount":"5000000000000000000"
      },
      {  
        "address":"0x0D30b2307540B4808a7867337BA5343E48EcD2Cb",
        "amount":"6000000000000000000"
      }
    ]
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
   "query":"mutation {\name     createAirdropManual(input: $input) {\n     airdropManual {\n     id\n     totalAddresses\n     totalAirdropAmount\n     }\n     }\n     }\n\n",
   "variables":{  
      "input":[  
        {  
          "address":"0x00417ed5049c1aaaaf765dc6058309d557beb3b2",
          "amount":"1000000000000000000"
        },
        {  
          "address":"0x3b43743ca6bb824Be079c96521338aCb44a94AE3",
          "amount":"2000000000000000000"
        },
        {  
          "address":"0x003BBCE1eAC59b406dd0e143e856542Df3659075",
          "amount":"3000000000000000000"
        },
        {  
          "address":"0xc6FAf247f4aF84670A1b2Cfb439805fD4eD665D5",
          "amount":"4000000000000000000"
        },
        {  
          "address":"0x403dAcf48EE82Fe50cd93d2141D49DBe9F252fE6",
          "amount":"5000000000000000000"
        },
        {  
          "address":"0x0D30b2307540B4808a7867337BA5343E48EcD2Cb",
          "amount":"6000000000000000000"
        }
      ]
    }
  }
  ```

  The value of `query` in the body is a `String`. 
  

## HTTP Response
```
{  
  "data":{  
    "createAirdropManual":{  
      "airdropManual":{  
        "id":"QWlyZHJvcE1hbnVhbDo0",
        "totalAddresses":"6",
        "totalAirdropAmount":"21000000000000000000"
      }
    }
  }
}
```
