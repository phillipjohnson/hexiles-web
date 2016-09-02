package com.letstalkdata.hexiles
package game

import com.letstalkdata.hexiles.shapes.Cube

import scala.language.postfixOps

/**
 * A snapshot of the game at a point in time.
 *
 * Author: Phillip Johnson
 * Date: 4/30/15
 */
class State(board: Board, pieces: Seq[Piece]) {

  def isTerminal: Boolean = {
    isBoardCovered && !boardPiecesOverlap
  }

  private def isBoardCovered: Boolean = {
    val allPieceCubes: Seq[Cube] = pieces.flatMap(p => p.getHexes).map(hex => hex.cube)
    board.tiles.forall(tile => allPieceCubes.contains(tile.cube))
  }

  private def boardPiecesOverlap: Boolean = {
    val allPieceHexes = pieces.flatMap(p => p.getHexes)
    board.tiles
      .map(t => allPieceHexes.filter(p => p.cube == t.cube))
      .exists(matches => matches.size > 1)
  }

  def asSolution: Solution = Solution(pieces.filter(p => p.hexes.forall(board.tiles contains)))
}
