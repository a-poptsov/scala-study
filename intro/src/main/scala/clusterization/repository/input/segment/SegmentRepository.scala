package clusterization.repository.input.segment

import clusterization.model.{IpAddress, IpSegment}

import scala.collection.Iterator

/**
  * Repository to fetch information about segments
  *
  * @author Alexey Poptsov
  */
trait SegmentRepository {

  /**
    * Open specific path and prepare to read data
    *
    * @param path - destination to open
    * @return ready to read data
    */
  def open(path: String): Iterator[String]

  /**
    * Parse strings to segemnts
    *
    * @param data - data to parse
    * @return collection of segments that where parsed
    */
  def parse(data: Iterator[String]): Array[IpSegment] = data.map(line => {
    val segmentName: String = line.replaceFirst("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s+", "").trim
    val ipRange: Array[String] = line.replaceFirst("\\s+\\S+", "").trim.split("-")
    new IpSegment(segmentName, IpAddress(ipRange(0)), IpAddress(ipRange(1)))
  }).toArray

  /**
    * Read information about segments from the destination
    *
    * @param path - destination path
    * @return collection of segments that where processed
    */
  def read(path: String): Array[IpSegment] = parse(open(path))
}
