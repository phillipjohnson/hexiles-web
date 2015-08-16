package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.Colors.Color
import com.letstalkdata.hexiles.shapes.Cube

/**
 * Author: Phillip Johnson
 * Date: 5/30/15
 */
case class Solution(pieces: Seq[Piece]) {
  //TODO: Consider implementing this: require(pieces.size == 5)

  val map: Map[Color, Seq[Cube]] = pieces.map(p => p.color -> p.getCubes).toMap

  override def toString = map.toString()

}
