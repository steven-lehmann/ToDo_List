package ToDo_Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.logging.Logger;


import javafx.beans.property.SimpleStringProperty;

public class Client_Model {
	
	protected ArrayList<Integer> listIds = new ArrayList<Integer>();
	
	protected static ArrayList<ToDo>toDoList = new ArrayList<ToDo>();
	

protected SimpleStringProperty newestMessage = new SimpleStringProperty();
protected DateTimeFormatter LocalFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private Logger logger = Logger.getLogger("");
	private Socket socket;
	private String token;
	private String username;
	private OutputStreamWriter socketOut;
	private BufferedReader socketIn;
	private ToDo toDo;

	protected static TreeSet<ToDo> toDolistServer = new TreeSet<ToDo>();

	
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

	public void createToDo(String titel, Prio priority, String description, LocalDate dueDate) throws IOException {
		synchronized (toDolistServer) {
		boolean status = false;
		String line = "CreateToDo|" + this.token + "|" + titel + "|" + priority + "|" + description + "|" + dueDate;
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
		this.username = userName;
		System.out.println("Sent: " + line);
		String msg = null;
		try {
		msg = socketIn.readLine();
		System.out.println("Received: " + msg);
		String[] parts = msg.split("\\|");
		
		if(parts[1].equalsIgnoreCase("true")) {
			status = true;
			this.token = parts[2];
			this.username = userName;
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
			System.out.println(this.token); // löschen später
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

	public boolean newPassword(String newPW) throws IOException {
		boolean status = false; 
		String line = "ChangePassword|" + this.token + "|" + newPW;
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


	public String getMyToDos() throws IOException {
		String idLine = "";
		boolean status = false;
		String line = "ListToDos|" + this.token;
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
		
		for(int i = 2; i < parts.length; i++) {
				idLine += parts[i] + "|";
			}
		
		
		}
		
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return idLine;
		
	}
	
	public ArrayList<ToDo> getTodolist() {
		return Client_Model.toDoList;
	}


	public String GeToDo(int id) throws IOException {
		String toDo = "";
		Boolean status = false;
		String line = "GetToDo|" + this.token + "|" + id;
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
			for(int i = 2; i < parts.length; i++) {
				toDo += parts[i] + "|";
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toDo;
	}


	public void DeleteToDo(int id) throws IOException {
		synchronized (toDolistServer) {
		Boolean status = false;
		String line = "DeleteToDo|" + this.token + "|" + id;
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
	
	public static TreeSet<ToDo> getTodolistserver() {
		return toDolistServer;
	}
	
}
