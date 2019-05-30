package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller controller = new Controller(s2);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane, 300, 450);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        s2.setScene(scene);
        s2.show();
    }
}
