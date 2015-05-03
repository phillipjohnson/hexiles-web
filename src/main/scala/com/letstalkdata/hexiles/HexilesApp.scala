package com.letstalkdata.hexiles

import com.letstalkdata.hexiles.game.{Piece, Board}
import com.letstalkdata.hexiles.graphics.{Colors, Point}
import com.letstalkdata.hexiles.shapes.Hexagon

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
object HexilesApp extends JSApp {
  val canvas = document.getElementById("game-canvas").asInstanceOf[dom.raw.HTMLCanvasElement]
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  def main() = {

    val board = new Board()
    board.draw(ctx)

    val piece = new Piece(Colors.Olivine)
    piece.draw(ctx)

    canvas.onclick = { (e: dom.MouseEvent) =>
      val pt = new Point(e.pageX.toFloat, e.pageY.toFloat)
      //val found = hexes.filter(hex => hex.contains(pt))
      //dom.console.log(pt + ": " + found)
      ctx.fillRect(e.pageX, e.pageY, 10, 10)
    }
  }

}
