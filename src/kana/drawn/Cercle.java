package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.geometry.Box;
import kana.drawn.geometry.Geo;
import kana.drawn.geometry.Pen;
import kana.drawn.geometry.Vector;

public class Cercle extends Drawn {

	public final Point pt1;
	public final Point pt2;

	public Cercle(Point pt1, Point pt2) {
		pt1.addParent();
		pt2.addParent();
		this.pt1 = pt1;
		this.pt2 = pt2;
	}

	public Vector getCenter() {
		return Geo.midPoint(pt1.getV(), pt2.getV());
	}

	public double getRange() {
		return Geo.distance(pt1.getV(), pt2.getV()) / 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		Vector c = getCenter();
		double r = getRange();
		Pen.strokeCercle(gc, c, r);
	}

	@Override
	public Box getBoundBox() {
		Vector c = getCenter();
		double r = getRange();
		Vector rr = new Vector(r, r);
		return new Box(c.sub(rr), c.add(rr));
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(pt1);
		ret.add(pt2);
		return ret;
	}

}
