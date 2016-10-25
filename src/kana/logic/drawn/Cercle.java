package kana.logic.drawn;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Cercle extends Drawn {

	public final Point center;
	private double range;

	public Cercle(Point center, double range) {
		this.center = center;
		this.range = range;
	}

	@Override
	public void draw(GraphicsContext gc) {
		double x = center.getX();
		double y = center.getY();
		double r = range;
		gc.strokeArc(x-r, y-r, 2*r, 2*r, 0, 360, ArcType.OPEN);
	}

	public double getRange() { return range; }

}
