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
- Query `READ` (require authentication)
  - [tokenExpireTime](/Workshop/engine/tokenExpireTime.md)

## Master
require authentication
- Query `READ`
  - [getAllUser](/Workshop/engine/getAllUser.md)
  - [getAllIssuer](/Workshop/engine/getAllIssuer.md)
  - [ethereumKey](/Workshop/engine/ethereumKey.md)
  - [tokenExpireTime](/Workshop/engine/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/engine/createUser.md)
  - [createUserV2](/Workshop/engine/createUserV2.md)

- Mutation `TRANSACT`
  - [fillGas](/Workshop/engine/fillGas.md)
  - [mintIL](/Workshop/engine/mintIL.md)
  - [sendEther](/Workshop/engine/sendEther.md)
  - [erc20Transfer](/Workshop/engine/erc20Transfer.md)
  - [erc721Transfer](/Workshop/engine/erc721Transfer.md)
  - [transferMasterToken](/Workshop/engine/transferMasterToken.md)
  - [submitTransaction](/Workshop/engine/submitTransaction.md)


## Issuer
require authentication
- Query `READ`
  - [erc20TokenBasic](/Workshop/explorer/erc20TokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [erc721TokenBasic](/Workshop/explorer/erc721TokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getAllUser](/Workshop/engine/getAllUser.md)
  - [ethereumKey](/Workshop/engine/ethereumKey.md)
  - [tokenExpireTime](/Workshop/engine/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/engine/createUser.md)
  - [createUserV2](/Workshop/engine/createUserV2.md)
  - [issueToken](/Workshop/engine/issueToken.md)
  - [publishFungibleVoucher](/Workshop/engine/publishFungibleVoucher.md)
  - publishNonFungibleVoucher

- Mutation `TRANSACT`
  - [fillGas](/Workshop/engine/fillGas.md)
  - [sendEther](/Workshop/engine/sendEther.md)
  - [erc20Transfer](/Workshop/engine/erc20Transfer.md)
  - [erc721Transfer](/Workshop/engine/erc721Transfer.md)
  - mintErc721
  - [submitTransaction](/Workshop/engine/submitTransaction.md)


## User
require authentication
- Query `READ`
  - [ethereumKey](/Workshop/engine/ethereumKey.md)
  - [tokenExpireTime](/Workshop/engine/tokenExpireTime.md)

- Mutation `TRANSACT`
  - [fillGas](/Workshop/engine/fillGas.md)
  - [sendEther](/Workshop/engine/sendEther.md)
  - [erc20Transfer](/Workshop/engine/erc20Transfer.md)
  - [erc721Transfer](/Workshop/engine/erc721Transfer.md)
  - [submitTransaction](/Workshop/engine/submitTransaction.md)


## Explorer
not require authentication
- Query `READ`
  - [block](/Workshop/explorer/block.md)
  - [blocks](/Workshop/explorer/blocks.md)
  - [transaction](/Workshop/explorer/transaction.md)
  - [transactions](/Workshop/explorer/transactions.md)
  - [account](/Workshop/explorer/account.md)
  - [erc20TokenList](/Workshop/explorer/erc20TokenList.md)
  - [erc20TokenBasic](/Workshop/explorer/erc20TokenBasic.md)
  - [erc20Transfers](/Workshop/explorer/erc20Transfers.md)
  - [erc721TokenList](/Workshop/explorer/erc721TokenList.md)
  - [erc721TokenBasic](/Workshop/explorer/erc721TokenBasic.md)
  - [erc721Transfers](/Workshop/explorer/erc721Transfers.md)
  - erc721TokenURI
  - [smartTokenList](/Workshop/explorer/smartTokenList.md)
  - [smartTokenBasic](/Workshop/explorer/smartTokenBasic.md)
    - info
      - voucherList
    - holders
    - Transfers
    - Transactions
  - issueTokenList
  - [smartVoucherList](/Workshop/explorer/smartVoucherList.md)
  - [smartVoucherErc20Basic](/Workshop/explorer/smartVoucherErc20Basic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [smartVoucherErc721Basic](/Workshop/explorer/smartVoucherErc721Basic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [consumeGas](/Workshop/explorer/consumeGas.md)
  - [consumeGasByAddress](/Workshop/explorer/consumeGasByAddress.md)
  - [purchaseGas](/Workshop/explorer/purchaseGas.md)
  - [purchaseGasByAddress](/Workshop/explorer/purchaseGasByAddress.md)
  - [mintILList](/Workshop/explorer/mintILList.md)
  - [IssueTokenAll](/Workshop/explorer/IssueTokenAll.md)
  - [IssueTokenByIssuer](/Workshop/explorer/IssueTokenByIssuer.md)
  - [IssueTokenByTransaction](/Workshop/explorer/IssueTokenByTransaction.md)
  - [PublishFungibleVoucherAll](/Workshop/explorer/PublishFungibleVoucherAll.md)
  - [PublishFungibleVoucherByPublisher](/Workshop/explorer/PublishFungibleVoucherByPublisher.md)
  - [PublishFungibleVoucherByTransaction](/Workshop/explorer/PublishFungibleVoucherByTransaction.md)
  - PublishNonfungibleVoucherAll
  - PublishNonfungibleVoucherByPublisher
  - PublishNonfungibleVoucherByTransaction

