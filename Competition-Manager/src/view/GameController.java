package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {
	
	Main main = new Main();
	
	@FXML
	private Button team1;
	

	@FXML
	private void showTeam() throws IOException {
		main.showNewScene("Game.fxml", "Teamanzeige");
	}

}
