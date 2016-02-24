package clusterization.model


/**
  * @author Alexey Poptsov
  */
class IpSegment(val segmentName: String, val start: IpAddress, val end: IpAddress) extends Ordered [IpSegment]{

  override def toString : String = start.toString + "-" + end.toString + " " + segmentName

  override def compare(that: IpSegment): Int = start.ip.compare(that.start.ip)

  def contains(address : IpAddress) : Boolean = start.ip <= address.ip && address.ip <= end.ip
}