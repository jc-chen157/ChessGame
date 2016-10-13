package ui.player_timer;

import app.middleware.TimerModel;
import app.middleware.UIObserver;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TimerView extends VBox implements UIObserver{
	
	private Label whiteTimerLabel = new Label(TimerModel.getInstance().getWhiteTimerStatus());
	private Label blackTimerLabel = new Label(TimerModel.getInstance().getBlackTimerStatus());
	
	public TimerView(){
		whiteTimerLabel.setPrefWidth(230);
		whiteTimerLabel.setPrefHeight(100);
		blackTimerLabel.setPrefWidth(230);
		blackTimerLabel.setPrefHeight(100);
		whiteTimerLabel.setStyle("-fx-border-color: Black; -fx-font-size: 32pt;");		
		blackTimerLabel.setStyle("-fx-border-color: Black; -fx-font-size: 32pt;");
		this.setPadding(new Insets(10));
		HBox a = new HBox();
		Button startButton = generateStartButton();
		Button stopButton = generateStopButton();
		a.getChildren().addAll(startButton, stopButton);

		this.getChildren().addAll(blackTimerLabel, a, whiteTimerLabel);
	}
	
	@Override
	public void updateView() {
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
					Platform.runLater(new Runnable() {
			            public void run() {
			            	whiteTimerLabel.setText(TimerModel.getInstance().getWhiteTimerStatus());
			        		blackTimerLabel.setText(TimerModel.getInstance().getBlackTimerStatus());
			        		}
			        });
				}
		});
		t.start();
	}
	
	private Button generateStartButton(){
		Button button = new Button("Start");
		button.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				TimerModel.getInstance().startTimer();
			}
			
		});
		return button;
	}
	private Button generateStopButton(){
		Button button = new Button("Stop");
		return button;
	}
	
}
