
# Get Smart Token Information
Your are able to fetch the information of the Smart Token which you issued via this API.

## GraphQL API

- Query String
  ```
  query getSmartTokenInfo {
    me {
      token {
        id
        issuer {
          firstName
          lastName
          ethereumAddress
        }
        contractAddress 
        name
        symbol
        totalSupply
        decimals
        price {
          numerator
          denominator
        }
        website
        proofOfContract {
          ipfs
        }
        logo {
          ipfs
        }
      }
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
    "query":"query getSmartTokenInfo {  me {  token {  id  issuer  {  firstName lastName  ethereumAddress  }  contractAddress  name  symbol  totalSupply  decimals  price {  numerator  denominator  }  website  proofOfContract {  ipfs  }  logo {  ipfs  }  }  }  }"
  }
  ```
  The value of `query` in the body is a `String`. 


### Response
```
{
  "data": {
    "me": {
      "token": {
        "id": "VG9rZW46wrRGCwoaw68Rw6nCujsXbMKew7Bzwqc=",
        "issuer": {
          "firstName": "Alice",
          "lastName": "Chen",
          "ethereumAddress": "0x57696b6d8a801bb571c3ef51ff0d9e135623b1cb"
        },
        "contractAddress": "0x4711e92ad968a6488500bc5dde2a48ee17743ab1",
        "name": "Wood House",
        "symbol": "WHOUSE",
        "totalSupply": "12000000000000000000000000000",
        "decimals": "18",
        "price": {
          "numerator": "1",
          "denominator": "9600"
        },
        "website": "qwerqwer.com",
        "proofOfContract": {
          "ipfs": "zBurKCMe3gh7h7nQf2QaaTzGuK1JT3BJyApP6t72zDFSLedmz5bJkZQiCVnd52PGrGxFoc2VNeYvqc25yxTYEFG87tRPT/proofOfContract/default"
        },
        "logo": {
          "ipfs": "zBurKCMe3gh7h7nQf2QaaTzGuK1JT3BJyApP6t72zDFSLedmz5bJkZQiCVnd52PGrGxFoc2VNeYvqc25yxTYEFG87tRPT/image/default"
        }
      }
    }
  }
}
```



## Parameters
### Response
- **`token`** \<Token>
  - **`id`** \<ID!>
    - Smart Token ID. ID is a global identifier.
  - **`issuer`** \<UserInfo!>
    - **`firstName`** \<String>
      - Issuer first name.
    - **`lastName`** \<String>
      - Issuer last name.
    - **`ethereumAddress`** \<String>
      - Issuer's ethereum address.
  - **`contractAddress`** \<String!>
    - The Smart Token contract address.
  - **`name`** \<String!>
    - Smart Token name.
  - **`symbol`** \<String!>
    - Smart Token symbol.
  - **`totalSupply`** \<String!>
    - Total supply of the Smart Token. The format is Decimaled Number.
  - **`decimals`** \<String>
    - Smart Token decimals.
  - **`price`** \<Fraction!>
    - Smart Token price.
    - **`numerator`** \<String!>
      - The numerator of this fraction.
    - **`denominator`** \<String!>
      - The denominator of this fraction.
  - **`website`** \<String!>
    - The Smart Token related website.
  - **`proofOfContract`** \<File!>
    - **`ipfs`** \<String!>
      - Information of the contract's IPFS link.
  - **`logo`** \<File!>
    - **`ipfs`** \<String!>
      - Information of the Smart Token logo's IPFS link.

