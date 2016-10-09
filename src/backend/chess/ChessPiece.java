package backend.chess;

public class ChessPiece {
	
	private Color aColor;
	private PieceType aType;
	private int aX;
    private int aY;
    private boolean hasMoved;

	public ChessPiece(Color pColor, PieceType pType){
		aColor = pColor;
		aType = pType;
		hasMoved = false;
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

    public void setPosition(int pX, int pY){
        aX = pX;
        aY = pY;
    }

    public int getX(){
        return aX;
    }

    public int getY(){
        return aY;
    }
    
    /**
     * Only Invoke this method if king has Moved.
     * @return
     */
    public boolean hasMoved(){
    	return hasMoved;
    }
    
    public void moved(){
    	hasMoved = true;
    }
	
}
