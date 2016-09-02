package ui;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.GameModel;
import model.UIObserver;
import resource.ChessPiece;
import resource.Color;
import rules.Referee;

/**
 * Main UI Class, aggregate the Piece Label and Grid View. 
 * Also add listener to those Classes.
 * @author JiajunChen
 *
 */
public class ChessBoardView extends GridPane implements UIObserver{
	
	private PieceLabel selectedPiece = null;
	private List<GridView> chessBoard = new ArrayList<>();
	
	public ChessBoardView(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				// white grid
				if((i +j) % 2 == 0){
					GridView grid = new GridView(i, j, Color.WHITE); 
					grid.setPrefSize(80, 80);
					grid.setStyle("-fx-background-color: azure;");
					grid.setAlignment(Pos.CENTER);
					grid.setVisible(true);
					chessBoard.add(grid);
					addSelectionListenerToGrid(grid);
					this.add(grid,i,j);
				}else{
					GridView grid = new GridView(i, j, Color.BLACK);
					grid.setPrefSize(80, 80);
					grid.setStyle("-fx-background-color: grey;");
					grid.setAlignment(Pos.CENTER);
					grid.setVisible(true);
					chessBoard.add(grid);
					addSelectionListenerToGrid(grid);
					this.add(grid, i, j);
				}
			}
		}
		this.setPrefSize(640, 640);
		this.setVisible(true);
	}
	
	public void init(){
		
	}

	/**
	 * At this point, a pull has been used. Really a push design would
	 * be so much better. 
	 * Also, use a repo to store all potential chess piece label so that
	 * during the game it doesnt have to load images again. redo this part
	 * 
	 */
	@Override
	public void updateView() {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				ChessPiece piece = GameModel.getInstance().getChessPiece(i, j);
				if(piece != null){
					String path = "file:images/" + piece.toString() + ".png";
					ImageView chessImage = new ImageView(new Image(path));
					PieceLabel chessLabel = new PieceLabel(chessImage, piece, null);
					
					addSelectionListenerToChessPiece(chessLabel);
					for(Node child: this.getChildren()){
						if(child instanceof GridView){
							GridView grid = (GridView) child;
							if(grid.getX() == i && grid.getY() == j){
								grid.getChildren().add(chessLabel);
                                chessLabel.updatePosition(grid);
							}
						}
					}
				}
			}
		}
		this.setPrefSize(640, 640);
		this.setVisible(true);
        GameModel.getInstance().printBackEnd();
    }

	/**
	 * Add listener to Chess Pieces. Should delegate the job to PieceLabel itself.
	 * @param pLabel
     */
	private void addSelectionListenerToChessPiece(PieceLabel pLabel){
		pLabel.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(!pLabel.isSelected() && selectedPiece == null){
					pLabel.setStyle("-fx-border-color: orange;");
					pLabel.select(true);
					selectedPiece = pLabel;
				}else{
					pLabel.setStyle("");
					pLabel.select(false);
					selectedPiece = null;
				}
			}
		});
	}

	/**
	 * Add listener to Grid. Again this job should be delegated to GridView itself.
	 * @param pGrid
     */
	private void addSelectionListenerToGrid(GridView pGrid){
		pGrid.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				// if no ChessPiece is selected, nothing happens.
				if(selectedPiece == null){
					return;
				}else{
                    // update UI and Model
					if(Referee.isValidMove(selectedPiece.getChessPiece(), pGrid)){
						GameModel.getInstance().updateUI();
                        selectedPiece = null;
                    }
				}
			}
		});
	}
}
