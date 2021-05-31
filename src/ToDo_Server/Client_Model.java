package ToDo_Server;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.logging.Logger;

import Contacts.appClasses.Contact;
import javafx.beans.property.SimpleStringProperty;

public class Client_Model {
	
	protected TreeSet<ToDo> myTreeToDoList = new TreeSet<ToDo>();
	protected TreeSet<ToDo> ourToDoList = new TreeSet<ToDo>();

protected SimpleStringProperty newestMessage = new SimpleStringProperty();
protected DateTimeFormatter LocalFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private Logger logger = Logger.getLogger("");
	
	public void connect(String ipAddress, int Port, String name, String password) {
		logger.info("Connect");
	}
	
	public void disconnect() {
		logger.info("Disconnect");
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

}
