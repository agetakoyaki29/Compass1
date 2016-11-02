package kana.compass.drawn;

import java.util.ArrayList;
import java.util.List;

import kana.compass.geometry.Bound2D;
import kana.compass.geometry.Geo;
import kana.compass.logic.Pen;


public class Line extends Drawn {

	private final Dot dot1;
	private final Dot dot2;

	public Line(Dot dot1, Dot dot2) {
		dot1.addParent();
		dot2.addParent();
		this.dot1 = dot1;
		this.dot2 = dot2;
	}

	@Override
	public void draw(Pen pen) {
		pen.strokeLine(dot1.getPt(), dot2.getPt());
	}

	@Override
	public Bound2D getBoundingBox() {
		return Geo.newBound2D(dot1.getPt(), dot2.getPt());
	}

	@Override
	public List<Drawn> getComponents() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(dot1);
		ret.add(dot2);
		return ret;
	}

}
