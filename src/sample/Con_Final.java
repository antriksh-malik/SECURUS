import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.*;

public class Con_Final extends JFrame {


    JTable table;

    public Con_Final(){
        setLayout(new FlowLayout());

        String[] column = {"NAME","USERNAME","PASSWORD"};
        Object[][] data = {
                {"Apurva", " Cab@27" , "qwerty101"},
                {"Tejas", "tejadon","mainHoonTeja"},
                {"Antriksh", "Space", "Kuch bhi"}
        };
        table = new JTable(data,column);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
