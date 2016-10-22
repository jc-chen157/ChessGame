package app;

import java.io.File;

import app.middleware.GameModel;
import app.middleware.TimerModel;
import backend.player.Player;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.MenuBar;
import javafx.stage.*;
import ui.gameBoard.ChessBoardView;
import ui.menuBar.ChessGameMenuBar;
import ui.player_timer.PlayerAndTimerDisplay;
import ui.player_timer.PlayerInfoPanel;
import ui.player_timer.TimerView;
import ui.recording.RecordingPanel;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.*;

/**
 * Main Class of the whole game. Start the Game.
 */
public class ChessGame extends Application{
	
	private static final int MARGIN_OUTER = 10;
	private static final int APP_WIDTH = 1000;
	private static final int APP_HEIGHT = 600;
	
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
        MenuBar menuBar = new ChessGameMenuBar();
        appplicationPane.setTop(menuBar);
        
        // assemble Player Info UI parts.
        gameBoard.add(chessBoard, 0, 0);
        addPlayerAndTimerComponent(appplicationPane);
        
        // add recording pane
        RecordingPanel recordingPanel = new RecordingPanel();
        model.addObserver(recordingPanel);
        appplicationPane.setRight(recordingPanel);
        
        // add music
        Media media = new Media(new File("src/sound/bgm.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
        
        // launch.
		Scene mainBoard = new Scene(appplicationPane, APP_WIDTH, APP_HEIGHT);
		primaryStage.setScene(mainBoard);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	private void addPlayerAndTimerComponent(BorderPane pBoard){
		PlayerAndTimerDisplay playerDisplay = new PlayerAndTimerDisplay();
        Player testPlayer = new Player("Jiajun Chen");
        PlayerInfoPanel playerInfo = new PlayerInfoPanel(testPlayer);
        TimerView timerView = new TimerView();
        TimerModel timerModel = TimerModel.getInstance();
        timerModel.addObserver(timerView);
        Player testPlayer2 = new Player("Wenrong Chen");
        PlayerInfoPanel playerInfo2 = new PlayerInfoPanel(testPlayer2);
        playerDisplay.getChildren().addAll(playerInfo, timerView, playerInfo2);
        pBoard.setLeft(playerDisplay);
	}
}