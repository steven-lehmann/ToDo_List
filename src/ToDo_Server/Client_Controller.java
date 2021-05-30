package ToDo_Server;

import java.time.LocalDate;

import javafx.event.Event;

public class Client_Controller {
	
	private Client_View view;
	private Client_Model model;

	public Client_Controller(Client_Model model, Client_View view) {
		this.view = view;
		this.model = model;
		
	//	view.btRegistration.setOnAction(this::changeViewRegistration);
		
		
		view.btLoginUser.setOnAction(event -> {
			String ipAddress = view.txtIpAddress.getText();
			int port = Integer.parseInt(view.txtPort.getText());
			String name = view.txtUsername.getText();
			String password = view.txtpPassword.getText();
			model.connect(ipAddress, port, name, password);
			view.changeMainView();
			// Überprüfung der Eingaben mit einer Methode ergänzen
			
		});
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
		view.btRegistration.setOnAction(this::changeViewRegistration);
		
		view.btCreateAccount.setOnAction(this::createAccount);
		
		view.btOurToDo.setOnAction(this::changeViewOurToDOs);
		
		view.btCreateToDo.setOnAction(this::changeViewCreateToDOs);
		
		view.btLogoutMyView.setOnAction(this::backToLogin);
		
		view.btLogoutOurView.setOnAction(this::backToLogin);
		
		view.btMyToDo.setOnAction(this::changeVieMyToDOs);
		
		view.btHome.setOnAction(this::changeVieMyToDOs);

		view.btSave.setOnAction(this::saveNewToDo);
		
		
		
	//	view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		// model.newestMessage.addListener( (o, oldValue, newValue) -> view.txtChatArea.appendText(newValue) );
		

		
	}
	private void saveNewToDo(Event e) {
		String titel = view.txtTitle.getText();
		Priority priority = view.chbPriority.getSelectionModel().getSelectedItem();
		String description = view.txtaDescription.getText();
		LocalDate dueDate = view.dpDueDate.getValue();
		
		ToDo toDo = model.createToDo(titel, priority, description, dueDate);
		model.myTreeToDoList.add(toDo);
		if (view.cbShare.isSelected()) {
			model.ourToDoList.add(toDo);
		}
	
		view.myList.getItems().clear();
		for(ToDo t : model.myTreeToDoList) {
			view.myList.getItems().add(t);
			
		}
		
		view.ourList.getItems().clear();
		for(ToDo t : model.ourToDoList) {
			view.ourList.getItems().add(t);
		
		}
	
		view.changeMainView();
		
	}
	
	private void changeViewRegistration (Event e) {
		view.changeViewRegistration();
	}
	
	private void changeViewOurToDOs(Event e) {
		view.changeViewOurToDOs();
	}
	
	private void changeViewCreateToDOs(Event e) {
		view.changeViewCreateToDOs();
	}
	
	private void changeVieMyToDOs(Event e) {
		view.changeVieMyToDOs();
	}
	
	private void backToLogin(Event e) {
		view.backToLogin();
	}
	private void createAccount (Event e) {
		//ToDo Einen neuen Benutzer erstellen
		view.chageViewLogin(); 
	}
}
