package clusterization.service

import clusterization.model.{IpAddress, IpSegment}

import scala.collection.Iterable

/**
  * Service that store information about segments
  *
  * @author Alexey Poptsov
  */
class SegmentStorage(val data: Array[IpSegment]) {

  val index: Array[Array[Array[IpSegment]]] = data.groupBy(_.start.ip).values.toArray
    .map(_.groupBy(_.end.ip).values.toArray.sortWith(_ (0).end.ip < _ (0).end.ip))
    .sortWith(_ (0)(0).start.ip < _ (0)(0).start.ip)

  /**
    * Find segments that contain provided ip address
    *
    * @param address  - criteria to find segments
    * @param useIndex - define an algorithm of searching segments
    * @return matched segments
    */
  def findMatched(address: IpAddress, useIndex: Boolean = true): Iterable[IpSegment] = if (useIndex) findUsingIndex(address) else data.toIterable.filter(_.contains(address))

  private def findUsingIndex(address: IpAddress): Iterable[IpSegment] = {
    index.takeWhile(_ (0)(0).start.ip <= address.ip).flatMap(_.filter(_ (0).end.ip > address.ip).toList.flatten)
  }

}

/**
  * Helper to create storage without using 'new' word
  */
object SegmentStorage {

  def apply(data: Array[IpSegment]): SegmentStorage = new SegmentStorage(data)
}
