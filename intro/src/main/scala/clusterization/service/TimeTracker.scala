package clusterization.service

/**
  * @author Alexey Poptsov
  */
object TimeTracker {

  def apply[R](block: => R, text: String = "Elapsed time:"): R = {
    val startTime = System.currentTimeMillis()
    val result = block
    println(text + " " + (System.currentTimeMillis() - startTime) + " milliseconds")
    result
  }

}
