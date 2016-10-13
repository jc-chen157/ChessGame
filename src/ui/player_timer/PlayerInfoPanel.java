package ui.player_timer;


import backend.player.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerInfoPanel extends HBox{
	
	private static final double MARGIN_OUTER = 10 ;
	private final String DEFAULTPATH = "default_profile.png";
	
	public PlayerInfoPanel(Player pPlayer){
		super();
		this.setPadding(new Insets(MARGIN_OUTER));
		ImageView profilePicture = getProfilePicture(pPlayer.getPicturePath());
		VBox infoDisplay = new VBox();
		infoDisplay.getChildren().add(generateInfoLabel(pPlayer.getName()));
		infoDisplay.getChildren().add(generateInfoLabel("" + pPlayer.getScore()));
		infoDisplay.getChildren().add(generateInfoLabel(pPlayer.getTitle().toString()));
		this.getChildren().addAll(profilePicture, infoDisplay);
	}

	private ImageView getProfilePicture(String pPath){
		ImageView picture = null;
		if(pPath == null){
			picture = new ImageView(new Image(DEFAULTPATH));
			picture.setFitHeight(90);
			picture.setFitWidth(90);
		}else{
			// should throw an exception for invalid path
			picture = new ImageView(new Image(pPath));
		}
		return picture;
	}
	
	private Label generateInfoLabel(String pInfo){
		Label infoLabel = new Label(pInfo);
		infoLabel.setStyle("-fx-border-color: Black;");		
		infoLabel.setPrefHeight(30);
		infoLabel.setPrefWidth(100);
		return infoLabel;
	}
}
