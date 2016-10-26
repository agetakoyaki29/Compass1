package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class FreePoint extends Point {
	protected double x = -1;
	protected double y = -1;

	public FreePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public FreePoint(Point pt) {
		this.x = pt.getX();
		this.y = pt.getY();
	}

	public FreePoint(MouseEvent event) {
		this.x = event.getX();
		this.y = event.getY();
	}

	@Override
	public void draw(GraphicsContext gc) {
	}

	@Override
	public List<Drawn> getChildren() {
		return new ArrayList<>();
	}

	@Override
	public double getX() { return x; }
	@Override
	public double getY() { return y; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }

}
