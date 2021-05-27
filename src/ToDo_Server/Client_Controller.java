package ToDo_Server;

public class Client_Controller {
	
	private Client_View view;
	private Client_Model model;

	public Client_Controller(Client_Model model, Client_View view) {
		this.view = view;
		this.model = model;
		
	/*	view.btnConnect.setOnAction( event -> {
			String ipAddress = view.txtIpAddress.getText();
			int port = Integer.parseInt(view.txtPort.getText());
			String name = view.txtName.getText();
			model.connect(ipAddress, port, name);
		}); 
		
		*/
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
	//	view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		// model.newestMessage.addListener( (o, oldValue, newValue) -> view.txtChatArea.appendText(newValue) );
		
		
	}

}
