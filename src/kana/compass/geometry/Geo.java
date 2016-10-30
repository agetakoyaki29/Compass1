package kana.compass.geometry;

import java.util.Collection;

import kana.compass.util.MyRuntimeException;


public class Geo {

	private Geo() {
		throw new MyRuntimeException("can't instanciate");
	}

	public static Vector midPoint(Vector v1, Vector v2) {
		return v1.add(v2).devide(2);
	}

	public static double sqeDistance(Vector v1, Vector v2) {
		return v1.sub(v2).sqrMagunitude();
	}

	public static double distance(Vector v1, Vector v2) {
		return v1.sub(v2).magnitude();
	}

	public static Box makeBoundingBox(Vector... vs) {
		return makeBoundingBox(vs);
	}
	public static Box makeBoundingBox(Collection<Vector> vs) {
		if(vs.size() <= 0) throw new MyRuntimeException("ge 1, plz");

		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;

		for (Vector v : vs) {
			minX = minX <= v.x ? minX : v.x;
			minY = minY <= v.y ? minY : v.y;
			maxX = v.x <= maxX ? maxX : v.x;
			maxY = v.y <= maxY ? maxY : v.y;
		}

		return new Box(new Vector(minX, minY), new Vector(maxX, maxY));
	}

}
