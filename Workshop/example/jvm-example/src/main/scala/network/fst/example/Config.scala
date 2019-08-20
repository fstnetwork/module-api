package network.fst.example

import java.net.URI

case class Config(
  id: String,
  password: String,
  passphrase: String,
  singInEndpoint: URI,
  apiEndpoint: URI,
)