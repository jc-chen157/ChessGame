package backend.recording;

import app.middleware.GameModel;
import backend.chess.ChessPiece;
import backend.chess.PieceType;

public class MoveCommand{

	
	private ChessPiece aSelectPiece;
	@SuppressWarnings("unused")
	private ChessPiece aEatenPiece;
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;
	
	public MoveCommand(ChessPiece pPiece, int pNewX, int pNewY) {
		aSelectPiece = pPiece;
		oldX = pPiece.getX();
		oldY = pPiece.getY();
		newX = pNewX;
		newY = pNewY;
		aEatenPiece = GameModel.getInstance().getChessPiece(pNewX, newY);
	}
	
	public void execute(){
		GameModel.getInstance().removeChessPiece(oldX, oldY);
		aSelectPiece.setPosition(newX, newY);
		GameModel.getInstance().addChessPiece(aSelectPiece, newX, newY);
   		aSelectPiece.moved();
   		if(aSelectPiece.getType() == PieceType.PAWN && 
   				(newX == 0 || newX == 7)){
   			GameModel.getInstance().removeChessPiece(newX, newY);
       		GameModel.getInstance().addChessPiece(
       				new ChessPiece(aSelectPiece.getColor(), PieceType.QUEEN), newX, newY);
       		GameModel.getInstance().getChessPiece(newX, newY).
       		setPosition(newX, newY);
   		}
	}
	
	//TODO: to be done later
	public void undo(){
		
	}
}
