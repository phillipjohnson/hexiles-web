package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.{Colors, Point, Drawable}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom.CanvasRenderingContext2D

/**
 * Author: Phillip Johnson

 */
class Piece(val hexes:Seq[Hexagon], val color:Colors.Color) extends Drawable {

  def getHexes = hexes

  def getCubes = hexes.map(_.cube)

  /**
   * Returns the x coordinate of the first hexile in the piece.
   * @return the x coordinate of the first hexile in the piece.
   */
  def x = hexes(0).x

  /**
   * Returns the y coordinate of the first hexile in the piece.
   * @return the y coordinate of the first hexile in the piece.
   */
  def y = hexes(0).y

  /**
   * Moves every hexile in the piece by the given x and y differentials.
   * @param dx the number of pixels to move in the x direction
   * @param dy the number of picels to move in the y direction
   */
  def move(dx:Float, dy:Float): Unit = {
    for(hex <- hexes) {
      hex.x += dx
      hex.y += dy
    }
  }

  /**
   * Aligns the piece to the nearest hexagon grid coordinate
   */
  def snapToGrid() = {
    for(hex <- hexes) {
      hex.snapToGrid(hex.centerPoint())
    }
  }

  /**
   * Rotates each hexile around the first hexile of the piece to the left.
   */
  def rotateLeft() = hexes.foreach(_.rotateLeft(hexes(0)))

  /**
   * Rotates each hexile around the first hexile of the piece to the right.
   */
  def rotateRight() = hexes.foreach(_.rotateRight(hexes(0)))

  /**
   * Flips each hexile over the y-axis of the first hexile in the piece.
   */
  def flip() = hexes.foreach(_.flipOver(hexes(0)))

  override def draw(context: CanvasRenderingContext2D): Unit = {
    hexes.foreach(hex => hex.draw(context, color))
  }

  /**
   * Renders a drop shadow around the piece.
   * @param context the canvas context
   */
  def highlight(context: CanvasRenderingContext2D): Unit = {
    hexes.foreach(hex => hex.draw(context, color, shadow = true))
    hexes.foreach(hex => hex.draw(context, color, shadow = false))
  }

  override def contains(point: Point): Boolean = hexes.exists(hex => hex.contains(point))


  def canEqual(other: Any): Boolean = other.isInstanceOf[Piece]

  override def equals(other: Any): Boolean = other match {
    case that: Piece =>
      (that canEqual this) &&
        hexes == that.hexes
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(hexes)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
