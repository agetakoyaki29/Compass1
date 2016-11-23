package kana.compass.gui.drawScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import kana.compass.MainApp;
import kana.compass.gui.SimplePopup;
import kana.compass.gui.drawScene.menuBar.MenuBarCtrl;
import kana.compass.logic.OpCentral;
import kana.compass.logic.ScopeTransform;
import kana.compass.stage.transition.SceneCtrl;
import kana.compass.util.Util;
import kana.compass.util.strConv.ExpStrConv;
import kana.scene.control.RadioToggleButton;


public class DrawSceneCtrl extends SceneCtrl {

	@FXML private BorderPane mainPane;

	@FXML private ScrollPane canvasScrollPane;
	@FXML private Canvas canvas;
	@FXML private Canvas hotCanvas;

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	@FXML private ToggleGroup toggleGroup;
	@FXML private RadioToggleButton drawLine;
	@FXML private RadioToggleButton drawCercle;

	@FXML private Text statusText;
	@FXML private Label scopeLabel;

	private MenuBarCtrl menuBarCtrl;

	private ScopeTransform scope;
	private CanvasManager manager;
	private OpCentral central;


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

		// status pane
		scopeLabel.getStyleClass().setAll("label");

		// init logic
		manager = new CanvasManager(canvasScrollPane, canvas, hotCanvas);
		central = new OpCentral(this, manager);

		scope = manager.getScope();

		// set event handler
		drawLine.setOnAction(event ->  central.drawLine());
		drawCercle.setOnAction(event -> central.drawCercle());

		// property bind
		scopeLabel.textProperty().bind(
				Util.toStringBinding(new ExpStrConv(), scope.powerProperty().asObject()) );

		// handle event

		// first action
		central.drawLine();

		// TODO for test
		button1.setTooltip(new Tooltip("for test"));
//		button1.setOnAction(event -> MainApp.MovePage(CanvasPageController.class));
//		button2.setOnAction(event -> { center.setHotDrawn(Test.class); });
		button3.setOnAction(event -> {System.out.println(button1.getStyle());});
	}

	// ----

	@FXML
	public void onCtxItmInitScope(ActionEvent event) {
		scope.initTrans();
		manager.repaint();
	}
	@FXML
	public void onCtxItmResetScope(ActionEvent event) {
		scope.setScale(1);
		manager.repaint();
	}
	@FXML
	public void onCtxBtnPlusScope(ActionEvent event) {
		scope.appendScale(1.5);
		manager.repaint();
	}
	@FXML
	public void onCtxBtnMinusScope(ActionEvent event) {
		scope.appendScale(1/1.5);
		manager.repaint();
	}
	@FXML
	public void onTBScopeClicked(MouseEvent event) {
		// TODO
//		Control control = (Control) event.getPickResult().getIntersectedNode();
//		control.getContextMenu().show(control, Side.LEFT, 0, 0);
//		event.consume();
	}

	// ---- access to components ----

	public MenuBarCtrl getMenuBarCtrl() { return menuBarCtrl; }
	public OpCentral getActionManager() { return central; }

	// ----

	public void setStatusText(String text) {
		statusText.setText(text);
	}

	public void showSimplePopup(String text) {
		new SimplePopup(text).show(canvas);
	}

	public void setOpTB(Node opTB) {
		mainPane.setTop( opTB );
	}

}
