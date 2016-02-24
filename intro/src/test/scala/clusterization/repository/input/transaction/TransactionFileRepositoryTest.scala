package clusterization.repository.input.transaction

import org.scalatest._

/**
  *
  * @author Alexey Poptsov
  */
class TransactionFileRepositoryTest extends FlatSpec with Matchers {

  "A TransactionRepository" should "parse strings correctly" in {
    val id = "-987654320000"
    val ip = "0.0.0.0"
    val transaction = TransactionFileRepository.parse(id + "\t" + ip)
    transaction.transactionId should be(id)
    transaction.transactionIp.toString should be(ip)
  }

}
