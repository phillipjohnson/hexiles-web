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


class Hexagon(val q: Int, val r:Int) {


  val cube = new Cube(q, -q - r, r)

  def perimeterPoints(center:Point, size:Float):Seq[Point] = {
    (0 to 5).map(i => new Point(
      center.x + size * math.cos(Hexagon.RotationAngle * i).toFloat,
      center.y - size * math.sin(Hexagon.RotationAngle * i).toFloat))
  }

  def perimeter(center:Point, size:Float):Seq[Segment] = {
    val points = perimeterPoints(center, size)
    (0 to 5).map(i => new Segment(points(i), points((i + 1) % 6)))
  }

  def centerPoint(radius:Float):Point = {
    new Point(radius * 3/2 * q, radius * Hexagon.Sqrt3 * (r + q/2))
  }


}
