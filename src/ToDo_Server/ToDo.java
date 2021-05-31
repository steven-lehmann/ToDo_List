package ToDo_Server;

import java.time.LocalDate;

public class ToDo implements Comparable <ToDo> {
	
	private static int IDNr = 0;
	private final int ID;
	private String title;
	private Priority priority;
	private String description;
	private LocalDate dueDate; 
	private Boolean sharedCheck;

	

	private static int getNexID() {
		return ++IDNr;
	}
	
	@Override
	public String toString() {
		return title + " Deadline: " + dueDate;
	}

	public ToDo(String title, Priority priority, String description, LocalDate dueDate) {
		this.ID = getNexID();
		this.title = title;
		this.priority = priority;
		this.description = description;
		this.dueDate = dueDate;
		this.sharedCheck = false;
	}
	
	public ToDo(String title, Priority priority, String description) {
		this.ID = getNexID();
		this.title = title;
		this.priority = priority;
		this.description = description;
		this.dueDate = null;
		this.sharedCheck = false;
		
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
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
	
	public Boolean getSharedCheck() {
		return sharedCheck;
	}

	public void setSharedCheck(Boolean sharedCheck) {
		this.sharedCheck = sharedCheck;
	}
	
	@Override
	public int compareTo(ToDo o) {
		int compValue = this.getTitle().compareTo(o.getTitle());
		if(compValue  == 0)
			return 0;
			else
				if (compValue < 0)
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

}
