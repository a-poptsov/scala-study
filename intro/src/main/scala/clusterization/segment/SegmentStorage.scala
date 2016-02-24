package clusterization.segment

import clusterization.IPAddress

import scala.collection.Iterable
import scala.collection.immutable.TreeMap

/**
  * @author Alexey Poptsov
  */
class SegmentStorage(val data: Array[Segment]) {

//TODO use multi map here
  val index:TreeMap[Long, Segment] = TreeMap(data.map{ s => s.start.ip -> s }: _*)

  def findMatched(address: IPAddress): Iterable[Segment] = index.until(address.ip+1).values.filter(_.contains(address))
  def findMatchedWithoutIndex(address: IPAddress): Iterable[Segment] = data.filter(_.contains(address))

}

object SegmentStorage {

  def apply(data: Array[Segment]): SegmentStorage = new SegmentStorage(data)
}
