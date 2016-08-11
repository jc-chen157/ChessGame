package ui;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import resource.ChessPiece;

public class PieceLabel extends Label{
	private boolean isSelected;
	private final ChessPiece aChessPiece;
	private GridView aCurrentGrid;
	
	public PieceLabel(ImageView pChessImage, ChessPiece pPiece, GridView pGrid){
		super("", pChessImage);
		aChessPiece = pPiece;
		isSelected = false;
		aCurrentGrid = pGrid;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void select(boolean pBool){
		isSelected = pBool;
	}
	
	public ChessPiece getChessPiece(){
		return aChessPiece;
	}

	public GridView getCurrentGrid(){
		return aCurrentGrid;
	}

	public void updatePosition(GridView pGrid){
		aCurrentGrid = pGrid;
	}

    private void setPositionForChessPiece(){
        aChessPiece.setPosition(aCurrentGrid.getX(), aCurrentGrid.getY());
    }
}
