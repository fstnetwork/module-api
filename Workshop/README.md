# Workshop
This Quick-Start Workshop covers the essentials of FST Engine APIs.

## Glossary

- `FST DataRail` / `Accountable Data Network`
  - An unique network mechanism that insures the consistency, insertion order and existence of any data.
- `Address`
  - An entry on Accountable Data Network, each address can actually belong to one private key, but it is cryptographically difficult to be reverse-attacked, since the address is the hash of the public key, and there are 16^40 addresses available.
- `Contract / Smart Contract`
  - A set of program modules deployed on accountable data network with their business-logics and storages are computed/executed exactly under their pre-configurations and constraints. A Smart Contract has its own address.
- `Ledger`
  - A kind of Smart Contract that stores the Accountable Data and verifies the authentication/authorisation of any operation on it.
- `ERC-20`
  - An interface for basic ledger operations and storage.
- `ERC-721`
  - An interface for basic non-fungible ledger operations and storage.
- `ERC-1376`
  - An interface for advanced ledger operations and storage with optimisations and high-extensibility, compatible to ERC-20.
- `Token / Smart Token / Fungible Token`
  - An ERC-1376 implementation with fungible, 18-decimals ledger. (1 Token = 1 * (10^18) value stored in Token Ledger)
- `Voucher / Smart Voucher / Fungible Voucher`
  - An ERC-1376 implementation with fungible, 0-decimal ledger. (1 Voucher = 1  value stored in Voucher Ledger)
- `EthereumKey / Wallet File / Key File / Key Storage`
  - A JSON that contains encrypted ECDSA private key (using AES-128 series), the passphrase hashing is used with Scrypt/PBKDF2.
- `Gas / Fuel`
  - A protocol-level and service-level gas that protects the entire Data Network from the DDOS attack, since any instruction on a Data Network must consume gas. In FST DataRail/Engine, the protocol-gas is called Ether, and the service-gas is Master Service Gas.
- `Transaction`
  - A data set contains digital-signature, timestamping, target address, gas amount and the bytecode to the Smart Contract.
- `Transfer`
  - A balance change or data transferring in a ledger, it contains ***from*** (originator), ***to*** (receiver) and the ***value*** (data amount).
- `Password`
  - The credential for the authentication in sign-in.
- `Passphrase`
  - The credential for decrypting the EthereumKey (Wallet File / Key File) to use private key for transaction signing.
- `Master`
  - The user that manages and control the whole white-labeled platform (FST Engine).
- `Issuer`
  - The user that authorised to issue token and publish voucher (ledger creation). Only Master can authorise Issuers for ledger creation.
- `End-User`
  - The common user that can use and control its own wallet.

## Permissions and Auth Overview

![permission](/Workshop/images/permission.png)

