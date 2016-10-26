package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;


public abstract class Point extends Drawn {

	@Override
	public void draw(GraphicsContext gc) {
		double x = getX();
		double y = getY();
		gc.strokeLine(x, y-1, x, y+1);
		gc.strokeLine(x-1, y, x+1, y);
	}

	@Override
	public List<Drawn> getChildren() {
		ArrayList<Drawn> ret = new ArrayList<>();
		return ret;
	}

	public abstract double getX();
	public abstract double getY();

	// ---- static ----

	public static Point midPoint(Point pt1, Point pt2) {
		double x = pt1.getX() + pt2.getX();
		double y = pt1.getY() + pt2.getY();
		x /= 2;
		y /= 2;
		return new FreePoint(x, y);
	}

	public static double distance(Point pt1, Point pt2) {
		double dx = pt1.getX() - pt2.getX();
		double dy = pt1.getY() - pt2.getY();
		dx = dx*dx;
		dy = dy*dy;
		double ret = Math.sqrt(dx + dy);
		return ret;
	}

}
