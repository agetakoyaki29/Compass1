package kana.compass.geometry;



public final class Vector {
	public final double x;
	public final double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

//	public double getX() {
//		return this.x;
//	}
//	public double getY() {
//		return this.y;
//	}
	/**
	 * get componet by dimention index
	 */
	public double get(int i) {
		if(i == 0) {
			return x;
		} else if(i == 1) {
			return y;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	public Vector set(int i, double d) {
		if(i == 0) {
			return xAligned(d);
		} else if(i == 1) {
			return yAligned(d);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vector) return false;
		Vector o = (Vector)obj;
		return x == o.x && y ==o.y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Vector add(Vector v) {
		return new Vector(x+v.x, y+v.y);
	}
	public Vector sub(Vector v) {
		return new Vector(x-v.x, y-v.y);
	}
	public Vector scale(Vector v) {
		return new Vector(x*v.x, y*v.y);
	}
	public Vector xAligned(double x) {
		return new Vector(x, y);
	}
	public Vector yAligned(double y) {
		return new Vector(x, y);
	}
	public Vector multiply(double d) {
		return new Vector(x*d, y*d);
	}
	public Vector devide(double d) {
		return new Vector(x/d, y/d);
	}
	public double sqrMagunitude() {
		Vector v = this.scale(this);
		return v.x + v.y;
	}
	public double magnitude() {
		return Math.sqrt(this.sqrMagunitude());
	}

	// ---- static ----

	public static Vector zero = new Vector(0, 0);

}
