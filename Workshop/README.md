# Workshop
This directory contains 5 aspects of FST Engine APIs.

![permission](/Workshop/images/permission.png)

## Glossaries

- Accountable Data Network
  - An unique network mechanism that insures the consistency, insertion order and existence of any data
- Address
  - An entry on Accountable Data Network, each address can actually belong to one private key, but it is cryptographically difficult to be reverse-attacked, since the address is the hash of the public key, and there are 16^40 addresses available
- Contract / Smart Contract
  - A set of program modules deployed on accountable data network with their business-logics and storages are computed/executed exactly under their pre-configurations and constraints. A Smart Contract has its own address
- Ledger
  - A kind of Smart Contract that stores the Accountable Data and verifies the authentication/authorisation of any operation on it
- ERC-20
  - An interface for basic ledger operations and storage
- ERC-721
  - An interface for basic non-fungible ledger operations and storage
- ERC-1376
  - An interface for advanced ledger operations and storage with optimisations and high-extensibility, compatible to ERC-20
- Token / Smart Token / Fungible Token
  - An ERC-1376 implementation with fungible, 18-decimals ledger.
- Voucher / Smart Voucher / Fungible Voucher
  - An ERC-1376 implementation with fungible, 0-decimal ledger. Each Voucher
- EthereumKey / Wallet File / Key File / Key Storage
  - A JSON that contains encrypted ECDSA private key, the passphrase hashing is used with Scrypt/PBKDF2
- Gas / Fuel
  - A protocol-level and service-level gas that protects the entire Data Network from the DDOS attack, since any instruction on a Data Network must consume gas. In FST PPB/Engine, the protocol-gas is called Ether, and the service-gas is Master Service Gas
- Transaction
  - A data set contains digital-signature, timestamping, target address, gas amount and the bytecode to the Smart Contract
- Transfer
  - A balance change or data transferring in a ledger, it contains from (originator), to (receiver) and the value (data amount)

## Auth
- Mutation
  - [signIn](/Workshop/signIn.md)

## Master
- Query `READ`
  - [getAllUser](/Workshop/getAllUser.md)
  - [getAllIssuer](/Workshop/getAllIssuer.md)
  - [ethereumKey](/Workshop/ethereumKey.md)
  - [tokenExpireTime](/Workshop/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/createUser.md)
  - [createUserV2](/Workshop/createUserV2.md)

- Mutation `TRANSACT`
  - [fillGas](/Workshop/fillGas.md)
  - [mintIL](/Workshop/mintIL.md)
  - [sendEther](/Workshop/sendEther.md)
  - [erc20Transfer](/Workshop/erc20Transfer.md)
  - [erc721Transfer](/Workshop/erc721Transfer.md)
  - [transferMasterToken](/Workshop/transferMasterToken.md)
  - [submitTransaction](/Workshop/submitTransaction.md)


## Issuer
- Query `READ`
  - [erc20TokenBasic](/Workshop/Explorer/erc20TokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [erc721TokenBasic](/Workshop/Explorer/erc721TokenBasic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [getAllUser](/Workshop/getAllUser.md)
  - [ethereumKey](/Workshop/ethereumKey.md)
  - [tokenExpireTime](/Workshop/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Workshop/createUser.md)
  - [createUserV2](/Workshop/createUserV2.md)
  - [issueToken](/Workshop/issueToken.md)
  - publishFungibleVoucher [WIP](/Workshop/publishFungibleVoucher.md)
  - publishNonFungibleVoucher

- Mutation `TRANSACT`
  - [fillGas](/Workshop/fillGas.md)
  - [sendEther](/Workshop/sendEther.md)
  - [erc20Transfer](/Workshop/erc20Transfer.md)
  - [erc721Transfer](/Workshop/erc721Transfer.md)
  - mintErc721
  - [submitTransaction](/Workshop/submitTransaction.md)


## User
- Query `READ`
  - [ethereumKey](/Workshop/ethereumKey.md)
  - [tokenExpireTime](/Workshop/tokenExpireTime.md)

- Mutation `TRANSACT`
  - [fillGas](/Workshop/fillGas.md)
  - [sendEther](/Workshop/sendEther.md)
  - [erc20Transfer](/Workshop/erc20Transfer.md)
  - [erc721Transfer](/Workshop/erc721Transfer.md)
  - [submitTransaction](/Workshop/submitTransaction.md)


## Explorer
- Query `READ`
  - [block](/Workshop/Explorer/block.md)
  - [blocks](/Workshop/Explorer/blocks.md)
  - [transaction](/Workshop/Explorer/transaction.md)
  - [transactions](/Workshop/Explorer/transactions.md)
  - [account](/Workshop/Explorer/account.md)
  - [erc20TokenList](/Workshop/Explorer/erc20TokenList.md)
  - [erc20TokenBasic](/Workshop/Explorer/erc20TokenBasic.md)
  - [erc20Transfers](/Workshop/Explorer/erc20Transfers.md)
  - [erc721TokenList](/Workshop/Explorer/erc721TokenList.md)
  - [erc721TokenBasic](/Workshop/Explorer/erc721TokenBasic.md)
  - [erc721Transfers](/Workshop/Explorer/erc721Transfers.md)
  - erc721TokenURI
  - [smartTokenList](/Workshop/Explorer/smartTokenList.md)
  - smartToken [WIP] vvvvvvvv
    - info
      - voucherList
    - holders
    - Transfers
    - Transactions
  - issueTokenList
  - smartVoucherList [WIP]
  - [smartVoucherErc20Basic](/Workshop/Explorer/smartVoucherErc20Basic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [smartVoucherErc721Basic](/Workshop/Explorer/smartVoucherErc721Basic.md)
    - info
    - holders
    - Transfers
    - Transactions
  - [consumeGas](/Workshop/Explorer/consumeGas.md)
  - [consumeGasByAddress](/Workshop/Explorer/consumeGasByAddress.md)
  - [purchaseGas](/Workshop/Explorer/purchaseGas.md)
  - [purchaseGasByAddress](/Workshop/Explorer/purchaseGasByAddress.md)
  - [mintILList](/Workshop/Explorer/mintILList.md)
  - [IssueTokenAll](/Workshop/Explorer/IssueTokenAll.md)
  - [IssueTokenByIssuer](/Workshop/Explorer/IssueTokenByIssuer.md)
  - [IssueTokenByTransaction](/Workshop/Explorer/IssueTokenByTransaction.md)
  - [PublishFungibleVoucherAll](/Workshop/Explorer/PublishFungibleVoucherAll.md)
  - [PublishFungibleVoucherByPublisher](/Workshop/Explorer/PublishFungibleVoucherByPublisher.md)
  - [PublishFungibleVoucherByTransaction](/Workshop/Explorer/PublishFungibleVoucherByTransaction.md)
  - PublishNonfungibleVoucherAll
  - PublishNonfungibleVoucherByPublisher
  - PublishNonfungibleVoucherByTransaction

