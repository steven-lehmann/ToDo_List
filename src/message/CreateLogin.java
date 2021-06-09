package message;

import ToDo_Server.Account;
import ToDo_Server.Client;


public class CreateLogin extends Message {
	
	/**
	 * Create a completely new login. After creating an account, the user must still
	 * login!
	 */
		private String username;
		private String password;

		public CreateLogin(String[] data) {
			super(data);
			this.username = data[1];
			this.password = data[2];
		}

		/**
		 * We can only create a new account if the name is at least 3 characters, and is not
		 * in use either as a user or as a chatroom
		 */
		@Override
		public void process(Client client) {
			boolean result = false;
			if (username != null && username.length() >= 3) {
				if (password != null && password.length() >= 3) { // lax password requirements
					if (Account.exists(username) == null) {
						Account newAccount = new Account(username, password);
						Account.add(newAccount);
						result = true;
					}
				}
			}
			client.send(new Result(result));
		}

	

}
