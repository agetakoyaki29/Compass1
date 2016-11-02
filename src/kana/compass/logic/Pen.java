package kana.compass.logic;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;


public class Pen {

	private final GraphicsContext gc;
	private final ScopeTransform scope;

	public Pen(GraphicsContext gc, ScopeTransform scope) {
		this.gc = gc;
		this.scope = scope;
	}

	public double get1Pixel() {
		return scope.get1Pixel();
	}

	public void strokeLine(double x1, double y1, double x2, double y2) {
		strokeLine(new Point2D(x1, y1), new Point2D(x2, y2));
	}
	public void strokeLine(Point2D pt1, Point2D pt2) {
		pt1 = scope.transform(pt1);
		pt2 = scope.transform(pt2);
		gc.strokeLine(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
	}

	public void strokeCercle(Bound2D bounds) {
		strokeCercle(bounds.getMin(), bounds.getSize());
	}
	public void strokeCercle(Point2D min, Point2D size) {
		strokeArc(min, size, 0, 360);
	}
	public void strokeArc(Bound2D bounds, double startAngle, double arcExtent) {
		strokeArc(bounds.getMin(), bounds.getSize(), startAngle, arcExtent);
	}
	public void strokeArc(Point2D min, Point2D size, double startAngle, double arcExtent) {
		if(Geo.isEmptySize(size)) return;
		min = scope.transform(min);
		size = scope.deltaTransform(size);
		startAngle = scope.angleTransform(startAngle);
		gc.strokeArc(min.getX(), min.getY(), size.getX(), size.getY(), startAngle, arcExtent, ArcType.OPEN);
	}

}
