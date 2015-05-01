package com.letstalkdata.hexiles.graphics

import org.scalajs.dom

/**
 * Author: Phillip Johnson
 * Date: 4/30/15
 */
trait Drawable {
  def draw(context: dom.CanvasRenderingContext2D): Unit

  def contains(point:Point): Boolean
}
