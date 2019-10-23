package poker.version_text;

import javafx.application.Application;
import javafx.stage.Stage;
import poker.version_text.controller.PokerGameController;
import poker.version_text.model.PokerGameModel;
import poker.version_text.view.PokerGameView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class PokerGame extends Application {
	public static int NUM_PLAYERS = 2;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;

	public static void main(String[] args) {
		launch();
	}

	public void PokerGameView(Stage stage, PokerGameModel model) {

		this.model = model; // Create and initialize the MVC components
		Scene scene1 = null;
		char num;

		Label label = new Label("Select Player:");

		Button submit = new Button("Submit");
		submit.setOnAction(e -> stage.setScene(scene1));

		final Spinner<Integer> spinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> val = //
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, PokerGame.NUM_PLAYERS);

		spinner.setValueFactory(val);
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		grid.getChildren().addAll(label, spinner);

		Scene sc1 = new Scene(grid);

		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);

		model = new PokerGameModel();

		stage.setScene(sc1);
		stage.show();

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
