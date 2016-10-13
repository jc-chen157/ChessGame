package ui.menuBar;

import java.util.Optional;

import app.middleware.GameModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ChessGameMenuBar extends MenuBar{
	
	private final String ABOUTINFO = "Version 1.0.1 \n" + "Developed by Jiajun Chen";
	
	public ChessGameMenuBar(){
		super();
		addGameMenu();
		addActionMenu();
		addPlayerMenu();
		addAboutMenu();
		this.setVisible(true);
	}

	/**
	 * Create Game Menu
	 */
	private void addGameMenu(){
		Menu gameMenu = new Menu("Game");
		
		// New Game Menu Item
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent t){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("New Game");
				alert.setContentText("Are you sure you want to start a new Game?");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.OK){
					GameModel.getInstance().reset();
				}else{
					// this is left as empty intentionally
				}
			}
		});
		
		// Open Game Menu Item
		MenuItem openGame = new MenuItem("Open...");
		openGame.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent t){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Function under development...");
				alert.setHeaderText(null);
				alert.setContentText("This function is not available yet ^_^\"   ");
				alert.showAndWait();
			}
		});
		
		// Exit Game Item
		MenuItem exitGame = new MenuItem("Exit");
		exitGame.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent t){
				Platform.exit();
			}
		});
		
		gameMenu.getItems().addAll(newGame, openGame, exitGame);
		this.getMenus().add(gameMenu);
	}
	
	/**
	 * Create Action Menu
	 */
	private void addActionMenu(){
		Menu actionMenu = new Menu("Action");
		
		// Undo Menu Item
		MenuItem undo = new MenuItem("Undo");
		undo.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent t){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Function under development...");
				alert.setHeaderText(null);
				alert.setContentText("This function is not available yet ^_^\"   ");
				alert.showAndWait();
			}
		});
		
		// Redo Menu Item
		MenuItem redo = new MenuItem("Open...");
		redo.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent t){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Function under development...");
				alert.setHeaderText(null);
				alert.setContentText("This function is not available yet ^_^\"   ");
				alert.showAndWait();
			}
		});
		
		actionMenu.getItems().addAll(undo, redo);
		this.getMenus().add(actionMenu);
	}
	
	/**
	 * Create Player Menu
	 */
	private void addPlayerMenu() {
		
	}
	
	/**
	 * Create About Menu
	 */
	private void addAboutMenu(){
		Menu aboutMenu = new Menu("About");
		
		// Undo Menu Item
		MenuItem about = new MenuItem("Version");
		about.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent t){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Verion");
				alert.setHeaderText(null);
				alert.setContentText(ABOUTINFO);
				alert.showAndWait();
			}
		});
		
		aboutMenu.getItems().addAll(about);
		this.getMenus().add(aboutMenu);
	}
}
