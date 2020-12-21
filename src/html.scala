package html

object Html {
  
  val table = Tag[thead.type with tbody.type]("table")
  val thead = Tag[tr.type]("thead")
  val tbody = Tag[tr.type]("tbody")
  val tr = Tag[td.type with th.type]("tr")
  val td = Tag[Tag[_]]("td")
  val th = Tag[Tag[_]]("th")

  val myTable = table(
    thead(
      tr(
        th("Logic"), th("Types")
      ),
    ),
    tbody(
      tr(
        td("true"), td("Any")
      ),
      tr(
        td("false"), td("Nothing")
      )
    )
  )

  def main(args: Array[String]): Unit = println(myTable)
}

object Node {
  implicit def toText(str: String): Node[Tag[_]] = Text(str)
}

sealed trait Node[C]

case class Element[C](tag: Tag[_ <: Tag[_]], content: Seq[Node[_]]) extends Node[C] {
  override def toString(): String =
    s"<${tag.id}>${content.mkString}</${tag.id}>"
}

case class Text(str: String) extends Node[Tag[_]] {
  override def toString(): String = str
}

case class Tag[C <: Tag[_]](id: String) {
  def apply(nodes: Node[_ >: C]*): Node[this.type] = Element(this, nodes)
}

