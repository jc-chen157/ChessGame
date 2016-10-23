package ui;

import java.util.ArrayList;
import java.util.List;

import app.ChessGame;
import app.middleware.GameModel;
import app.middleware.UIObserver;
import backend.chess.ChessPiece;
import backend.chess.Color;
import backend.rules.RuleBook;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;

/**
 * Main UI Class, aggregate the Piece Label and Grid View. 
 * Also add listener to those Classes.
 * @author JiajunChen
 *
 */
public class ChessBoardPanel extends GridPane implements UIObserver{
	
	private PieceLabel aSelectedPiece = null;
	private List<BoardSquareView> aChessBoard = new ArrayList<>();
	private AudioClip aClickSound = new AudioClip(ChessGame.class.getClassLoader().getResource("click.mp3").toString());
	/**
	 * The constructor sets the board view. 
	 */
	public ChessBoardPanel(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				// white grid
				if((i +j) % 2 == 0){
					BoardSquareView grid = new BoardSquareView(i, j, Color.WHITE); 
					grid.setPrefSize(80, 80);
					grid.setStyle("-fx-background-color: azure;");
					grid.setAlignment(Pos.CENTER);
					grid.setVisible(true);
					aChessBoard.add(grid);
					addSelectionListenerToGrid(grid);
					this.add(grid,i,j);
				}else{
					BoardSquareView grid = new BoardSquareView(i, j, Color.BLACK);
					grid.setPrefSize(80, 80);
					grid.setStyle("-fx-background-color: grey;");
					grid.setAlignment(Pos.CENTER);
					grid.setVisible(true);
					aChessBoard.add(grid);
					addSelectionListenerToGrid(grid);
					this.add(grid, i, j);
				}
			}
		}
		this.setPrefSize(640, 640);
		this.setVisible(true);
	}
	
	/**
	 * Initial
	 */
	public void initializeView(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				ChessPiece piece = GameModel.getInstance().getChessPiece(i, j);
				if(piece != null){
					String path = piece.toString() + ".png";
					ImageView chessImage = new ImageView(new Image(path));
					PieceLabel chessLabel = new PieceLabel(chessImage, piece, null);
					addSelectionListenerToChessPiece(chessLabel);
					for(Node child: this.getChildren()){
						if(child instanceof BoardSquareView){
							BoardSquareView grid = (BoardSquareView) child;
							if(grid.getX() == i && grid.getY() == j){
								grid.getChildren().add(chessLabel);
							}
						}
					}
				}
			}
		}
		this.setPrefSize(640, 640);
		this.setVisible(true);
//      GameModel.getInstance().printBackEnd();
	}

	/**
	 * At this point, a pull has been used. Really a push design would
	 * be so much better. 
	 * Also, use a repo to store all potential chess piece label so that
	 * during the game it doesnt have to load images again. redo this part
	 * 
	 * TODO: Improve efficiency. To be considered later.
	 */
	@Override
	public void updateView() {
		for(BoardSquareView grid: aChessBoard){
			grid.getChildren().clear();
		}
		initializeView();
    }

	/**
	 * Add listener to Chess Pieces. Should delegate the job to PieceLabel itself.
	 * @param pLabel
     */
	private void addSelectionListenerToChessPiece(PieceLabel pLabel){
		pLabel.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				aClickSound.play();
				if(aSelectedPiece == null){
					if(!pLabel.isSelected() && 
							pLabel.getChessPiece().getColor() != GameModel.getInstance().getLastTurn() ){
						pLabel.setStyle("-fx-border-color: orange;");
						pLabel.select(true);
						aSelectedPiece = pLabel;
					}
				}else{
					if(pLabel == aSelectedPiece){
						pLabel.setStyle("");
						pLabel.select(false);
						aSelectedPiece = null;
					}
				}
			}
		});
	}

	/**
	 * Add listener to Grid. Again this job should be delegated to GridView itself.
	 * @param pGrid
     */
	private void addSelectionListenerToGrid(BoardSquareView pGrid){
		pGrid.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
                aClickSound.play();
                if(aSelectedPiece == null){
					return;
                }
				// if no ChessPiece is selected, nothing happens.
				if(aSelectedPiece.getChessPiece().getX() == pGrid.getX() &&
						aSelectedPiece.getChessPiece().getY() == pGrid.getY()){
					return;
				}else{
                    // update UI and Model
					if(RuleBook.isValidMove(aSelectedPiece.getChessPiece(), pGrid)){
//						TimerModel.getInstance().pauseTimer(aSelectedPiece.getChessPiece().getColor());
                        aSelectedPiece = null;
                    }
                    
				}
			}
		});
	}
}
