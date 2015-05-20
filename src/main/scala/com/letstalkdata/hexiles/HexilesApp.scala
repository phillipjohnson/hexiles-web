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

  private val board = new Board()
  private val pieces:Seq[Piece] = makePieces()

  private var redraw = false

  var toMove:Piece = pieces(0)
  var offsetX = 0.0f
  var offsetY = 0.0f
  var cursor = new Point(0, 0)

  def main() = {
    doRedraw()

    canvas.onmouseup = { (e:dom.MouseEvent) =>
      if(redraw) {
        redraw = false
        cursor = new Point(e.pageX.toFloat, e.pageY.toFloat)
        toMove.snapToGrid()
        doRedraw()
      }
    }

    canvas.onmousedown = { (e: dom.MouseEvent) =>
      if(e.shiftKey) rotatePiece(e)
      else if(e.button == 0) selectPiece(e)
    }

    canvas.onmousemove = { (e:dom.MouseEvent) =>
      if(redraw) {
        val newCursor = new Point(e.pageX.toFloat, e.pageY.toFloat)
        val dx = newCursor.x - cursor.x
        val dy = newCursor.y - cursor.y
        toMove.move(dx, dy)
        cursor = newCursor
      }
    }

    dom.setInterval(() => run(), 10)
  }

  private def makePieces():Seq[Piece] = {
    List(
      new Piece(List((7, -2), (6, -2), (8, -2), (7, -3), (8, -3)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Olivine),
      new Piece(List((2, 0), (1, 0), (1, 1), (3, 0), (4, -1)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Purple),
      new Piece(List((10, -2), (10, -3), (10, -4), (11, -2), (12, -2)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Tan),
      new Piece(List((2, 6), (1, 6), (3, 6), (1, 7), (2, 7)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Mauve),
      new Piece(List((7, 5), (8, 4), (8, 5), (6, 6), (5, 6)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Periwinkle),
      new Piece(List((11, 1), (12, 0), (10, 1), (10, 2), (9, 2)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Gold)
    )
  }

  private def selectPiece(e:dom.MouseEvent) = {
    cursor = new Point(e.pageX.toFloat, e.pageY.toFloat)
    val pieceOpt = findClickedPiece(cursor)
    if(pieceOpt.isDefined) {
      toMove = pieceOpt.get
      offsetX = cursor.x - toMove.x
      offsetY = cursor.y - toMove.y
      redraw = true
    }
  }

  private def rotatePiece(e:dom.MouseEvent) = {
    cursor = new Point(e.pageX.toFloat, e.pageY.toFloat)
    val pieceOpt = findClickedPiece(cursor)
    if(pieceOpt.isDefined) {
      toMove = pieceOpt.get
      toMove.rotateLeft()
      offsetX = cursor.x - toMove.x
      offsetY = cursor.y - toMove.y
      redraw = true
    }
  }

  private def doRedraw(): Unit = {
    ctx.fillStyle = "#FFFFFF"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
    board.draw(ctx)
    pieces.foreach(piece => piece.draw(ctx))
  }

  private def findClickedPiece(point:Point):Option[Piece] = {
    pieces.find(piece => piece.contains(point))
  }

  private def run() = {
    if(redraw) doRedraw()
  }

}
