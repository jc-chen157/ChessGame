package app;

import app.middleware.GameModel;
import javafx.application.*;

import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.*;
import ui.gameBoard.ChessBoardView;
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
		BorderPane appplicationPane = new BorderPane();
		GridPane gameBoard = new GridPane();
		appplicationPane.setCenter(gameBoard);

        gameBoard.setHgap(MARGIN_OUTER);
        gameBoard.setVgap(MARGIN_OUTER);
        gameBoard.setPadding(new Insets(MARGIN_OUTER));
       
        // initialize the game.
        GameModel model = GameModel.getInstance();
        ChessBoardView chessBoard = new ChessBoardView();
        model.addObserver(chessBoard);
        model.reset();
        
        // add the game menu
        MenuBar menuBar = new MenuBar();
        // --- Menu File
        Menu menuFile = new Menu("File");
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        // --- Menu View
        Menu menuView = new Menu("View");
 
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        appplicationPane.setTop(menuBar);
        
        // assemble different UI parts.
        gameBoard.add(new Label("aaa"), 0, 1);
        gameBoard.add(chessBoard, 1, 1);
        gameBoard.add(new Label("bbb"), 2, 1);
        
        // launch.
		Scene mainBoard = new Scene(appplicationPane, 800, 800);
		primaryStage.setScene(mainBoard);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}