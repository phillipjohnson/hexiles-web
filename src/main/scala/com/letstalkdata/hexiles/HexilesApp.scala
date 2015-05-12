package com.letstalkdata.hexiles

import com.letstalkdata.hexiles.game.{Piece, Board}
import com.letstalkdata.hexiles.graphics.{Colors, Point}

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

  private val board = new Board()
  private val pieces:Seq[Piece] = List(new Piece(Colors.Olivine))

  private var redraw = false

  def main() = {
    var toMove:Piece = pieces(0)
    var offsetX = 0.0f
    var offsetY = 0.0f

    doRedraw()

    canvas.onmouseup = { (e:dom.MouseEvent) =>
      redraw = false
    }

    canvas.onmousedown = { (e: dom.MouseEvent) =>
      val pt = new Point(e.pageX.toFloat, e.pageY.toFloat)
            val pieceOpt = findClickedPiece(pt)
            if(pieceOpt.isDefined) {
              toMove = pieceOpt.get
              offsetX = pt.x - toMove.x
              offsetY = pt.y - toMove.y
              redraw = true
            }
    }

    canvas.onmousemove = { (e:dom.MouseEvent) =>
      if(redraw) {
        toMove.moveTo(new Point((e.pageX - offsetX).toFloat, (e.pageY - offsetY).toFloat))
      }
    }

    dom.setInterval(() => run(), 10)
  }

  private def doRedraw(): Unit = {
    ctx.fillStyle = "#FFFFFF"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
    board.draw(ctx)
    pieces.foreach(piece => piece.draw(ctx))
  }

  private def findClickedPiece(point:Point):Option[Piece] = {
    dom.console.log(pieces.find(piece => piece.contains(point)).toString)
    pieces.find(piece => piece.contains(point))
  }

  private def run() = {
    if(redraw) doRedraw()
  }

}
