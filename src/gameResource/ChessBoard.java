package gameResource;


/**
 * ChessBoard Class implemented with SingleTon Design Pattern.
 * It is like an actual chess board. Manages all the resources on the board.
 */
public class ChessBoard {

    // board itself
    public static final ChessBoard INSTANCE = new ChessBoard();
    private ChessPiece[][] aBoard = new ChessPiece[8][8];

    /**
     * Constructor
     */
    private ChessBoard(){}


    /**
     * Return the Instance of the ChessBoard
     * @return
     */
    public static ChessBoard getInstance(){
        return INSTANCE;
    }
    
    /**
     * Reset the whole board. Manually construct new pieces
     */
    public void reset(){
    	aBoard = new ChessPiece[8][8];
    	aBoard[0][0] = new ChessPiece(Color.BLACK, PieceType.ROOK);
    	aBoard[0][0].setPosition(0, 0);
    	aBoard[0][1] = new ChessPiece(Color.BLACK, PieceType.KNIGHT);
    	aBoard[0][1].setPosition(0, 1);
    	aBoard[0][2] = new ChessPiece(Color.BLACK, PieceType.BISHOP);
    	aBoard[0][2].setPosition(0, 2);
    	aBoard[0][3] = new ChessPiece(Color.BLACK, PieceType.QUEEN);
    	aBoard[0][3].setPosition(0, 3);
    	aBoard[0][4] = new ChessPiece(Color.BLACK, PieceType.KING);
    	aBoard[0][4].setPosition(0, 4);
    	aBoard[0][5] = new ChessPiece(Color.BLACK, PieceType.BISHOP);
    	aBoard[0][5].setPosition(0, 5);
    	aBoard[0][6] = new ChessPiece(Color.BLACK, PieceType.KNIGHT);
    	aBoard[0][6].setPosition(0, 6);
    	aBoard[0][7] = new ChessPiece(Color.BLACK, PieceType.ROOK);
    	aBoard[0][7].setPosition(0, 7);
    	for(int i = 0; i < 8; i++){
    		aBoard[1][i] = new ChessPiece(Color.BLACK, PieceType.PAWN);
    		aBoard[1][i].setPosition(1, i);
    	}
    	for(int i = 0; i < 8; i++){
    		aBoard[6][i] = new ChessPiece(Color.WHITE, PieceType.PAWN);
    		aBoard[6][i].setPosition(6, i);
    	}
    	aBoard[7][0] = new ChessPiece(Color.WHITE, PieceType.ROOK);
    	aBoard[7][0].setPosition(7, 0);
    	aBoard[7][1] = new ChessPiece(Color.WHITE, PieceType.KNIGHT);
    	aBoard[7][1].setPosition(7, 1);
    	aBoard[7][2] = new ChessPiece(Color.WHITE, PieceType.BISHOP);
    	aBoard[7][2].setPosition(7, 2);
    	aBoard[7][3] = new ChessPiece(Color.WHITE, PieceType.QUEEN);
    	aBoard[7][3].setPosition(7, 3);
    	aBoard[7][4] = new ChessPiece(Color.WHITE, PieceType.KING);
    	aBoard[7][4].setPosition(7, 4);
    	aBoard[7][5] = new ChessPiece(Color.WHITE, PieceType.BISHOP);
    	aBoard[7][5].setPosition(7, 5);
    	aBoard[7][6] = new ChessPiece(Color.WHITE, PieceType.KNIGHT);
    	aBoard[7][6].setPosition(7, 6);
    	aBoard[7][7] = new ChessPiece(Color.WHITE, PieceType.ROOK);
    	aBoard[7][7].setPosition(7, 7);
    }
    
    public ChessPiece getPiece(int pX, int pY){
    	return aBoard[pX][pY];
    }
    
    public void removePiece(int pX, int pY){
        aBoard[pX][pY] = null;
    }
    
    public void addPiece(ChessPiece pChess, int pX, int pY){
        aBoard[pX][pY] = pChess;
    }
}
