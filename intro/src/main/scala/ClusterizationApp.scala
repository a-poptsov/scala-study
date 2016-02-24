import java.io.PrintWriter

import clusterization.IPAddress
import clusterization.segment._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Future, Await}
import scala.io.Source

/**
  * @author Alexey Poptsov
  */
object ClusterizationApp extends App {

  var startTime = System.currentTimeMillis()

  var argsDefined: Boolean = args.length == 3
  val segmentsPath: String = if (argsDefined) args(0) else "ranges.tsv"
  val transactionsPath: String = if (argsDefined) args(1) else "transactions.tsv"
  val outputPath: String = if (argsDefined) args(2) else "output.tsv"
  val segments: SegmentStorage = SegmentStorage(SegmentsFileReader(segmentsPath))

  println("Initialization takes " + (System.currentTimeMillis() - startTime) + " milliseconds")
  startTime = System.currentTimeMillis()

  val out = new PrintWriter(outputPath)
  Await.ready(Future.sequence(Source.fromFile(transactionsPath, "UTF-8").getLines().map(transaction => Future({
    val transactionId: String = transaction.replaceFirst("\\s+\\S+", "").trim
    val transactionIp: IPAddress = new IPAddress(transaction.replaceFirst(".+\\s+", "").trim)
    for (segment <- segments.findMatched(transactionIp)) {
      out.println(transactionId + "\t" + segment.segmentName)
    }
  }))), Duration.Inf)
  out.close()

  println("Clusterization takes " + (System.currentTimeMillis() - startTime) + " milliseconds")
}