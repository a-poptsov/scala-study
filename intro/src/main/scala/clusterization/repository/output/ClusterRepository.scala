package clusterization.repository.output

import java.io.PrintWriter

/**
  *
  * @author Alexey Poptsov
  */
class ClusterRepository(path: String) {

  private val out = new PrintWriter(path)

  def write(data: String) = out.println(data)

  def close() = out.close()

}
