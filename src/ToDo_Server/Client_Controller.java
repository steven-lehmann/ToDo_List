package ToDo_Server;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
			try {
				model.connectionControl();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
		view.btRegistration.setOnAction(this::changeViewRegistration);
		
		view.btCreateAccount.setOnAction(arg0 -> {
			try {
				createAccount(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		view.btChangePassword.setOnAction(this::changeViewPW);
		
		view.btCreateToDo.setOnAction(this::changeViewCreateToDOs);
		
		view.btLogoutMyView.setOnAction(this::backToLogin);
		
		//view.btLogoutOurView.setOnAction(this::backToLogin);
		
		//view.btMyToDo.setOnAction(this::changeVieMyToDOs);
		
		view.btHome.setOnAction(this::changeVieMyToDOs);

		view.btSave.setOnAction(arg0 -> {
			try {
				saveNewToDo(arg0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		view.myList.setOnMouseClicked(this::showToDo);
		
		//view.ourList.setOnMouseClicked(this::showOurToDo);
		
		//view.btOurToDo2.setOnAction(this::changeViewOurToDOs);
		
		view.btDelete.setOnAction(this::deleteToDo);
		
		view.btLoginUser.setOnAction(arg0 -> {
			try {
				loginClient(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		view.btLogoutMyView.setOnAction(arg0 -> {
			try {
				logOut(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		
		
	//	view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		// model.newestMessage.addListener( (o, oldValue, newValue) -> view.txtChatArea.appendText(newValue) );
		


		
	}
	
	private void logOut(Event e) throws IOException {
		model.logOut();
		view.backToLogin();
	}
	private void loginClient(Event e) throws IOException {
		String userName = view.txtUsernameLogin.getText();
		String password = view.txtpPasswordLogin.getText();
		Boolean passwordCheck = model.login(userName, password);
		if (passwordCheck == true) {
		view.changeViewMyToDOs();
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Wrong password");
			errorAlert.setContentText("Please fill in the correct password");
			errorAlert.showAndWait();
		}
		
	}
	
	
	private void showToDo(MouseEvent mouseevent1) {
		view.changeViewCreateToDOs();
		// disable
		ToDo toDo = (ToDo) view.myList.getSelectionModel().getSelectedItem();
		this.updateView(toDo);
		
	}
	
	/*
	private void showOurToDo(MouseEvent mouseevent1) {
		view.changeViewCreateToDOs();
		// disable
		ToDo toDo = (ToDo) view.ourList.getSelectionModel().getSelectedItem();
		this.updateView(toDo);
		
	}
	*/
	private void deleteToDo(Event e) {
		int id = Integer.parseInt(view.txtID.getText());
		ToDo toDo = model.getSelectedToDo(id);
		model.myTreeToDoList.remove(toDo);
		this.updateView(null);
		this.updateAllLists();
		
	}
	
	private void updateAllLists() {
		view.myList.getItems().clear();
		//view.ourList.getItems().clear();
		for(ToDo t : model.myTreeToDoList) {
			view.myList.getItems().add(t);
		}
		for(ToDo t : model.ourToDoList) {
			//view.ourList.getItems().add(t);
		}
		
	}

	private void updateView(ToDo toDo) {
		if (toDo != null) {
			view.txtTitle.setText(toDo.getTitle());
			view.txtaDescription.setText(toDo.getDescription());
			view.chbPriority.getSelectionModel().select(toDo.getPriority());
			view.dpDueDate.setValue(toDo.getDueDate());

			/*if(toDo.getSharedCheck()) {
				view.cbShare.setSelected(true);
			} else {
				view.cbShare.setSelected(false);
			}*/

			view.txtID.setText(String.valueOf(toDo.getID()));
			//erstellt von
			
		} else {
			view.txtTitle.setText("");
			view.txtaDescription.setText("");
			view.chbPriority.getSelectionModel().select(null);
			view.dpDueDate.setValue(null);
			//view.cbShare.setSelected(false);
			view.txtID.setText("");
		}
		
	}
	
	

	private void saveNewToDo(Event e) throws IOException {
		String titel = view.txtTitle.getText();
		Priority priority = view.chbPriority.getSelectionModel().getSelectedItem();
		String description = view.txtaDescription.getText();
		LocalDate dueDate = view.dpDueDate.getValue();
		
		//model.createToDo(titel, priority, description, dueDate);
		System.out.println(dueDate);
		
	/*	ToDo toDo = model.createToDo(titel, priority, description, dueDate);
		model.myTreeToDoList.add(toDo);
		/*if (view.cbShare.isSelected()) {
			model.ourToDoList.add(toDo);
			toDo.setSharedCheck(true);
		}*/
	
		view.myList.getItems().clear();
		for(ToDo t : model.myTreeToDoList) {
			view.myList.getItems().add(t);
		}
		
		/*view.ourList.getItems().clear();
		for(ToDo t : model.ourToDoList) {
			view.ourList.getItems().add(t);
<<<<<<< HEAD
		}
		*/
	
		view.changeMainView();
		
	}
	
	private void changeViewRegistration (Event e) {
		view.changeViewRegistration();
	}
	
	private void changeViewPW(Event e) {
		view.changeViewPW();
	}
	
	private void changeViewCreateToDOs(Event e) {
		view.changeViewCreateToDOs();
	}
	
	private void changeVieMyToDOs(Event e) {
		view.changeViewMyToDOs();
	}
	
	private void backToLogin(Event e) {
		view.backToLogin();
	}
	private void createAccount (Event e) throws IOException {
		String name = view.txtUsername.getText();
		String password = view.txtpPassword.getText();
		if(password != null && password.length()>= 3) {
			if (name != null && name.length() >= 3) {
			Boolean login = model.createAccount(name, password);
			view.chageViewLogin();
			}
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Password or Username is invalid");
			errorAlert.setContentText("username and password must be equal or greater than 3");
			errorAlert.showAndWait();
		}
		
		; 
	}
}
