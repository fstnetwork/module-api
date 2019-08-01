# Workshop
This folders contains five categorise of FST Engine APIs.

![permission](/Workshop/images/permission.png)

## Auth
- Mutation
  - [signIn](/Workshop/Auth/signIn.md)

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
  - erc20/erc721 token [WIP] vvvvvv
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
  - publishFungibleVoucher [WIP] vvvvvvvvvv
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

