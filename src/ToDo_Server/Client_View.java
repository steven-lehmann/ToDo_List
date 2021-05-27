package ToDo_Server;

import javafx.stage.Stage;

public class Client_View {
	
	final private Client_Model model;
	protected static Stage stage;

	public Client_View(Stage stage, Client_Model model) {
		this.model = model;
		this.stage = stage;
	}
	
	
	public void start() {
		stage.show();
	}
	

}
