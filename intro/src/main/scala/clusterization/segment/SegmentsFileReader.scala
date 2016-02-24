package clusterization.segment

import scala.io.Source

/**
  *
  * @author Alexey Poptsov
  */
object SegmentsFileReader extends SegmentsReader {

  override def open(path: String): Iterator[String] = Source.fromFile(path, "UTF-8").getLines()

  def apply(path: String) : Array[Segment] = read(path)
}
