package mountain;

import java.util.LinkedList;

import fractal.*;

public class RandomMountain extends Fractal {
	private Point a, b, c;
	LinkedList<Side> sides;

	/**
	 * An object that handles RandomMountain fractals. RandomMountains are
	 * Mountains consisting of pseudo-triangles, i.e triangles that have had
	 * their middle points displaced by a random factor.
	 * 
	 * @param a
	 *            First point of pseudo-triangle
	 * @param b
	 *            Second point of pseudo-triangle
	 * @param c
	 *            Third point of pseudo-triangle
	 */
	public RandomMountain(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		sides = new LinkedList<Side>();
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return "Random Bergfraktal";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle
	 *            the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		double dev = 5; // Faktorn som bestämmer hur mycket punkterna i
						// fraktalen förskjuts

		if (order == 0) {
			sides = new LinkedList<Side>();
		} // Återställer fraktallistan om order sätts till 0

		turtle.moveTo(a.getX(), a.getY());
		drawFractal(turtle, order, a, b, c, dev);
	}

	/**
	 * Recursive method: Draws a triangle or recursively splits that triangle
	 * into 4 triangles and draws them
	 * 
	 * @param turtle
	 *            the turtle graphic object needed to draw the lines
	 * @param order
	 *            the order of the fractal
	 * @param p1
	 *            point 1 of the triangle
	 * @param p2
	 *            point 2 of the triangle
	 * @param p3
	 *            point 3 of the triangle
	 * @param dev
	 *            how much the deviation should be added to the middle points
	 */
	private void drawFractal(TurtleGraphics turtle, int order, Point p1,
			Point p2, Point p3, double dev) {
		if (order == 0) {
			drawTriangle(turtle, p1, p2, p3);
		} else {
			// Skapar Side objekt med de ändpunkter som skickats in, om de inte
			// redan finns i listan 'sides'
			Side side1 = checkList(p1, p2, dev);
			Side side2 = checkList(p2, p3, dev);
			Side side3 = checkList(p3, p1, dev);

			// Hämtar mittpunkterna från sidorna ovan
			Point m1 = side1.midPoint;
			Point m2 = side2.midPoint;
			Point m3 = side3.midPoint;

			// Ritar upp fyra trianglar från de punkter som skickats in + deras
			// mittpunkter
			drawFractal(turtle, order - 1, p1, m1, m3, dev);
			drawFractal(turtle, order - 1, p2, m1, m2, dev);
			drawFractal(turtle, order - 1, p3, m2, m3, dev);
			drawFractal(turtle, order - 1, m1, m2, m3, dev);

			// Minskar random-faktorn med en faktor 0.5
			dev *= 0.5;
		}
	}

	/**
	 * Checks the list 'sides' for a side matching the end-points sent in. If it
	 * already exists that side is returned, otherwise, one is created.
	 * 
	 * @param a
	 *            one of the end-points for the side
	 * @param b
	 *            the other end-point for the side
	 * @param dev
	 *            how much the middle-point of this side should deviate in the y
	 *            coords
	 * @return side with specified end-points
	 */
	private Side checkList(Point a, Point b, double dev) {
		Side tmp = new Side(a, b, dev);
		if (!sides.contains(tmp)) {
			sides.add(tmp);
			return tmp;
		} else {
			return sides.get(sides.indexOf(tmp));
		}
	}

	/**
	 * Draws a triangle with the three points sent in, as corner points
	 * 
	 * @param turtle
	 *            the turtle graphic object needed to draw the lines
	 * @param t1
	 *            First point of the triangle
	 * @param t2
	 *            Second point of the triangle
	 * @param t3
	 *            Third point of the triangle
	 */
	private void drawTriangle(TurtleGraphics turtle, Point t1, Point t2,
			Point t3) {
		turtle.moveTo(t1.getX(), t1.getY());
		turtle.forwardTo(t2.getX(), t2.getY());
		turtle.forwardTo(t3.getX(), t3.getY());
		turtle.forwardTo(t1.getX(), t1.getY());
	}

	/**
	 * Moves the point in the y-direction, by a factor proportional to dev
	 * 
	 * @param r1
	 *            the point to be displaced
	 * @param dev
	 *            the factor that determines how much the point is displaced
	 */
	private void randDisplaceY(Point r1, double dev) {
		r1.move(r1.getX(), r1.getY() + RandomUtilities.randFunc(dev));
	}

	/**
	 * Creates a new point positioned directly in the middle of the two points
	 * sent in
	 * 
	 * @param p1
	 *            first end-point
	 * @param p2
	 *            second end-point
	 * @return
	 */
	private Point midPoint(Point p1, Point p2) {
		return new Point((p1.getX() + p2.getX()) / 2.0,
				(p1.getY() + p2.getY()) / 2.0);
	}

	/**
	 * A class that contains a Side object, which consists of two end-points and
	 * a middle point. Has two constructors, one accepts just two points and
	 * calculates a middle point. The other also takes an argument 'dev'
	 * (double) that randomizes the y-value of the middle point by that factor.
	 * 
	 */
	private class Side {
		public Point p1, p2, midPoint;

		/**
		 * Constructor that only takes two points and creates a middle-point
		 * 
		 * @param p1
		 *            First end-point
		 * @param p2
		 *            Second end-point
		 */
		private Side(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			midPoint = midPoint(p1, p2);
		}

		/**
		 * Constructor that takes two points plus a factor that determines how
		 * much the middle point will deviate (randomly) in its y-value.
		 * 
		 * @param p1
		 *            First end-point
		 * @param p2
		 *            Second end-point
		 * @param dev
		 *            a factor that determines the deviation in the
		 *            middle-point's y-value
		 */
		private Side(Point p1, Point p2, double dev) {
			this.p1 = p1;
			this.p2 = p2;
			midPoint = midPoint(p1, p2);
			randDisplaceY(midPoint, dev);
		}

		public boolean equals(Object other) {
			if ((other == null) || (getClass() != other.getClass())) {
				return false;
			} else {
				Side otherSide = (Side) other;
				return (otherSide.p1 == p1 && otherSide.p2 == p2)
						|| (otherSide.p2 == p1 && otherSide.p1 == p2);
			}
		}
	}

}
