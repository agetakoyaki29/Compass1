package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Drawn {

	public final Point pt1;
	public final Point pt2;

	public Line(Point pt1, Point pt2) {
		pt1.addParent();
		pt2.addParent();
		this.pt1 = pt1;
		this.pt2 = pt2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(pt1.getX(), pt1.getY(), pt2.getX(), pt2.getY());
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(pt1);
		ret.add(pt2);
		return ret;
	}

}
