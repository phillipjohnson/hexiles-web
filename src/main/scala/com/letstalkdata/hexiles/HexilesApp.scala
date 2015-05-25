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
        cursor = getRelativeCursor(e)
        toMove.snapToGrid()
        doRedraw()
      }
    }

    canvas.onmousedown = { (e: dom.MouseEvent) =>
      if(e.button == 0) selectPiece(e)
    }

    canvas.onmousemove = { (e:dom.MouseEvent) =>
      if(redraw) {
        val newCursor = getRelativeCursor(e)
        val dx = newCursor.x - cursor.x
        val dy = newCursor.y - cursor.y
        toMove.move(dx, dy)
        cursor = newCursor
      }
    }

    dom.onkeydown = { (e:dom.KeyboardEvent) => alterPiece(e) }

    dom.setInterval(() => run(), 10)
  }

  private def makePieces():Seq[Piece] = {
    List(
      new Piece(List((2, 6), (1, 6), (1, 7), (2, 7), (3, 6)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Olivine),
      new Piece(List((12, -3), (11, -4), (12, -4), (13, -3), (12, -2)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Purple),
      new Piece(List((7, -2), (6, -2), (8, -3), (8, -2), (9, -3)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Tan),
      new Piece(List((12, 1), (13, 0), (13, 1), (11, 2), (12, 2)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Mauve),
      new Piece(List((2, 2), (3, 0), (2, 1), (1, 3), (2, 3)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Periwinkle),
      new Piece(List((7, 6), (6, 6), (5, 6), (8, 5), (9, 4)).map(coord => new Hexagon(coord._1, coord._2)), Colors.Gold)
    //TODO: Add final piece
    )
  }

  private def selectPiece(e:dom.MouseEvent) = {
    cursor = getRelativeCursor(e)
    val pieceOpt = findClickedPiece(cursor)
    if(pieceOpt.isDefined) {
      toMove = pieceOpt.get
      offsetX = cursor.x - toMove.x
      offsetY = cursor.y - toMove.y
      redraw = true
    }
  }

  private def alterPiece(e:dom.KeyboardEvent) = {
    if (e.keyCode == 37) toMove.rotateLeft()
    else if (e.keyCode == 39) toMove.rotateRight()
    else if (e.keyCode == 32 || e.keyCode == 13) {
      e.preventDefault()
      toMove.flip()
    }
    doRedraw()
  }

  private def doRedraw(): Unit = {
    ctx.fillStyle = "#FFFFFF"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
    board.draw(ctx)
    pieces.foreach(piece => piece.draw(ctx))
    toMove.highlight(ctx)
  }

  private def getRelativeCursor(e:dom.MouseEvent):Point = {
    val x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft - Math.floor(canvas.offsetLeft)
    val y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop - Math.floor(canvas.offsetTop) + 1
    new Point(x.toFloat, y.toFloat)
  }

  private def findClickedPiece(point:Point):Option[Piece] = {
    pieces.find(piece => piece.contains(point))
  }

  private def run() = {
    if(redraw) doRedraw()
  }

}
