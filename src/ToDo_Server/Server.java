package ToDo_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import message.Message;

public class Server {
	protected static ObservableList<Client> clients = FXCollections.observableArrayList();
	//protected static ArrayList<Client> clients2 = new ArrayList<Client>();
	
	private static final Logger logger = Logger.getLogger("");
	private volatile static boolean stop = false;
	private static ServerSocket server;
	private static int port = 50002;
	private static String decision;
	
	public static void startServer(int Port) {
		logger.info("Start server");
		try {
			server = new ServerSocket(port, 10, null);
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (!stop) {
						try {
							Socket socket = server.accept();
							Client client = new Client(socket);
							System.out.println("Verbindung zum Server hergestellt");
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
		if (server != null) {
			try {
				server.close();
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
	
	public void broadcast(Message outMsg) {
		logger.info("Broadcasting message to clients");
		for (Client c : clients) {
			c.send(outMsg);
		}
	}

}
