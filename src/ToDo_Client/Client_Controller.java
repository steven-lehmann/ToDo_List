package ToDo_Client;

public class Client_Controller {
	
	private Client_View view;
	private Client_Model model;

	public Client_Controller(Client_Model model, Client_View view) {
		this.view = view;
		this.model = model;
	}

}
