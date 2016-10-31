package kana.compass.geometry;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Affine;
import kana.compass.drawn.Dot;


public class Pen {

	private final GraphicsContext gc;
	private final Affine coord;

	public Pen(GraphicsContext gc, Affine coord) {
		this.gc = gc;
		this.coord = coord;
	}

	public void strokeLine(double x1, double y1, double x2, double y2) {
		strokeLine(new Point2D(x1, y1), new Point2D(x2, y2));
	}
	public void strokeLine(Point2D pt1, Point2D pt2) {
		pt1 = coord.transform(pt1);
		pt2 = coord.transform(pt2);
		gc.strokeLine(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
	}

	public void strokeCercle(Bound2D bounds) {
		bounds = Geo.transform(coord, bounds);
		if(bounds.isEmpty()) return;
		Point2D min = bounds.getMin();
		Point2D size = bounds.getSize();
		gc.strokeArc(min.getX(), min.getY(), size.getX(), size.getY(), 0, 360, ArcType.OPEN);
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
