package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Controller2 {

    Stage s2;

    @FXML
    Button b;

    Controller2(Stage s2){
        this.s2 = s2;
    }

    @FXML
    public void onClick(){
        System.out.println("W2BCLICK");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("widok1.fxml"));
        Controller controller = new Controller(s2);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s2.setTitle("WIDOK2");
        s2.setScene(new javafx.scene.Scene(pane, 1200, 675));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        s2.setY((d.getHeight()-675)/2);
        s2.setX((d.getWidth()-1200)/2);
        s2.show();
    }
}
