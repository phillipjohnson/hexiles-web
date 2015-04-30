package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics.{Segment, Point}
import utest._

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
object HexagonTest extends TestSuite {

  val Epsilon = 0.00001f

  val tests = TestSuite {
    'perimeter_has_6_lines {
      val center = new Point(0.0f, 0.0f)
      val hex = new Hexagon(0,0)
      val size = 1
      val segments = hex.perimeter(center, size)

      assert(6 == segments.size)
    }
    'calculates_correct_perimeter_segments {
      val center = new Point(0.0f, 0.0f)
      val hex = new Hexagon(0,0)

      val size = 1

      val segments = hex.perimeter(center, size)
      val height = (math.sqrt(3)/2 * size).toFloat

      val expected0 = new Segment(new Point(size,0), new Point(0.5f * size, -height))
      assert(segmentsReasonablyEqual(segments(0), expected0, Epsilon))

      val expected1 = new Segment(new Point(0.5f * size, -height), new Point(0.5f * -size, -height))
      assert(segmentsReasonablyEqual(segments(1), expected1, Epsilon))

      val expected2 = new Segment(new Point(0.5f * -size, -height), new Point(-size, 0))
      assert(segmentsReasonablyEqual(segments(2), expected2, Epsilon))

      val expected3 = new Segment(new Point(-size, 0), new Point(0.5f * -size, height))
      assert(segmentsReasonablyEqual(segments(3), expected3, Epsilon))

      val expected4 = new Segment(new Point(0.5f * -size, height), new Point(0.5f * size, height))
      assert(segmentsReasonablyEqual(segments(4), expected4, Epsilon))

      val expected5 = new Segment(new Point(0.5f * size,height), new Point(size,0))
      assert(segmentsReasonablyEqual(segments(5), expected5, Epsilon))

    }
    'center_point_is_correct {
      val hex = new Hexagon(1, 1)
      hex.centerPoint(10)
    }
  }

  def pointsReasonablyEqual(p1:Point, p2:Point, e: Float) = {
    math.abs(p1.x - p2.x) < e && math.abs(p1.y - p2.y) < e
  }

  def segmentsReasonablyEqual(s1:Segment, s2:Segment, e:Float) = {
    pointsReasonablyEqual(s1.start, s2.start, e) && pointsReasonablyEqual(s1.end, s2.end, e)
  }
}
