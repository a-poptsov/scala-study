package clusterization.service

/**
  * Helper to track time
  *
  * @author Alexey Poptsov
  */
object TimeTracker {

  /**
    * Track how many time does it take to run a function.
    *
    * @param func - function to track
    * @param text - message to personalize output
    * @tparam R return type of function
    * @return result of function invocation
    */
  def apply[R](func: => R, text: String = "Elapsed time:"): R = {
    val startTime = System.currentTimeMillis()
    val result = func
    println(text + " " + (System.currentTimeMillis() - startTime) + " milliseconds")
    result
  }

}
