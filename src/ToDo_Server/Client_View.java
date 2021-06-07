package ToDo_Server;

import java.time.LocalDate;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Client_View {
	
	final private Client_Model model;
	protected static Stage stage;
	
	protected Scene sceneRoot, sceneLogin, sceneRegistration, sceneMainView, sceneSharedToDos, sceneCreateToDo;
	
	protected BorderPane root, registrationView, loginView, myToDoView, ourToDoView, toDoView;
		
	protected GridPane centerServerConnection, centerRegistration, centerLogin, centerToDo;
	
	protected ListView myList, ourList;
	
	protected ToolBar toolbarMyView, toolbarOurView, bottombarMyView, bottombarOurView, toolbarToDo, bottombarToDo;
	
	protected Label lbRegistration, lbUsername, lbUserPassword, lbLogin, lbUsernameLogin, lbUserPasswordLogin, lbIpAddress, lbPort,
		lbPortMyView, lbPortNrMyView, lbPortOurView, lbPortNrOurView, lbServerMyView, lbServerIPMyView,
			lbServerOurView, lbServerIPOurView, lbTitle, lbDescription, lbDueDate, lbShare, lbCreator, 
			lbCreateDate, lbPriority, lbNoAccount;
	
	protected final String SERVERIPMYVIEW = "127.0.0.1", SEREVRIPOURVIEW = "127.0.0.1", PORTNRMYVIEW = "50002",
				PORTNROURVIEW = "50002"; 
	
	protected Button btServerConnect, btLogin, btRegistration, btCreateAccount, btLoginUser, btLogoutMyView, btChangePassword, btOurToDo, btOurToDo2,
		btMyToDo, btLogoutOurView, btHome, btSave, btDelete, btCreateToDo;
	
	protected TextField txtUsername, txtUsernameLogin, txtIpAddress, txtPort, txtTitle, txtCreator, txtID;
	
	protected TextArea txtaDescription;
	
	protected DatePicker dpDueDate, dpCreateDate;
	
	protected CheckBox cbShare;
	
	protected ChoiceBox <Priority> chbPriority;
	
	protected PasswordField txtpPassword, txtpPasswordLogin;

	public Client_View(Stage stage, Client_Model model) {
		this.model = model;
		this.stage = stage;
		
		
		// Main View für Anmeldung oder Registration
		
		this.root = new BorderPane();
		this.root.getStyleClass().add("rootView");
		this.centerServerConnection = new GridPane();
		this.centerServerConnection.getStyleClass().add("centerServerConnection");
		
		this.lbIpAddress = new Label ("IP address");
		this.lbIpAddress.getStyleClass().add("lbServerConnection");
		this.lbPort = new Label ("Port");
		this.lbPort.getStyleClass().add("lbServerConnection");
		
		this.btServerConnect = new Button("Verbinden");
		this.btServerConnect.getStyleClass().add("btServerConnection");
	
		this.txtIpAddress = new TextField();
		this.txtPort = new TextField();
		
		this.centerServerConnection.add(this.lbIpAddress, 0, 0);
		this.centerServerConnection.add(this.txtIpAddress, 1, 0);
		this.centerServerConnection.add(this.lbPort, 0, 1);
		this.centerServerConnection.add(this.txtPort, 1, 1);
		this.centerServerConnection.add(this.btServerConnect, 0, 2, 2, 1);
		
		this.root.getChildren().add(this.centerServerConnection);
		
		//View für Registrierung
		
		this.registrationView = new BorderPane();
		this.registrationView.getStyleClass().add("registrationView");
		this.centerRegistration = new GridPane();
		this.centerRegistration.getStyleClass().add("centerRegistration");
		
		this.lbRegistration = new Label("Registration");
		this.lbRegistration.getStyleClass().add("lbRegistration");
		this.lbUsername = new Label("Benutzername");
		this.lbUsername.getStyleClass().add("lbUserRegistration");
		this.lbUserPassword = new Label("Passwort");
		this.lbUserPassword.getStyleClass().add("lbUserRegistration");
		
		this.txtUsername = new TextField();
		this.txtpPassword = new PasswordField();
		
		this.btCreateAccount = new Button("Account erstellen");
		this.btCreateAccount.getStyleClass().add("btCreateAccount");
		
		this.centerRegistration.add(this.lbRegistration, 0, 0);
		this.centerRegistration.add(this.lbUsername, 0, 1);
		this.centerRegistration.add(this.txtUsername, 1, 1);
		this.centerRegistration.add(this.lbUserPassword, 0, 2);
		this.centerRegistration.add(this.txtpPassword, 1, 2);
		this.centerRegistration.add(this.btCreateAccount, 0, 3, 2, 1);
		
		this.registrationView.setCenter(this.centerRegistration);
		
		
		//View für Anmeldung
		
		this.loginView = new BorderPane();
		this.loginView.getStyleClass().add("loginView");
		this.centerLogin = new GridPane();
		this.centerLogin.getStyleClass().add("centerLogin");
		
		this.lbLogin = new Label("Login");
		this.lbLogin.getStyleClass().add("lbLogin");
		this.lbUsernameLogin = new Label("Benutzername");
		this.lbUsernameLogin.getStyleClass().add("lbUserLogin");
		this.lbUserPasswordLogin = new Label("Passwort");
		this.lbUserPasswordLogin.getStyleClass().add("lbUserLogin");
		this.lbIpAddress.getStyleClass().add("lbUserLogin");
		this.lbPort.getStyleClass().add("lbUserLogin");
		this.lbNoAccount = new Label("Noch keinen Account? ");
		this.lbNoAccount.getStyleClass().add("lbNoAccount");
		
		this.txtUsernameLogin = new TextField();
		this.txtpPasswordLogin = new PasswordField();

		
		this.btLoginUser = new Button("Login");
		this.btLoginUser.getStyleClass().add("btLoginUser");
		this.btRegistration = new Button("Registrieren");
		this.btRegistration.getStyleClass().add("btRegistration");
		
		this.centerLogin.add(this.lbLogin, 0, 0);
		this.centerLogin.add(this.lbUsernameLogin, 0, 1);
		this.centerLogin.add(this.txtUsernameLogin, 1, 1);
		this.centerLogin.add(this.lbUserPasswordLogin, 0, 2);
		this.centerLogin.add(this.txtpPasswordLogin, 1, 2);
		this.centerLogin.add(this.btLoginUser, 0, 3, 2, 1);
		this.centerLogin.add(this.lbNoAccount, 0, 4);
		this.centerLogin.add(this.btRegistration, 0, 5, 2, 1);
		
		this.loginView.setCenter(this.centerLogin);
		
		
		//View Meine ToDo's
		this.myToDoView = new BorderPane();
		
		this.myList = new ListView();
		
		this.toolbarMyView = new ToolBar();
		this.bottombarMyView = new ToolBar();
		
		this.btLogoutMyView = new Button("Logout");
		this.btChangePassword = new Button("Passwort ändern");
		this.btOurToDo = new Button("Unsere ToDo's");
		this.btCreateToDo = new Button("+ ToDo");
		
		this.lbPortMyView = new Label("Port: ");
		this.lbPortNrMyView = new Label(this.PORTNRMYVIEW);
		this.lbServerMyView = new Label("Server IP: ");
		this.lbServerIPMyView = new Label(this.SERVERIPMYVIEW);
		
		this.toolbarMyView.getItems().addAll(this.btLogoutMyView, this.btChangePassword, this.btOurToDo, this.btCreateToDo);
		this.bottombarMyView.getItems().addAll(this.lbPortMyView, this.lbPortNrMyView, this.lbServerMyView, this.lbServerIPMyView);	
		
		
		this.myToDoView.setTop(this.toolbarMyView);
		this.myToDoView.setCenter(this.myList);
		this.myToDoView.setBottom(this.bottombarMyView);
		
		
		//View Unsere ToDo's
		this.ourToDoView = new BorderPane();
		
		this.ourList = new ListView();
		
		this.toolbarOurView = new ToolBar();
		this.bottombarOurView = new ToolBar();
		
		this.btMyToDo = new Button("Meine ToDo's");
		this.btLogoutOurView = new Button("Logout");
		
		this.lbPortOurView = new Label("Port: ");
		this.lbPortNrOurView = new Label(this.PORTNROURVIEW);
		this.lbServerOurView = new Label("Server IP: ");
		this.lbServerIPOurView = new Label(this.SEREVRIPOURVIEW);
		
		this.toolbarOurView.getItems().addAll(this.btMyToDo, this.btLogoutOurView);
		this.bottombarOurView.getItems().addAll(this.lbPortOurView, this.lbPortNrOurView, this.lbServerOurView, this.lbServerIPOurView);
		
		this.ourToDoView.setTop(this.toolbarOurView);
		this.ourToDoView.setCenter(this.ourList);
		this.ourToDoView.setBottom(this.bottombarOurView);
		
		
		//View für ToDo's zu erstellen und Detailansicht
		
		this.toDoView = new BorderPane();
		
		this.centerToDo = new GridPane();
		
		this.toolbarToDo = new ToolBar();
		this.bottombarToDo = new ToolBar();
		
		this.btHome = new Button("Home");
		this.btOurToDo2 = new Button("Zurück zu \"unsere ToDo's\"");
		this.btSave = new Button("Speichern");
		this.btDelete = new Button("Erledigt/Löschen");
		
		this.lbTitle = new Label("Titel");
		this.lbDescription = new Label("Beschreibung");
		this.lbDueDate = new Label("Fälligkeitsdatum");
		this.lbShare = new Label("ToDo teilen");
		this.lbCreator = new Label("Erstellt von");
		//this.lbCreateDate = new Label("Erstellt am");
		this.lbPriority = new Label("Priorität");
		
		this.txtTitle = new TextField();
		this.txtaDescription = new TextArea();
		
		this.dpDueDate = new DatePicker();
		dpDueDate.setDayCellFactory(picker -> new DateCell() {
		        public void updateItem(LocalDate date, boolean empty) {
		            super.updateItem(date, empty);
		            LocalDate today = LocalDate.now();

		            setDisable(empty || date.compareTo(today) < 0 );
		        }
		        
		    });
		
		dpDueDate.setEditable(false);
		
		this.txtID = new TextField();
		this.cbShare = new CheckBox();
		this.txtCreator = new TextField();
		this.txtCreator.setEditable(false);
		/*this.dpCreateDate = new DatePicker();
		this.dpCreateDate.setEditable(false);*/
		this.chbPriority = new ChoiceBox <Priority>();
		this.chbPriority.getItems().addAll(Priority.values());
		
		this.centerToDo.add(this.lbTitle, 0, 0);
		this.centerToDo.add(this.txtTitle, 1, 0);
		this.centerToDo.add(this.lbDescription, 0, 1);
		this.centerToDo.add(this.txtaDescription, 1, 1);
		this.centerToDo.add(this.lbPriority, 0, 2);
		this.centerToDo.add(this.chbPriority, 1, 2);
		this.centerToDo.add(this.lbDueDate, 0, 3);
		this.centerToDo.add(this.dpDueDate, 1, 3);
		this.centerToDo.add(this.lbShare, 0, 4);
		this.centerToDo.add(this.cbShare, 1, 4);
		this.centerToDo.add(this.lbCreator, 0, 5);
		this.centerToDo.add(this.txtCreator, 1, 5);
		this.centerToDo.add(this.txtID, 2, 7);
		//this.centerToDo.add(this.lbCreateDate, 0, 6);
		//this.centerToDo.add(this.dpCreateDate, 1, 6);
		
		
		this.toolbarToDo.getItems().addAll(this.btHome, this.btOurToDo2, this.btSave);
		this.bottombarToDo.getItems().addAll(this.btDelete);
		
		this.toDoView.setTop(this.toolbarToDo);
		this.toDoView.setCenter(this.centerToDo);
		this.toDoView.setBottom(this.bottombarToDo);
		
		
		//Set Scene
		sceneRoot = new Scene(root, 700, 550);
		sceneRoot.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneRoot);
		stage.setTitle("ToDo App");
		
		
		sceneLogin = new Scene(loginView, 700, 550);
		sceneLogin.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneLogin);
		stage.setTitle("ToDo App");
		
		sceneRegistration = new Scene(registrationView, 700, 550);
		sceneRegistration.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneRegistration);
		stage.setTitle("ToDo App");
		
		sceneMainView = new Scene(myToDoView, 700, 550);
		sceneMainView.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneMainView);
		stage.setTitle("ToDo App");
		
		sceneSharedToDos = new Scene(ourToDoView, 700, 550);
		sceneSharedToDos.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneSharedToDos);
		stage.setTitle("ToDo App");
		
		sceneCreateToDo = new Scene(toDoView, 700, 550);
		sceneCreateToDo.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneCreateToDo);
		stage.setTitle("ToDo App");
		
		

		
	}
	
	
	public void start() {
		stage.setScene(sceneRoot);
		stage.show();
	}


	public void changeViewRegistration() {
		stage.setScene(sceneRegistration);
		stage.show();
		
	}


	public void chageViewLogin() {
		stage.setScene(sceneLogin);
		stage.show();
		
	}


	public void changeMainView() {
		stage.setScene(sceneMainView);
		stage.show();
		
	}


	public void changeViewOurToDOs() {
		stage.setScene(sceneSharedToDos);
		stage.show();
	}


	public void changeViewCreateToDOs() {
		stage.setScene(sceneCreateToDo);
		stage.show();
		
	}


	public void backToLogin() {
		stage.setScene(sceneLogin);
		stage.show();
		
	}


	public void changeVieMyToDOs() {
		stage.setScene(sceneMainView);
		stage.show();
		
	}
	

}
