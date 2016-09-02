package model;

import java.util.ArrayList;
import java.util.List;

import resource.ChessBoard;
import resource.ChessPiece;
import ui.GridView;

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
	
	public void reset(){
		aChessBoard.reset();
		notifyObserver();
	}

	public ChessPiece getChessPiece(int pX, int pY){
		return aChessBoard.getPiece(pX, pY);
	}
	
	public void addObserver(UIObserver pObserver){
		aObserverList.add(pObserver);
	}
	
	private void notifyObserver(){
		for(UIObserver observer: aObserverList){
			observer.updateView();
		}
	}
	
	public void updateUI(){
		notifyObserver();
	}

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
}
