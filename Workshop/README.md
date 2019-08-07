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
  - [ethereumKey](/Workshop/engine/ethereumKey.md)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/engine/createUser.md)
  - [createUserAndTransferAsset](/Workshop/engine/createUserAndTransferAsset.md)

- Mutation `TRANSACT`
  - [fillGas](/Workshop/engine/fillGas.md)
  - [mintIL](/Workshop/engine/mintIL.md)
  - [transferETH](/Workshop/engine/transferETH.md)
  - [transferERC20Token](/Workshop/engine/transferERC20Token.md)
  - [transferERC721Token](/Workshop/engine/transferERC721Token.md)
  - [transferMasterToken](/Workshop/engine/transferMasterToken.md)
  - [submitSignedTransaction](/Workshop/engine/submitSignedTransaction.md)


## Issuer
(Authentication Required)
- Query `READ`
  - [getERC20TokenBasicInfo](/Workshop/explorer/getERC20TokenBasicInfo.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getERC721TokenBasic](/Workshop/explorer/getERC721TokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getAllUser](/Workshop/engine/getAllUser.md)
  - [ethereumKey](/Workshop/engine/ethereumKey.md)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/engine/createUser.md)
  - [createUserAndTransferAsset](/Workshop/engine/createUserAndTransferAsset.md)
  - [issueToken](/Workshop/engine/issueToken.md)
  - [publishFungibleVoucher](/Workshop/engine/publishFungibleVoucher.md)
  - publishNonFungibleVoucher

- Mutation `TRANSACT`
  - [fillGas](/Workshop/engine/fillGas.md)
  - [transferETH](/Workshop/engine/transferETH.md)
  - [transferERC20Token](/Workshop/engine/transferERC20Token.md)
  - [transferERC721Token](/Workshop/engine/transferERC721Token.md)
  - mintErc721
  - [submitSignedTransaction](/Workshop/engine/submitSignedTransaction.md)


## User
(Authentication Required)
- Query `READ`
  - [ethereumKey](/Workshop/engine/ethereumKey.md)
  - [getAccessTokenExpireTime](/Workshop/engine/getAccessTokenExpireTime.md)

- Mutation `TRANSACT`
  - [fillGas](/Workshop/engine/fillGas.md)
  - [transferETH](/Workshop/engine/transferETH.md)
  - [transferERC20Token](/Workshop/engine/transferERC20Token.md)
  - [transferERC721Token](/Workshop/engine/transferERC721Token.md)
  - [submitSignedTransaction](/Workshop/engine/submitSignedTransaction.md)


## Explorer
(Authentication NOT Required)
- Query `READ`
  - [getBlockInfoByBlockNumber](/Workshop/explorer/getBlockInfoByBlockNumber.md)
  - [getBlocksInfo](/Workshop/explorer/getBlocksInfo.md)
  - [getTransactionInfoByHash](/Workshop/explorer/getTransactionInfoByHash.md)
  - [getATransactionsInfo](/Workshop/explorer/getATransactionsInfo.md)
  - [getAccountInfo](/Workshop/explorer/getAccountInfo.md)
  - [getERC20TokenList](/Workshop/explorer/getERC20TokenList.md)
  - [getERC20TokenBasicInfo](/Workshop/explorer/getERC20TokenBasicInfo.md)
  - [getAllERC20TokenTransferHistory](/Workshop/explorer/getAllERC20TokenTransferHistory.md)
  - [getERC721TokenList](/Workshop/explorer/getERC721TokenList.md)
  - [getERC721TokenBasic](/Workshop/explorer/getERC721TokenBasic.md)
  - [getAllERC721TokenTransferHistory](/Workshop/explorer/getAllERC721TokenTransferHistory.md)
  - erc721TokenURI
  - [getSmartTokenList](/Workshop/explorer/getSmartTokenList.md)
  - [getSmartTokenInfoByAddress](/Workshop/explorer/getSmartTokenInfoByAddress.md)
    - info
      - voucherList
    - holders
    - Transfers
    - Transactions
  - issueTokenList
  - [getSmartVoucherList](/Workshop/explorer/getSmartVoucherList.md)
  - [getSmartVoucher1376Basic](/Workshop/explorer/getSmartVoucher1376Basic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getSmartVoucher721Basic](/Workshop/explorer/getSmartVoucher721Basic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getGasConsumeHistory](/Workshop/explorer/getGasConsumeHistory.md)
  - [getGasConsumeHistoryByAddress](/Workshop/explorer/getGasConsumeHistoryByAddress.md)
  - [getGasPurchaseHistory](/Workshop/explorer/getGasPurchaseHistory.md)
  - [getGasPurchaseHistoryByAddress](/Workshop/explorer/getGasPurchaseHistoryByAddress.md)
  - [getILMintHistory](/Workshop/explorer/getILMintHistory.md)
  - [getILMintHistoryByAddress](/Workshop/explorer/getILMintHistoryByAddress.md)
  - [getTokenIssuenceHistory](/Workshop/explorer/getTokenIssuenceHistory.md)
  - [getTokenIssuenceHistoryByAddress](/Workshop/explorer/getTokenIssuenceHistoryByAddress.md)
  - [getTokenIssuenceHistoryByTransaction](/Workshop/explorer/getTokenIssuenceHistoryByTransaction.md)
  - [getAllSmartVoucher20](/Workshop/explorer/getAllSmartVoucher20.md)
  - [getSmartVoucher20ByPublisher](/Workshop/explorer/getSmartVoucher20ByPublisher.md)
  - [getSmartVoucher20ByTransaction](/Workshop/explorer/getSmartVoucher20ByTransaction.md)
  - [getAllSmartVoucher721](/Workshop/explorer/getAllSmartVoucher721.md)
  - [getSmartVoucher721ByPublisher](/Workshop/explorer/getSmartVoucher721ByPublisher.md)
  - [getSmartVoucher721ByTransaction](/Workshop/explorer/getSmartVoucher721ByTransaction.md)

