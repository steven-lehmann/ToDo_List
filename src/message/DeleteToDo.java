package message;

import ToDo_Server.Client;
import ToDo_Server.ToDo;

public class DeleteToDo extends Message {
	private String token;
	private String id; 

	public DeleteToDo(String[] data) {
		super(data);
		this.token = data[1];
		this.id = data[2];
	}

	@Override
	public void process(Client client) {
		boolean result = false; 
		if(client.getToken().equals(token)) {
			for(ToDo t : ToDo.getTodolistserver()) {
				if(t.getID() == Integer.parseInt(id)) {
					ToDo.getTodolistserver().remove(id);
					result = true;
				}
			}
		}
		
		client.send(new Result(result));
	}

}
