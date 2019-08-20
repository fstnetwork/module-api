package network.fst.example.api.http

import java.io.Closeable
import java.net.URI

import com.fasterxml.jackson.databind.JsonNode

trait HttpClient extends Closeable {
  def postJson(endpoint: URI, body: JsonNode, headers: Array[(String, String)] = Array.empty): JsonNode
}

object HttpClient {
  def default = apply()

  def apply(): HttpClient = {
    new ApacheHttpClientImpl()
  }
}
