# Workshop
This directory contains 5 aspects of FST Engine APIs.

![permission](/Workshop/images/permission.png)

## Glossary

- `Accountable Data Network`
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
  - An ERC-1376 implementation with fungible, 18-decimals ledger.
- `Voucher / Smart Voucher / Fungible Voucher`
  - An ERC-1376 implementation with fungible, 0-decimal ledger.
- `EthereumKey / Wallet File / Key File / Key Storage`
  - A JSON that contains encrypted ECDSA private key, the passphrase hashing is used with Scrypt/PBKDF2.
- `Gas / Fuel`
  - A protocol-level and service-level gas that protects the entire Data Network from the DDOS attack, since any instruction on a Data Network must consume gas. In FST PPB/Engine, the protocol-gas is called Ether, and the service-gas is Master Service Gas.
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

## Auth
- Mutation
  - [signIn](/Workshop/signin/signIn.md)
- Query `READ` (Authentication Required)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

## Master
(Authentication Required)
- Query `READ`
  - [getAllUser](/Workshop/engine/getAllUser.md)
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
(Authentication Required)
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
  - [getAllUser](/Workshop/engine/getAllUser.md)
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


## User
(Authentication Required)
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
(Authentication NOT Required)
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

