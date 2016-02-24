package clusterization.repository.input.segment

import clusterization.model.IpSegment

import scala.io.Source

/**
  * @author Alexey Poptsov
  */
object SegmentsFileRepository extends SegmentsRepository {

  override def open(path: String): Iterator[String] = Source.fromFile(path, "UTF-8").getLines()

  def apply(path: String) : Array[IpSegment] = read(path)
}
