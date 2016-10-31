package kana.compass.gui.canvasPage;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kana.compass.MainApp;
import kana.compass.drawn.hot.Test;
import kana.compass.gui.SimplePopup;
import kana.compass.gui.canvasPage.actToolBar.ActToolBarCtrl;
import kana.compass.gui.menuBar.MenuBarCtrl;
import kana.compass.logic.ActionManager;
import kana.compass.logic.CanvasManager;
import kana.compass.stage.transition.SceneCtrl;
import kana.scene.control.RadioToggleButton;


public class CanvasSceneCtrl extends SceneCtrl {

	@FXML private BorderPane mainPane;

	@FXML private Canvas canvas;
	@FXML private Canvas hotCanvas;

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	@FXML private ToggleGroup toggleGroup;
	@FXML private RadioToggleButton drawLine;
	@FXML private RadioToggleButton drawCercle;

	@FXML private Text statusText;

	private MenuBarCtrl menuBarCtrl;

	private CanvasManager cm;
	private ActionManager manager;

	private CanvasMouseHandler handler;


	@Override
	public void init() {
		// init gui
		Stage stage = MainApp.getInstance().getStage();
		stage.setWidth(800);
		stage.setHeight(600);
		stage.centerOnScreen();
		stage.setMaximized(true);

		BorderPane root = (BorderPane) getRoot();
		menuBarCtrl = new MenuBarCtrl(this);
//		root.getChildren().add(0, menuBarCtrl.getRoot());
		root.setTop(menuBarCtrl.getRoot());

		// canvas full screen
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		canvas.setWidth(screen.getWidth());
		canvas.setHeight(screen.getHeight());
		hotCanvas.setWidth(canvas.getWidth());
		hotCanvas.setHeight(canvas.getHeight());

		// init logic
		cm = new CanvasManager(canvas, hotCanvas);

		// init handler
		handler = new CanvasMouseHandler(hotCanvas);

		// init manager
		manager = new ActionManager(this, handler, cm);

		// set event handler
		drawLine.setOnAction(event ->  manager.drawLine());
		drawCercle.setOnAction(event -> manager.drawCercle());

		// first action
		manager.drawLine();

		// TODO for test
		button1.setTooltip(new Tooltip("for test"));
//		button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
		button2.setOnAction(event -> { manager.setHotDrawn(Test.class); });
		button3.setOnAction(event -> drawCercle.setSelected(true));
	}

	// ---- access to components ----

	public MenuBarCtrl getMenuBarCtrl() { return menuBarCtrl; }
	public ActionManager getActionManager() { return manager; }

	// ----

	public void setStatusText(String text) {
		statusText.setText(text);
	}

	public void showSimplePopup(String text) {
		new SimplePopup(text).show(canvas);
	}

	public void setActToolBar(ActToolBarCtrl actToolBarCtrl) {
		mainPane.setTop( actToolBarCtrl.getRoot() );
	}

}
