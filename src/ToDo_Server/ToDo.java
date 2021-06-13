package ToDo_Server;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

import message.Message;



public class ToDo implements Serializable, Comparable <ToDo>, Sendable{
	
	private static final long serialVersionUID = 1;
	private static int IDNr = -1;
	private final int ID;
	private String title;
	private Prio priority;
	private String description;
	private LocalDate dueDate; 
	private String username;

	
	//protected static TreeSet<ToDo> toDolistServer = new TreeSet<ToDo>();


	private static int getNexID() {
		return ++IDNr;
	}
	
	@Override
	public String toString() {
		return title + " Deadline: " + title + " " +ID;
	}
	


	public ToDo(String title, Prio priority, String description, String username, LocalDate dueDate) {
		this.ID = getNexID();
		this.title = title;
		this.priority = priority;
		this.description = description;
		this.dueDate = dueDate;
		this.username = username;
	}
	
	
	/*public ToDo(String title, Prio priority, String description, String username) {
		this.ID = getNexID();
		this.title = title;
		this.priority = priority;
		this.description = description;
		this.username = username;
	}*/
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static int getIDNr() {
		return IDNr;
	}

	public static void setIDNr(int iDNr) {
		IDNr = iDNr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Prio getPriority() {
		return priority;
	}

	public void setPriority(Prio priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getID() {
		return ID;
	}
	
	
	@Override
	public int compareTo(ToDo o) {
		//int compValue = this.getTitle().compareTo(o.getTitle());
		if(this.ID  == o.getID())
			return 0;
			else
				if (this.ID < o.getID())
					return -1;
				else
					return 1;
	}
	
	public boolean equals(ToDo o) {
		if(this.ID == o.getID()) 
			return true;
		else 
			return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(Message msg) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	

}
