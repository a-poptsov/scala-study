package clusterization.segment

import clusterization.IPAddress

/**
  * TODO implement this as tree
  *
  * @author Alexey Poptsov
  */
class SegmentStorage(val data: Array[Segment]) {

  def findMatched(ip: IPAddress): Array[Segment] = data.filter(segment => segment.contains(ip))

}

object SegmentStorage {

  def apply(data: Array[Segment]): SegmentStorage = new SegmentStorage(data)
}
