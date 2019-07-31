# EngineAPIWorkshop201908
This folders contains five categorise of FST Engine APIs.

## Auth
- Mutation
  - [signIn](/EngineAPIWorkshop201908/Auth/signIn.md)

## Master
- Query `READ`
  - [getAllUser](/EngineAPIWorkshop201908/Master/getAllUser.md)
  - [getAllIssuer](/EngineAPIWorkshop201908/Master/getAllIssuer.md)
  - [ethereumKey](/EngineAPIWorkshop201908/Master/ethereumKey.md)
  - [tokenExpireTime](/EngineAPIWorkshop201908/Master/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/EngineAPIWorkshop201908/Master/createUser.md)
  - [createUserV2](/EngineAPIWorkshop201908/Master/createUserV2.md)

- Mutation `TRANSACT`
  - [fillGas](/EngineAPIWorkshop201908/Master/fillGas.md)
  - [mintIL](/EngineAPIWorkshop201908/Master/mintIL.md)
  - [sendEther](/EngineAPIWorkshop201908/Master/sendEther.md)
  - [erc20Transfer](/EngineAPIWorkshop201908/Master/erc20Transfer.md)
  - [erc721Transfer](/EngineAPIWorkshop201908/Master/erc721Transfer.md)
  - [transferMasterToken](/EngineAPIWorkshop201908/Master/transferMasterToken.md)
  - [submitTransaction](/EngineAPIWorkshop201908/Master/submitTransaction.md)


## Issuer
- Query `READ`
  - token [WIP]
    - voucherList
    - holders
    - Transfers
  - [getAllUser](/EngineAPIWorkshop201908/Master/getAllUser.md)
  - [ethereumKey](/EngineAPIWorkshop201908/Master/ethereumKey.md)
  - [tokenExpireTime](/EngineAPIWorkshop201908/Master/tokenExpireTime.md)

- Mutation `CREATE`
  - [createUser](/EngineAPIWorkshop201908/Master/createUser.md)
  - [createUserV2](/EngineAPIWorkshop201908/Master/createUserV2.md)
  - [issueToken](/EngineAPIWorkshop201908/Issuer/issueToken.md)
  - publishFungibleVoucher [WIP]
  - publishNonFungibleVoucher

- Mutation `TRANSACT`
  - [fillGas](/EngineAPIWorkshop201908/Master/fillGas.md)
  - [sendEther](/EngineAPIWorkshop201908/Master/sendEther.md)
  - [erc20Transfer](/EngineAPIWorkshop201908/Master/erc20Transfer.md)
  - [erc721Transfer](/EngineAPIWorkshop201908/Master/erc721Transfer.md)
  - mintErc721
  - [submitTransaction](/EngineAPIWorkshop201908/Master/submitTransaction.md)


## User
- Query `READ`
  - [ethereumKey](/EngineAPIWorkshop201908/Master/ethereumKey.md)
  - [tokenExpireTime](/EngineAPIWorkshop201908/Master/tokenExpireTime.md)

- Mutation `TRANSACT`
  - [fillGas](/EngineAPIWorkshop201908/Master/fillGas.md)
  - [sendEther](/EngineAPIWorkshop201908/Master/sendEther.md)
  - [erc20Transfer](/EngineAPIWorkshop201908/Master/erc20Transfer.md)
  - [erc721Transfer](/EngineAPIWorkshop201908/Master/erc721Transfer.md)
  - [submitTransaction](/EngineAPIWorkshop201908/Master/submitTransaction.md)


## Explorer
- Query `READ`
  - [block](/EngineAPIWorkshop201908/Explorer/block.md)
  - [blocks](/EngineAPIWorkshop201908/Explorer/blocks.md)
  - [transaction](/EngineAPIWorkshop201908/Explorer/transaction.md)
  - [transactions](/EngineAPIWorkshop201908/Explorer/transactions.md)
  - [account](/EngineAPIWorkshop201908/Explorer/account.md)
  - [erc20TokenList](/EngineAPIWorkshop201908/Explorer/erc20TokenList.md)
  - [erc20TokenBasic](/EngineAPIWorkshop201908/Explorer/erc20TokenBasic.md)
  - [erc20Transfers](/EngineAPIWorkshop201908/Explorer/erc20Transfers.md)
  - [erc721TokenList](/EngineAPIWorkshop201908/Explorer/erc721TokenList.md)
  - [erc721TokenBasic](/EngineAPIWorkshop201908/Explorer/erc721TokenBasic.md)
  - [erc721Transfers](/EngineAPIWorkshop201908/Explorer/erc721Transfers.md)
  - erc721TokenURI
  - [smartTokenList](/EngineAPIWorkshop201908/Explorer/smartTokenList.md)
  - smartTokenBasic [WIP]
  - smartTokenTransfer [WIP]
  - issueTokenList
  - smartVoucherList [WIP]
  - [smartVoucherErc20Basic](/EngineAPIWorkshop201908/Explorer/smartVoucherErc20Basic.md)
  - [smartVoucherErc721Basic](/EngineAPIWorkshop201908/Explorer/smartVoucherErc721Basic.md)
  - smartVoucherErc20Transfer [WIP]
  - smartVoucherErc721Transfer
  - [consumeGas](/EngineAPIWorkshop201908/Explorer/consumeGas.md)
  - [consumeGasByAddress](/EngineAPIWorkshop201908/Explorer/consumeGasByAddress.md)
  - [purchaseGas](/EngineAPIWorkshop201908/Explorer/purchaseGas.md)
  - [purchaseGasByAddress](/EngineAPIWorkshop201908/Explorer/purchaseGasByAddress.md)
  - [mintILList](/EngineAPIWorkshop201908/Explorer/mintILList.md)
  - [IssueTokenAll](/EngineAPIWorkshop201908/Explorer/IssueTokenAll.md)
  - [IssueTokenByIssuer](/EngineAPIWorkshop201908/Explorer/IssueTokenByIssuer.md)
  - [IssueTokenByTransaction](/EngineAPIWorkshop201908/Explorer/IssueTokenByTransaction.md)
  - [PublishFungibleVoucherAll](/EngineAPIWorkshop201908/Explorer/PublishFungibleVoucherAll.md)
  - [PublishFungibleVoucherByPublisher](/EngineAPIWorkshop201908/Explorer/PublishFungibleVoucherByPublisher.md)
  - [PublishFungibleVoucherByTransaction](/EngineAPIWorkshop201908/Explorer/PublishFungibleVoucherByTransaction.md)
  - PublishNonfungibleVoucherAll
  - PublishNonfungibleVoucherByPublisher
  - PublishNonfungibleVoucherByTransaction

