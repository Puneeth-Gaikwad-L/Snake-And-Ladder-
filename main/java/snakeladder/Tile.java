package snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{
    
    public Tile(int tilesize){
        setWidth(tilesize);
        setHeight(tilesize);
//        setArcHeight(10);
//        setArcWidth(10);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);
    }
}
