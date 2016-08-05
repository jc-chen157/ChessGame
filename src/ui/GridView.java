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
		return aX;
	}
	
	public int getY(){
		return aY;
	}
	
	public Color getColor(){
		return aColor;
	}
}
