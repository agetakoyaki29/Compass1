package kana.compass.geometry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import kana.compass.drawn.Point;
import kana.compass.util.MyRuntimeException;


public class Pen {

	private Pen() {
		throw new MyRuntimeException("can't instanciate");
	}

	public static void strokeLine(GraphicsContext gc, Vector v1, Vector v2) {
		gc.strokeLine(v1.x, v1.y, v2.x, v2.y);
	}
	public static void strokeLine(GraphicsContext gc, Point pt1, Point pt2) {
		Vector v1 = pt1.getV();
		Vector v2 = pt2.getV();
		strokeLine(gc, v1, v2);
	}

	public static void strokeCercle(GraphicsContext gc, Vector c, double r) {
		gc.strokeArc(c.x-r, c.y-r, 2*r, 2*r, 0, 360, ArcType.OPEN);
	}
	public static void strokeCercle(GraphicsContext gc, Point c, double r) {
		strokeCercle(gc, c.getV(), r);
	}

}
