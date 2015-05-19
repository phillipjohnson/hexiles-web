package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.{Point, Drawable}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom

/**
 * Author: Phillip Johnson
 * Date: 4/30/15
 */
class Board extends Drawable {
  val tiles:Seq[Hexagon] = (0 to 4).flatMap(row => {
    (5 - row to 9 - row).map(column => new Hexagon(column, row))
  })

  override def draw(context:dom.CanvasRenderingContext2D): Unit = {
    tiles.foreach(tile => tile.draw(context))
  }

  override def contains(point: Point): Boolean = tiles.exists(tile => tile.contains(point))
}