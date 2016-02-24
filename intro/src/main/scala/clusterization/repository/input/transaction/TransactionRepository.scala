package clusterization.repository.input.transaction

import clusterization.model.{IpAddress, Transaction}

/**
  *
  * @author Alexey Poptsov
  */
trait TransactionRepository {

  def parse(data: String): Transaction = new Transaction(data.replaceFirst("\\s+\\S+", "").trim, IpAddress(data.replaceFirst(".+\\s+", "")))

}
