package clusterization.service

import clusterization.model.{IpAddress, IpSegment}

import scala.collection.Iterable

/**
  * Service that store information about segments
  *
  * @author Alexey Poptsov
  */
class SegmentStorage(val data: Array[IpSegment]) {

  val index: List[List[List[IpSegment]]] = data.toList.groupBy(_.start.ip).values.toList
    .map(_.groupBy(_.end.ip).values.toList.sortWith(_.head.end.ip < _.head.end.ip))
    .sortWith(_.head.head.start.ip < _.head.head.start.ip)

  /**
    * Find segments that contain provided ip address
    * use full data scan algorithm
    *
    * @param address  - criteria to find segments
    * @return matched segments
    */
  def fullScan(address: IpAddress): Iterable[IpSegment] = data.toIterable.filter(_.contains(address))

  /**
    * Find segments that contain provided ip address
    * use index during scan
    *
    * @param address  - criteria to find segments
    * @return matched segments
    */
  def search(address: IpAddress): Iterable[IpSegment] = {
    index.takeWhile(_.head.head.start.ip <= address.ip).flatMap(_.filter(_.head.end.ip > address.ip).flatten)
  }

}

/**
  * Helper to create storage without using 'new' word
  */
object SegmentStorage {

  def apply(data: Array[IpSegment]): SegmentStorage = new SegmentStorage(data)
}
