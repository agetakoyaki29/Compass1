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

	// ---- Point2D ----

	public static Point2D newPoint2D(MouseEvent event) {
		return new Point2D(event.getX(), event.getY());
	}
	public static Point2D newPoint2D(GestureEvent event) {
		return new Point2D(event.getX(), event.getY());
	}

	// scale
	public static Point2D scale(Point2D pt, double d) {
		return new Point2D(d * pt.getX(), d * pt.getY());
	}

	public static double distance(Point2D pt1, Point2D pt2) {
		return pt1.subtract(pt2).magnitude();
	}

	public static double sqrDistance(Point2D pt1, Point2D pt2) {
		return Geo.sqrMagnitude( pt1.subtract(pt2) );
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

	public static double sqrMagnitude(Point2D pt) {
		double d1 = pt.getX();
		double d2 = pt.getY();
		return d1 * d1 + d2 * d2;
	}

	public static Point2D inverseTransform(Affine affine, Point2D pt) {
		try {
			return affine.inverseTransform(pt);
		} catch (NonInvertibleTransformException e) {
			throw new MyRuntimeException(e);
		}
	}

	// ---- Bound2D ----

//	public static Bound2D newBound2D(Vector... vs) {
//		return makeBoundingBox(vs);
//	}
//	public static Bound2D newBound2D(Collection<Vector> vs) {
//		if(vs.size() <= 0) throw new MyRuntimeException("ge 1, plz");
//
//		double minX = Double.POSITIVE_INFINITY;
//		double minY = Double.POSITIVE_INFINITY;
//		double maxX = Double.NEGATIVE_INFINITY;
//		double maxY = Double.NEGATIVE_INFINITY;
//
//		for (Vector v : vs) {
//			minX = minX <= v.x ? minX : v.x;
//			minY = minY <= v.y ? minY : v.y;
//			maxX = v.x <= maxX ? maxX : v.x;
//			maxY = v.y <= maxY ? maxY : v.y;
//		}
//
//		return new Box(new Vector(minX, minY), new Vector(maxX, maxY));
//	}

	public static Bound2D transform(Affine affine, Bound2D bounds) {
		Point2D min = affine.transform(bounds.getMin());
		Point2D max = affine.transform(bounds.getMax());
		return new Bound2D(min, max);
	}

}
