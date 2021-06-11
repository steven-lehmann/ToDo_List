package message;

import java.util.ArrayList;

import ToDo_Server.Client;
import ToDo_Server.Client_Model;
import ToDo_Server.ToDo;

public class ListToDos extends Message {
	
	private String token;
	private ArrayList<String> ids = new ArrayList<String>();

	public ListToDos(String[] data) {
		super(data);
		this.token = data[1];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		if (client.getToken().equals(token)) {
			for(ToDo t : client.getAccount().getToDoList()) {
				if(t.getUsername().equals(client.getAccount().getUsername())) {		
					this.ids.add(Integer.toString(t.getID()));
			
					
					
				}
			}
			result = true;
		}
		
		client.send(new Result(result, ids));
	}
}
