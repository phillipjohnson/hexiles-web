package com.letstalkdata.hexiles
package game

import com.letstalkdata.hexiles.graphics.Colors
import com.letstalkdata.hexiles.shapes.Hexagon
import utest._

/**
 * Author: Phillip Johnson
 * Date: 5/31/15
 */
class SolutionTest extends TestSuite {

  val tests = TestSuite {
    'state_equality_determined_by_solution {
      val boardOne = new Board()
      val pieceOneA = Piece((0 to 2).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.White)
      val pieceOneB = Piece((2 to 4).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.Black)

      val stateOne = new State(boardOne, List(pieceOneA, pieceOneB))

      val boardTwo = new Board()
      val pieceTwoA = Piece((0 to 2).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.White)
      val pieceTwoB = Piece((2 to 4).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.Black)

      val stateTwo = new State(boardTwo, List(pieceTwoA, pieceTwoB))

      assert(stateOne.asSolution.equals(stateTwo.asSolution))
    }
    'two_different_solutions_are_non_equal {
      val boardOne = new Board()
      val pieceOneA = Piece((0 to 2).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.White)
      val pieceOneB = Piece((2 to 4).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.Black)

      val stateOne = new State(boardOne, List(pieceOneA, pieceOneB))

      val boardTwo = new Board()
      val pieceTwoA = Piece((0 to 3).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.White)
      val pieceTwoB = Piece((3 to 4).flatMap(row => {
        (7 - row to 11 - row).map(column => Hexagon(column, row))
      }), Colors.Black)

      val stateTwo = new State(boardTwo, List(pieceTwoA, pieceTwoB))

      assert(!stateOne.equals(stateTwo))
    }
  }
}
