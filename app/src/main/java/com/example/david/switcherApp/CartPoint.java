package com.example.david.switcherApp;//Basic Cart_Point function, eliminates Cart_Vector for simplicity

/**
 * This is a very simple representation of a cartesian coordinate. It can technically represent any
 * point on the grid, though for this game only whole numbers are needed. It also provides
 * methods for simple mathematical operations and distance calculation.
 *
 * @author David Kirk
 */
public class CartPoint {

	public double x;
	public double y;

	/**
	 * Default constructor. Creates the point at (0, 0).
	 */
	public CartPoint() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructor for a specific coordinate
	 * @param xCoord the x-coordinate of the new CartPoint
	 * @param yCoord the y-coordinate of the new CartPoint
	 */
	public CartPoint(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}

	/**
	 * Adds this point and another point's coordinates individually (x1+x2, y1+y2)
	 * @param otherPoint the other point to be added to
	 * @return the sum of this point and the other point
	 */
	public CartPoint add(CartPoint otherPoint) {
		CartPoint returnPoint = new CartPoint((x + otherPoint.x), (y + otherPoint.y));
		return returnPoint;
	}

	/**
	 * Subtracts another point from this point's coordinates (x1-x2, y1-y2)
	 * @param otherPoint the point to be subtracted from this point
	 * @return the difference between the two points
	 */
	public CartPoint subtract(CartPoint otherPoint) {
		CartPoint returnPoint = new CartPoint((x - otherPoint.x), (y - otherPoint.y));
		return returnPoint;
	}

	/**
	 * Multiplies this point's coordinates by a constant (a*x, a*y)
	 * @param coefficient the coefficient to be multiplied by this point's coordinates
	 * @return the product of this point and the coefficient
	 */
	public CartPoint multiply(double coefficient) {
		CartPoint returnPoint = new CartPoint((x*coefficient), (y*coefficient));
		return returnPoint;
	}

	/**
	 * Divides this point's coordinates by a constant (x/a, y/a)
	 * @param coefficient the coefficient to be divided by
	 * @return the quotient of this point and the coefficient
	 */
	public CartPoint divide(double coefficient) {
		CartPoint returnPoint = new CartPoint((x/coefficient), (y/coefficient));
		return returnPoint;
	}

	/**
	 * Overrides Object's equals method, from when Model was going to use HashMaps. No longer used,
	 * but nice to have. Copied with modifications from:
	 * http://www.geeksforgeeks.org/overriding-equals-method-in-java/
	 * @param o the object being compared to this one
	 * @return true if both are the same, false otherwise
	 */
	@Override
	public boolean equals(Object o) {

		if(o == this) { //same instance
			return true;
		}

		if (!(o instanceof CartPoint)) {
            return false;
        }

        CartPoint c = (CartPoint)o;

		if (x == c.x && y == c.y) {
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * Overrides the hashCode method, from when I planned on using HashMaps in Model. No longer
	 * used, but nice to have. It always assumes coordinates are positive. Copied from:
	 * https://stackoverflow.com/questions/9135759/java-hashcode-for-a-point-class
	 * @return
	 */
	@Override
	public int hashCode() {
	    int result = (int)x;
	    result = 31 * result + (int)y;
	    return result;
	}

	/**
	 * Overrides Object's toString method to make a string with the CartPoint's coordinates.
	 * @return coordinates as a string
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	/**
	 * Returns the numberical distance between two given points
	 * @param point1 the first point
	 * @param point2 the second point
	 * @return the distance between the two points, as a double
	 */
	static double cartDistance(CartPoint point1, CartPoint point2) {
		return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
	}
}