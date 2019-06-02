package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {

    Stage s1;

    SampleController(Stage s1){
        this.s1 = s1;
    }

    @FXML
    public void onClickStart(){
        System.out.println("W1BCLICK");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        SettingsController controller = new SettingsController(s1);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane, 300, 450);
        scene.getStylesheets().add("sample/style.css");
        s1.setScene(scene);
        s1.show();
    }
    @FXML
    Text t1;
    @FXML
    public void onDrag(){

    }

    @FXML
    public void onClickEndGame(){
        Platform.exit();
    }
}
