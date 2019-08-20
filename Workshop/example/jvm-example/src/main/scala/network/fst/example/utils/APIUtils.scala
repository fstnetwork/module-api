package network.fst.example.utils

import network.fst.example.api.{GraphQLRequest, GraphqlClient}
import network.fst.example.model.{Input, SignIn}

object APIUtils {

  def signIn(client: GraphqlClient, id: String, password: String): String = {
    val request = GraphQLRequest(
      query =
        """mutation SignIn($input: SignInInput!) {
          |  signIn(input: $input) {
          |    access_token
          |  }
          |}""".stripMargin,
      variables = Input(SignIn(id, password))
    )
    val res = client.sendGraphqlRequest(request)
    res.path("data").path("signIn").path("access_token").textValue()
  }

  def getAddress(client: GraphqlClient, token: String): String = {
    val request = GraphQLRequest(
      query =
        """query MyAddress {
          |  ethereumKey {
          |    address
          |  }
          |}""".stripMargin)
    val res = client.sendGraphqlRequest(request, Some(token))
    res.path("data").path("ethereumKey").path("address").textValue()
  }
}
