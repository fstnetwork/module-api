import { Transaction as EthereumTx } from "ethereumjs-tx";

const transaction = {
  nonce: "...",
  gasPrice: "...",
  gas: "...",
  to: "...",
  value: "...",
  data: "...",
  chainId: "..."
}
const privateKey = '...'

function signTransaction(transaction, privateKey) {
  const ethereumTx = new EthereumTx(transaction, {
    chain: Number(transaction.chainId)
  });
  ethereumTx.sign(privateKey);
  return ethereumTx.serialize().toString('hex');
}