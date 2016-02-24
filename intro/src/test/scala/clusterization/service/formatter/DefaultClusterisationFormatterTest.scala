package clusterization.service.formatter

import org.scalatest._

/**
  * @author Alexey Poptsov
  */
class DefaultClusterisationFormatterTest extends FlatSpec with Matchers {

  "A Formatter" should "separate transaction and segment using \\t" in {
    val formatter = DefaultClusterisationFormatter
    val result = formatter.format("id", "name")
    result should be("id\tname")
  }

}
