package app;

import java.io.File;

import backend.player.Player;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import middleware.engine.GameModel;
import ui.ChessBoardPanel;
import ui.GameMenuBar;
import ui.PlayerTimerPanel;
import ui.PlayerInfoView;
import ui.RecordingPanel;
import ui.TimerView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.*;

/**
 * Main Class of the whole game. Start the Game.
 */
@SuppressWarnings("unused")
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
        ChessBoardPanel chessBoard = new ChessBoardPanel();
        model.addObserver(chessBoard);
        model.reset();
        
        // add the game menu
        GameMenuBar menuBar = new GameMenuBar();
        appplicationPane.setTop(menuBar);
        
        // assemble Player Info UI parts.
        gameBoard.add(chessBoard, 0, 0);
        addPlayerAndTimerComponent(appplicationPane, model);
        
        // add recording pane
        RecordingPanel recordingPanel = new RecordingPanel();
        model.addObserver(recordingPanel);
        appplicationPane.setRight(recordingPanel);
        
        // TODO: enable music later
        // add music
//        Media media = new Media(ChessGame.class.getClassLoader().getResource("bgm.mp3").toString());
//        MediaPlayer player = new MediaPlayer(media);
//        player.setCycleCount(MediaPlayer.INDEFINITE);
//        player.play();
        
        // launch.
		Scene mainBoard = new Scene(appplicationPane, APP_WIDTH, APP_HEIGHT);
		primaryStage.setScene(mainBoard);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	private void addPlayerAndTimerComponent(BorderPane pBoard, GameModel pModel){
		PlayerTimerPanel playerDisplay = new PlayerTimerPanel();
        Player testPlayer = new Player("Jiajun Chen");
        PlayerInfoView playerInfo = new PlayerInfoView(testPlayer);
        
        // TODO: Finish timer implementation
        TimerView timerView = new TimerView();
        pModel.setTimerView(timerView);
        
        Player testPlayer2 = new Player("Wenrong Chen");
        PlayerInfoView playerInfo2 = new PlayerInfoView(testPlayer2);
        playerDisplay.getChildren().addAll(playerInfo, timerView, playerInfo2);
        pBoard.setLeft(playerDisplay);
	}
}