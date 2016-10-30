package kana.compass.geometry;

import javafx.geometry.Point2D;
import kana.compass.util.MyRuntimeException;


/**
 * 含む最小の箱
 */
public final class Box {
	private final Point2D min;
	private final Point2D max;

	public Box(Point2D min, Point2D max) {
		this.min = min;
		this.max = max;
		// TODO heavy validtion
		if( !contains(min.midpoint(max)) ) throw new MyRuntimeException("min must lt max");
	}

//	private Vector min;
//	private Vector size;

//	public Vector closet(Vector aaa)
	public boolean contains(Point2D v) {
		return min.getX() <= v.getX() && v.getX() <= max.getX()
			&& min.getY() <= v.getY() && v.getY() <= max.getY()
			;
	}
//	public boolean contains(Box aaa)
//	public boolean intersects(Box aaa)
//	public double sqrDistance(Vector aaa)

	public static Box zero = new Box(Point2D.ZERO, Point2D.ZERO);		// TODO 線,点と同じ面積・・・

}
