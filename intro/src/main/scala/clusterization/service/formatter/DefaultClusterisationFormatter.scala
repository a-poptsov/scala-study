package clusterization.service.formatter

/**
  * Default implementation of clusterization formatter
  *
  * @author Alexey Poptsov
  */
object DefaultClusterisationFormatter extends ClusterizationFormatter {

  override def format(transactionId: String, segmentName: String): String = transactionId + "\t" + segmentName
}
