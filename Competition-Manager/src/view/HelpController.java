package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelpController {
	
	@FXML
	private Label welcome;
	
	@FXML
	public void initialize() {
		String name = System.getProperty("user.name");
		welcome.setText("Guten Tag, " + name + "!");
	}

}
