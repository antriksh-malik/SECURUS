
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Controller3 {


    static String ans;
    static String q;
    static String name;
    String user = Controller.user;
    static Parent root;

    {
        String sql = "SELECT nam, user_id, statement, answer " +
                "FROM info";

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/S3CURUS", "root", "pass123");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                String User = rs.getString("user_id");
                String state = rs.getString("statement");
                String answer = rs.getString("answer");
                String naam = rs.getString("nam");

                if(User.equals(user)){
                    q = state;
                    ans = answer;
                    name = naam;

                    break;
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public Label q_box ;

    public Controller3() throws SQLException {
    }

    @FXML
    private void initialize() {
        q_box.setText(q);
    };

    @FXML
    public void conf(ActionEvent actionEvent) throws Exception {
        final JavaSoundRecorder recorder = new JavaSoundRecorder();

        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            recorder.finish();
        });

        stopper.start();
        // start recording
        recorder.start();
        System.out.println(name);

        complete_speech speaker = new complete_speech();
        //speaker.speech();
        if(speaker.speech()){
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            Con_Final conFinal = new Con_Final();
            conFinal.setDefaultCloseOperation(EXIT_ON_CLOSE);
            conFinal.setSize(600,200);
            conFinal.setVisible(true);
            conFinal.setTitle("S3CURUS");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Wrong Person");
            alert.showAndWait();
            System.exit(0);
        }

    }
}

