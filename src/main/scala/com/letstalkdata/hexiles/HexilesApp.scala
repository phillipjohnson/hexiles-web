package com.letstalkdata.hexiles

import com.letstalkdata.hexiles.graphics.Point
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
  def main() = {

    val hexes:Seq[Hexagon] = (0 to 4).flatMap(row => {
      (4 - row to 8 - row).map(column => new Hexagon(column, row))
    })

    hexes.foreach(hex => {
      val center = hex.centerPoint(35)
      val points = hex.perimeterPoints(center, 35)
      ctx.beginPath()
      (0 until 6).foreach(i => {
        val start = points(i)
        val end = points((i + 1) % 6)
        ctx.moveTo(start.x, start.y)
        ctx.lineTo(end.x, end.y)
      })
      ctx.stroke()
    })

  }

}
