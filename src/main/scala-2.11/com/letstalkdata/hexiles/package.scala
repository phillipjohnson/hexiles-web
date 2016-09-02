package com.letstalkdata

/**
 * Provides generic class and operators for dealing with 2D positions. As well dealing with 2D areas.
 */
package object hexiles {

  /**
   * Generic base class Point, holding the two ordinates
   *
   * @param x The abscissa
   * @param y The ordinate
   * @tparam P Numeric type
   */
  case class Point[P: Numeric](x: P, y: P) {

    import Numeric.Implicits.infixNumericOps
    import Ordering.Implicits.infixOrderingOps

    /** Binaire sum operator for two coordinates */
    def +(p: Point[P]) = Point(x + p.x, y + p.y)

    /** Binaire sum operator e,g. (a, b) + n => (a + n, b +n) */
    def +(term: P) = Point(x + term, y + term)

    /** Binaire subtract operator for the difference of two coordinates */
    def -(p: Point[P]) = Point(x - p.x, y - p.y)

    /** Binaire multiply operator for two coordinates, multplies each of the ordinate */
    def *(p: Point[P]) = Point(x * p.x, y * p.y)

    /** Binaire multiply operator e,g. (a, b) * n=> (a * n, b * n) */
    def *(factor: P) = Point(x * factor, y * factor)

    private def interSectsArea[P: Numeric](p0: Point[P], p1: Point[P], p2: Point[P], p3: Point[P]) = {
      @inline def intersectsWith(a0: P, b0: P, a1: P, b1: P) = a0 <= b1 && a1 <= b0

      intersectsWith(p0.x, p1.x, p2.x, p3.x) &&
        intersectsWith(p0.y, p1.y, p2.y, p3.y)
    }
  }

}
