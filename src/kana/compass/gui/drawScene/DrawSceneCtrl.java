package kana.compass.gui.drawScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kana.compass.canvas.ScopeTransform;
import kana.compass.canvas.TwinCanvasCtrl;
import kana.compass.gui.SimplePopup;
import kana.compass.gui.drawScene.menuBar.MenuBarCtrl;
import kana.compass.logic.OpCentral;
import kana.compass.stage.transition.SceneCtrl;
import kana.compass.util.Util;
import kana.compass.util.strConv.ExpStrConv;
import kana.scene.control.RadioToggleButton;
import lombok.Getter;


public class DrawSceneCtrl extends SceneCtrl {

	@FXML private BorderPane mainPane;

	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;

	@FXML private ToggleGroup toggleGroup;
	@FXML private RadioToggleButton drawLine;
	@FXML private RadioToggleButton drawCercle;

	@FXML private Text statusText;
	@FXML private Label scopeLabel;

	private TwinCanvasCtrl twinCanvasCtrl;
	private ScopeTransform scope;
	
	@Override
	public BorderPane getRoot() {
		return (BorderPane) super.getRoot();
	}

	@Override
	public void init() {
		MenuBarCtrl menuBarCtrl;
		OpCentral central;
		
		// init gui
		Stage stage = getStage();
		stage.setWidth(800);
		stage.setHeight(600);
		stage.centerOnScreen();
		stage.setMaximized(true);

		BorderPane root = getRoot();
		
		// set menu
		menuBarCtrl = new MenuBarCtrl();
		root.setTop(menuBarCtrl.getRoot());
		
		// set twin canvas
		twinCanvasCtrl = new TwinCanvasCtrl();
		mainPane.setCenter(twinCanvasCtrl.getRoot());

		// status pane
		scopeLabel.getStyleClass().setAll("label");

		// init logic
		central = new OpCentral(this, twinCanvasCtrl);

		scope = twinCanvasCtrl.getScope();

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
		twinCanvasCtrl.repaint();
	}
	@FXML
	public void onCtxItmResetScope(ActionEvent event) {
		scope.setScale(1);
		twinCanvasCtrl.repaint();
	}
	@FXML
	public void onCtxBtnPlusScope(ActionEvent event) {
		scope.appendScale(1.5);
		twinCanvasCtrl.repaint();
	}
	@FXML
	public void onCtxBtnMinusScope(ActionEvent event) {
		scope.appendScale(1/1.5);
		twinCanvasCtrl.repaint();
	}
	@FXML
	public void onTBScopeClicked(MouseEvent event) {
		// TODO
//		Control control = (Control) event.getPickResult().getIntersectedNode();
//		control.getContextMenu().show(control, Side.LEFT, 0, 0);
//		event.consume();
	}

	// ----

	public void setStatusText(String text) {
		statusText.setText(text);
	}

	public void showSimplePopup(String text) {
		new SimplePopup(text).show(twinCanvasCtrl.getRoot());
	}

	public void setOpTB(Node opTB) {
		mainPane.setTop( opTB );
	}

}
