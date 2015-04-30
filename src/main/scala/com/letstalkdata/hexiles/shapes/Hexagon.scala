package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics._

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
object Hexagon {
  private val RotationAngle = (math.Pi / 3).toFloat
  private val Sqrt3 = math.sqrt(3).toFloat
}


class Hexagon(val column: Int, val row:Int) {


  val cube = new Cube(column, -column - row, row)

  def perimeterPoints(center:Point, size:Float):Seq[Point] = {
    (0 until 6).map(i => new Point(
      center.x + size * math.cos(Hexagon.RotationAngle * i).toFloat,
      center.y - size * math.sin(Hexagon.RotationAngle * i).toFloat))
  }

  def perimeter(center:Point, size:Float):Seq[Segment] = {
    val points = perimeterPoints(center, size)
    (0 until 6).map(i => new Segment(points(i), points((i + 1) % 6)))
  }

  def centerPoint(radius:Float):Point = {
    new Point(radius * 1.5f * column, radius * Hexagon.Sqrt3 * (row + column/2.0f))
  }


}
