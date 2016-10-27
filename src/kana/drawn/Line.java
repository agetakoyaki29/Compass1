package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import kana.drawn.geometry.Box;
import kana.drawn.geometry.Pen;


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
		Pen.strokeLine(gc, pt1, pt2);
	}

	@Override
	public Box getBoundBox() {
		return null;
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		ret.add(pt1);
		ret.add(pt2);
		return ret;
	}

}
