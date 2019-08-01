const { Transaction } = require("ethereumjs-tx");

const transaction = {
  nonce: "...",
  gasPrice: "...",
  gas: "...",
  to: "...",
  value: "...",
  data: "...",
  chainId: "..."
}

const privateKeyString = '...';
const privateKey = (Buffer.from(privateKeyString, "hex"));

function signTransaction(transaction, privateKey) {
  const ethereumTx = new Transaction(transaction, {
    chain: Number(transaction.chainId)
  });
  ethereumTx.sign(privateKey);
  return ethereumTx.serialize().toString('hex');
}

signTransaction(transaction, privateKey);

