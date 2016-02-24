package clusterization.service

import clusterization.model.{IpAddress, IpSegment}

import scala.collection.Iterable
import scala.collection.immutable.TreeMap

/**
  * @author Alexey Poptsov
  */
class SegmentStorage(val data: Array[IpSegment]) {

  val index: TreeMap[Long, Array[IpSegment]] = TreeMap(data.groupBy(_.start.ip).map({ case (ip, segments) => (ip, segments) }).toArray: _*)

  def findMatched(address: IpAddress, useIndex : Boolean = true): Iterable[IpSegment] = (if (useIndex) index.until(address.ip + 1).values.flatten else data.toIterable).filter(_.contains(address))

}

object SegmentStorage {

  def apply(data: Array[IpSegment]): SegmentStorage = new SegmentStorage(data)
}
