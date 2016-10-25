package kana.logic.drawns;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;

public class Point extends Drawn {
	private double x = -1;
	private double y = -1;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() { return x; }
	public double getY() { return y; }

	@Override
	public void draw(GraphicsContext gc) {
	}

	@Override
	public Collection<Drawn> getChildren() {
		return new ArrayList<>();
	}

}
