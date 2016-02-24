package clusterization.repository.input.transaction

import clusterization.model.{IpAddress, Transaction}

import scala.collection.Iterator

/**
  * Repository to fetch information about transactions
  *
  * @author Alexey Poptsov
  */
trait TransactionRepository {

  private val groupSize = 100

  /**
    * Open specific path and prepare to read data
    *
    * @param path - destination to open
    * @return ready to read data
    */
  def open(path: String): Iterator[String]

  /**
    * parse data to transaction
    *
    * @param data - input string
    * @return transaction
    */
  def parse(data: String): Transaction = new Transaction(data.replaceFirst("\\s+\\S+", "").trim, IpAddress(data.replaceFirst(".+\\s+", "")))

  /**
    * Read information about transactions from the destination
    *
    * @param path      - destination path
    * @param processor - method that will process transaction
    * @tparam R - return type of processing
    * @return collection of transactions that where processed
    */
  def read[R](path: String, processor: Seq[String] => R): Iterator[R] = {
    open(path).grouped(groupSize)
      .map(transactions => processor(transactions))
  }

}
