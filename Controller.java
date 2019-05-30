package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Controller {

    @FXML
    ComboBox<String> cBoxSizeOfScene;

    Stage s1;

    @FXML
    void initialize(){
        cBoxSizeOfScene.getItems().add("4098x2160");
        cBoxSizeOfScene.getItems().add("1920x1080");

        System.out.println("asd");
        cBoxSizeOfScene.getSelectionModel().selectFirst();
    }

    Controller(Stage s1){
        this.s1 = s1;
    }

    @FXML
    public void onClickStart(){
        System.out.println("W1BCLICK");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        Controller2 controller = new Controller2(s1);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s1.setScene(new javafx.scene.Scene(pane, 300, 450));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        /*s1.setY((d.getHeight()-675)/2);
        s1.setX((d.getWidth()-1200)/2);*/
        s1.show();
    }

    @FXML
    public void onClickEndGame(){
        Platform.exit();
    }
}
