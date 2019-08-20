package network.fst.example.api.http

import java.net.URI

import com.fasterxml.jackson.databind.JsonNode
import network.fst.example.utils.{DefaultObjectMapper, Logging}
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.{ContentType, StringEntity}
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicHeader

class ApacheHttpClientImpl extends HttpClient with Logging {
  private val client = HttpClients.createDefault()

  override def postJson(endpoint: URI, body: JsonNode, headers: Array[(String, String)]): JsonNode = {
    try {
      val request = new HttpPost(endpoint)
      request.setHeaders(
        headers.map { case (name, value) => new BasicHeader(name, value) }
      )
      request.setEntity(new StringEntity(
        body.toString,
        ContentType.APPLICATION_JSON
      ))

      client.execute(request, (response: HttpResponse) => {
        val entity = response.getEntity
        val is = entity.getContent
        DefaultObjectMapper.readTree(is)
      })
    } catch {
      case error: Throwable =>
        log.warn("request error", error)
        throw error
    }
  }

  override def close(): Unit = {
    client.close()
  }
}
