package resource;

public class ChessPiece {
	
	private Color aColor;
	private PieceType aType;
	
	public ChessPiece(Color pColor, PieceType pType){
		aColor = pColor;
		aType = pType;
	}
	
	public Color getColor(){
		return aColor;
	}
	
	public PieceType getType(){
		return aType;
	}
	
}
