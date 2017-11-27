package com.example.david.switcherapp;//Basic Cart_Point function, eliminates Cart_Vector for simplicity

public class CartPoint {

	public double x;
	public double y;

	//default constructor
	public CartPoint() {
		x = 0;
		y = 0;
	}

	//constructor to set coords
	public CartPoint(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}

	//addition (adds coords of both points)
	public CartPoint add(CartPoint otherPoint) {
		CartPoint returnPoint = new CartPoint((x + otherPoint.x), (y + otherPoint.y));
		return returnPoint;
	}

	//subtraction (subtracts coords of both points)
	public CartPoint subtract(CartPoint otherPoint) {
		CartPoint returnPoint = new CartPoint((x - otherPoint.x), (y - otherPoint.y));
		return returnPoint;
	}

	//multiply by coefficient 
	public CartPoint multiply(double coefficient) {
		CartPoint returnPoint = new CartPoint((x*coefficient), (y*coefficient));
		return returnPoint;
	}

	//divide by coefficient
	public CartPoint divide(double coefficient) {
		CartPoint returnPoint = new CartPoint((x/coefficient), (y/coefficient));
		return returnPoint;
	}

	//more or less copied from: http://www.geeksforgeeks.org/overriding-equals-method-in-java/
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

	//assuming coords are always positive
	//taken from: https://stackoverflow.com/questions/9135759/java-hashcode-for-a-point-class
	public int hashCode() {
	    int result = (int)x;
	    result = 31 * result + (int)y;
	    return result;
	}

	//returns point coords
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	//returns numerical distance between two given points
	static double cartDistance(CartPoint point1, CartPoint point2) {
		return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
	}
}