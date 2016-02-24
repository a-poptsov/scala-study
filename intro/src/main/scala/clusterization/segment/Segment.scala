package clusterization.segment

import clusterization.IPAddress

/**
  * @author Alexey Poptsov
  */
class Segment(val segmentName: String, val start: IPAddress, val end: IPAddress) extends Ordered [Segment]{

  override def toString : String = start.toString + "-" + end.toString + " " + segmentName

  def contains(address : IPAddress) : Boolean = start.ip <= address.ip && address.ip <= end.ip

  override def compare(that: Segment): Int = start.ip.compare(that.start.ip)
}