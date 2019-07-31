# EngineAPIWorkshop201908
This folders contains five categorise of FST Engine APIs.

## Auth
- Mutation
  - [signIn](/Auth/signIn.md)

## Master
- Query `READ`
  - [getAllUser](/Master/getAllUser.md)
  - [getAllIssuer](/Master/getAllIssuer.md)
  - [ethereumKey](/Master/ethereumKey.md)
  - [tokenExpireTime](/Master/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Master/createUser.md)
  - [createUserV2](/Master/createUserV2.md)

- Mutation `TRANSACT`
  - [fillGas](/Master/fillGas.md)
  - [mintIL](/Master/mintIL.md)
  - [sendEther](/Master/sendEther.md)
  - [erc20Transfer](/Master/erc20Transfer.md)
  - [erc721Transfer](/Master/erc721Transfer.md)
  - [transferMasterToken](/Master/transferMasterToken.md)
  - [submitTransaction](/Master/submitTransaction.md)


## Issuer
- Query `READ`
  - token
    - voucherList
    - holders
    - Transfers
  - [getAllUser](/Master/getAllUser.md)
  - [ethereumKey](/Master/ethereumKey.md)
  - [tokenExpireTime](/Master/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/Master/createUser.md)
  - [createUserV2](/Master/createUserV2.md)
  - [issueToken](/Issuer/issueToken.md)
  - publishFungibleVoucher
  - publishNonFungibleVoucher

- Mutation `TRANSACT`
  - [fillGas](/Master/fillGas.md)
  - [sendEther](/Master/sendEther.md)
  - [erc20Transfer](/Master/erc20Transfer.md)
  - [erc721Transfer](/Master/erc721Transfer.md)
  - mintErc721
  - [submitTransaction](/Master/submitTransaction.md)


## User
- Query `READ`
  - [ethereumKey](/Master/ethereumKey.md)
  - [tokenExpireTime](/Master/tokenExpireTime.md)

- Mutation `TRANSACT`
  - [fillGas](/Master/fillGas.md)
  - [sendEther](/Master/sendEther.md)
  - [erc20Transfer](/Master/erc20Transfer.md)
  - [erc721Transfer](/Master/erc721Transfer.md)
  - [submitTransaction](/Master/submitTransaction.md)


## Explorer
- Query `READ`
  - [block](/Explorer/block.md)
  - [blocks](/Explorer/blocks.md)
  - [transaction](/Explorer/transaction.md)
  - [transactions](/Explorer/transactions.md)
  - [account](/Explorer/account.md)
  - [erc20TokenList](/Explorer/erc20TokenList.md)
  - [erc20TokenBasic](/Explorer/erc20TokenBasic.md)
  - [erc20Transfers](/Explorer/erc20Transfers.md)
  - [erc721TokenList](/Explorer/erc721TokenList.md)
  - [erc721TokenBasic](/Explorer/erc721TokenBasic.md)
  - [erc721Transfers](/Explorer/erc721Transfers.md)
  - erc721TokenURI [WIP]
  - [smartTokenList](/Explorer/smartTokenList.md)
  - smartTokenBasic [WIP]
  - smartTokenTransfer [WIP]
  - issueTokenList
  - smartVoucherList [WIP]
  - smartVoucherBasic [WIP]
  - smartVoucherTransfer [WIP]
  - [consumeGas](/Explorer/consumeGas.md)
  - [consumeGasByAddress](/Explorer/consumeGasByAddress.md)
  - [purchaseGas](/Explorer/purchaseGas.md)
  - [purchaseGasByAddress](/Explorer/purchaseGasByAddress.md)
  - [mintILList](/Explorer/mintILList.md)
  - [IssueTokenAll](/Explorer/IssueTokenAll.md)
  - [IssueTokenByIssuer](/Explorer/IssueTokenByIssuer.md)
  - [IssueTokenByTransaction](/Explorer/IssueTokenByTransaction.md)
  - [PublishFungibleVoucherAll](/Explorer/PublishFungibleVoucherAll.md)
  - [PublishFungibleVoucherByPublisher](/Explorer/PublishFungibleVoucherByPublisher.md)
  - [PublishFungibleVoucherByTransaction](/Explorer/PublishFungibleVoucherByTransaction.md)
  - PublishNonfungibleVoucherAll
  - PublishNonfungibleVoucherByPublisher
  - PublishNonfungibleVoucherByTransaction

