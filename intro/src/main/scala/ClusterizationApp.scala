import java.io.PrintWriter

import clusterization.segment._

import scala.io.Source

/**
  * @author Alexey Poptsov
  */
object ClusterizationApp extends App {

  var argsDefined: Boolean = args.length == 3
  val segmentsPath: String = if (argsDefined) args(0) else "ranges.tsv"
  val transactionsPath: String = if (argsDefined) args(1) else "transactions.tsv"
  val outputPath: String = if (argsDefined) args(2) else "output.tsv"

  val segments: SegmentStorage = SegmentStorage(SegmentsFileReader(segmentsPath))

  val out = new PrintWriter(outputPath)
  for (transaction <- Source.fromFile(transactionsPath, "UTF-8").getLines()) {
    val transactionId: String = transaction.replaceFirst("\\s+\\S+", "").trim
    val transactionIp: IPAddress = new IPAddress(transaction.replaceFirst(".+\\s+", "").trim)
    for (segment <- segments.findMatched(transactionIp)) {
      out.println(transactionId + "\t" + segment.segmentName)
    }
  }
  out.close()


}