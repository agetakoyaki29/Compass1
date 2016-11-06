package kana.compass.geometry;

import java.util.Arrays;
import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.MouseEvent;
import kana.compass.util.MyRuntimeException;


/**
 * 2D
 */
public class Geo {

	private Geo() {
		throw new MyRuntimeException("can't instanciate");
	}

	// ---- angle ----

	public static Point2D onUnitCercle(double angle) {
		double rad = Math.toRadians(angle);
		return new Point2D(Math.cos(rad), -Math.sin(rad));
	}

	public static Point2D onCercle(Point2D center, double range, double angle) {
		if(range <= 0) throw new MyRuntimeException("range must > 0");
		return Geo.scale(onUnitCercle(angle), range).add(center);
	}

	public static double angle2D(Point2D pt1, Point2D pt2) {
		double sign = Geo.crossProduct2D(pt1, pt2) >= 0 ? 1 : -1;
		return sign * pt1.angle(pt2);
	}

	// ---- Point2D ----

	public static Point2D newPoint2D(MouseEvent event) {
		return new Point2D(event.getX(), event.getY());
	}
	public static Point2D newPoint2D(GestureEvent event) {
		return new Point2D(event.getX(), event.getY());
	}

	public static boolean isEmptySize(Point2D pt) {
		return pt.getX() < 0 || pt.getY() < 0;
	}

	public static double distance(Point2D pt1, Point2D pt2) {
		return pt1.subtract(pt2).magnitude();
	}

	public static double sqrDistance(Point2D pt1, Point2D pt2) {
		return Geo.sqrMagnitude( pt1.subtract(pt2) );
	}

	public static double sqrMagnitude(Point2D pt) {
		double x = pt.getX();
		double y = pt.getY();
		return x*x + y*y;
	}

	public static double crossProduct2D(Point2D pt1, Point2D pt2) {
		return pt1.getX()*pt2.getY() - pt1.getY()*pt2.getX();
	}

	public static double dotProduct2D(Point2D pt1, Point2D pt2) {
		return pt1.getX()*pt2.getX() + pt1.getY()*pt2.getY();
	}

	public static Point2D normal(Point2D pt) {
		return new Point2D(pt.getY(), -pt.getX());
	}

	public static Point2D diagonally(Point2D pt, double d) {
		return new Point2D(pt.getX() + d, pt.getY() + d);
	}

	public static Point2D scale(Point2D pt, double d) {
		return new Point2D(d * pt.getX(), d * pt.getY());
	}

	public static Point2D midpoint(Point2D... pts) {
		return midpoint(Arrays.asList(pts));
	}
	public static Point2D midpoint(Collection<Point2D> pts) {
		if(pts.size() <= 0) return null;
		Point2D ret = Point2D.ZERO;
		for (Point2D pt : pts) {
			ret = ret.add(pt);
		}
		return Geo.scale(ret, 1/pts.size());
	}

	// ---- Bound2D ----

	public static Bound2D newBound2D(Point2D... pts) {
		return newBound2D(pts);
	}
	public static Bound2D newBound2D(Collection<Point2D> pts) {
		if(pts.size() <= 0) throw new MyRuntimeException("ge 1, plz");

		double minX, minY, maxX, maxY;
		minX = minY = Double.POSITIVE_INFINITY;
		maxX = maxY = Double.NEGATIVE_INFINITY;

		for (Point2D pt : pts) {
			double x = pt.getX(), y = pt.getY();
			minX = minX <= x ? minX : x;
			minY = minY <= y ? minY : y;
			maxX = x <= maxX ? maxX : x;
			maxY = y <= maxY ? maxY : y;
		}

		return new Bound2D(new Point2D(minX, minY), new Point2D(maxX, maxY));
	}

}
