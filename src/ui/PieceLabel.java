package ui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import resource.ChessPiece;

public class PieceLabel extends Label{
	private boolean isSelected;
	private final ChessPiece aChessPiece;
	
	public PieceLabel(ImageView pChessImage, ChessPiece pPiece){
		super("", pChessImage);
		aChessPiece = pPiece;
		isSelected = false;
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
}
