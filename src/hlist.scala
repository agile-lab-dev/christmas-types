package hlists

sealed trait HList[T <: HList[_]] {
  def :+:[V](value: V) = HCons(value, this)
  def length: Int
}
case class HCons[H, T <: HList[_]](head: H, tail: T) extends HList[HCons[H, T]] {
  def length = tail.length + 1
}

case object HNil extends HList[Nothing] {
  def length = 0
}

object Test {
  val myHList = 'x' :+: "hello" :+: 42 :+: HNil
  def main(args: Array[String]): Unit = println(myHList.length)
}