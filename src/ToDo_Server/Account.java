package ToDo_Server;

import java.util.ArrayList;

public class Account {
	
	private String eMail;
	private String password;
	private ArrayList<ToDo>  toDoList;
	
	public Account(String eMail, String password, ArrayList<ToDo> toDoList) {
		this.eMail = eMail;
		this.password = password;
		this.toDoList = toDoList;
		
		
	}


	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<ToDo> getToDoList() {
		return toDoList;
	}

	public void setToDoList(ArrayList<ToDo> toDoList) {
		this.toDoList = toDoList;
	}
	
	@Override
	public String toString() {
		return "Account [eMail=" + eMail + "]";
	}

}