## Common API Usage (**Important !**)

 - FST Engine API utilises [GraphQL](https://graphql.org) to make complex data-integrations agiler, type-safer and clearer
   - Please check and try immediately in your FST Engine GraphQL Playground provided by FST Network
 - Please don't forget to set `Authorization` http header via [Auth](#Auth) before performing the operations that need to be authorised (also in GraphQL Playground)
 - There are 3 common types of APIs in FST Engine: `READ`, `CREATE` and `TRANSACT`
   - `READ` indicates that the operation **reads** data from Accountable Data Network (FST DataRail)
   - `CREATE` indicates that the operation **creates** data (e.g. Smart Contracts, Ledgers) to Accountable Data Network (FST DataRail)
   - `TRANSACT` indicates that the operation **updates** data to Accountable Data Network (FST DataRail)
 - The APIs in `CREATE` type and `TRANSACT` type mostly require the **Transaction Signing** process with the API response
   - Please see more details at [signTransactionExample.md](/Workshop/example/signTransactionExample.md)
 - As for `READ` API, the pagination in this type of API follows the principle of GraphQL cursor-based pagination 
   - In short, the `pageInfo.endCursor` in the response for current page will be the `after` in next page API call. And the `first` is equal to the `limit` in common databases usage

## Auth
(Authentication and Authorisation)
- Mutation `AUTH`
  - [signIn](/Workshop/signin/signIn.md)
- Query `READ` (Authentication Required)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

## Master
([Auth](#Auth) is required)
- Query `READ`
  - [getAllEndUser](/Workshop/engine/getAllEndUser.md)
  - [getAllIssuer](/Workshop/engine/getAllIssuer.md)
  - [getEthereumKey](/Workshop/engine/getEthereumKey.md)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/engine/createUser.md)
  - [createUserAndTransferAsset](/Workshop/engine/createUserAndTransferAsset.md)

- Mutation `TRANSACT`
  - [purchaseMasterServiceGas](/Workshop/engine/purchaseMasterServiceGas.md)
  - [mintIL](/Workshop/engine/mintIL.md)
  - [transferETH](/Workshop/engine/transferETH.md)
  - [transferFungibleToken](/Workshop/engine/transferFungibleToken.md)
  - [transferFungibleVoucher](/Workshop/engine/transferFungibleVoucher.md)
  - [transferNonfungibleToken](/Workshop/engine/transferNonfungibleToken.md)
  - [transferMasterToken](/Workshop/engine/transferMasterToken.md)
  - [submitSignedTransaction](/Workshop/engine/submitSignedTransaction.md)


## Issuer
([Auth](#Auth) is required)
- Query `READ`
  - [getFungibleTokenBasic](/Workshop/explorer/getFungibleTokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getNonfungibleTokenBasic](/Workshop/explorer/getNonfungibleTokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getAllEndUser](/Workshop/engine/getAllEndUser.md)
  - [getEthereumKey](/Workshop/engine/getEthereumKey.md)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/engine/createUser.md)
  - [createUserAndTransferAsset](/Workshop/engine/createUserAndTransferAsset.md)
  - [issueToken](/Workshop/engine/issueToken.md)
  - [publishFungibleVoucher](/Workshop/engine/publishFungibleVoucher.md)
  - [publishNonFungibleVoucher](/Workshop/engine/publishNonFungibleVoucher.md)

- Mutation `TRANSACT`
  - [purchaseMasterServiceGas](/Workshop/engine/purchaseMasterServiceGas.md)
  - [transferETH](/Workshop/engine/transferETH.md)
  - [transferFungibleToken](/Workshop/engine/transferFungibleToken.md)
  - [transferFungibleVoucher](/Workshop/engine/transferFungibleVoucher.md)
  - [transferNonfungibleToken](/Workshop/engine/transferNonfungibleToken.md)
  - mintNonfungibleVoucher
  - [submitSignedTransaction](/Workshop/engine/submitSignedTransaction.md)


## End-User
([Auth](#Auth) is required)
- Query `READ`
  - [getEthereumKey](/Workshop/engine/getEthereumKey.md)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

- Mutation `TRANSACT`
  - [purchaseMasterServiceGas](/Workshop/engine/purchaseMasterServiceGas.md)
  - [transferETH](/Workshop/engine/transferETH.md)
  - [transferFungibleToken](/Workshop/engine/transferFungibleToken.md)
  - [transferFungibleVoucher](/Workshop/engine/transferFungibleVoucher.md)
  - [transferNonfungibleToken](/Workshop/engine/transferNonfungibleToken.md)
  - [submitSignedTransaction](/Workshop/engine/submitSignedTransaction.md)


## Explorer
(Auth is NOT required)
- Query `READ`
  - [getBlockInfoByBlockNumber](/Workshop/explorer/getBlockInfoByBlockNumber.md)
  - [getBlocksInfo](/Workshop/explorer/getBlocksInfo.md)
  - [getTransactionInfoByHash](/Workshop/explorer/getTransactionInfoByHash.md)
  - [getAllTransactionsInfo](/Workshop/explorer/getAllTransactionsInfo.md)
  - [getAccountInfo](/Workshop/explorer/getAccountInfo.md)
  - [getFungibleTokenList](/Workshop/explorer/getFungibleTokenList.md)
  - [getFungibleTokenBasic](/Workshop/explorer/getFungibleTokenBasic.md)
  - [getAllFungibleTokenTransferHistory](/Workshop/explorer/getAllFungibleTokenTransferHistory.md)
  - [getNonfungibleTokenList](/Workshop/explorer/getNonfungibleTokenList.md)
  - [getNonfungibleTokenBasic](/Workshop/explorer/getNonfungibleTokenBasic.md)
  - [getAllNonfungibleTokenTransferHistory](/Workshop/explorer/getAllNonfungibleTokenTransferHistory.md)
  - getNonfungibleTokenURI
  - [getSmartTokenList](/Workshop/explorer/getSmartTokenList.md)
  - [getSmartTokenInfoByAddress](/Workshop/explorer/getSmartTokenInfoByAddress.md)
  - [getSmartVoucherList](/Workshop/explorer/getSmartVoucherList.md)
  - [getFungibleSmartVoucherBasic](/Workshop/explorer/getFungibleSmartVoucherBasic.md)
  - [getNonfungibleSmartVoucherBasic](/Workshop/explorer/getNonfungibleSmartVoucherBasic.md)
  - [getGasConsumeHistory](/Workshop/explorer/getGasConsumeHistory.md)
  - [getGasConsumeHistoryByAddress](/Workshop/explorer/getGasConsumeHistoryByAddress.md)
  - [getGasPurchaseHistory](/Workshop/explorer/getGasPurchaseHistory.md)
  - [getGasPurchaseHistoryByAddress](/Workshop/explorer/getGasPurchaseHistoryByAddress.md)
  - [getILMintHistory](/Workshop/explorer/getILMintHistory.md)
  - [getILMintHistoryByAddress](/Workshop/explorer/getILMintHistoryByAddress.md)
  - [getTokenIssuenceHistory](/Workshop/explorer/getTokenIssuenceHistory.md)
  - [getTokenIssuenceHistoryByAddress](/Workshop/explorer/getTokenIssuenceHistoryByAddress.md)
  - [getTokenIssuenceHistoryByTransaction](/Workshop/explorer/getTokenIssuenceHistoryByTransaction.md)
  - [getAllFungibleSmartVoucher](/Workshop/explorer/getAllFungibleSmartVoucher.md)
  - [getFungibleSmartVoucherByPublisher](/Workshop/explorer/getFungibleSmartVoucherByPublisher.md)
  - [getFungibleSmartVoucherByTransaction](/Workshop/explorer/getFungibleSmartVoucherByTransaction.md)
  - [getAllNonfungibleSmartVoucher](/Workshop/explorer/getAllNonfungibleSmartVoucher.md)
  - [getNonfungibleSmartVoucherByPublisher](/Workshop/explorer/getNonfungibleSmartVoucherByPublisher.md)
  - [getNonfungibleSmartVoucherByTransaction](/Workshop/explorer/getNonfungibleSmartVoucherByTransaction.md)
