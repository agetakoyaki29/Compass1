package gui.canvasPage;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import logic.ActionSenter;
import logic.Note;
import main.MainApp;
import screenTransition.PageController;


public class CanvasPageController implements PageController {

	@FXML private BorderPane borderPane;
	@FXML private Canvas canvas;
	@FXML private Canvas hotCanvas;

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	private MenuBarController menuBarController;

	private Note note;
	private ActionSenter act;	// TODO

	@Override
	public void init() {
		MainApp.getInstance().getStage().setMaximized(true);

		// init gui
		//menuBarController = new MenuBarController(this);

		// init logic
		note = new Note(canvas.getGraphicsContext2D());
		note.repaint();

		hotCanvas.setBlendMode(BlendMode.COLOR_BURN);	// TODO 見やすく
		GraphicsContext gc = hotCanvas.getGraphicsContext2D();
		System.out.println(gc.getPixelWriter().getPixelFormat().getType());
		gc.strokeLine(100, 30, 20, 120);
		gc.setFill(new Color(0, 1, 0, 0.1));
		gc.clearRect(0, 0, 200, 200);
		gc.strokeRect(10, 10, 101, 10);

		//button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
		//button2.setOnAction(event -> System.out.println( ((Region)canvas.getParent()).getBorder() ));
	}

}