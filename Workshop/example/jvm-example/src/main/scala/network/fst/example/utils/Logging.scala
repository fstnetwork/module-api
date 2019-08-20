package network.fst.example.utils

import org.slf4j.{Logger, LoggerFactory}

trait Logging {
  protected lazy val log: Logger = LoggerFactory.getLogger(logName)

  protected def logName: String = {
    this.getClass.getName.stripSuffix("$")
  }
}
