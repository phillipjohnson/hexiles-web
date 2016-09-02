package com.letstalkdata.hexiles
package shapes

import com.letstalkdata.hexiles.graphics._
import utest._

/**
 * Author: Phillip Johnson
 * Date: 5/16/15
 */
object RectangleTest extends TestSuite {

  val tests = TestSuite {
    'topLeftIntersect {
      val middle = Rectangle(Point(10, 10), Point(20, 20))
      val topLeft = Rectangle(Point(5, 5), Point(15, 15))
      assert(middle intersects topLeft)
      assert(topLeft intersects middle)
    }

    'topRightIntersect {
      val middle = Rectangle(Point(10, 10), Point(20, 20))
      val topRight = Rectangle(Point(15, 5), Point(25, 15))
      assert(middle intersects topRight)
      assert(topRight intersects middle)
    }

    'bottomLeftIntersect {
      val middle = Rectangle(Point(10, 10), Point(20, 20))
      val bottomLeft = Rectangle(Point(5, 15), Point(15, 25))
      assert(middle intersects bottomLeft)
      assert(bottomLeft intersects middle)
    }

    'bottomRightIntersect {
      val middle = Rectangle(Point(10, 10), Point(20, 20))
      val bottomRight = Rectangle(Point(15, 15), Point(25, 25))
      assert(middle intersects bottomRight)
      assert(bottomRight intersects middle)
    }

    'outsideDoesNotIntersect {
      val middle = Rectangle(Point(10, 10), Point(20, 20))
      val outside = Rectangle(Point(50, 50), Point(60, 60))
      assert(!middle.intersects(outside))
    }
  }
}
