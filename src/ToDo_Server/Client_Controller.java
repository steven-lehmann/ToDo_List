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
		
		view.myList.setOnMouseClicked(arg0 -> {
			try {
				showToDo(arg0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//view.ourList.setOnMouseClicked(this::showOurToDo);
		
		//view.btOurToDo2.setOnAction(this::changeViewOurToDOs);
		
		view.btDelete.setOnAction(arg0 -> {
			try {
				deleteToDo(arg0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
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
		
		view.btChange.setOnAction(arg0 -> {
			try {
				changePassword(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		view.btBackToDo.setOnAction(this::changeMainView);
		
		view.btShowMyToDos.setOnAction(arg0 -> {
			try {
				showMyToDos(arg0);
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
	
	
	private void showToDo(MouseEvent mouseevent1) throws IOException {
		view.changeViewCreateToDOs();
		// disable
		ToDo toDo = (ToDo) view.myList.getSelectionModel().getSelectedItem();
		model.GeToDo(toDo.getID());
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
	private void deleteToDo(Event e) throws IOException {
		int id = Integer.parseInt(view.txtID.getText());
		model.deleteToDo(id);
		this.updateView(null);
		view.changeMainView();

		
	}
	
	private void updateView(ToDo toDo) {
		if (toDo != null) {
			view.txtTitle.setText(toDo.getTitle());
			view.txtaDescription.setText(toDo.getDescription());
			view.chbPriority.getSelectionModel().select(toDo.getPriority());
			view.dpDueDate.setValue(toDo.getDueDate());

			
			view.txtCreator.setText(toDo.getUsername());
			view.txtID.setText(String.valueOf(toDo.getID()));
		} else {
			view.txtTitle.setText("");
			view.txtaDescription.setText("");
			view.chbPriority.getSelectionModel().select(null);
			view.dpDueDate.setValue(null);
			view.txtID.setText("");
			view.txtCreator.setText("");
		}
		
	}
	
	

	private void saveNewToDo(Event e) throws IOException {
		String titel = view.txtTitle.getText();
		Prio priority = view.chbPriority.getSelectionModel().getSelectedItem();
		String description = view.txtaDescription.getText();
		LocalDate dueDate = view.dpDueDate.getValue();
		
		model.createToDo(titel, priority, description, dueDate);
		
	/*	ToDo toDo = model.createToDo(titel, priority, description, dueDate);
		model.myTreeToDoList.add(toDo);
		/*if (view.cbShare.isSelected()) {
			model.ourToDoList.add(toDo);
			toDo.setSharedCheck(true);
		}
	
		view.myList.getItems().clear();
		for(ToDo t : model.myTreeToDoList) {
			view.myList.getItems().add(t);
		}
		
		/*view.ourList.getItems().clear();
		for(ToDo t : model.ourToDoList) {
			view.ourList.getItems().add(t);
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
		if(password != null && password.length()>= 3 & name != null && name.length() >= 3) {
			Boolean login = model.createAccount(name, password);
			view.chageViewLogin();
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Password or Username is invalid");
			errorAlert.setContentText("username and password must be equal or greater than 3");
			errorAlert.showAndWait();
		}
	}
	
	private void changePassword(Event e) throws IOException {
		String newPW = view.txtNewPW.getText();
		model.newPassword(newPW);
		
	}
	
	private void changeMainView(Event e) {
		view.changeMainView();
	}
	
	private void showMyToDos(Event e) throws IOException {
		model.getMyToDos();
		view.myList.getItems().clear();
		for(ToDo t : ToDo.getTodolistserver()) {
			view.myList.getItems().add(t);
			
			
		/*	for(int id : model.listIds) {
				if(t.getID() == iD) {
					view.myList.getItems().add(t);
				}
			} */
		}
	}
}