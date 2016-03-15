package de.zalando.beard.filter

import scala.collection.immutable.Map
import scalaz._, Scalaz._

/**
  * @author boopathi
  */
object FilterManager {

  type StateRegistry[A] = State[Registry, A]

  val empty: StateRegistry[Unit] = State.state(Registry(Map.empty))

  case class Registry(data: Map[String, Filter]) {

    def get(name: String): Option[Filter] =
      data.get(name)

    def add(filter: Filter): Registry =
      Registry(data + (filter.name -> filter))

  }

  def register (filter: Filter): StateRegistry[Unit] =
    modify[Registry] { _.add(filter) }

}

object DefaultFilterRegistry {

  import FilterManager._

  val filters: StateRegistry[Unit] = for {
    _ <- register(LowercaseFilter())
    _ <- register(UppercaseFilter())
    _ <- register(DateFormatFilter())
    _ <- register(CapitalizeFilter())
  } yield ()

}