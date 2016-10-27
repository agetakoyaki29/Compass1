package kana.drawn.geometry;

import kana.main.MyRuntimeException;


/**
 * 含む最小の箱
 */
public final class Box {
	private final Vector min;
	private final Vector max;

	public Box(Vector min, Vector max) {
		this.min = min;
		this.max = max;
		// TODO heavy validtion
		if( !contains(Geo.midPoint(min, max)) ) throw new MyRuntimeException("min must lt max");
	}

//	private Vector min;
//	private Vector size;

//	public Vector closet(Vector aaa)
	public boolean contains(Vector v) {
		return min.x <= v.x && v.x <= max.x
			&& min.y <= v.y && v.y <= max.y
			;
	}
//	public boolean contains(Box aaa)
//	public boolean intersects(Box aaa)
//	public double sqrDistance(Vector aaa)

	public static Box zero = new Box(Vector.zero, Vector.zero);		// TODO 線,点と同じ面積・・・

}
