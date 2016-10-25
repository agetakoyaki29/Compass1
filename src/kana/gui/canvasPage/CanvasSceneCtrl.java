package kana.gui.canvasPage;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import kana.logic.ActionManager;
import kana.logic.CanvasListener;
import kana.logic.Paper;
import kana.main.MainApp;
import kana.sceneTransition.SceneCtrl;


public class CanvasSceneCtrl extends SceneCtrl {

	@FXML private Canvas canvas;
	@FXML private Canvas hotCanvas;

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	private MenuBarCtrl menuBarCtrl;

	private CanvasListener listener;

	private Paper paper;
	private ActionManager manager;	// TODO

	@Override
	public void init() {
		// init gui
		MainApp.getInstance().getStage().setMaximized(true);

		VBox root = (VBox) getRoot();
		menuBarCtrl = new MenuBarCtrl(this);
		root.getChildren().add(0, menuBarCtrl.getRoot());

		canvas.setWidth(800);
		canvas.setHeight(800);
		hotCanvas.setWidth(canvas.getWidth());
		hotCanvas.setHeight(canvas.getHeight());

		listener = new CanvasListener(hotCanvas);

		// init logic
		paper = new Paper(canvas.getGraphicsContext2D());
		paper.repaint();

		manager = new ActionManager();

		// TODO for test
		hotCanvas.setBlendMode(BlendMode.COLOR_BURN);
		GraphicsContext gc = hotCanvas.getGraphicsContext2D();

		gc.strokeLine(100, 30, 20, 120);
		gc.setFill(new Color(0, 1, 0, 0.1));
		gc.clearRect(0, 0, 200, 200);
		gc.strokeRect(10, 10, 101, 10);

		//button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
		//button2.setOnAction(event -> System.out.println( ((Region)canvas.getParent()).getBorder() ));
	}

	public MenuBarCtrl getMenuBarCtrl() { return menuBarCtrl; }
	public Paper getPaper() { return paper; }
	public ActionManager getActionManager() { return manager; }

}
