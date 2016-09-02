package com.letstalkdata.hexiles
package graphics

import org.scalajs.dom

/**
 * A trait representing any object that can be drawn on the canvas
 *
 * Author: Phillip Johnson
 * Date: 4/30/15
 */
trait Drawable[T] {
  def draw(context: dom.CanvasRenderingContext2D): Unit

  def contains(point: Point[T]): Boolean
}
