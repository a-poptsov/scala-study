package clusterization.repository.input.segment

import org.scalatest._

/**
  *
  * @author Alexey Poptsov
  */
class SegmentFileRepositoryTest extends FlatSpec with Matchers {

  "A SegmentRepository" should "parse strings correctly" in {
    val segments = SegmentFileRepository.parse(Array("0.0.0.0-255.255.255.255\tseg1", "100.100.100.100-155.155.155.155\tseg2").toIterator)
    segments.length should be(2)
    segments(0).segmentName should be ("seg1")
    segments(0).start.toString should be ("0.0.0.0")
    segments(0).end.toString should be ("255.255.255.255")
    segments(1).segmentName should be ("seg2")
    segments(1).start.toString should be ("100.100.100.100")
    segments(1).end.toString should be ("155.155.155.155")
  }

}
