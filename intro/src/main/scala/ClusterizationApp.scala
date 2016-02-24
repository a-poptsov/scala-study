import clusterization.repository.input.segment.SegmentFileRepository
import clusterization.repository.input.transaction.TransactionFileRepository
import clusterization.repository.output.FileRepository
import clusterization.service.formatter.DefaultClusterisationFormatter
import clusterization.service.{SegmentStorage, TimeTracker}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Application to put transactions to specific segments using ip address
  * first arg will be a path to the file that describes segments
  * second arg will be a path to the file that describes transactions
  * third arg will be a path to the file that becomes output
  *
  * @author Alexey Poptsov
  */
object ClusterizationApp extends App {

  var argsDefined = args.length == 3
  if (!argsDefined) {
    println("Default params(ranges.tsv, transactions.tsv, output.tsv) will be used!")
  }
  val segmentsPath = if (argsDefined) args(0) else "ranges.tsv"
  val transactionsPath = if (argsDefined) args(1) else "transactions.tsv"
  val outputPath = if (argsDefined) args(2) else "output.tsv"

  val segments: SegmentStorage = TimeTracker(SegmentStorage(SegmentFileRepository(segmentsPath)), "Initialization takes")

  val repository = new FileRepository(outputPath)
  TimeTracker(Await.ready(Future.sequence(TransactionFileRepository.read(transactionsPath, process)), Duration.Inf), "Clusterization takes")

  def process(transactions: Seq[String]): Future[Unit] = Future({
    transactions.foreach(transactionStr => {
      val transaction = TransactionFileRepository.parse(transactionStr)
      for (segment <- segments.findMatched(transaction.transactionIp)) {
        repository.write(DefaultClusterisationFormatter.format(transaction.transactionId, segment.segmentName))
      }
    })
  })

  repository.close()
}