package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import kana.compass.geometry.Geo;
import kana.compass.geometry.Vector;


public class MidPoint extends Point {
	public final Point pt1;
	public final Point pt2;

	public MidPoint(Point pt1, Point pt2) {
		this.pt1 = pt1;
		this.pt2 = pt2;
	}

	@Override
	public List<Drawn> getComponents() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(pt1);
		ret.add(pt2);
		return ret;
	}

	@Override
	public Vector getV() {
		return Geo.midPoint(pt1.getV(), pt2.getV());
	}

}
