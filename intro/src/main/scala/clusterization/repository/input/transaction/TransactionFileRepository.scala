package clusterization.repository.input.transaction

import scala.io.Source

/**
  * Transaction repository implementation that works with files
  *
  * @author Alexey Poptsov
  */
object TransactionFileRepository extends TransactionRepository {

  private val encoding = "UTF-8"


  override def open(path: String): Iterator[String] = Source.fromFile(path, encoding).getLines


}
