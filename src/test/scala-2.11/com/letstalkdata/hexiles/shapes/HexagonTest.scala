package com.letstalkdata.hexiles
package shapes

import com.letstalkdata.hexiles.graphics.Segment
import utest._

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
object HexagonTest extends TestSuite {

  val Epsilon = 0.00001f
  val size = Hexagon.radius

  val tests = TestSuite {
    'perimeter_has_6_lines {
      val hex = Hexagon(0,0)
      val segments = hex.perimeter()

      assert(6 == segments.size)
    }
    'calculates_correct_perimeter_segments {
      val hex = Hexagon(0,0)

      val segments = hex.perimeter()
      val height = (math.sqrt(3)/2 * size).toFloat

      val expected0 = Segment(Point(size,0),Point(0.5f * size, -height))
      assert(segmentsReasonablyEqual(segments.head, expected0, Epsilon))

      val expected1 = Segment(Point(0.5f * size, -height),Point(0.5f * -size, -height))
      assert(segmentsReasonablyEqual(segments(1), expected1, Epsilon))

      val expected2 = Segment(Point(0.5f * -size, -height),Point(-size, 0))
      assert(segmentsReasonablyEqual(segments(2), expected2, Epsilon))

      val expected3 = Segment(Point(-size, 0),Point(0.5f * -size, height))
      assert(segmentsReasonablyEqual(segments(3), expected3, Epsilon))

      val expected4 = Segment(Point(0.5f * -size, height),Point(0.5f * size, height))
      assert(segmentsReasonablyEqual(segments(4), expected4, Epsilon))

      val expected5 = Segment(Point(0.5f * size,height),Point(size,0))
      assert(segmentsReasonablyEqual(segments(5), expected5, Epsilon))
    }
    'hexagon_contains_center_point {
      val hex = Hexagon(1, 0)
      val center =Point(1.5d * size, math.sqrt(3).toFloat/2.0d * size)

      assert(hex.contains(center))
    }
    'hexagon_does_not_contain_outside_point {
      val hex = Hexagon(1, 0)
      val outside =Point(5 * size, 5 * size)

      assert(!hex.contains(outside))
    }
    'hexagon_contains_edge {
      val hex = Hexagon(1, 0)
      val edge =Point[Double](1.5f * size, 0)

      assert(hex.contains(edge))
    }
    'hexagon_contains_vertex {
      val hex = Hexagon(1, 0)
      val vertex = Point(2.0f * size -1, 1)

      assert(hex.contains(vertex))
    }
  }

  def pointsReasonablyEqual(p1:Point[Double], p2:Point[Double], e: Float) = {
    math.abs(p1.x - p2.x) < e && math.abs(p1.y - p2.y) < e
  }

  def segmentsReasonablyEqual(s1:Segment, s2:Segment, e:Float) = {
    pointsReasonablyEqual(s1.start, s2.start, e) && pointsReasonablyEqual(s1.end, s2.end, e)
  }
}
