package kana.logic;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kana.logic.drawns.Cercle;
import kana.logic.drawns.Drawn;
import kana.logic.drawns.Line;
import kana.logic.drawns.Point;


public class Paper {

	private GraphicsContext gc;

	private DrawnPool pool = new DrawnPool();

	public Paper(GraphicsContext gc) {
		this.gc = gc;

		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		fillWhole();

		// TODO for test
		pool.add(new Line(new Point(10, 12), new Point(44, 51)));
		pool.add(new Cercle(new ArrayList<Point>(pool.getPoints()).get(1), 33));
	}

	public void repaint() {
		fillWhole();

		for (Drawn drawn : pool.getDrawn()) {
			drawn.draw(gc);
		}
	}

	// ---- private ----

	private void fillWhole() {
		Canvas canvas = gc.getCanvas();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

}
