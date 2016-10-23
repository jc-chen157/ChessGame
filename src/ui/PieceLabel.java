package ui;

import backend.chess.ChessPiece;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PieceLabel extends Label{
	private boolean isSelected;
	private final ChessPiece aChessPiece;
	private BoardSquareView aCurrentGrid;
	
	public PieceLabel(ImageView pChessImage, ChessPiece pPiece, BoardSquareView pGrid){
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

	public BoardSquareView getCurrentGrid(){
		return aCurrentGrid;
	}
}
