package kana.compass.geometry;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;


public final class Bound2D {

	public static Bound2D EMPTY = new Bound2D(new Point2D(0, 0), new Point2D(-1, -1));

	private final Point2D min;
	private final Point2D max;

	public Bound2D(Point2D min, Point2D max) {
		this.min = min;
		this.max = max;
	}

	public Point2D getMin() { return min; }
	public Point2D getMax() { return max; }
	public Point2D getSize() { return max.subtract(min); }

	@Override
	public String toString() {
		return this.getClass().getSimpleName()
				+ " [minX:" + min.getX() + ", minY:" + min.getY()
				+ ", maxX:" + max.getX() + ", maxY:" + max.getY() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj instanceof Bound2D) return false;
		Bound2D local = (Bound2D) obj;
		return local.getMin().equals(min) && local.getMax().equals(max);
	}

	public boolean contains(Point2D pt) {
		return contains(pt.getX(), pt.getY());
	}
	public boolean contains(double x, double y) {
		return min.getX() <= x && x <= max.getX()
			&& min.getY() <= y && y <= max.getY();
	}

	public boolean contains(Bound2D bounds) {
		Point2D localMin = bounds.getMin();
		Point2D localMax = bounds.getMax();
		return contains(localMin.getX(), localMin.getY(), localMax.getX(), localMax.getY());
	}
	public boolean contains(double minX, double minY, double maxX, double maxY) {
		boolean isEmpty = minX > maxX || minY > maxY;
		return isEmpty || (contains(minX, minY) && contains(maxX, maxY));
	}

	public boolean isEmpty() {
		return min.getX() > max.getX() || min.getY() > max.getY();
	}

//	public boolean intersects(Bound2D bounds) {
//		Point2D localMin = bounds.getMin();
//		Point2D localMax = bounds.getMax();
//		return contains(localMin.getX(), localMin.getY(), localMax.getX(), localMax.getY());
//	}
//	public boolean intersects(double minX, double minY, double maxX, double maxY) {
//
//	}

//	public Vector closet(Vector aaa)
//	public double sqrDistance(Vector aaa)


	public static void test1() {
		BoundingBox bb1 = new BoundingBox(0, 0, 10, 10);
		BoundingBox bb2 = new BoundingBox(2, 2, -1, -1);
		System.out.println(bb1.contains(bb2));
		System.out.println(bb1.contains(2, 2, -1, -1));
	}

}
