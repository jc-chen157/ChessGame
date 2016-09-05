package mainUI;

import game.Model.GameModel;
import game.UI.ChessBoardView;
import javafx.application.*;

import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

/**
 * Main Class of the whole game. Start the Game.
 */
public class ChessGame extends Application{
	
	private static final int MARGIN_OUTER = 10;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// set up the root.
		primaryStage.setTitle("Chess");
		GridPane root = new GridPane();
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));
       
        // initialize the game.
        GameModel model = GameModel.getInstance();
        ChessBoardView chessBoard = new ChessBoardView();
        model.addObserver(chessBoard);
        model.reset();
        
        // assemble different UI parts.
        root.add(new Label("aaa"), 0, 0);
        root.add(chessBoard, 1, 0);
        root.add(new Label("bbb"), 2, 0);
        
        // launch.
		Scene mainBoard = new Scene(root, 800, 800);
		primaryStage.setScene(mainBoard);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}