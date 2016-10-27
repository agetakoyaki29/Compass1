package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import kana.drawn.geometry.Geo;
import kana.drawn.geometry.Vector;

public class CenterPoint extends Point {
	public final Cercle cercle;

	public CenterPoint(Cercle cercle) {
		this.cercle = cercle;
		cercle.addParent();
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(cercle);
		return ret;
	}

	@Override
	public Vector getV() {
		return Geo.midPoint(cercle.pt1.getV(), cercle.pt2.getV());
	}

}
