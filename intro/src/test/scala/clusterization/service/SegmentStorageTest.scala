package clusterization.service

import clusterization.model.{IpAddress, IpSegment}
import org.scalatest._

/**
  *
  * @author Alexey Poptsov
  */
class SegmentStorageTest extends FlatSpec with Matchers {

  "A Storage" should "work with empty data" in {
    val storage = SegmentStorage(Array())
    storage.search(IpAddress("0.0.0.0")).toList.length should be(0)
    storage.fullScan(IpAddress("0.0.0.0")).toList.length should be(0)
  }

  "A Storage" should "be able to find correct segments" in {
    val address1 = IpAddress("0.0.0.0")
    val address2 = IpAddress("255.255.255.255")
    val segment1 = new IpSegment("seg1", address1, address2)
    val address3 = IpAddress("100.100.100.100")
    val address4 = IpAddress("150.150.150.150")
    val segment2 = new IpSegment("seg2", address3, address4)

    val storage = SegmentStorage(Array(segment1, segment2))

    val result = storage.search(IpAddress("0.0.0.0"))
    result should be(Array(segment1))
  }

  "A Storage" should "provide the same result using index and without it" in {
    val address1 = IpAddress("0.0.0.0")
    val address2 = IpAddress("255.255.255.255")
    val segment1 = new IpSegment("seg1", address1, address2)
    val address3 = IpAddress("100.100.100.100")
    val address4 = IpAddress("150.150.150.150")
    val segment2 = new IpSegment("seg2", address3, address4)

    val storage = SegmentStorage(Array(segment1, segment2))

    val resultWithIndex = storage.search(IpAddress("125.0.0.0"))
    val resultWithoutIndex = storage.fullScan(IpAddress("125.0.0.0"))

    resultWithIndex should be(resultWithoutIndex)
  }

  "A Storage" should "work with segments that defined in the same range" in {
    val address1 = IpAddress("100.100.100.100")
    val address2 = IpAddress("150.150.150.150")
    val segment1 = new IpSegment("seg1", address1, address2)
    val address3 = IpAddress("100.100.100.100")
    val address4 = IpAddress("150.150.150.150")
    val segment2 = new IpSegment("seg2", address3, address4)

    val storage = SegmentStorage(Array(segment1, segment2))

    val result = storage.search(IpAddress("125.0.0.0"))
    result.toSet should be(Set(segment1, segment2))
  }


}
