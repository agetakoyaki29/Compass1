package kana.compass.geometry;

import javafx.geometry.Point2D;
import kana.compass.util.MyRuntimeException;


public class Geo {

	private Geo() {
		throw new MyRuntimeException("can't instanciate");
	}

	// ---- Point2D ----

	// newPoint2D(MouseEvent event)

	// scale

	public static double distance(Point2D pt1, Point2D pt2) {
		return pt1.subtract(pt2).magnitude();
	}

	public static double sqrDistance(Point2D pt1, Point2D pt2) {
		return Geo.sqrMagnitude( pt1.subtract(pt2) );
	}

//	public static Vector midpoint(Vector... vs) {
//		return v1.add(v2).devide(2);
//	}
//	public static Vector midpoint(Collection<Vector> vs) {
//	}

	public static double sqrMagnitude(Point2D pt) {
		double d1 = pt.getX();
		double d2 = pt.getY();
		return d1 * d1 + d2 * d2;
	}

	// ---- x ----

//	public static Box makeBoundingBox(Vector... vs) {
//		return makeBoundingBox(vs);
//	}
//	public static Box makeBoundingBox(Collection<Vector> vs) {
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

}
