package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics._
import org.scalajs.dom

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
object Hexagon {
  private val radius = 35.0f
  private val RotationAngle = (math.Pi / 3).toFloat
  private val Sqrt3 = math.sqrt(3).toFloat
  private val TwoThirds = (2.0/3.0).toFloat
}


class Hexagon(val column: Int, val row:Int) extends Drawable {
  val cube = new Cube(column, -column - row, row)

  var x = Hexagon.radius * 1.5f * column
  var y = Hexagon.radius * Hexagon.Sqrt3 * (row + column/2.0f)


  def draw(context:dom.CanvasRenderingContext2D) = {
    draw(context, Colors.White)
  }

  def draw(context:dom.CanvasRenderingContext2D, fill:Colors.Color) = {
    val points = perimeterPoints(centerPoint(), Hexagon.radius)
    context.beginPath()
    context.moveTo(points(0).x, points(0).y)
    (0 until 6).foreach(i => {
      val end = points((i + 1) % 6)
      context.lineTo(end.x, end.y)
    })
    context.closePath()
    context.fillStyle = fill.toString
    context.fill()
    context.strokeStyle = Colors.Black.toString
    context.stroke()
  }

  def contains(point:Point) = {
    val fractionalColumn = point.x * Hexagon.TwoThirds / Hexagon.radius
    val fractionalRow = (-point.x / 3.0f + Hexagon.Sqrt3 / 3.0f * point.y) / Hexagon.radius

    var rx = math.round(fractionalColumn)
    var ry = math.round(-fractionalColumn - fractionalRow)
    var rz = math.round(fractionalRow)

    val dx = math.abs(rx - cube.x)
    val dy = math.abs(ry - cube.y)
    val dz = math.abs(rz - cube.z)

    if(dx > dy && dx > dz) rx = -ry - rz
    else if(dy > dz) ry = -rx - rz
    else rz = -rx - ry

    //dom.console.log(rx + " " + ry + " " +rz)
    //dom.console.log(cube.x + " " + cube.y + " " +cube.z)
    rx == cube.x && ry == cube.y && rz == cube.z
  }

  private def perimeterPoints(center:Point, size:Float):Seq[Point] = {
    (0 until 6).map(i => new Point(
      center.x + size * math.cos(Hexagon.RotationAngle * i).toFloat,
      center.y - size * math.sin(Hexagon.RotationAngle * i).toFloat))
  }

  def perimeter():Seq[Segment] = {
    val points = perimeterPoints(centerPoint(), Hexagon.radius)
    (0 until 6).map(i => new Segment(points(i), points((i + 1) % 6)))
  }

  def centerPoint() = {
    new Point(x, y)
  }


  override def toString = "(" + column + ", " + row + ")"


}
