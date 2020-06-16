import com.github.sarxos.webcam.Webcam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class Controller extends AnchorPane {
    public AnchorPane rootPane;
    @FXML
    private TextField user_id;
    @FXML
    private PasswordField pass_word;

    static String user;

    public Controller() {
    }
    @FXML
    public void next_screen(ActionEvent actionEvent) throws Exception {
        user = user_id.getText();
        String pass = pass_word.getText();
        SecurePasswordStorage passManager = new SecurePasswordStorage();

        String sql = "SELECT user_id, pass " +
                     "FROM info";

        try (Connection conn =  DriverManager
                .getConnection("jdbc:mysql://localhost:3306/S3CURUS","root","pass123");
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                String User = rs.getString("user_id");
                String Pass = rs.getString("pass");
                passManager.signUp(User,Pass);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        boolean status = passManager.authenticateUser(user, pass);

        if (user.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fill all the Boxes Correctly !!!");
            alert.showAndWait();
        } else {
            if (status) {
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                String titleTxt = "";
                alert.setTitle(titleTxt);
                alert.setHeaderText("Access Granted");
                String s = " Verify your Face";
                alert.setContentText(s);
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType buttonType = result.orElse(ButtonType.CANCEL);
                if (buttonType == ButtonType.OK) {

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
                else {
                    alert.close();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String titleTxt = "";
                alert.setTitle(titleTxt);
                alert.setHeaderText("USER NOT FOUND");
                String s = " Try Again";
                alert.setContentText(s);
                alert.showAndWait();

            }
            }


        }
    }
