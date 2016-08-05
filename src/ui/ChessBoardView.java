package ui;

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
	 */
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				ChessPiece piece = GameModel.getInstance().getChessPiece(i, j);
				if(piece != null){
					String path = "file:images/" + piece.toString() + ".png";
					ImageView chessImage = new ImageView(new Image(path));
					PieceLabel chessLabel = new PieceLabel(chessImage, piece);
					
					addSelectionListenerToChessPiece(chessLabel);
					for(Node child: this.getChildren()){
						if(child instanceof GridView){
							GridView grid = (GridView) child;
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
	}
	
	private void addSelectionListenerToChessPiece(PieceLabel pLabel){
		pLabel.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(!pLabel.isSelected()){
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
	
	private void addSelectionListenerToGrid(GridView pGrid){
		pGrid.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(selectedPiece == null){
					return;
				}else{
					for(GridView grid: chessBoard){
						if(!grid.getChildren().isEmpty() && ((PieceLabel) grid.getChildren().get(0)) == selectedPiece){
							grid.getChildren().clear();
							pGrid.getChildren().add(selectedPiece);
						}
					}
				}
			}
			
		});
	}
}
