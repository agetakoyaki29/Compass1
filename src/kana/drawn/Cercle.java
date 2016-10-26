package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Cercle extends Drawn {

	public final Point pt1;
	public final Point pt2;

	public Cercle(Point pt1, Point pt2) {
		pt1.addParent();
		pt2.addParent();
		this.pt1 = pt1;
		this.pt2 = pt2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point c = Point.midPoint(pt1, pt2);
		double x = c.getX();
		double y = c.getY();
		double r = Point.distance(c, pt1);
		gc.strokeArc(x-r, y-r, 2*r, 2*r, 0, 360, ArcType.OPEN);
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(pt1);
		ret.add(pt2);
		return ret;
	}

}
