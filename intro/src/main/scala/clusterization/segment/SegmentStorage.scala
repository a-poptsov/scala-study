package clusterization.segment

import clusterization.IPAddress

import scala.collection.Iterable
import scala.collection.immutable.TreeMap

/**
  * @author Alexey Poptsov
  */
class SegmentStorage(val data: Array[Segment]) {

  val index: TreeMap[Long, Array[Segment]] = TreeMap(data.groupBy(_.start.ip).map({ case (ip, segments) => (ip, segments) }).toArray: _*)

  def findMatched(address: IPAddress, useIndex : Boolean = true): Iterable[Segment] = (if (useIndex) index.until(address.ip + 1).values.flatten else data.toIterable).filter(_.contains(address))

}

object SegmentStorage {

  def apply(data: Array[Segment]): SegmentStorage = new SegmentStorage(data)
}
