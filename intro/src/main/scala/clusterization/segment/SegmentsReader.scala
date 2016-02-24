package clusterization.segment

import clusterization.IPAddress

import scala.collection.Iterator

/**
  *
  * @author Alexey Poptsov
  */
trait SegmentsReader {

  def open(path: String) : Iterator[String]

  def parse(iterator : Iterator[String]): Array[Segment] = iterator.map(line => {
    val segmentName: String = line.replaceFirst("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s+", "").trim
    val ipRange: Array[String] = line.replaceFirst("\\s+\\S+", "").trim.split("-")
     new Segment(segmentName, new IPAddress(ipRange(0)), new IPAddress(ipRange(1)))
  }).toArray

  def read(path: String) : Array[Segment] = parse(open(path))
}
