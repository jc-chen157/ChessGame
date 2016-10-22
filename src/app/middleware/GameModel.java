package app.middleware;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import backend.chess.ChessBoard;
import backend.chess.ChessPiece;
import backend.chess.Color;
import backend.recording.MoveCommand;

/**
 * The bridge between back-end and front-end of the chess game.
 * @author Jiajun Chen
 *
 */
public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private final List<UIObserver> aObserverList = new ArrayList<>();
	private ChessBoard aChessBoard = ChessBoard.getInstance();
	private Stack<MoveCommand> aMoveStack = new Stack<>();
	private GameModel(){}
	
	public static GameModel getInstance(){
		return INSTANCE;
	}

	/**
	 * Add chessPiece to the board. 
	 * @param pChess
	 * @param pX
	 * @param pY
	 */
	public void addChessPiece(ChessPiece pChess, int pX, int pY){
       aChessBoard.addPiece(pChess, pX, pY);
    }
	
	/**
	 * Return the ChessPiece for the specific Index
	 * @param pX
	 * @param pY
	 * @return
	 */
	public ChessPiece getChessPiece(int pX, int pY){
		return aChessBoard.getPiece(pX, pY);
	}
	
	/**
	 * Remove ChessPiece on the board.
	 * @param pX
	 * @param pY
	 */
	public void removeChessPiece(int pX, int pY){
		aChessBoard.removePiece(pX, pY);
	}
	
	public void executeMove(MoveCommand pCommand){
		aMoveStack.push(pCommand);
		pCommand.execute();
		System.out.println(pCommand.toString());
		notifyObserver();
	}
	
	public void undoMove(){
		aMoveStack.pop().undo();
		notifyObserver();
	}
	
	public String getLastMove(){
		return aMoveStack.peek().toString();
	}
	
	public Color getLastTurn(){
		return aMoveStack.peek().getColor();
	}
	
    /**
     * Reset the whole game. 
     */
	public void reset(){
		aChessBoard.reset();
		notifyObserver();
	}
	
	/**
	 * Add observer
	 * @param pObserver
	 */
	public void addObserver(UIObserver pObserver){
		aObserverList.add(pObserver);
	}
	
	/**
	 * Tell the observer that the back-end data has changed.
	 * Notify the UI to update itself. 
	 */
	public void updateUI(){
		notifyObserver();
	}
	
	/**
	 * Print the board in text View.
	 */
    public void printBackEnd(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                ChessPiece piece = aChessBoard.getPiece(i,j);
                if(piece == null){
                    System.out.print("NULL" +" | ");
                }else{
                    System.out.print(piece.toString() +" | ");
                }
            }
            System.out.println("");
        }
    }

	private void notifyObserver(){
		for(UIObserver observer: aObserverList){
			observer.updateView();
		}
	}

}