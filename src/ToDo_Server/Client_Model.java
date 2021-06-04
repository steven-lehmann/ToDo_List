package ToDo_Server;


import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.logging.Logger;



import javafx.beans.property.SimpleStringProperty;
import message.CreateLogin;
import message.Message;

public class Client_Model {
	
	protected TreeSet<ToDo> myTreeToDoList = new TreeSet<ToDo>();
	protected TreeSet<ToDo> ourToDoList = new TreeSet<ToDo>();

protected SimpleStringProperty newestMessage = new SimpleStringProperty();
protected DateTimeFormatter LocalFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private Logger logger = Logger.getLogger("");
	private Socket socket;
	
	public void connect(String ipAddress, int Port, String name, String password) {
		logger.info("Connect");
		try {
			socket = new Socket(ipAddress, Port);
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
	
	
	
	public void sendMessage(String message) {
		logger.info("Send message");
	}

	public String receiveMessage() {
		logger.info("Receive message");
		return newestMessage.get();
	}

	public ToDo createToDo(String titel, Priority priority, String description, LocalDate dueDate) {
		ToDo toDo = new ToDo(titel, priority, description, dueDate);
		return toDo;
		
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

	public void createAccount(String name, String password) {
		String[] input = new String[3];
		 input[1] = name;
		 input[2] = password;
		Message m = new CreateLogin(input);
		try {
			m.send(socket);
			Message msg = Message.receive(socket);
			String result = msg.toString();
			logger.info(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
