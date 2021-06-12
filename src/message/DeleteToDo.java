package message;

import ToDo_Server.Client;
import ToDo_Server.ToDo;
import java.util.logging.Logger;

public class DeleteToDo extends Message {
	public static Logger logger = Logger.getLogger("");
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
			
			for(ToDo t : ToDo.getTodolistserver())  {
				System.out.println(t);
				if(t.getID() == Integer.parseInt(id)) {
					ToDo.getTodolistserver().remove(t);
					result = true;
				} 
					if(t.getUsername().equals(client.getAccount().getUsername())) {
						logger.info("what is t:" + t.toString());
						int index = ToDo.getTodolistserver().indexOf(t);
						
						ToDo.getTodolistserver().remove(t);
						logger.info("what is deleted :" + t);
						logger.info("Did it work : " + ToDo.getTodolistserver().remove(t));
						result = true;
					}
				}
			}
		client.send(new Result(result));
	}

}