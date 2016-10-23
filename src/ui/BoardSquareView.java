package ui;

import backend.chess.Color;
import javafx.scene.layout.HBox;

public class BoardSquareView extends HBox{
	
	int aX;
	int aY;
	Color aColor;
	
	public BoardSquareView(int i, int j, Color color){
		super();
		aX = i;
		aY = j;
		aColor = color;
	}
	
	public int getX(){
		return aY;
	}
	
	public int getY(){
		return aX;
	}
	
	public Color getColor(){
		return aColor;
	}
}
