package logic;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.drawn.Cercle;
import logic.drawn.Drawn;
import logic.drawn.Line;
import logic.drawn.Point;


public class Note {

	private GraphicsContext gc;

	private ArrayList<Drawn> drawns = new ArrayList<Drawn>();

	public Note(GraphicsContext gc) {
		this.gc = gc;

		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		fillWhole();

		// for test
		drawns.add(new Line(new Point(10, 12), new Point(44, 51)));
		drawns.add(new Cercle(((Line)drawns.get(0)).pt2, 33));
	}

	public void repaint() {
		fillWhole();

		for (Drawn drawn : drawns) {
			drawn.draw(gc);
		}
	}

	// ---- private ----

	private void fillWhole() {
		Canvas canvas = gc.getCanvas();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

}
