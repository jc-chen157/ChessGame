package ui;

import backend.chess.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import middleware.engine.GameModel;
import middleware.engine.UIObserver;

public class TimerView extends VBox implements UIObserver{
	
	private Timeline aWhiteTimeline;
	private Timeline aBlackTimeline;
	private Label aWhiteTimerLabel = new Label();
	private Label aBlackTimerLabel = new Label();
	
	
	public TimerView(){
		
		aWhiteTimerLabel.setPrefWidth(230);
		aWhiteTimerLabel.setPrefHeight(100);
		aBlackTimerLabel.setPrefWidth(230);
		aBlackTimerLabel.setPrefHeight(100);
		aWhiteTimerLabel.setStyle("-fx-border-color: Black; -fx-font-size: 32pt;");		
		aBlackTimerLabel.setStyle("-fx-border-color: Black; -fx-font-size: 32pt;");
		aWhiteTimerLabel.textProperty().bind(GameModel.getInstance().getTimerStringProperty(Color.WHITE));
		aBlackTimerLabel.textProperty().bind(GameModel.getInstance().getTimerStringProperty(Color.BLACK));
		this.setPadding(new Insets(10));
		
		HBox buttonBox = new HBox();
		Button startButton = generateStartButton();
		Button stopButton = generateStopButton();
		buttonBox.getChildren().addAll(startButton, stopButton);

		aWhiteTimeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                    	GameModel.getInstance().timerTick(Color.WHITE);
                    }
                })
            );
		aWhiteTimeline.setCycleCount(Timeline.INDEFINITE);
		aBlackTimeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                    	GameModel.getInstance().timerTick(Color.BLACK);

                    }
                })
            );
		aBlackTimeline.setCycleCount(Timeline.INDEFINITE);
		this.getChildren().addAll(aBlackTimerLabel, buttonBox, aWhiteTimerLabel);
	}
	
	@Override
	public void updateView() {
		// This method is empty
	}
	
	public void triggerTimer(Color pColor){
		if(pColor == Color.WHITE){
			aWhiteTimeline.play();
			aBlackTimeline.pause();
		}else{
			aWhiteTimeline.pause();
			aBlackTimeline.play();
		}
	}
	
	private Button generateStartButton(){
		Button button = new Button("Start");
		button.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				aWhiteTimeline.play();
			}
		});
		return button;
	}
	
	private Button generateStopButton(){
		Button button = new Button("Stop");
		button.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				aWhiteTimeline.pause();
				aBlackTimeline.pause();
			}
		});
		return button;
	}
}
