package ToDo_Server;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Account implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private static Logger logger = Logger.getLogger("");
	private String password;
	private static final SecureRandom rand = new SecureRandom();
	private final String username;
	private static final int iterations = 127;
	
	private static final ArrayList<Account> accounts = new ArrayList<Account>();
	private static ArrayList<ToDo> toDoList;
	
	private final byte[] salt = new byte[64];
	private String hashedPassword;
	
	public Account(String username, String password) {
		this.username = username;
		Account.toDoList = new ArrayList<ToDo>();
		this.hashedPassword = hash(password);
		
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
	
	@Override
	public String toString() {
		return "Account [username=" + username +"]";
	}

	public static void add(Account newAccount) {
		synchronized (accounts) {
			accounts.add(newAccount);
		}
	}

		
	public boolean checkPassword(String password) {
		String newHash = hash(password);
		boolean success = hashedPassword.equals(newHash);
		return success;
	
	}
	
	public void changePassword(String newPassword) {
		rand.nextBytes(salt); // Change the salt with the password!
		this.hashedPassword = hash(newPassword);
	}

	public static String getToken() {
		byte[] token = new byte[16];
		rand.nextBytes(token);
		return bytesToHex(token);
	
	}
	
	private String hash(String password) {
		try {
			char[] chars = password.toCharArray();
			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return bytesToHex(hash);
		} catch (Exception e) {
			logger.severe("Secure password hashing not possible - stopping server");
			System.exit(0);
			return null; // Will never execute, but keeps Java happy
		}
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	
	}

	public ArrayList<ToDo> getToDoList() {
		return Account.toDoList;
	}

	public void setToDoList(ArrayList<ToDo> toDoList) {
		this.toDoList = toDoList;
	}
	public static void addToDo(ToDo toDo) {
		synchronized (toDoList) {
			toDoList.add(toDo);
		}
	}
}
