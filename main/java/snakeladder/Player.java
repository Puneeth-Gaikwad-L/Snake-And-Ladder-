package snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private Circle coin;
    private int currentPosition;
    private String name;

    private static Board board = new Board();

    public Player(int tailSize, Color coinColor, String Playername){
        coin = new Circle(tailSize/3);
        coin.setFill(coinColor);
        currentPosition =0;
        movePlayer(1);
        name = Playername;
    }

    public void movePlayer(int diceValue){
        if(currentPosition+diceValue<=100) {
            currentPosition += diceValue;
            TranslateTransition SecondMove = null, firstMove = translateAnimation(diceValue);


            int newPosition = board.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition != -1){
                currentPosition = newPosition;
                SecondMove = translateAnimation(6);
            }
            if(SecondMove==null){
                firstMove.play();
            }else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove, new PauseTransition(Duration.millis(100)),SecondMove);
                sequentialTransition.play();
            }
        }

//        int x = board.getXCoordinate(currentPosition);
//        int y = board.getYCoordinate(currentPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }

    private TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(100*diceValue), coin);
        animate.setToX(board.getXCoordinate(currentPosition));
        animate.setToY(board.getYCoordinate(currentPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    public void startingPosition(){
        currentPosition = 1;
        movePlayer(1);
    }
    boolean isWinner(){
        if(currentPosition==100){
            return true;
        }
        return false;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
