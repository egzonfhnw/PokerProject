package poker.version_text;

import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_text.controller.PokerGameController;
import poker.version_text.model.PokerGameModel;
import poker.version_text.view.PokerGameView;
import java.io.File;

import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;

public class PokerGame extends Application {
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;

	public static void main(String[] args) {
	
		String path = "src\\yugioh.mp3"; 
    	
     	 //Instantiating Media class  
         Media media = new Media(new File(path).toURI().toString()); 
         
         //Instantiating MediaPlayer class   
         MediaPlayer mediaPlayer = new MediaPlayer(media);
         
         mediaPlayer.play();
         
		launch();
	}

	public void PokerGameView(Stage stage, PokerGameModel model) {

		this.model = model;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create and initialize the MVC components
		model = new PokerGameModel();
		view = new PokerGameView(primaryStage, model);
		controller = new PokerGameController(model, view);
	}
}
