package com.letstalkdata.hexiles

import com.letstalkdata.hexiles.game.{Board, Piece, Solution, State}
import com.letstalkdata.hexiles.graphics.{Colors, Point}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.JSExport

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
@JSExport
object HexilesApp {
  val canvas = document.getElementById("game-canvas").asInstanceOf[dom.raw.HTMLCanvasElement]
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  private val board = new Board()
  private var pieces: Seq[Piece] = makePieces()
  var toMove: Piece = pieces(2)
  var offsetX = 0.0f
  var offsetY = 0.0f
  var cursor = Point(0, 0)
  private var solutions: Set[Solution] = Set.empty
  private var state = new State(board, pieces)
  private var redraw = false

  @JSExport
  def main() = {
    doRedraw()

    canvas.onmouseup = { (e: dom.MouseEvent) => deselectPiece(e) }

    canvas.onmousedown = { (e: dom.MouseEvent) =>
      if (e.button == 0) selectPiece(e)
    }

    canvas.onmousemove = { (e: dom.MouseEvent) => movePiece(e) }

    dom.onkeydown = { (e: dom.KeyboardEvent) => alterPiece(e) }

    dom.setInterval(() => run(), 10)
  }

  @JSExport
  def resetBoard(): Unit = {
    pieces = makePieces()
    toMove = pieces(2)
    doRedraw()
  }

  private def makePieces(): Seq[Piece] = {
    List(
       Piece(List((2, 7), (1, 8), (1, 7), (2, 6), (3, 6)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Olivine),
       Piece(List((12, -3), (10, -2), (11, -3), (13, -3), (12, -2)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Purple),
       Piece(List((7, -2), (6, -2), (8, -3), (8, -2), (9, -3)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Melon),
       Piece(List((2, 9), (3, 8), (3, 9), (2, 10), (1, 10)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Pink),
       Piece(List((2, 2), (3, 0), (2, 1), (1, 3), (2, 3)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Cerulean),
       Piece(List((7, 6), (6, 6), (5, 6), (8, 5), (9, 4)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Forest),
       Piece(List((12, 3), (10, 5), (11, 4), (13, 2), (12, 2)).map(coord =>  Hexagon(coord._1, coord._2)), Colors.Gold)
    )
  }

  private def doRedraw(): Unit = {
    ctx.fillStyle = "#FFFFFF"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
    board.draw(ctx)
    pieces.foreach(piece => piece.draw(ctx))
    if (!victory) toMove.highlight(ctx)
  }

  def victory = state.isTerminal

  private def selectPiece(e: dom.MouseEvent) = {
    cursor = getRelativeCursor(e)
    val pieceOpt = findClickedPiece(cursor)
    if (pieceOpt.isDefined) {
      toMove = pieceOpt.get
      offsetX = cursor.x - toMove.x
      offsetY = cursor.y - toMove.y
      redraw = true
    }
  }

  private def alterPiece(e: dom.KeyboardEvent) = {
    if (e.keyCode == 37) toMove.rotateLeft()
    else if (e.keyCode == 39) toMove.rotateRight()
    else if (e.keyCode == 32 || e.keyCode == 13) {
      e.preventDefault()
      toMove.flip()
    }
    doRedraw()
    checkVictory()
  }

  private def movePiece(e: dom.MouseEvent) = {
    if (redraw) {
      val newCursor = getRelativeCursor(e)
      val dx = newCursor.x - cursor.x
      val dy = newCursor.y - cursor.y
      toMove.move(dx, dy)
      cursor = newCursor
    }
  }

  private def deselectPiece(e: dom.MouseEvent) = {
    if (redraw) {
      redraw = false
      cursor = getRelativeCursor(e)
      toMove.snapToGrid()
      doRedraw()
      checkVictory()
    }
  }

  private def getRelativeCursor(e: dom.MouseEvent): Point = {
    val x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft - Math.floor(canvas.offsetLeft)
    val y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop - Math.floor(canvas.offsetTop) + 1
    Point(x.toFloat, y.toFloat)
  }

  private def findClickedPiece(point: Point): Option[Piece] = {
    pieces.find(piece => piece.contains(point))
  }

  private def checkVictory(): Boolean = {
    state = new State(board, pieces)
    if (victory) {
      val solution = state.asSolution
      if (solutions.contains(solution)) {
        //TODO: Solution already found
      } else {
        solutions = solutions + solution
        increaseScore()
      }
    }
    victory
  }

  private def increaseScore(): Unit = {
    document.getElementById("score").textContent = solutions.size.toString
  }

  private def run() = {
    if (redraw) doRedraw()
  }

}
