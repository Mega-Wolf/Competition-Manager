package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import other.Player;
import other.Team;

public class Main extends Application {

	private static BorderPane root;
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Verwaltungsprogramm Schülerfußballturnier");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setCenterContent(String resource) throws IOException {
		AnchorPane centerPane = FXMLLoader.load(getClass().getResource(resource));
		root.setCenter(centerPane);
	}
	
	public void showNewScene(String resource, String stage) throws IOException {
		AnchorPane newPane = FXMLLoader.load(getClass().getResource(resource));
		
		Stage newStage = new Stage();
		newStage.setTitle(stage);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(primaryStage);
		Scene newScene = new Scene(newPane);
		newStage.setScene(newScene);
		newStage.showAndWait();
	}
	
	public void clearCenter() {
		root.setCenter(null);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
