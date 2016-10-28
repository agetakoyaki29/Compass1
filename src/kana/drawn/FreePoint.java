package kana.drawn;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import kana.drawn.geometry.Vector;

public class FreePoint extends Point {
	private Vector vector;

	public FreePoint(double x, double y) {
		this.vector = new Vector(x, y);
	}

	public FreePoint(Vector vector) {
		this.vector = vector;
	}

	public FreePoint(MouseEvent event) {
		this.vector = new Vector(event.getX(), event.getY());
	}

	@Override
	public void draw(GraphicsContext gc) {
	}

	@Override
	public List<Drawn> getComponents() {
		return new ArrayList<>();
	}

	@Override
	public Vector getV() {
		return vector;
	}
	public void setV(Vector vector) {
		if(vector == null) throw new NullPointerException();
		this.vector = vector;
	}
	public void setX(double x) {
		vector = vector.xAligned(x);
	}
	public void setY(double y) {
		vector = vector.xAligned(y);
	}

}
