package hlists

sealed trait HList[T <: HList[_]] {
  def :+:[V](value: V) = new HCons(value, this)
  def length: Int
  type Intersection
}

class HCons[H, T <: HList[_]](head: H, val tail: T) extends HList[HCons[H, T]] {
  def length = tail.length + 1
  type Intersection = H with tail.Intersection
}

object HNil extends HList[Nothing] {
  def length = 0
  type Intersection = Any
}

object Test extends App {

  val myHlist = 'z' :+: "x" :+: 4 :+: HNil
  println(myHlist.length)

}