package ui;

import javafx.application.*;

import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.*;
import model.GameModel;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class ChessGame extends Application{
	
	private static final int MARGIN_OUTER = 10;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Chess");
		GridPane root = new GridPane();
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));
        
       
        GameModel model = GameModel.getInstance();
        ChessBoardView chessBoard = new ChessBoardView();
        model.addObserver(chessBoard);
        model.reset();
        
        root.add(new Label(""), 0, 0);
        root.add(chessBoard, 1, 0);
        root.add(new Label(""), 2, 0);
        
		Scene mainBoard = new Scene(root, 1000, 800);
		primaryStage.setScene(mainBoard);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
}