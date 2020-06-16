import com.github.sarxos.webcam.Webcam;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.commons.vfs2.FileSystemException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class Controller2 {


    public void nxt_screen(ActionEvent actionEvent) throws FileSystemException {

        ImagProj imagProj = new ImagProj();
        if(imagProj.Imag()>0.80){
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            String titleTxt = "";
            alert.setTitle(titleTxt);
            alert.setHeaderText("Face Verified");
            String s = " Verify your Voice ";
            alert.setContentText(s);
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.CANCEL);
            if (buttonType == ButtonType.OK) {

                Parent root3 = null;
                try {
                    root3 = FXMLLoader.load(getClass().getResource("/sample/sample3.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();

                }

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setTitle("Securus");
                window.setScene(new Scene(root3, 600, 500));
                window.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Person");
            alert.setHeaderText("Face not Recognized");
            alert.showAndWait();
            System.exit(0);
        }
    }

    public void retry(ActionEvent actionEvent) throws FileNotFoundException {

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Stay Still");
        alert1.setHeaderText("Face camera and Stay Still for 3 sec");
        alert1.showAndWait();

        Webcam webcam1 = Webcam.getDefault();
        webcam1.open();
        try {
            ImageIO.write(webcam1.getImage(), "PNG", new File("Securus.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }

        webcam1.close();

        Parent root2 = null;
        try {
            root2 = FXMLLoader.load(getClass().getResource("/sample/sample2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();

        }

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Securus");
        Image image = new Image(new FileInputStream("Securus.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView, root2);
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }
}
