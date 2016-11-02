package kana.compass.drawn;

import javafx.geometry.Point2D;
import kana.compass.geometry.Bound2D;
import kana.compass.logic.Pen;

public class Cercle extends Arc {

	private final Dot dot1;
	private final Dot dot2;

	public Cercle(Dot dot1, Dot dot2) {
		super(dot1, dot2, 0, 360);
		dot1.addParent();
		dot2.addParent();
		this.dot1 = dot1;
		this.dot2 = dot2;
	}

	@Override
	public void draw(Pen pen) {
		pen.strokeCercle( getBoundingBox() );
	}

	@Override
	public Bound2D getBoundingBox() {
		Point2D c = getCenter();
		double r = getRange();
		Point2D rr = new Point2D(r, r);
		return new Bound2D(c.subtract(rr), c.add(rr));
	}

	// ---- ----

	public Dot getDot1() { return dot1; }
	public Dot getDot2() { return dot2; }

}
