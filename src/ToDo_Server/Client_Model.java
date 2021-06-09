package ToDo_Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.logging.Logger;



import javafx.beans.property.SimpleStringProperty;

public class Client_Model {
	
	protected TreeSet<ToDo> myTreeToDoList = new TreeSet<ToDo>();
	protected TreeSet<ToDo> ourToDoList = new TreeSet<ToDo>();

protected SimpleStringProperty newestMessage = new SimpleStringProperty();
protected DateTimeFormatter LocalFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private Logger logger = Logger.getLogger("");
	private Socket socket;
	private String token;
	private OutputStreamWriter socketOut;
	private BufferedReader socketIn;

	
	public void connect(String ipAddress, int port) {
		logger.info("Connect");
		try {
			socket = new Socket(ipAddress, port);
			socketOut = new OutputStreamWriter(socket.getOutputStream());
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
			
		} catch (Exception e) {
			logger.warning(e.toString());
		}
	}
	
	
	public void disconnect() {
		logger.info("Disconnect");
		if (socket != null)
			try {
				socket.close();
			} catch (IOException e) {
				// Uninteresting
			}
	}
	
	
	
	/* public void sendMessage(String message) {
		logger.info("Send message");
	} */

	/* public String receiveMessage() {
		logger.info("Receive message");
		return newestMessage.get();
	} */

	public void createToDo(String titel, Priority priority, String description, LocalDate dueDate) throws IOException {
		// ToDo toDo = new ToDo(titel, priority, description, dueDate);
		//return toDo;
		boolean status = false;
		String line = "Login|" + this.token + "|" + titel + "|" + priority + "|" + description + "|" + dueDate;
		socketOut.write(line + "\n");
		socketOut.flush();
		System.out.println("Sent: " + line);
		String msg = null;
		try {
		msg = socketIn.readLine();
		System.out.println("Received: " + msg);
		String[] parts = msg.split("\\|");
		if(parts[1].equalsIgnoreCase("true")) {
			status = true;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public ToDo getSelectedToDo(int id) {
		ToDo toDo = null;
		for(ToDo t : myTreeToDoList) {
			if(t.getID() == id) {
				toDo = t;
			}
		}
		return toDo;
	}

	public boolean createAccount(String name, String password) throws IOException {
	boolean status = false; 
	String line = "CreateLogin|" + name + "|" + password;
	socketOut.write(line + "\n");
	socketOut.flush();
	System.out.println("Sent: " + line);
	String msg = null;
	try {
	msg = socketIn.readLine();
	System.out.println("Received: " + msg);
	String[] parts = msg.split("\\|");
	if(parts[1].equalsIgnoreCase("true")) {
		status = true;
	}
	} catch (IOException e) {
		e.printStackTrace();
	}
	 return status;
	
	}


	public boolean login(String userName, String password) throws IOException {
		boolean status = false; 
		String line = "Login|" + userName + "|" + password;
		socketOut.write(line + "\n");
		socketOut.flush();
		System.out.println("Sent: " + line);
		String msg = null;
		try {
		msg = socketIn.readLine();
		System.out.println("Received: " + msg);
		String[] parts = msg.split("\\|");
		this.token = parts[2];
		if(parts[1].equalsIgnoreCase("true")) {
			status = true;
		} 
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Hat nicht funktioniert");
		
		} 
		return status;


	}	
	
	public boolean logOut() throws IOException {
		boolean status = false; 
		String line = "Logout";
		socketOut.write(line + "\n");
		socketOut.flush();
		System.out.println("Sent: " + line);
		String msg = null;
		try {
		msg = socketIn.readLine();
		System.out.println("Received: " + msg);
		String[] parts = msg.split("\\|");
		if(parts[1].equalsIgnoreCase("true")) {
			status = true;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return status;
		
		}


	public void connectionControl() throws IOException {
		boolean status = false;
		String line = "Ping";
		socketOut.write(line + "\n");
		socketOut.flush();
		System.out.println("Sent: " + line);
		String msg = null;
		try {
		msg = socketIn.readLine();
		System.out.println("Received: " + msg);
		String[] parts = msg.split("\\|");
		if(parts[1].equalsIgnoreCase("true")) {
			status = true;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	



}
