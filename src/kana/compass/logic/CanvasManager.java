package kana.compass.logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.transform.Affine;


public class CanvasManager {

	private final Canvas canvas;
	private final Canvas hotCanvas;
	private final Affine coord = new Affine();
	private final Paper paper;
	private final HotPaper hotPaper;

	public CanvasManager(Canvas canvas, Canvas hotCanvas) {
		this.canvas = canvas;
		this.hotCanvas = hotCanvas;
		paper = new Paper(canvas.getGraphicsContext2D(), coord);
		hotPaper = new HotPaper(hotCanvas.getGraphicsContext2D(), coord);

		paper.repaint();
		hotPaper.repaint();

//		coord.append(new Translate(-10, -10));
	}

	public Paper getPaper() { return paper; }
	public HotPaper getHotPaper() { return hotPaper; }

}
