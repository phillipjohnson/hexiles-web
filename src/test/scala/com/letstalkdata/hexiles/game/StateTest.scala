package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.Colors
import com.letstalkdata.hexiles.shapes.Hexagon
import utest._

/**
 * Author: Phillip Johnson
 * Date: 5/27/15
 */
object StateTest extends TestSuite {

  val tests = TestSuite {
    'empty_board_is_not_terminal {
      val board = new Board()

      assert(!new State(board, List.empty).isTerminal)
    }
    'filled_board_is_terminal {
      val board = new Board()

      //Creating one piece for each tile
      val pieces = (0 to 4).flatMap(row => {
        (7 - row to 11 - row).map(column => new Hexagon(column, row))
      }).map(hex => new Piece(List(hex), Colors.White))

      val state = new State(board, pieces)

      assert(state.isTerminal)
    }
    'partially_filled_board_is_not_terminal {
      val board = new Board()
      val pieces = (0 to 3).flatMap(row => {
        (7 - row to 11 - row).map(column => new Hexagon(column, row))
      }).map(hex => new Piece(List(hex), Colors.White))

      val state = new State(board, pieces)

      assert(!state.isTerminal)
    }
    'board_with_overlaps_is_not_terminal {
      val board = new Board()
      val pieces = (0 to 4).flatMap(row => {
        (7 - row to 11 - row).map(column => new Hexagon(column, row))
      }).map(hex => new Piece(List(hex), Colors.White)).toList

      val state = new State(board, new Piece(List(new Hexagon(7, 0)), Colors.White) :: pieces)

      assert(!state.isTerminal)
    }
  }
}
