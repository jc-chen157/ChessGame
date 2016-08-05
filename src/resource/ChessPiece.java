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
	
	@Override
	public String toString(){
		String color = aColor.toString().toLowerCase();
		String type = aType.toString().toLowerCase();
		String finalType = type.substring(0, 1).toUpperCase() + type.substring(1);
		return color + finalType;
	}
	
}
