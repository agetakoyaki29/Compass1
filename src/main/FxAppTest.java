package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FxAppTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("FXMLﾃｽﾄ");

        Pane root = FXMLLoader.load(getClass().getResource("test1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public static void play() {
    	launch();
    }

}
