package kana.gui.canvasPage;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.VBox;
import kana.logic.ActionManager;
import kana.logic.HotPaper;
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
	private HotPaper hotPaper;
	private ActionManager manager;

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

		// init handler
		listener = new CanvasListener(hotCanvas);

		// init logic
		paper = new Paper(canvas.getGraphicsContext2D());
		paper.repaint();

		hotCanvas.setBlendMode(BlendMode.COLOR_BURN);
		hotPaper = new HotPaper(hotCanvas.getGraphicsContext2D());
		hotPaper.repaint();

		// init manager
		manager = new ActionManager(listener, paper, hotPaper);
		manager.tempCercle();

		// TODO for test
//		button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
		//button2.setOnAction(event -> System.out.println( ((Region)canvas.getParent()).getBorder() ));
		button3.setOnAction(event -> manager.callRepaint());
	}

	public MenuBarCtrl getMenuBarCtrl() { return menuBarCtrl; }
	public Paper getPaper() { return paper; }
	public ActionManager getActionManager() { return manager; }

}
