package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.Colors.Color
import com.letstalkdata.hexiles.shapes.Cube

/**
 * Author: Phillip Johnson
 * Date: 5/30/15
 */
class Solution(pieces:Seq[Piece]) {
  //TODO: Consider implementing this: require(pieces.size == 5)

  val map:Map[Color,Seq[Cube]] = pieces.map(p => p.color -> p.getCubes).toMap

  override def toString = map.toString()

  def canEqual(other: Any): Boolean = other.isInstanceOf[Solution]

  override def equals(other: Any): Boolean = other match {
    case that: Solution =>
      (that canEqual this) &&
        this.map.equals(that.map)
    case _ => false
  }

  override def hashCode(): Int = {
    map.hashCode()
  }

}
