package sample;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GameController {

    private int cr;
    private ImageView blackSpot;
    private ArrayList<ImageView> solvedPuzzle;
    private ArrayList<ImageView> currentGameBoard;
    private int moves = 0;
    private ArrayList<String> lastTimes = new ArrayList<>();


    @FXML
    public Text timer;

    @FXML
    public GridPane gameGP;

    @FXML
    public ScrollPane sp;

    @FXML
    public VBox vb;

    String timeToString(int min, int sec){
        String result = "";
        if(min < 10) {
            result += "0";
        }
        result += min + ":";
        if(sec < 10){
            result += "0";
        }
        result += sec;
        return result;
    }

    Thread time = new Thread(()->{
        String currentTime;
        int sec = 0, min = 0;
        while (true){
            if(sec <59){
                sec++;
            }else{
                min++;
                sec=0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                System.out.println(ex);
            }
            currentTime = timeToString(min, sec);
            timer.setText(currentTime);
        }
    });

    public void initialize() throws IOException{
        for(int i = 0; i < lastTimes.size(); ++i) {
            vb.getChildren().add(new Text(lastTimes.get(i)));
        }
        timer.setText("00:00");
        for(int i = 0; i < cr; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(600.0/cr);
            RowConstraints row = new RowConstraints();
            row.setMinHeight(600.0/cr);
            gameGP.getColumnConstraints().add(column);
            gameGP.getRowConstraints().add(row);
        }
        String path = "src\\img\\";
        path += String.valueOf((int)(Math.random()*3)) + ".jpg";


        BufferedImage bi = ImageIO.read(new File(path));
        currentGameBoard =  new ArrayList<>();

        for(int i = 0; i < cr; ++i){
            for(int j = 0; j < cr; ++j){
                currentGameBoard.add(new ImageView(SwingFXUtils.toFXImage(
                        bi.getSubimage(600/cr*i, 600/cr*j, 600/cr, 600/cr), null)));
                currentGameBoard.get(i*cr + j).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    try {
                        move(event);
                    }catch (IOException ex){
                        System.out.println(ex);
                    }
                });
                currentGameBoard.get(i*cr + j).setId(i*cr+j + "");
            }
        }

        bi = ImageIO.read(new File("src\\img\\black.jpg"));
        blackSpot = new ImageView(SwingFXUtils.toFXImage(
                bi.getSubimage(0, 0, 600/cr, 600/cr), null));
        blackSpot.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                move(event);
            }catch (IOException ex){
                System.out.println(ex);
            }
        });
        blackSpot.setId(cr*cr-1 + "");
        currentGameBoard.remove(cr*cr-1);
        currentGameBoard.add(blackSpot);
        //Collections.shuffle(currentGameBoard);
        solvedPuzzle = currentGameBoard;

        int i = 0, j = 0;
        for(ImageView iv : currentGameBoard){
            gameGP.add(iv, i, j);
            if(j < cr-1){
                j++;
            } else {
                j = 0;
                ++i;
            }
        }
    }

    public boolean canBeMoved(ImageView clickedImg, ImageView bS){
        int clickedImgRow  = gameGP.getRowIndex(clickedImg);
        int clickedImgColumn  = gameGP.getColumnIndex(clickedImg);
        int blankRow = gameGP.getRowIndex(bS);
        int blankColumn = gameGP.getColumnIndex(bS);
        if(Math.abs(blankColumn - clickedImgColumn) == 1 && Math.abs(blankRow - clickedImgRow) == 0
                || Math.abs(blankRow - clickedImgRow) == 1 && Math.abs(blankColumn - clickedImgColumn) == 0) {
            return true;
        }
        return false;
    }

    public boolean isSolved(){

        ObservableList<Node> childrens = gameGP.getChildren();

        int i = 0;
        String nodeId;
        for (Node node : childrens) {
            if(Integer.parseInt(node.getId()) != i){
                return false;
            }
            ++i;
        }
        return true;
    }

    public void move(MouseEvent event)throws IOException{
        if(moves == 0){
            time.start();
        }
        moves++;
        ImageView clickedImg = (ImageView)(event.getSource());
        if(blackSpot == clickedImg){
            return;
        }

        ImageView tmp = new ImageView();
        if(canBeMoved(clickedImg, blackSpot)){

            tmp.setImage(clickedImg.getImage());
            clickedImg.setImage(blackSpot.getImage());
            blackSpot.setImage(tmp.getImage());

            tmp.setId(clickedImg.getId());
            clickedImg.setId(blackSpot.getId());
            blackSpot.setId(tmp.getId());

            tmp = clickedImg;
            clickedImg = blackSpot;
            blackSpot = tmp;

            if(isSolved()){
                endGame();
            }
        }
    }

    void getLastTimes(String path) throws IOException{
        FileReader fr = new FileReader(path);
        int pom;
        String file = "";
        while((pom = fr.read()) != -1){
            file += (char)(pom);
        }
        String[] arr = file.split("\n", 0);
        for(int i = 0; i < arr.length; ++i){
            lastTimes.add(arr[i]);
        }
    }

    void saveLastTimes(String path) throws IOException{
        FileWriter fw = new FileWriter(path);
        for(int i = 0; i < lastTimes.size(); ++i){
            fw.write(lastTimes.get(i) + "\n");
        }
        fw.flush();
    }

    public GameController(Stage s, int cr)throws IOException{
        this.cr = cr;
        getLastTimes("C:\\Users\\mateu\\Desktop\\Studia\\GUI\\Puzzle\\src\\sample\\lastTimes.txt");
    }

    public void endGame() throws IOException{
        time.stop();
        lastTimes.add(timer.getText());
        saveLastTimes("C:\\Users\\mateu\\Desktop\\Studia\\GUI\\Puzzle\\src\\sample\\lastTimes.txt");
        vb.getChildren().add(new Text(timer.getText()));

    }
}