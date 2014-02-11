package mountain;

import fractal.*;

public class Mountain extends Fractal {
	private Point a, b, c;

	/**
	 * An object that handles Mountain fractals. Basically triangle fractals.
	 * 
	 * @param a
	 *            First point of triangle
	 * @param b
	 *            Second point of triangle
	 * @param c
	 *            Third point of triangle
	 */
	public Mountain(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Bergfraktal";
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(a.getX(), a.getY());
		drawFractal(turtle, order, a, b, c);
	}

	/* 
	 * Reursive method: Draws a recursive line of the triangle. 
	 */
	private void drawFractal(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3) {
		if (order == 0) {
			drawTriangle(turtle, p1, p2, p3);
		} else {
			Point m1 = midPoint(p1, p2);
			Point m2 = midPoint(p2, p3);
			Point m3 = midPoint(p3, p1);
			
			drawFractal(turtle, order - 1, p1, m1, m3);
			drawFractal(turtle, order - 1, p2, m1, m2);
			drawFractal(turtle, order - 1, p3, m2, m3);
			
			drawFractal(turtle, order - 1, m1, m2, m3);
			
		}
	}
	
	private void drawTriangle(TurtleGraphics turtle, Point t1, Point t2, Point t3) {
		turtle.moveTo(t1.getX(), t1.getY());
		turtle.forwardTo(t2.getX(), t2.getY());
		turtle.forwardTo(t3.getX(), t3.getY());
		turtle.forwardTo(t1.getX(), t1.getY());
	}
	
	private Point midPoint(Point p1, Point p2) {
		return new Point((p1.getX() + p2.getX()) / 2.0, (p1.getY() + p2.getY()) / 2.0);
	}
}
