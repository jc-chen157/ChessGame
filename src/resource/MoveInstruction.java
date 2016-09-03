package resource;

public class MoveInstruction {
	
	private ChessPiece aSelectedPiece;
	private int targetX;
	private int targetY;
	
	public MoveInstruction(ChessPiece pPiece, int pX, int pY){
		aSelectedPiece = pPiece;
		targetX = pX;
		targetY = pY;
	}

}
