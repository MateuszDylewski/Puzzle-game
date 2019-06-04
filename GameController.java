package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameController {

    int cr;

    @FXML
    GridPane gameGP;



    public void initialize(){
        for(int i = 0; i < cr; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            RowConstraints row = new RowConstraints();
            gameGP.getColumnConstraints().add(column);
            gameGP.getRowConstraints().add(row);
        }

        for(int i = 0; i < cr; ++i){//540
            for(int j = 0; j < cr; ++j) {
                Button b = new Button();
                b.setMinSize(180, 180);
                gameGP.add(b, i, j);
            }
        }

    }

    public GameController(Stage s, int cr){
        this.cr = cr;

    }
}
