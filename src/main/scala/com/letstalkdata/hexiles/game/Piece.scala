package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.{Colors, Point, Drawable}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom.CanvasRenderingContext2D

/**
 * Author: Phillip Johnson

 */
class Piece(color:Colors.Color) extends Drawable {

  val hexes:Seq[Hexagon] = List(new Hexagon(3,3), new Hexagon(3, 4), new Hexagon(4, 2), new Hexagon(4, 3), new Hexagon(3, 2))

  def x = hexes(0).x
  def y = hexes(0).y

  def moveTo(point:Point) = {
    //val dx = x - point.x
    //val dy = y - point.y
    for(hex <- hexes) {
      //TODO: Set the location of the hex on the grid

      //TODO: WILL NOT WORK with >1 hex
      hex.x = point.x
      hex.y = point.y
    }
  }

  def move(dx:Float, dy:Float): Unit = {
    for(hex <- hexes) {
      //TODO: Set the location of the hex on the grid
      hex.x += dx
      hex.y += dy
    }
  }

  def snapToGrid() = {
    for(hex <- hexes) {
      hex.snapToGrid(hex.centerPoint())
    }
  }

  def resetGridPosition(point:Point) = {
    for(hex <- hexes) {
      hex.resetGridPosition(point)
    }
  }

  def rotateLeft() = hexes.foreach(_.rotateLeft(hexes(0)))

  override def draw(context: CanvasRenderingContext2D): Unit = {
    hexes.foreach(hex => hex.draw(context, color))
  }

  override def contains(point: Point): Boolean = hexes.exists(hex => hex.contains(point))

  def intersects(board:Board): Boolean = {
    for(tile <- board.tiles) {
      if(hexes.exists(_.boundingRect().intersects(tile.boundingRect()))) return true
    }

    false
  }
}
