

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Database dat = new Database();
        dat.main(null);
        Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        primaryStage.setTitle("Securus");
        primaryStage.setScene(new Scene(root, 569, 239));
        primaryStage.getIcons().add(new Image("image.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
