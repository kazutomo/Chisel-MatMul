package matmul

object DesignParams {
  private def getenv(k: String) : String = {
    try {sys.env(k)} catch { case _ : Throwable => "" }
  }

  private def getvalint(k: String, default: Int) : Int = {
    val ret = getenv(k)
    if (ret == "")  default
    else  ret.toInt
  }

  val n = getvalint("SMATMUL_N", 4)
  val nbits = getvalint("SMATMUL_NBITS", 8)
}
