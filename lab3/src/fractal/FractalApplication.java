package fractal;

import mountain.Mountain;
import mountain.Point;
import mountain.RandomMountain;
import koch.Koch;

public class FractalApplication {
	public static void main(String[] args) {
		final Point a = new Point(250, 150);
		final Point b = new Point(450, 450);
		final Point c = new Point(150, 400);
		Fractal[] fractals = new Fractal[3];
		fractals[2] = new Koch(300);
		fractals[1] = new Mountain(a, b, c);
		fractals[0] = new RandomMountain(a, b, c);
	    new FractalView(fractals, "Fraktaler");
	}

}
