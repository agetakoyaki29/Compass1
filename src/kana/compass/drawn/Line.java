package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import kana.compass.canvas.Pen;
import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;


public class Line extends Drawn {

	private final Point2D startPt;
	private final Point2D stopPt;

	public Line(Point2D startPt, Point2D stopPt) {
		this.startPt = startPt;
		this.stopPt = stopPt;
	}

	@Override
	public void draw(Pen pen) {
		pen.strokeLine(startPt, stopPt);
	}

	@Override
	public Bound2D getBoundingBox() {
		return Geo.newBound2D(startPt, stopPt);
	}

	@Override
	public List<Point2D> getPts() {
		List<Point2D> ret = new ArrayList<>();
		ret.add(startPt);
		ret.add(stopPt);
		return ret;
	}

	public Point2D closet(Point2D pt) {
		Point2D v = stopPt.subtract(startPt);
		Point2D p = pt.subtract(startPt);
		Point2D n = Geo.normal(v);
		double distance = Geo.crossProduct2D(v, p) / v.magnitude();
		return pt.add(Geo.scale(n, distance));
	}

	public double distance(Point2D pt) {
		Point2D v = stopPt.subtract(startPt);
		Point2D p = pt.subtract(startPt);
		return Math.abs( Geo.crossProduct2D(v, p) / v.magnitude() );
	}

}
