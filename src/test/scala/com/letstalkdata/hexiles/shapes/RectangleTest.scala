package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics._
import utest._

/**
 * Author: Phillip Johnson
 * Date: 5/16/15
 */
object RectangleTest extends TestSuite {

  val tests = TestSuite {
    'topLeftIntersect {
      val middle = new Rectangle(new Point(10, 10), new Point(20, 20))
      val topLeft = new Rectangle(new Point(5, 5), new Point(15, 15))
      assert(middle intersects topLeft)
      assert(topLeft intersects middle)
    }

    'topRightIntersect {
      val middle = new Rectangle(new Point(10, 10), new Point(20, 20))
      val topRight = new Rectangle(new Point(15, 5), new Point(25, 15))
      assert(middle intersects topRight)
      assert(topRight intersects middle)
    }

    'bottomLeftIntersect {
      val middle = new Rectangle(new Point(10, 10), new Point(20, 20))
      val bottomLeft = new Rectangle(new Point(5, 15), new Point(15, 25))
      assert(middle intersects bottomLeft)
      assert(bottomLeft intersects middle)
    }

    'bottomRightIntersect {
      val middle = new Rectangle(new Point(10, 10), new Point(20, 20))
      val bottomRight = new Rectangle(new Point(15, 15), new Point(25, 25))
      assert(middle intersects bottomRight)
      assert(bottomRight intersects middle)
    }

    'outsideDoesNotIntersect {
      val middle = new Rectangle(new Point(10, 10), new Point(20, 20))
      val outside = new Rectangle(new Point(50, 50), new Point(60, 60))
      assert(!middle.intersects(outside))
    }
  }
}
