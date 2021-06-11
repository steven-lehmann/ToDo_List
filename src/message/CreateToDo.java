package message;

import java.time.LocalDate;

import ToDo_Server.Account;
import ToDo_Server.Client;
import ToDo_Server.Client_Model;
import ToDo_Server.Prio;
import ToDo_Server.ToDo;

public class CreateToDo extends Message {
	private String token;
	private String title;
	private String priority;
	private String description;
	private String dueDate;
	private String username;
	private LocalDate today = LocalDate.now();
	

	public CreateToDo(String[] data) {
		super(data);
		this.token = data[1];
		this.title = data[2];
		this.priority = data[3];
		this.description = data[4];
		this.dueDate = data[5];
	}

	@Override
	public void process(Client client) {
		boolean result = false; 
		Prio prio = Prio.valueOf(priority);
		LocalDate localDate = LocalDate.parse(dueDate);
		if (client.getToken().equals(token)) {
			if(title.length()>= 3) {
				if(localDate.compareTo(today) >= 0) {
					this.username = client.getAccount().getUsername();
					ToDo toDo = new ToDo(this.title, prio, this.description,localDate, this.username);
					//Client_Model.getTodolist()
					ToDo.getTodolistserver().add(toDo);
					result = true;
				}
			}
		}
		client.send(new Result(result, ToDo.getIDNr()));
		
	}

}
