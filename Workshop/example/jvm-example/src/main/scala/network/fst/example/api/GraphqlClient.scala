package network.fst.example.api

import java.net.URI

import com.fasterxml.jackson.databind.JsonNode
import network.fst.example.api.http.HttpClient
import network.fst.example.utils.{DefaultObjectMapper, Logging}

class GraphqlClient(endpoint: URI, httpClient: HttpClient) extends Logging {
  def sendGraphqlRequest(
    graphqlRequest: GraphQLRequest[_],
    maybeToken: Option[String] = None): JsonNode = {
    val json = DefaultObjectMapper.valueToTree[JsonNode](graphqlRequest)
    log.trace(
      s"sending graphQL request to {}:\n{}",
      Array(endpoint, DefaultObjectMapper.prettyWriteValueAsString(json)): _*
    )
    httpClient.postJson(
      endpoint,
      json,
      maybeToken.map(token => "Authorization" -> s"Bearer $token").toArray,
    )
  }
}
