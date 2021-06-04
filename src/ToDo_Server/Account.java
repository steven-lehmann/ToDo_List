package ToDo_Server;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String password;
	private ArrayList<Integer>  toDoList;
	private final String username;
	
	private static final ArrayList<Account> accounts = new ArrayList<>();
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		this.toDoList = new ArrayList<Integer>();
	}
	
	/**
	 * Find and return an existing account
	 */
	public static Account exists(String username) {
		synchronized (accounts) {
			for (Account account : accounts) {
				if (account.username.equals(username)) return account;
			}
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Integer> getToDoList() {
		return toDoList;
	}

	public void setToDoList(ArrayList<Integer> toDoList) {
		this.toDoList = toDoList;
	}
	
	@Override
	public String toString() {
		return "Account [username=" + username + "]";
	}




	public static void add(Account newAccount) {
		synchronized (accounts) {
			accounts.add(newAccount);
		}
	}


}
