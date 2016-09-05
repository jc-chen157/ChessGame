package gameModel;

import gameResource.ChessPiece;
import gameResource.Color;
import gameResource.PieceType;
import gameUI.GridView;

/**
 * A static Class that verify if a move is legal.
 * Created by JiajunChen on 16/8/9.
 */
public class RuleBook {
	
    public static boolean isValidMove(ChessPiece pPiece, GridView pGrid){
    	boolean isValid = false;
    	switch(pPiece.getType()){
    		case PAWN:
    			isValid = isValidPawnMove(pPiece, pGrid);
    			break;
    		case ROOK:
    			isValid = isValidRookMove(pPiece, pGrid);
    			break;
    		case KNIGHT:
    			isValid = isValidKnightMove(pPiece, pGrid);
    			break;
    		case BISHOP:
    			isValid = isValidBishopMove(pPiece, pGrid);
    			break;	
    		case QUEEN:
    			isValid = isValidQueenMove(pPiece, pGrid);
    			break;	
    		case KING:
    			isValid = isValidKingMove(pPiece, pGrid);
    			if(isCastleValid(pPiece, pGrid)) return true;
    			break;	
    		default: isValid = false;
    			break;
    	}
    	if(isValid){
   			GameModel.getInstance().removeChessPiece(pPiece.getX(), pPiece.getY());
       		GameModel.getInstance().addChessPiece(pPiece, pGrid.getX(), pGrid.getY());
       		pPiece.setPosition(pGrid.getX(), pGrid.getY());	
       		pPiece.moved();
       		if(pPiece.getType() == PieceType.PAWN && 
       				(pGrid.getX() == 0 || pGrid.getX() == 7)){
       			GameModel.getInstance().removeChessPiece(pPiece.getX(), pPiece.getY());
           		GameModel.getInstance().addChessPiece(
           				new ChessPiece(pPiece.getColor(), PieceType.QUEEN), pGrid.getX(), pGrid.getY());
           		GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()).
           		setPosition(pGrid.getX(), pGrid.getY());
       		}
    	}
    	return isValid;
    }
    
    /*
     * Verify if a pawn move is legal.
     */
    private static boolean isValidPawnMove(ChessPiece pPiece, GridView pGrid){
    	if(pPiece.getColor() == Color.BLACK){
    		// empty grid move first
    		if(GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
    			// normal case
    			if(pPiece.getY() == pGrid.getY() && pPiece.getX() + 1 == pGrid.getX()){
        			return true;
        		}else if(pPiece.getY() == pGrid.getY() && pPiece.getX() == 1 ){
        			return (pPiece.getX() + 2 == pGrid.getX() || pPiece.getX() + 1 == pGrid.getX()) && 
        					GameModel.getInstance().getChessPiece(pPiece.getX() + 1, pPiece.getY()) == null;
        		}
    		// taking opponenet's piece
    		}else{
    			if(pPiece.getX() + 1 == pGrid.getX() && 
        				(pPiece.getY() + 1 == pGrid.getY() || pPiece.getY() - 1 == pGrid.getY())){
        			return GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()).getColor() == Color.WHITE;
        		}
    		}
    	}else{
    		// empty grid move first
    		if(GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
    			// normal case
    			if(pPiece.getY() == pGrid.getY() && pPiece.getX() - 1 == pGrid.getX()){
        			return true;
        		}else if(pPiece.getY() == pGrid.getY() && pPiece.getX() == 6 ){
        			return pPiece.getX() - 2 == pGrid.getX() || pPiece.getX() - 1 == pGrid.getX();
        		}
    		// taking opponenet's piece
    		}else{
    			if(pPiece.getX() - 1 == pGrid.getX() && 
        				(pPiece.getY() + 1 == pGrid.getY() || pPiece.getY() - 1 == pGrid.getY())){
        			return GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()).getColor() == Color.BLACK;
        		}
    		}
    	}
		return false;
    }

    /*
     * Verify if a Rook Move is Legal.
     */
    private static boolean isValidRookMove(ChessPiece pPiece, GridView pGrid){
    	if(pPiece.getColor() == Color.BLACK){
    		// pre-condition check.
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.BLACK){
    			return false;
    		}
    		if(pPiece.getY() == pGrid.getY() && pPiece.getX() != pGrid.getX()){
    			int beg = pPiece.getX() > pGrid.getX() ? pPiece.getX() : pGrid.getX();
    			int end =  pPiece.getX() > pGrid.getX() ? pGrid.getX() : pPiece.getX();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(i, pPiece.getY()) != null){
    					return false;
    				}
    			}
    			return true;
    		}else if(pPiece.getX() == pGrid.getX() && pPiece.getY() != pGrid.getY()){
    			int beg = pPiece.getY() > pGrid.getY() ? pPiece.getY() : pGrid.getY();
    			int end =  pPiece.getY() > pGrid.getY() ? pGrid.getY() : pPiece.getY();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(i, pPiece.getY()) != null){
    					return false;
    				}
    			}
    			return true;
    		}
    	}else{
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
        	if(atGridPiece != null && atGridPiece.getColor() == Color.WHITE){
        		return false;
        	}
        	if(pPiece.getY() == pGrid.getY() && pPiece.getX() != pGrid.getX()){
    			int beg = pPiece.getX() > pGrid.getX() ? pPiece.getX() : pGrid.getX();
    			int end =  pPiece.getX() > pGrid.getX() ? pGrid.getX() : pPiece.getX();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(i, pPiece.getY()) != null){
    					System.out.println("failed");
    					return false;
    				}
    			}
    			return true;
    		}else if(pPiece.getX() == pGrid.getX() && pPiece.getY() != pGrid.getY()){
    			int beg = pPiece.getY() > pGrid.getY() ? pPiece.getY() : pGrid.getY();
    			int end =  pPiece.getY() > pGrid.getY() ? pGrid.getY() : pPiece.getY();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(i, pPiece.getY()) != null){
    					return false;
    				}
    			}
    			return true;
    		}
    	}
    	return false;
    }
    
    /*
     * Verify if a Knight Move is Valid
     */
    private static boolean isValidKnightMove(ChessPiece pPiece, GridView pGrid){
    	// pre-condition
    	if(pPiece.getX() == pGrid.getX() && pPiece.getY() == pGrid.getY()){
    		return false;
    	}
    	if(pPiece.getColor() == Color.BLACK){
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.BLACK){
    			return false;
    		}
    		return (Math.abs(pPiece.getX() - pGrid.getX()) == 1 
    				&& Math.abs(pPiece.getY() - pGrid.getY()) == 2) || 
    				(Math.abs(pPiece.getX() - pGrid.getX()) == 2 && 
    				Math.abs(pPiece.getY() - pGrid.getY()) == 1);
    	}else{
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.WHITE){
    			return false;
    		}
    		return (Math.abs(pPiece.getX() - pGrid.getX()) == 1 
    				&& Math.abs(pPiece.getY() - pGrid.getY()) == 2) || 
    				(Math.abs(pPiece.getX() - pGrid.getX()) == 2 && 
    				Math.abs(pPiece.getY() - pGrid.getY()) == 1);
    		
    	}
    }
    
    /*
     * Verify if a Bishop Move is Legal. 
     */
    private static boolean isValidBishopMove(ChessPiece pPiece, GridView pGrid){
		// pre-condition check.
    	if(pPiece.getX() == pGrid.getX() && pPiece.getY() == pGrid.getY()){
    		return false;
    	}
    	if(Math.abs(pPiece.getX() - pGrid.getX()) != Math.abs(pPiece.getY() - pGrid.getY())){
    		return false;
    	}
    	if(pPiece.getColor() == Color.BLACK){
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.BLACK){
    			return false;
    		}
    		int begX = pPiece.getX() > pGrid.getX() ? pPiece.getX() : pGrid.getX();
			int begY = pPiece.getY() > pGrid.getY() ? pPiece.getY() : pGrid.getY();
			int endX = pPiece.getX() > pGrid.getX() ? pGrid.getX() : pPiece.getX();
			int endY = pPiece.getY() > pGrid.getY() ? pGrid.getY() : pPiece.getY();
    		
    		for(int i = begX + 1; i < endX; i++){
    			if(begY < endY){
    				begY++;
    				if(GameModel.getInstance().getChessPiece(i, begY) != null){
    					return false;
    				}
    			}else{
    				begY--;
    				if(GameModel.getInstance().getChessPiece(i, begY) != null){
    					return false;
    				}
    			}
    		}
    		return true;
    	}else{
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.WHITE){
    			return false;
    		}
    		int begX = pPiece.getX() > pGrid.getX() ? pPiece.getX() : pGrid.getX();
			int begY = pPiece.getY() > pGrid.getY() ? pPiece.getY() : pGrid.getY();
			int endX =  pPiece.getX() > pGrid.getX() ? pGrid.getX() : pPiece.getX();
			int endY =  pPiece.getY() > pGrid.getY() ? pGrid.getY() : pPiece.getY();
    		
    		for(int i = begX + 1; i < endX; i++){
    			if(begY < endY){
    				begY++;
    				if(GameModel.getInstance().getChessPiece(i, begY) != null){
    					return false;
    				}
    			}else{
    				begY--;
    				if(GameModel.getInstance().getChessPiece(i, begY) != null){
    					return false;
    				}
    			}
    		}
    		return true;
    	}
    }
    
    /*
     * Verify if a Queen Move is Legal.
     * A queen is just a combination of Rook and Bishop.
     */
    private static boolean isValidQueenMove(ChessPiece pPiece, GridView pGrid){
    	return isValidRookMove(pPiece, pGrid) || isValidBishopMove(pPiece, pGrid);
    }
    
    /*
     * Verify if a King Move is Legal.
     * A king is just like a queen, but it can only move to its nearby grid.
     */
    private static boolean isValidKingMove(ChessPiece pPiece, GridView pGrid){
    	System.out.println("activated");
    	return 	(Math.abs(pPiece.getX() - pGrid.getX()) == 1 ||
    			Math.abs(pPiece.getX() - pGrid.getX()) == 0) && 
    			(Math.abs(pPiece.getY() - pGrid.getY()) == 1 || 
    			Math.abs(pPiece.getY() - pGrid.getY()) == 0) &&
    			isValidQueenMove(pPiece, pGrid);
    }
    
    /*
     * TODO: Verify if a Castle Move is Legal.
     */
	private static boolean isCastleValid(ChessPiece pPiece, GridView pGrid){
		// white long castle.
		if(pGrid.getX() == 7 && pGrid.getY() == 2 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(7, 1) != null || 
					GameModel.getInstance().getChessPiece(7, 2) != null ||
					GameModel.getInstance().getChessPiece(7, 3) != null ){
				return false;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(7, 0); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor()){
				if(!pPiece.hasMoved() && !rook.hasMoved()){
					GameModel.getInstance().removeChessPiece(pPiece.getX(), pPiece.getY());
					GameModel.getInstance().removeChessPiece(7, 0);
					GameModel.getInstance().addChessPiece(pPiece, pGrid.getX(), pGrid.getY());
					GameModel.getInstance().addChessPiece(rook, pGrid.getX(), pGrid.getY()+1);
					pPiece.setPosition(pGrid.getX(), pGrid.getY());
					pPiece.moved();
					rook.setPosition(pGrid.getX(), pGrid.getY()+1);
					rook.moved();
					return true;
				}
			}
		}
		// white short castle
		if(pGrid.getX() == 7 && pGrid.getY() == 6 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(7, 5) != null || 
					GameModel.getInstance().getChessPiece(7, 6) != null){
				return false;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(7, 7); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor()){
				if(!pPiece.hasMoved() && !rook.hasMoved()){
					GameModel.getInstance().removeChessPiece(pPiece.getX(), pPiece.getY());
					GameModel.getInstance().removeChessPiece(7, 7);
					GameModel.getInstance().addChessPiece(pPiece, pGrid.getX(), pGrid.getY());
					GameModel.getInstance().addChessPiece(rook, pGrid.getX(), pGrid.getY() - 1);
					pPiece.setPosition(pGrid.getX(), pGrid.getY());
					pPiece.moved();
					rook.setPosition(pGrid.getX(), pGrid.getY() - 1);
					rook.moved();
					return true;
				}
			}
		}
		
		// black long castle
		if(pGrid.getX() == 0 && pGrid.getY() == 2 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(0, 1) != null || 
					GameModel.getInstance().getChessPiece(0, 2) != null ||
					GameModel.getInstance().getChessPiece(0, 3) != null ){
				return false;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(0, 0); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor()){
				if(!pPiece.hasMoved() && !rook.hasMoved()){
					GameModel.getInstance().removeChessPiece(pPiece.getX(), pPiece.getY());
					GameModel.getInstance().removeChessPiece(0, 0);
					GameModel.getInstance().addChessPiece(pPiece, pGrid.getX(), pGrid.getY());
					GameModel.getInstance().addChessPiece(rook, pGrid.getX(), pGrid.getY()+1);
					pPiece.setPosition(pGrid.getX(), pGrid.getY());
					pPiece.moved();
					rook.setPosition(pGrid.getX(), pGrid.getY()+1);
					rook.moved();
					return true;
				}
			}
		}
		// black short castle
		if(pGrid.getX() == 0 && pGrid.getY() == 6 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(0, 5) != null || 
					GameModel.getInstance().getChessPiece(0, 6) != null){
				return false;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(0, 7); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor()){
				if(!pPiece.hasMoved() && !rook.hasMoved()){
					GameModel.getInstance().removeChessPiece(pPiece.getX(), pPiece.getY());
					GameModel.getInstance().removeChessPiece(0, 7);
					GameModel.getInstance().addChessPiece(pPiece, pGrid.getX(), pGrid.getY());
					GameModel.getInstance().addChessPiece(rook, pGrid.getX(), pGrid.getY() - 1);
					pPiece.setPosition(pGrid.getX(), pGrid.getY());
					pPiece.moved();
					rook.setPosition(pGrid.getX(), pGrid.getY() - 1);
					rook.moved();
					return true;
				}
			}
		}
    	return false;
    }
    // System.out.println("Piece position " + pPiece.getX() + ", " + pPiece.getY() );
	// System.out.println("Grid position " + pGrid.getX() + ", " + pGrid.getY() );

}
