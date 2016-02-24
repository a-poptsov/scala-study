package clusterization.repository.input.transaction

import scala.io.Source

/**
  *
  * @author Alexey Poptsov
  */
object TransactionFileRepository extends TransactionRepository{

  private val encoding = "UTF-8"
  private val groupSize = 100

  def read[R](path: String, processor: Seq[String] => R): Iterator[R] = {
    Source.fromFile(path, encoding).getLines.grouped(groupSize)
      .map(transactions => processor(transactions))
  }

}
