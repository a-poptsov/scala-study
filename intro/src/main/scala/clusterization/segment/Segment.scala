package clusterization.segment

import clusterization.IPAddress

/**
  *
  * @author Alexey Poptsov
  */
class Segment(val segmentName: String, start: IPAddress, end: IPAddress) {

  override def toString : String = start.toString + "-" + end.toString + " " + segmentName

  def contains(address : IPAddress) : Boolean = start.value <= address.value && address.value <= end.value

}