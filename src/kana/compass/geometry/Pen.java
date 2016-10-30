package kana.compass.geometry;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import kana.compass.drawn.Dot;
import kana.compass.util.MyRuntimeException;


public class Pen {

	private Pen() {
		throw new MyRuntimeException("can't instanciate");
	}

	public static void strokeLine(GraphicsContext gc, Point2D pt1, Point2D pt2) {
		gc.strokeLine(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
	}
	public static void strokeLine(GraphicsContext gc, Dot point1, Dot point2) {
		Point2D pt1 = point1.getPt();
		Point2D pt2 = point2.getPt();
		strokeLine(gc, pt1, pt2);
	}

	public static void strokeCercle(GraphicsContext gc, Point2D c, double r) {
		gc.strokeArc(c.getX()-r, c.getY()-r, 2*r, 2*r, 0, 360, ArcType.OPEN);
	}
	public static void strokeCercle(GraphicsContext gc, Dot c, double r) {
		strokeCercle(gc, c.getPt(), r);
	}

}
