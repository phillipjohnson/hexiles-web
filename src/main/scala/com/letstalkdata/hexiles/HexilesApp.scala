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
    val h = new Hexagon(0,0)
    val p = h.centerPoint(50)

    val points = h.perimeterPoints(p,50)

    ctx.beginPath()
    (0 until 6).foreach(i => {
      val start = points(i)
      val end = points((i + 1) % 6)
      ctx.moveTo(start.x, start.y)
      ctx.lineTo(end.x, end.y)
    })
    ctx.stroke()

    val h2 = new Hexagon(1,1)
    val p2 = h2.centerPoint(50)

    val points2 = h2.perimeterPoints(p2, 50)

    ctx.beginPath()
    (0 until 6).foreach(i => {
      val start = points2(i)
      val end = points2((i + 1) % 6)
      ctx.moveTo(start.x, start.y)
      ctx.lineTo(end.x, end.y)
    })
    ctx.stroke()

  }
}
