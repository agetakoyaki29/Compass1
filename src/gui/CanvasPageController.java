package gui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import main.MainApp;
import screenTransition.PageController;


public class CanvasPageController implements PageController {

	@FXML private Canvas canvas;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	private GraphicsContext gc2;

	@Override
	public void init() {
		MainApp.getInstance().get().getStage().setMaximized(true);

		gc2 = canvas.getGraphicsContext2D();

		resizeCanvas();

		gc2.setFill(Color.WHITE);
		gc2.setStroke(Color.BLACK);
		gc2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc2.strokeLine(10, 10, 200, 200);

		//button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
		//button2.setOnAction(event -> System.out.println(canvas.getWidth() + "," + canvas.getHeight()));
	}

	private void resizeCanvas() {
		Region region = (Region)canvas.getParent();
		canvas.setWidth(region.getWidth());
		canvas.setHeight(region.getHeight());
	}

}
