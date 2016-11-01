package kana.compass.logic;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import kana.compass.geometry.Bound2D;


public class Pen {

	private final GraphicsContext gc;
	private final ScopeTransform scope;

	public Pen(GraphicsContext gc, ScopeTransform scope) {
		this.gc = gc;
		this.scope = scope;
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
		bounds = scope.transform(bounds);
		if(bounds.isEmpty()) return;
		Point2D min = bounds.getMin();
		Point2D size = bounds.getSize();
		gc.strokeArc(min.getX(), min.getY(), size.getX(), size.getY(), 0, 360, ArcType.OPEN);
	}

}
