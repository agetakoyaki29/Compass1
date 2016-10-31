package kana.compass.drawn.hot;

import kana.compass.drawn.Dot;
import kana.compass.drawn.Drawn;
import kana.compass.drawn.Line;
import kana.compass.geometry.Pen;


public class HotLine extends OldHotDrawn {

	public Dot pt1 = null;
	public Dot pt2 = null;

	@Override
	public void draw(Pen pen) {
		if(pt2 == null || pt1 == null) return;

		pen.strokeLine(pt1.getPt(), pt2.getPt());
	}

	public Drawn makeCold() {
		return new Line(pt1, pt2);
	}

}
