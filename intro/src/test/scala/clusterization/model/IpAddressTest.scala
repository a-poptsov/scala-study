package clusterization.model

import org.scalatest._

/**
  *
  * @author Alexey Poptsov
  */
class IpAddressTest extends FlatSpec with Matchers {

  "An Address" should "be defined by number" in {
    val value = 100101102103L
    val address = IpAddress(value)

    address.ip should be(value)
    address.toString should be("100.101.102.103")
    address.part(1) should be(100L)
    address.part(2) should be(101L)
    address.part(3) should be(102L)
    address.part(4) should be(103L)
  }

  "An Address" should "be defined by string" in {
    val address = IpAddress("200.201.202.203")

    address.ip should be(200201202203L)
    address.toString should be("200.201.202.203")
    address.part(1) should be(200L)
    address.part(2) should be(201L)
    address.part(3) should be(202L)
    address.part(4) should be(203L)
  }

  "An Address" should "be defined by array" in {
    val address = IpAddress(Array(250L, 251L, 252L, 253L))

    address.ip should be(250251252253L)
    address.toString should be("250.251.252.253")
    address.part(1) should be(250L)
    address.part(2) should be(251L)
    address.part(3) should be(252L)
    address.part(4) should be(253L)
  }

  "An Address" should "be validate input" in {
    an [IllegalArgumentException] should be thrownBy  IpAddress(Array(255L, 255L, 255L, 256L))
    an [IllegalArgumentException] should be thrownBy  IpAddress(Array(255L, 255L, -1L, 255L))
  }

}
