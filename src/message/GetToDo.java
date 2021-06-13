package message;

import ToDo_Server.Client;
import ToDo_Server.Client_Model;
import ToDo_Server.ToDo;

public class GetToDo extends Message {
	private String token; 
	private String id;
	private String line;

	public GetToDo(String[] data) {
		super(data);
		this.token = data[1];
		this.id = data[2];
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Client client) {
		boolean result = false; 
		if(client.getToken().equals(token)) {
			for(ToDo t : Client_Model.getTodolistserver()) {
				if(t.getID() == Integer.parseInt(id)) {
				line = Integer.toString(t.getID()) + "|" + t.getTitle() + "|" + t.getPriority() + "|" + 
						t.getDescription() + "|" + t.getDueDate();
				}
			}
			result = true;
		}
		
		client.send(new Result(result, line));
		
	}

}
