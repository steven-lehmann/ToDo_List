package ToDo_Client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client_Main extends Application {
	
	private Client_Model model;
	private Client_View view;
	private Client_Controller controller;

	public static void main(String[] args) {
		launch();
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new Client_Model();
		view = new Client_View(primaryStage, model);
		controller = new Client_Controller(model, view);
		view.start();	
	}

}
