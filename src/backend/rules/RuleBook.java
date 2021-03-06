package backend.rules;

import backend.chess.ChessPiece;
import backend.chess.Color;
import backend.chess.PieceType;
import backend.recording.BasicMoveCommand;
import backend.recording.CastleCommand;
import backend.recording.Command;
import middleware.engine.GameModel;
import ui.BoardSquareView;

/**
 * A static Class that verify if a move is legal.
 * Created by JiajunChen on 16/8/9.
 */
public class RuleBook {
	
    public static Command generateMoveCommand(ChessPiece pPiece, BoardSquareView pGrid){
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
    			CastleType isCastleValid = isCastleValid(pPiece, pGrid);
    			if(isCastleValid!= null){
    				return new CastleCommand(pPiece, isCastleValid);
    			}
    			break;	
    		default: isValid = false;
    			break;
    	}
    	// TODO: add the move command here 
    	if(isValid){
   			return new BasicMoveCommand(pPiece, pGrid.getX(), pGrid.getY());
    	}
    	return null;
    }
    
    /*
     * Verify if a pawn move is legal.
     */
    private static boolean isValidPawnMove(ChessPiece pPiece, BoardSquareView pGrid){
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
    private static boolean isValidRookMove(ChessPiece pPiece, BoardSquareView pGrid){

    	if(pPiece.getColor() == Color.BLACK){
    		// pre-condition check.
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.BLACK){
    			return false;
    		}
    		if(pPiece.getY() == pGrid.getY() && pPiece.getX() != pGrid.getX()){
    			int beg =  pPiece.getX() > pGrid.getX() ? pGrid.getX() : pPiece.getX();
    			int end = pPiece.getX() > pGrid.getX() ? pPiece.getX() : pGrid.getX();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(i, pPiece.getY()) != null){
    					return false;
    				}
    			}
    			return true;
    		}else if(pPiece.getX() == pGrid.getX() && pPiece.getY() != pGrid.getY()){
    			int beg =  pPiece.getY() > pGrid.getY() ? pGrid.getY() : pPiece.getY();
    			int end = pPiece.getY() > pGrid.getY() ? pPiece.getY() : pGrid.getY();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(pPiece.getX(), i) != null){
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
    			int beg = pPiece.getX() > pGrid.getX() ? pGrid.getX() : pPiece.getX();
    			int end = pPiece.getX() > pGrid.getX() ? pPiece.getX() : pGrid.getX();
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(i, pPiece.getY()) != null){
    					return false;
    				}
    			}
    			return true;
    		}else if(pPiece.getX() == pGrid.getX() && pPiece.getY() != pGrid.getY()){
    			int beg =  pPiece.getY() > pGrid.getY() ? pGrid.getY() : pPiece.getY();
    			int end = pPiece.getY() > pGrid.getY() ? pPiece.getY() : pGrid.getY();
    			
    			for(int i = beg + 1; i < end; i++){
    				if(GameModel.getInstance().getChessPiece(pPiece.getX(), i) != null){
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
    private static boolean isValidKnightMove(ChessPiece pPiece, BoardSquareView pGrid){
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
    private static boolean isValidBishopMove(ChessPiece pPiece, BoardSquareView pGrid){
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
    		int pieceX =  pPiece.getX();
    		int pieceY = pPiece.getY();
    		int gridX =  pGrid.getX();
    		int gridY = pGrid.getY();
    		
    		if(pieceX > gridX){
    			if(pieceY < gridY){
    				for(int i = pieceX - 1; i > gridX; i--){
    					pieceY++;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}else{
    				for(int i = pieceX - 1; i > gridX; i--){
    					pieceY--;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}
    		}
    		if(pieceX < gridX){
    			if(pieceY < gridY){
    				for(int i = pieceX + 1; i < gridX; i++){
    					pieceY++;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}else{
    				for(int i = pieceX + 1; i < gridX; i++){
    					pieceY--;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}
    		}
    		return true;
    	}else{
    		ChessPiece atGridPiece = GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY());
    		if(atGridPiece != null && atGridPiece.getColor() == Color.WHITE){
    			return false;
    		}
    		int pieceX =  pPiece.getX();
    		int pieceY = pPiece.getY();
    		int gridX =  pGrid.getX();
    		int gridY = pGrid.getY();
    		
    		if(pieceX > gridX){
    			if(pieceY < gridY){
    				for(int i = pieceX - 1; i > gridX; i--){
    					pieceY++;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}else{
    				for(int i = pieceX - 1; i > gridX; i--){
    					pieceY--;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}
    		}
    		if(pieceX < gridX){
    			if(pieceY < gridY){
    				for(int i = pieceX + 1; i < gridX; i++){
    					pieceY++;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
    				}
    			}else{
    				for(int i = pieceX + 1; i < gridX; i++){
    					pieceY--;
    					if(GameModel.getInstance().getChessPiece(i, pieceY) != null){
        					return false;
        				}
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
    private static boolean isValidQueenMove(ChessPiece pPiece, BoardSquareView pGrid){
    	return isValidRookMove(pPiece, pGrid) || isValidBishopMove(pPiece, pGrid);
    }
    
    /*
     * Verify if a King Move is Legal.
     * A king is just like a queen, but it can only move to its nearby grid.
     */
    private static boolean isValidKingMove(ChessPiece pPiece, BoardSquareView pGrid){
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
	private static CastleType isCastleValid(ChessPiece pPiece, BoardSquareView pGrid){
		// white long castle.
		if(pGrid.getX() == 7 && pGrid.getY() == 2 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(7, 1) != null || 
					GameModel.getInstance().getChessPiece(7, 2) != null ||
					GameModel.getInstance().getChessPiece(7, 3) != null ){
				return null;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(7, 0); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor() &&
				!pPiece.hasMoved() && !rook.hasMoved()){
					return CastleType.LONG;
				}
		}
		// white short castle
		if(pGrid.getX() == 7 && pGrid.getY() == 6 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(7, 5) != null || 
					GameModel.getInstance().getChessPiece(7, 6) != null){
				return CastleType.SHORT;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(7, 7); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor() &&
				!pPiece.hasMoved() && !rook.hasMoved()){
					return CastleType.SHORT;
			}
		}
		
		// black long castle
		if(pGrid.getX() == 0 && pGrid.getY() == 2 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(0, 1) != null || 
					GameModel.getInstance().getChessPiece(0, 2) != null ||
					GameModel.getInstance().getChessPiece(0, 3) != null ){
				return null;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(0, 0); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor() &&
				!pPiece.hasMoved() && !rook.hasMoved()){
					return CastleType.LONG;
			}
		}
		// black short castle
		if(pGrid.getX() == 0 && pGrid.getY() == 6 &&
				GameModel.getInstance().getChessPiece(pGrid.getX(), pGrid.getY()) == null){
			// first pre-condition, make sure the path is clear.
			if(GameModel.getInstance().getChessPiece(0, 5) != null || 
					GameModel.getInstance().getChessPiece(0, 6) != null){
				return null;
			}
			ChessPiece rook = GameModel.getInstance().getChessPiece(0, 7); 
			if(rook != null && rook.getType() == PieceType.ROOK && rook.getColor() == pPiece.getColor() &&
				!pPiece.hasMoved() && !rook.hasMoved()){
					return CastleType.SHORT;
			}
		}
    	return null;
    }
    // System.out.println("Piece position " + pPiece.getX() + ", " + pPiece.getY() );
	// System.out.println("Grid position " + pGrid.getX() + ", " + pGrid.getY() );
}
