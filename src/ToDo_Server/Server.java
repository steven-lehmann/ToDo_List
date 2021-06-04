package ToDo_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server {
	protected static ObservableList<Client> clients = FXCollections.observableArrayList();
	
	private static final Logger logger = Logger.getLogger("");
	private volatile static boolean stop = false;
	private static ServerSocket listener;
	private static int port = 50002;
	private static String decision;
	
	public static void startServer(int Port) {
		logger.info("Start server");
		try {
			listener = new ServerSocket(port, 10, null);
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (!stop) {
						try {
							Socket socket = listener.accept();
							logger.info("Listener triggered");
							Client client = new Client(socket);
							clients.add(client);
						} catch (Exception e) {
							logger.info(e.toString());
						}
					}
				}
			};
			Thread t = new Thread(r, "ServerSocket");
			t.start();
		} catch (IOException e) {
			logger.info(e.toString());
		}
	}
	
	public static void stopServer() {
		logger.info("Stop server");
		for (Client c : clients) c.stop();
		
		logger.info("Stop server");
		stop = true;
		if (listener != null) {
			try {
				listener.close();
			} catch (IOException e) {
				// Uninteresting
			}
		}
	}
	
	public ObservableList<Client> getClientList() {
		logger.info("Get client list");
		return clients;
	}

	public static void main(String[] args) {
		startServer(port);
		
		/*logger.info("Stop Server? (yes)");
		Scanner scan = new Scanner(System.in);
		decision = scan.next();
		if(decision.equalsIgnoreCase("YES")) {
			stopServer(); 
		} */
		
	}

}
