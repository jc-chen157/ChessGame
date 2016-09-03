package model;

import java.util.ArrayList;
import java.util.List;

import resource.ChessBoard;
import resource.ChessPiece;
import ui.GridView;

/**
 * The bridge between back-end and front-end of the chess game.
 * @author Jiajun Chen
 *
 */
public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private final List<UIObserver> aObserverList = new ArrayList<>();
	private ChessBoard aChessBoard = ChessBoard.getInstance();
	
	private GameModel(){}
	
	public static GameModel getInstance(){
		return INSTANCE;
	}

    public ChessBoard getChessBoard(){
        return aChessBoard;
    }
	
    /**
     * Reset the whole game. 
     */
	public void reset(){
		aChessBoard.reset();
		notifyObserver();
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
