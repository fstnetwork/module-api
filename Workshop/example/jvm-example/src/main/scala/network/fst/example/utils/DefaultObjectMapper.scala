package network.fst.example.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.{ObjectMapper, ObjectWriter}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object DefaultObjectMapper extends ObjectMapper {
  this.registerModule(DefaultScalaModule)

  private val noQuotedFieldNameJsonWriter: ObjectWriter = this.writer().without(JsonGenerator.Feature.QUOTE_FIELD_NAMES)

  def writeValueAsStringWithoutFieldNameQuote(value: AnyRef): String = {
    noQuotedFieldNameJsonWriter.writeValueAsString(value)
  }

  def prettyWriteValueAsString(value: AnyRef): String = {
    writerWithDefaultPrettyPrinter().writeValueAsString(value)
  }
}
