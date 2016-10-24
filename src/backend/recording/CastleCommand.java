package backend.recording;

import backend.chess.ChessPiece;
import backend.chess.Color;
import backend.rules.CastleType;
import middleware.engine.GameModel;

public class CastleCommand implements Command{

	private ChessPiece aKingPiece;
	private ChessPiece aRookPiece;
	private CastleType aType;

	public CastleCommand(ChessPiece pKing, CastleType pType){
		if(pType == CastleType.LONG){
			if(pKing.getColor() == Color.WHITE){
				aKingPiece = pKing;
				aRookPiece = GameModel.getInstance().getChessPiece(7, 0); 
				aType = pType;
			}else{
				aKingPiece = pKing;
				aRookPiece = GameModel.getInstance().getChessPiece(0, 0); 
				aType = pType;
			}
		}else{
			if(pKing.getColor() == Color.WHITE){
				aKingPiece = pKing;
				aRookPiece = GameModel.getInstance().getChessPiece(7, 7); 
				aType = pType;
			}else{
				aKingPiece = pKing;
				aRookPiece = GameModel.getInstance().getChessPiece(0, 7); 
				aType = pType;
			}
		}
	}
	
	@Override
	public void execute() {
		if(aType == CastleType.LONG){
			if(aKingPiece.getColor() == Color.WHITE){
				GameModel.getInstance().removeChessPiece(aKingPiece.getX(), aKingPiece.getY());
				GameModel.getInstance().removeChessPiece(7, 0);
				GameModel.getInstance().addChessPiece(aKingPiece, 7, 2);
				GameModel.getInstance().addChessPiece(aRookPiece, 7, 3);
				aKingPiece.setPosition(7, 2);
				aKingPiece.moved();
				aRookPiece.setPosition(7, 3);
				aRookPiece.moved();
			}else{
				GameModel.getInstance().removeChessPiece(aKingPiece.getX(), aKingPiece.getY());
				GameModel.getInstance().removeChessPiece(0, 0);
				GameModel.getInstance().addChessPiece(aKingPiece, 0, 2);
				GameModel.getInstance().addChessPiece(aRookPiece, 0, 3);
				aKingPiece.setPosition(0, 2);
				aKingPiece.moved();
				aRookPiece.setPosition(0, 3);
				aRookPiece.moved();
			}
		}else{
			if(aKingPiece.getColor() == Color.WHITE){
				GameModel.getInstance().removeChessPiece(aKingPiece.getX(), aKingPiece.getY());
				GameModel.getInstance().removeChessPiece(7, 7);
				GameModel.getInstance().addChessPiece(aKingPiece, 7, 6);
				GameModel.getInstance().addChessPiece(aRookPiece, 7, 5);
				aKingPiece.setPosition(7, 6);
				aKingPiece.moved();
				aRookPiece.setPosition(7, 5);
				aRookPiece.moved();
			}else{
				GameModel.getInstance().removeChessPiece(aKingPiece.getX(), aKingPiece.getY());
				GameModel.getInstance().removeChessPiece(0, 7);
				GameModel.getInstance().addChessPiece(aKingPiece, 0, 6);
				GameModel.getInstance().addChessPiece(aRookPiece, 0, 5);
				aKingPiece.setPosition(0, 6);
				aKingPiece.moved();
				aRookPiece.setPosition(0, 5);
				aRookPiece.moved();
			}
		}
	}

	@Override
	public void undo() {
		if(aType == CastleType.LONG){
			if(aKingPiece.getColor() == Color.WHITE){
				GameModel.getInstance().removeChessPiece(7, 3);
				GameModel.getInstance().removeChessPiece(7, 2);
				GameModel.getInstance().addChessPiece(aKingPiece, 7, 4);
				GameModel.getInstance().addChessPiece(aRookPiece, 7, 0);
				aKingPiece.setPosition(7, 5);
				aKingPiece.moved();
				aRookPiece.setPosition(7, 0);
				aRookPiece.moved();
			}else{
				GameModel.getInstance().removeChessPiece(0, 3);
				GameModel.getInstance().removeChessPiece(0, 2);
				GameModel.getInstance().addChessPiece(aKingPiece, 0, 4);
				GameModel.getInstance().addChessPiece(aRookPiece, 0, 0);
				aKingPiece.setPosition(0, 5);
				aKingPiece.moved();
				aRookPiece.setPosition(0, 0);
				aRookPiece.moved();
			}
		}else{
			if(aKingPiece.getColor() == Color.WHITE){
				GameModel.getInstance().removeChessPiece(7, 6);
				GameModel.getInstance().removeChessPiece(7, 5);
				GameModel.getInstance().addChessPiece(aKingPiece, 7, 4);
				GameModel.getInstance().addChessPiece(aRookPiece, 7, 7);
				aKingPiece.setPosition(7, 4);
				aKingPiece.moved();
				aRookPiece.setPosition(7, 7);
				aRookPiece.moved();
			}else{
				GameModel.getInstance().removeChessPiece(0, 6);
				GameModel.getInstance().removeChessPiece(0, 5);
				GameModel.getInstance().addChessPiece(aKingPiece, 0, 4);
				GameModel.getInstance().addChessPiece(aRookPiece, 0, 7);
				aKingPiece.setPosition(0, 4);
				aKingPiece.moved();
				aRookPiece.setPosition(0, 7);
				aRookPiece.moved();
			}
		}
	}

	@Override
	public Color getColor() {
		return aKingPiece.getColor();
	}
	
	@Override 
	public String toString() {
		if(aType == CastleType.SHORT) {
			return "0-0";
		}else{
			return "0-0-0";
		}
	}

}
