package kana.drawn;

import java.util.ArrayList;
import java.util.List;

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
	public double getX() {
		Point pt = Point.midPoint(cercle.pt1, cercle.pt2);
		return pt.getX();
	}

	@Override
	public double getY() {
		Point pt = Point.midPoint(cercle.pt1, cercle.pt2);
		return pt.getY();
	}

}
