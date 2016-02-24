package clusterization.repository.input.segment

import clusterization.model.{IpAddress, IpSegment}

import scala.collection.Iterator

/**
  * @author Alexey Poptsov
  */
trait SegmentsRepository {

  def open(path: String) : Iterator[String]

  def parse(iterator : Iterator[String]): Array[IpSegment] = iterator.map(line => {
    val segmentName: String = line.replaceFirst("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s+", "").trim
    val ipRange: Array[String] = line.replaceFirst("\\s+\\S+", "").trim.split("-")
     new IpSegment(segmentName, IpAddress(ipRange(0)), IpAddress(ipRange(1)))
  }).toArray

  def read(path: String) : Array[IpSegment] = parse(open(path))
}
