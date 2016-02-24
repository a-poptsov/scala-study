package clusterization.service.formatter

/**
  *
  * @author Alexey Poptsov
  */
trait ClusterizationFormatter {

  def format(transactionId: String, segmentName: String): String

}
