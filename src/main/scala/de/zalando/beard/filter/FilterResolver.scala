package de.zalando.beard.filter

import org.slf4j.LoggerFactory
import scala.collection.immutable.Map

/**
  * @author dpersa
  */
trait FilterResolver {
  var registry: Map[String, Filter]
  def resolve(identifier: String, parameterNames: Set[String]): Option[Filter]
  def register(filter: Filter)
  def apply() = this
}

object DefaultFilterResolver extends FilterResolver {
  val logger = LoggerFactory.getLogger(this.getClass)

  val defaultFilters = Seq(
    LowercaseFilter(),
    UppercaseFilter(),
    DateFormatFilter(),
    CapitalizeFilter()
  )

  var registry = defaultFilters.map(filter => (filter.name, filter)).toMap[String, Filter]

  override def resolve(identifier: String, parameterNames: Set[String]): Option[Filter] = {
//    logger.info(s"Resolve filter ${identifier}")
    registry.get(identifier)
  }

  override def register(filter: Filter) = {
    registry = registry + (filter.name -> filter)
  }
}
