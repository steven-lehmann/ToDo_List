package ToDo_Server;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	
	protected Scene scene;
	
	protected BorderPane root, registrationView, loginView, myToDoView, ourToDoView, toDoView;
	
	protected HBox centerMainLogin;
	
	protected GridPane centerRegistration, centerLogin, centerToDo;
	
	protected ListView myList, ourList;
	
	protected ToolBar toolbarMyView, toolbarOurView, bottombarMyView, bottombarOurView, toolbarToDo, bottombarToDo;
	
	protected Label lbRegistration, lbUsername, lbUserPassword, lbLogin, lbUsernameLogin, lbUserPasswordLogin,
		lbPortMyView, lbPortNrMyView, lbPortOurView, lbPortNrOurView, lbServerMyView, lbServerIPMyView,
			lbServerOurView, lbServerIPOurView, lbTitle, lbDescription, lbDueDate, lbShare, lbCreator, lbCreateDate;
	
	protected final String SERVERIPMYVIEW = "127.0.0.1", SEREVRIPOURVIEW = "127.0.0.1", PORTNRMYVIEW = "50002",
				PORTNROURVIEW = "50002"; 
	
	protected Button btLogin, btRegistration, btCreateAccount, btLoginUser, btLogoutMyView, btChangePassword, btOurToDo,
		btMyToDo, btLogoutOurView, btHome, btSave, btDelete;
	
	protected TextField txtUsername, txtUsernameLogin, txtTitle, txtCreator;
	
	protected TextArea txtaDescription;
	
	protected DatePicker dpDueDate, dpCreateDate;
	
	protected CheckBox cbShare;
	
	protected PasswordField txtpPassword, txtpPasswordLogin;

	public Client_View(Stage stage, Client_Model model) {
		this.model = model;
		this.stage = stage;
		
		
		//Main View für Anmeldung oder Registration
		
		this.root = new BorderPane();
		this.centerMainLogin = new HBox();
		
		this.btLogin = new Button("Anmelden");
		this.btRegistration = new Button("Registrieren");
		
		this.centerMainLogin.getChildren().addAll(this.btLogin, this.btRegistration);
		
		this.root.setCenter(this.centerMainLogin);
		
		
		//View für Registrierung
		
		this.registrationView = new BorderPane();
		this.centerRegistration = new GridPane();
		
		this.lbRegistration = new Label("Registration");
		this.lbUsername = new Label("Benutzername");
		this.lbUserPassword = new Label("Passwort");
		
		this.txtUsername = new TextField();
		this.txtpPassword = new PasswordField();
		
		this.btCreateAccount = new Button("Account erstellen");
		
		this.centerRegistration.add(this.lbRegistration, 0, 0);
		this.centerRegistration.add(this.lbUsername, 0, 1);
		this.centerRegistration.add(this.txtUsername, 1, 1);
		this.centerRegistration.add(this.lbUserPassword, 0, 2);
		this.centerRegistration.add(this.txtpPassword, 1, 2);
		this.centerRegistration.add(this.btCreateAccount, 2, 3);
		
		this.registrationView.setCenter(this.centerRegistration);
		
		
		//View für Anmeldung
		
		this.loginView = new BorderPane();
		this.centerLogin = new GridPane();
		
		this.lbLogin = new Label("Login");
		this.lbUsernameLogin = new Label("Benutzername");
		this.lbUserPasswordLogin = new Label("Passwort");
		
		this.txtUsernameLogin = new TextField();
		this.txtpPasswordLogin = new PasswordField();
		
		this.btLoginUser = new Button("Login");
		
		this.centerLogin.add(this.lbLogin, 0, 0);
		this.centerLogin.add(this.lbUsernameLogin, 0, 1);
		this.centerLogin.add(this.txtUsernameLogin, 1, 1);
		this.centerLogin.add(this.lbUserPasswordLogin, 0, 2);
		this.centerLogin.add(this.txtpPasswordLogin, 1, 2);
		this.centerLogin.add(this.btLoginUser, 2, 3);
		
		this.loginView.setCenter(this.centerLogin);
		
		
		//View Meine ToDo's
		this.myToDoView = new BorderPane();
		
		this.myList = new ListView();
		
		this.toolbarMyView = new ToolBar();
		this.bottombarMyView = new ToolBar();
		
		this.btLogoutMyView = new Button("Logout");
		this.btChangePassword = new Button("Passwort ändern");
		this.btOurToDo = new Button("Unsere ToDo's");
		
		this.lbPortMyView = new Label("Port: ");
		this.lbPortNrMyView = new Label(this.PORTNRMYVIEW);
		this.lbServerMyView = new Label("Server IP: ");
		this.lbServerIPMyView = new Label(this.SERVERIPMYVIEW);
		
		this.toolbarMyView.getItems().addAll(this.btLogoutMyView, this.btChangePassword, this.btOurToDo);
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
		this.btSave = new Button("Speichern");
		this.btDelete = new Button("Löschen");
		
		this.lbTitle = new Label("Titel");
		this.lbDescription = new Label("Beschreibung");
		this.lbDueDate = new Label("Fälligkeitsdatum");
		this.lbShare = new Label("ToDo teilen");
		this.lbCreator = new Label("Erstellt von");
		this.lbCreateDate = new Label("Erstellt am");
		
		this.txtTitle = new TextField();
		this.txtaDescription = new TextArea();
		this.dpDueDate = new DatePicker();
		this.cbShare = new CheckBox();
		this.txtCreator = new TextField();
		this.dpCreateDate = new DatePicker();
		
		this.centerToDo.add(this.lbTitle, 0, 0);
		this.centerToDo.add(this.txtTitle, 1, 0);
		this.centerToDo.add(this.lbDescription, 0, 1);
		this.centerToDo.add(this.txtaDescription, 1, 1);
		this.centerToDo.add(this.lbDueDate, 0, 2);
		this.centerToDo.add(this.dpDueDate, 1, 2);
		this.centerToDo.add(this.lbShare, 0, 3);
		this.centerToDo.add(this.cbShare, 1, 3);
		this.centerToDo.add(this.lbCreator, 0, 4);
		this.centerToDo.add(this.txtCreator, 1, 4);
		this.centerToDo.add(this.lbCreateDate, 0, 5);
		this.centerToDo.add(this.dpCreateDate, 1, 5);
		
		
		this.toolbarToDo.getItems().addAll(this.btHome, this.btSave);
		this.bottombarToDo.getItems().addAll(this.btDelete);
		
		this.toDoView.setTop(this.toolbarToDo);
		this.toDoView.setCenter(this.centerToDo);
		this.toDoView.setBottom(this.bottombarToDo);
		
		
		//Set Scene
		scene = new Scene(root, 800, 550);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("ToDo App");

		
	}
	
	
	public void start() {
		stage.show();
	}
	

}
