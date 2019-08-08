# Gas Consume History
- Endpoint
  - For development: `http://api.dev.fstk.io/signin`
  - Customer: *`CUSTOMER_URL`*
- Method: `POST`
- Header:
  - accept: `application/json`
  - content-type: `application/json`
- API Query
  - [signIn](#signIn)
  - [getAccessTokenExpireTime](#getAccessTokenExpireTime)

## signIn
### GraphQL Query
``` js
  
```

### Response
```json

```

### Parameters
#### Request
- `first` \<String>
- `after` \<Int>

#### Response
- `consumeGas` <ＣonsumeGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[ＣonsumeGasEdge]>
    - `node` <ＣonsumeGasType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `corporation` <String!>
      - `initiator` <String!>
      - `key` <String!>
      - `price` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `amount` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>

---

## getAllGasConsumeHistoryByAddress

### GraphQL Query
```js

```
### Response
```json

```
### Parameters
#### Request
- `address` <String!>
- `first` \<String>
- `after` \<Int>

#### Response
- `consumeGas` <ＣonsumeGasConnection!>
  - `pageInfo` <PageInfo!>
  - `edges` <[ＣonsumeGasEdge]>
    - `node` <ＣonsumeGasType>
      - `id` <ID!>
      - `block` <String!>
      - `transactionHash` <String!>
      - `transctionLogIndex` <String!>
      - `contract` <String!>
      - `corporation` <String!>
      - `initiator` <String!>
      - `key` <String!>
      - `price` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `amount` \<FractionType>
        - `numerator` <String!>
        - `denominator` <String!>
      - `age` <String!>
    - `cursor` <String!>
  - `totalCount` <Int!>