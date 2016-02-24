package clusterization.service.formatter

/**
  * Helper  to define format of clusterization output
  *
  * @author Alexey Poptsov
  */
trait ClusterizationFormatter {

  /**
    * Format transaction id and segment name to correct
    *
    * @param transactionId - id of transaction
    * @param segmentName   - name of segment
    * @return formatted string
    */
  def format(transactionId: String, segmentName: String): String

}
