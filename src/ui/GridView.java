package ui;

import javafx.scene.layout.HBox;
import resource.Color;

public class GridView extends HBox{
	
	int aX;
	int aY;
	Color aColor;
	
	public GridView(int i, int j, Color color){
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
