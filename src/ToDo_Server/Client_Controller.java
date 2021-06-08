package ToDo_Server;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class Client_Controller {
	
	private Client_View view;
	private Client_Model model;

	public Client_Controller(Client_Model model, Client_View view) {
		this.view = view;
		this.model = model;
		
	//	view.btRegistration.setOnAction(this::changeViewRegistration);
		
		
		
			
		view.btServerConnect.setOnAction(event -> {
			String ipAddress = view.txtIpAddress.getText();
			int port = Integer.parseInt(view.txtPort.getText());
			model.connect(ipAddress, port);
			view.backToLogin();
		});
			// Überprüfung der Eingaben mit einer Methode ergänzen
			
	
		
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
		
		view.myList.setOnMouseClicked(this::showToDo);
		
		view.ourList.setOnMouseClicked(this::showOurToDo);
		
		view.btOurToDo2.setOnAction(this::changeViewOurToDOs);
		
		view.btDelete.setOnAction(this::deleteToDo);
		
		
		
	//	view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		// model.newestMessage.addListener( (o, oldValue, newValue) -> view.txtChatArea.appendText(newValue) );
		

		
	}
	
	private void showToDo(MouseEvent mouseevent1) {
		view.changeViewCreateToDOs();
		// disable
		ToDo toDo = (ToDo) view.myList.getSelectionModel().getSelectedItem();
		this.updateView(toDo);
		
	}
	
	private void showOurToDo(MouseEvent mouseevent1) {
		view.changeViewCreateToDOs();
		// disable
		ToDo toDo = (ToDo) view.ourList.getSelectionModel().getSelectedItem();
		this.updateView(toDo);
		
	}
	
	private void deleteToDo(Event e) {
		int id = Integer.parseInt(view.txtID.getText());
		ToDo toDo = model.getSelectedToDo(id);
		model.myTreeToDoList.remove(toDo);
		if(toDo.getSharedCheck())
			model.ourToDoList.remove(toDo);
		this.updateView(null);
		this.updateAllLists();
		
	}
	
	private void updateAllLists() {
		view.myList.getItems().clear();
		view.ourList.getItems().clear();
		for(ToDo t : model.myTreeToDoList) {
			view.myList.getItems().add(t);
		}
		for(ToDo t : model.ourToDoList) {
			view.ourList.getItems().add(t);
		}
		
	}

	private void updateView(ToDo toDo) {
		if (toDo != null) {
			view.txtTitle.setText(toDo.getTitle());
			view.txtaDescription.setText(toDo.getDescription());
			view.chbPriority.getSelectionModel().select(toDo.getPriority());
			view.dpDueDate.setValue(toDo.getDueDate());
			if(toDo.getSharedCheck()) {
				view.cbShare.setSelected(true);
			} else {
				view.cbShare.setSelected(false);
			}
			view.txtID.setText(String.valueOf(toDo.getID()));
			//erstellt von
			
		} else {
			view.txtTitle.setText("");
			view.txtaDescription.setText("");
			view.chbPriority.getSelectionModel().select(null);
			view.dpDueDate.setValue(null);
			view.cbShare.setSelected(false);
			view.txtID.setText("");
		}
		
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
			toDo.setSharedCheck(true);
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
		String name = view.txtUsername.getText();
		String password = view.txtpPassword.getText();
		model.createAccount(name, password);
		view.chageViewLogin(); 
	}
}
