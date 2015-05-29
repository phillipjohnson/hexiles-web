package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics._
import org.scalajs.dom

/**
 * The geometric shape that serves as the building block of the board and pieces.
 *
 * The position of the hexagon is stored in a cubal coordinate system. For more
 * details, see http://www.redblobgames.com/grids/hexagons/
 *
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
object Hexagon {
  val radius = 35.0f
  private val RotationAngle = (math.Pi / 3).toFloat
  val Sqrt3 = math.sqrt(3).toFloat
  private val TwoThirds = (2.0/3.0).toFloat
}


class Hexagon(val column: Int, val row:Int) extends Drawable {
  var cube = new Cube(column, -column - row, row)

  /**
   * The x component of the pixel coordinate of the hexagon's center
   */
  var x = cube.toPoint.x

  /**
   * * The y component of the pixel coordinate of the hexagon's center
   */
  var y = cube.toPoint.y


  override def draw(context:dom.CanvasRenderingContext2D):Unit = {
    draw(context, Colors.White, shadow = false)
  }

  /**
   * Draws the hexagon and fills it with the provided color.
   * @param context the canvas context
   * @param fill the color to fill the shape
   */
  def draw(context:dom.CanvasRenderingContext2D, fill:Colors.Color):Unit = {
    draw(context, fill, shadow = false)
  }

  /**
   * Draws the hexagon, fills it with the provided color, and optionally adds a drop shadow.
   * @param context the canvas context
   * @param fill the color to fill the shape
   * @param shadow if true, a drop shadow will be rendered around the hexagon
   */
  def draw(context:dom.CanvasRenderingContext2D, fill:Colors.Color, shadow:Boolean):Unit = {
    val points = perimeterPoints()
    context.beginPath()
    context.moveTo(points(0).x, points(0).y)
    (0 until 6).foreach(i => {
      val end = points((i + 1) % 6)
      context.lineTo(end.x, end.y)
    })
    context.closePath()
    context.fillStyle = fill.toString
    if(shadow) {
      context.shadowColor = Colors.Gray20.toString
      context.shadowBlur = 20
      context.shadowOffsetX = 5
      context.shadowOffsetY = 5
    } else {
      context.shadowColor = "transparent"
    }
    context.fill()
    context.strokeStyle = Colors.Gray20.toString
    context.stroke()
    drawCoords(context)
  }

  private def drawCoords(context:dom.CanvasRenderingContext2D): Unit = {
    context.moveTo(centerPoint().x, centerPoint().y)
    context.strokeText(cube.toString, centerPoint().x, centerPoint().y, 1000)
  }

  /**
   * Returns true if the provided point is within the bounds of the hexagon.
   * @param point the point to check
   * @return true if the provided point is within the bounds of the hexagon.
   */
  def contains(point:Point) = {
    val nearestCube = pointToCube(point)
    nearestCube.x == cube.x && nearestCube.y == cube.y && nearestCube.z == cube.z
  }

  /**
   * Moves the hexagon to the nearest hexagon grid coordinate
   * @param point the point to use for realignment. Typically, the center of the hexagon.
   */
  def snapToGrid(point:Point) = {
    resetGridPosition(point)
    x = cube.toPoint.x
    y = cube.toPoint.y
  }

  private def resetGridPosition(point:Point) = {
    val nearestCube = pointToCube(point)
    cube = nearestCube
  }

  private def pointToCube(point:Point):Cube = {
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

    new Cube(rx, ry, rz)
  }

  private def perimeterPoints():Seq[Point] = {
    val center = centerPoint()
    val size = Hexagon.radius
    (0 until 6).map(i => new Point(
      center.x + size * math.cos(Hexagon.RotationAngle * i).toFloat,
      center.y - size * math.sin(Hexagon.RotationAngle * i).toFloat))
  }

  /**
   * Returns the list of line segments that represent the perimeter of the hexagon.
   * @return
   */
  def perimeter():Seq[Segment] = {
    val points = perimeterPoints()
    (0 until 6).map(i => new Segment(points(i), points((i + 1) % 6)))
  }

  /**
   * Returns the center point of the hexagon
   * @return the center point of the hexagon
   */
  def centerPoint() = {
    new Point(x, y)
  }

  def boundingRect():Rectangle = {
    val top = perimeterPoints().minBy(_.y).y
    val left = perimeterPoints().minBy(_.x).x
    val bottom = perimeterPoints().maxBy(_.y).y
    val right = perimeterPoints().maxBy(_.x).x

    new Rectangle(new Point(left, top), new Point(right, bottom))
  }

  /**
   * Rotates the hexagon around another hexagon to the left.
   * @param pivot the hexagon around which to rotate
   */
  def rotateLeft(pivot:Hexagon) = {
    rotate(pivot, left = true)
  }

  /**
   * Rotates the hexagon around another hexagon to the right.
   * @param pivot the hexagon around which to rotate
   */
  def rotateRight(pivot:Hexagon) = {
    rotate(pivot, left = false)
  }

  private def rotate(pivot:Hexagon, left:Boolean) = {
    val dx = pivot.cube.x - this.cube.x
    val dy = pivot.cube.y - this.cube.y
    val dz = pivot.cube.z - this.cube.z

    val newX = if(left) cube.x - dz else cube.x - dy
    val newY = if(left) cube.y - dx else cube.y - dz
    val newZ = if(left) cube.z - dy else cube.z - dx

    cube = new Cube(newX, newY, newZ)

    x = cube.toPoint.x
    y = cube.toPoint.y
  }

  /**
   * Flips the hexagon over the y-axis of another hexagon.
   * @param pivot the hexagon over which to flip
   */
  def flipOver(pivot:Hexagon) = {
    val dx = pivot.cube.x - this.cube.x

    val newX = this.cube.x + dx * 2
    val newY = this.cube.y - dx
    val newZ = this.cube.z - dx

    cube = new Cube(newX, newY, newZ)

    x = cube.toPoint.x
    y = cube.toPoint.y
  }

  override def toString = "Hex: (" + column + ", " + row + ")"


}
