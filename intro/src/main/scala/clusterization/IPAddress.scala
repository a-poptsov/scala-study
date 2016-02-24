package clusterization

/**
  * @author Alexey Poptsov
  */
class IPAddress(val ip: Long) {
  val parts: Array[Long] = IPAddress.partsModifiers.map(modifier => {
    val part = ip / modifier % 1000
    if (0 <= part && part <= 255) part else throw new IllegalArgumentException("incorrect ip4 input")
  })

  def this(ip: String) = {
    this(IPAddress(ip.split("\\.").map(part => part.toLong)))
  }

  override def toString = parts.mkString(".")

}

object IPAddress {
  private val partsModifiers: Array[Long] = Array(1000000000L, 1000000L, 1000L, 1L)

  def apply(parts: Array[Long]): Long = parts.zip(partsModifiers).map(pair => pair._1 * pair._2).sum
}
