package gui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import logic.Note;
import main.MainApp;
import screenTransition.PageController;


public class CanvasPageController implements PageController {

	@FXML private BorderPane borderPane;
	@FXML private Canvas canvas;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	private Note note;

	@Override
	public void init() {
		MainApp.getInstance().getStage().setMaximized(true);

		resizeCanvas();

		note = new Note(canvas.getGraphicsContext2D());
		note.repaint();

		//button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
		button2.setOnAction(event -> System.out.println( ((Region)canvas.getParent()).getBorder() ));
	}

	private void resizeCanvas() {
		Region region = (Region)canvas.getParent();
		canvas.setWidth(region.getWidth());
		canvas.setHeight(region.getHeight());
	}

}
