## Signing Transaction Example code

```javascript
// This example demostrates how to sign a Transaction Object responded from FST Engine API
// Please see more details at https://github.com/fstnetwork/eth-key-lib-js

// npm i "https://github.com/fstnetwork/eth-key-lib-js"
import { DecryptEthereumKeyJson, SignTransaction } from "eth-key-lib-js";

// The transaction object in the response from FST Engine API
const transaction = {
  chainId: 42,
  data: "0x",
  gas: "0x5208",
  gasPrice: "0x3b9aca00",
  nonce: "0xed",
  to: "0x1211ef4E91607766a19e544a2F8d0CA68837cAd0",
  value: "0xc12dc63fa970000"
};

// The submitToken is also in the response from FST Engine API
// submitToken is used when submitting the signed transaction
const submitToken = "A JWT String....";

// The encrypted key-store file / wallet file / key file of the signer
const ethereumKey = {
  id: "64031d31-53a4-11e8-b00a-2b7a29c9f6b9",
  address: "0x3a7af8b8c19c404670c1470273bca449148df4ed",
  crypto: {
    kdf: "scrypt",
    mac: "0913dca5ed0b0a792a004acc0740fb739676334734220266e155400d61ae3dab",
    cipher: "aes-128-ctr",
    kdfparams: {
      n: 262144,
      p: 1,
      r: 8,
      salt: "16b3885904ba626fa5bfaab6d6b368eff1ff0ba2ab739adbec3a8fb063c43aba",
      dklen: 32
    },
    ciphertext:
      "dc1bfefb51e55f4d063f835a44f5c172008fea7eeb4b0da7fb7e50f3a18cf9ef",
    cipherparams: {
      iv: "b343d847b8a72ad68c6bf10866757421"
    }
  },
  version: 3
};

// Decrypt the ethereumKey and get wallet object containing the private key
// walletObj.privateKeyBuffer is the private key for signing
const walletObj = DecryptEthereumKeyJson("THE PASSPHRASE", ethereumKey);

// Sign the transaction object with the private key to get Signed Transaction
const signedTx = SignTransaction(walletObj.privateKeyBuffer, transaction);

// Assemble the signedTx and submitToken, then use it in submitTransaction API
// Please refer to the section of submitTransaction in FST Engine API Doc
SUBMIT_TRASACTION({ signedTx, submitToken }).then(resp =>
  // The hash of the transaction
  console.log(resp.data.submitTransaction.transactionHash)
);
```
