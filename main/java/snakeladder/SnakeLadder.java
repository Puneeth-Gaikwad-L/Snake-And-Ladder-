package snakeladder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class SnakeLadder extends Application {

    private static Scene scene;
    public static int tailsize = 40, width = 10, height = 10;
    public static final int buttonLine = height*tailsize+30, infoLine = buttonLine-30;

    private static Dice dice2 = new Dice();
    private Player PlayerOne, PlayerTwo;

    private Boolean StartGame = false, PlayeOneTurn = false, PlayerTwoTurn = false;

    private Pane CreateContent(){
        Pane root = new Pane();
        root.setPrefSize(tailsize*width, height*tailsize + 100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tailsize);
                tile.setTranslateX(j*tailsize);
                tile.setTranslateY(i*tailsize);
                root.getChildren().add(tile);
            }
        }

        FileInputStream file = null;
        try {
            file = new FileInputStream("D:\\vs code\\vs- code\\java_snake_ladder\\src\\main\\image.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image img = new Image(file);
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitWidth(width*tailsize);
        board.setFitHeight(height*tailsize);

        // Adding the buttons
        // Initialising the buttons
        Button player1 = new Button("Player 1");
        Button player2 = new Button("Player 2");
        Button start = new Button("Start");

        // placing the buttons
        player1.setTranslateY(buttonLine);
        player1.setTranslateX(40);
        player1.setDisable(true);
        player2.setTranslateY(buttonLine);
        player2.setTranslateX(320);
        player2.setDisable(true);
        start.setTranslateY(buttonLine);
        start.setTranslateX(185);

        //        initialising the label

        Label playerOne = new Label("");
        Label playerTwo = new Label("");
        Label dice = new Label("Start the Game");

        playerOne.setTranslateY(infoLine);
        playerOne.setTranslateX(40);
        playerTwo.setTranslateY(infoLine);
        playerTwo.setTranslateX(300);
        dice.setTranslateY(infoLine);
        dice.setTranslateX(150);

        PlayerOne = new Player(tailsize, Color.BLACK, "Puneeth");

        PlayerTwo = new Player(tailsize-5, Color.DARKBLUE, "Suprith");

        player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(StartGame){
                    if(PlayeOneTurn){
                        int diceValue = dice2.getRolledDice();
                        dice.setText("Dice Value\n" + "    "+ diceValue);
                        PlayerOne.movePlayer(diceValue);

                        if(PlayerOne.isWinner()) {
                            dice.setText(PlayerOne.getName() + "is the Winner");
                            PlayeOneTurn = false;
                            player1.setDisable(true);
                            playerOne.setText("");
                            PlayerTwoTurn = true;
                            player2.setDisable(true);
                            playerTwo.setText("");

                            start.setDisable(false);
                            start.setText("Restart");
                            StartGame=false;

                        }else{
                            PlayeOneTurn = false;
                            player1.setDisable(true);
                            playerOne.setText("");

                            PlayerTwoTurn = true;
                            player2.setDisable(false);
                            playerTwo.setText("Your Turn "+PlayerTwo.getName());
                        }


                    }
                }

            }
        });

        player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(StartGame){
                    if(PlayerTwoTurn){
                        int diceValue = dice2.getRolledDice();
                        dice.setText("Dice Value\n" + "    "+ diceValue);
                        PlayerTwo.movePlayer(diceValue);

                        if(PlayerTwo.isWinner()){
                            dice.setText(PlayerTwo.getName()+"is the Winner");
                            PlayeOneTurn = false;
                            player1.setDisable(true);
                            playerOne.setText("");
                            PlayerTwoTurn = true;
                            player2.setDisable(true);
                            playerTwo.setText("");

                            start.setDisable(false);
                            start.setText("Restart");
                            StartGame=false;
                        }else{
                            PlayeOneTurn = true;
                            player1.setDisable(false);
                            playerOne.setText("Your Turn "+PlayerOne.getName());

                            PlayerTwoTurn = true;
                            player2.setDisable(true);
                            playerTwo.setText("");
                        }


                    }
                }

            }
        });

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartGame = true;
                dice.setText("Game Started");
                start.setDisable(true);
                PlayeOneTurn = true;
                playerOne.setText("Your Turn "+ PlayerOne.getName());
                player1.setDisable(false);
                PlayerOne.startingPosition();
                PlayerTwoTurn = false;
                playerTwo.setText("");
                player2.setDisable(true);
                PlayerTwo.startingPosition();
            }
        });


        root.getChildren().addAll(board,  player1, player2, start,
                playerOne, playerTwo, dice, PlayerOne.getCoin(), PlayerTwo.getCoin()
                );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(CreateContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}