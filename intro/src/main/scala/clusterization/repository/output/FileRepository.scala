package clusterization.repository.output

import java.io.PrintWriter

/**
  * Repository that writes information to the file
  *
  * @author Alexey Poptsov
  */
class FileRepository(path: String) {

  private val out = new PrintWriter(path)

  /**
    * Write stirng as new line of a file
    *
    * @param data - string to write
    */
  def write(data: String) = out.println(data)

  /**
    * Close file
    */
  def close() = out.close()

}
