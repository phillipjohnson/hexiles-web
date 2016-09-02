package com.letstalkdata.hexiles
package game

import com.letstalkdata.hexiles.graphics.{Colors, Drawable}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom.CanvasRenderingContext2D

/**
 * Author: Phillip Johnson

 */
case class Piece(hexes: Seq[Hexagon], color: Colors.Color) extends Drawable[Double] {

  def getHexes = hexes

  def getCubes = hexes.map(_.cube)

  /**
   * Returns the x coordinate of the first hexile in the piece.
   * @return the x coordinate of the first hexile in the piece.
   */
  def x = hexes.head.x

  /**
   * Returns the y coordinate of the first hexile in the piece.
   * @return the y coordinate of the first hexile in the piece.
   */
  def y = hexes.head.y

  /**
   * Moves every hexile in the piece by the given x and y differentials.
   * @param dx the number of pixels to move in the x direction
   * @param dy the number of picels to move in the y direction
   */
  def move(dx: Double, dy: Double): Unit = {
    for (hex <- hexes) {
      hex.x += dx
      hex.y += dy
    }
  }

  /**
   * Aligns the piece to the nearest hexagon grid coordinate
   */
  def snapToGrid() = {
    for (hex <- hexes) {
      hex.snapToGrid(hex.centerPoint())
    }
  }

  /**
   * Rotates each hexile around the first hexile of the piece to the left.
   */
  def rotateLeft() = hexes.foreach(_.rotateLeft(hexes.head))

  /**
   * Rotates each hexile around the first hexile of the piece to the right.
   */
  def rotateRight() = hexes.foreach(_.rotateRight(hexes.head))

  /**
   * Flips each hexile over the y-axis of the first hexile in the piece.
   */
  def flip() = hexes.foreach(_.flipOver(hexes.head))

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

  override def contains(point: Point[Double]): Boolean = hexes.exists(hex => hex.contains(point))
}
