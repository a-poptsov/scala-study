package clusterization.repository.input.segment

import clusterization.model.IpSegment

import scala.io.Source

/**
  * Segment repository implementation that works with files
  *
  * @author Alexey Poptsov
  */
object SegmentFileRepository extends SegmentRepository {

  override def open(path: String): Iterator[String] = Source.fromFile(path, "UTF-8").getLines()

  /**
    * Helper to do read operation easier
    *
    * @param path - path to the file
    * @return collection of segments
    */
  def apply(path: String): Array[IpSegment] = read(path)
}
