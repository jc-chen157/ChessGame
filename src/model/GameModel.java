package model;

import java.util.ArrayList;
import java.util.List;

import resource.ChessBoard;
import resource.ChessPiece;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private final List<UIObserver> aObserverList = new ArrayList<>();
	private ChessBoard aChessBoard = ChessBoard.getInstance();
	
	
	private GameModel(){}
	
	public static GameModel getInstance(){
		return INSTANCE;
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
}
