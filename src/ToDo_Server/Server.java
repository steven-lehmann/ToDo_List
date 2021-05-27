package ToDo_Server;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server {
	
	private static final Logger logger = Logger.getLogger("");
	protected ObservableList<Client> clients = FXCollections.observableArrayList();
	
	public void startServer(int Port) {
		logger.info("Start server");
	}
	
	public void stopServer() {
		logger.info("Stop server");
	}
	
	public ObservableList<Client> getClientList() {
		logger.info("Get client list");
		return clients;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
