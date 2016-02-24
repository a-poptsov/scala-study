package clusterization.service.formatter

/**
  *
  * @author Alexey Poptsov
  */
object DefaultClusterisationFormatter extends ClusterizationFormatter {

  override def format(transactionId: String, segmentName: String): String = transactionId + "\t" + segmentName
}
