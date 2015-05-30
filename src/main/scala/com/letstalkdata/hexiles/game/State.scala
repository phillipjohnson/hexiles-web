package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.shapes.Cube

/**
 * A snapshot of the game at a point in time.
 *
 * Author: Phillip Johnson
 * Date: 4/30/15
 */
class State(board:Board, pieces:Seq[Piece]) {

  def isTerminal: Boolean = {
    isBoardCovered && !boardPiecesOverlap
  }

  private def isBoardCovered: Boolean = {
    val allPieceCubes:Seq[Cube] = pieces.flatMap(p => p.getHexes).map(hex => hex.cube)
    board.tiles.forall(tile => allPieceCubes.contains(tile.cube))
  }

  private def boardPiecesOverlap:Boolean = {
    val allPieceHexes = pieces.flatMap(p => p.getHexes)
    board.tiles
      .map(t => allPieceHexes.filter(p => p.cube == t.cube))
      .exists(matches => matches.size > 1)
  }

  def solution(): Set[Piece] = {
    pieces.filter(p => p.getHexes.forall(board.tiles contains)).toSet
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[State]

  override def equals(other: Any): Boolean = other match {
    case that: State =>
      (that canEqual this) &&
        this.solution() == that.solution()
    case _ => false
  }

  override def hashCode(): Int = {
    solution().map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
