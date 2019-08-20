package network.fst.example.api

case class GraphQLRequest[V](query: String, variables: Option[V] = None)

object GraphQLRequest {
  def apply[V](query: String, variables: V): GraphQLRequest[V] = {
    GraphQLRequest(query, Some(variables))
  }
}
