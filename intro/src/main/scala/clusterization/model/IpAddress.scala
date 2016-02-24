package clusterization.model

/**
  * @author Alexey Poptsov
  */
class IpAddress(val ip: Long) {

  private val parts = IpAddress.partsModifiers.map(modifier => {
    val part = ip / modifier % 1000
    if (0 <= part && part <= 255) part else throw new IllegalArgumentException("incorrect ip4 input")
  })

  override def toString = parts.mkString(".")

  def part(id: Int): Long = {
    if (id >= 0 && id <= 3) parts(id) else throw new IllegalArgumentException("ip4 contains only 4 parts")
  }

}

object IpAddress {
  val partsModifiers: Array[Long] = Array(1000000000L, 1000000L, 1000L, 1L)

  def apply(parts: Array[Long]): IpAddress = apply(parts.zip(partsModifiers).map(pair => pair._1 * pair._2).sum)

  def apply(ip: Long): IpAddress = new IpAddress(ip)

  def apply(ip: String): IpAddress = apply(ip.trim.split("\\.").map(part => part.toLong))
}


