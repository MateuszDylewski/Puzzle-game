package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class SettingsController {

    private Stage s2;
    private int choosen;

    @FXML
    Text t3x3;
    @FXML
    Text t4x4;
    @FXML
    Text t5x5;
    @FXML
    Text tCS;
    @FXML
    TextField tfR;

    SettingsController(Stage s2){
        this.s2 = s2;
        choosen = 1;
    }

    @FXML
    public void onClick3x3(){
        while(choosen != 3) {
            t3x3.setId("choosen");
            t4x4.setId("");
            t5x5.setId("");
            choosen = 3;
        }
    }
    @FXML
    public void onClick4x4(){
        while (choosen != 4) {
            t3x3.setId("");
            t4x4.setId("choosen");
            t5x5.setId("");
            choosen = 4;
        }
    }
    @FXML
    public void onClick5x5() {
        while (choosen != 5) {
            t3x3.setId("");
            t4x4.setId("");
            t5x5.setId("choosen");
            choosen = 5;
        }
    }

    @FXML
    public void onClickBack(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        SampleController controller = new SampleController(s2);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane, 300, 450);
        scene.getStylesheets().add("sample/style.css");
        s2.setScene(scene);
        s2.show();
    }

    @FXML
    public void onClickPlay() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GameController controller = new GameController(s2, choosen);
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane, 1000, 660);
        scene.getStylesheets().add("sample/style.css");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        s2.setX((screensize.getWidth()-1000)/2);
        s2.setY((screensize.getHeight()-660)/3);
        s2.setScene(scene);
        s2.show();
    }


}
