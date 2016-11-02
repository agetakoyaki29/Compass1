package kana.compass.geometry;

import java.util.Arrays;
import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import kana.compass.util.MyRuntimeException;


public class Geo {

	private Geo() {
		throw new MyRuntimeException("can't instanciate");
	}

	// ---- angle ----

	public static Point2D arcPoint(Point2D center, double range, double angle) {
		if(range <= 0) throw new MyRuntimeException("range must > 0");
		double rad = Math.toRadians(angle);
		Point2D pt = new Point2D(range * Math.cos(rad), range * -Math.sin(rad));
		return center.add(pt);
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
		double d1 = pt.getX();
		double d2 = pt.getY();
		return d1 * d1 + d2 * d2;
	}

	public static double crossProduct2D(Point2D pt1, Point2D pt2) {
		return pt1.getX()*pt2.getY() - pt1.getY()*pt2.getX();
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

		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;

		for (Point2D pt : pts) {
			double x = pt.getX(), y = pt.getY();
			minX = minX <= x ? minX : x;
			minY = minY <= y ? minY : y;
			maxX = x <= maxX ? maxX : x;
			maxY = y <= maxY ? maxY : y;
		}

		return new Bound2D(new Point2D(minX, minY), new Point2D(maxX, maxY));
	}

	public static Bound2D transform(Affine affine, Bound2D bounds) {
		Point2D min = affine.transform(bounds.getMin());
		Point2D max = affine.transform(bounds.getMax());
		return new Bound2D(min, max);
	}

	public static Point2D inverseTransform(Affine affine, Point2D pt) {
		try {
			return affine.inverseTransform(pt);
		} catch (NonInvertibleTransformException e) {
			throw new MyRuntimeException(e);
		}
	}

}
