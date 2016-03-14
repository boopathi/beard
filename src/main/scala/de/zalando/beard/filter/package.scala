package de.zalando.beard

/**
  * @author boopathi
  */
package object filter {
  import scalaz._, Scalaz._, effect._, IO._
  import scala.language.higherKinds
  import de.zalando.beard.filter._

  type FilterResolverMonadT[M[+_], +A] = StateT[M, FilterResolver, A]
  type FilterResolverMonadIO[+A] = FilterResolverMonadT[IO, A]
}
