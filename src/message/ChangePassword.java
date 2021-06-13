package message;

import ToDo_Server.Account;
import ToDo_Server.Client;


public class ChangePassword extends Message{
	
	private String token;
	private String password;
	
	public ChangePassword(String[] data) {
		super(data);
		this.token = data[1];
		this.password = data[2];
	}
	
	@Override
	public void process(Client client) {
		boolean result = false;
		if (client.getToken().equals(token)) {
			if (password != null && password.length() >= 3) {
				Account account = client.getAccount();
				account.changePassword(password);
				result = true;
		}
		}
		client.send(new Result(result));
	}
}
