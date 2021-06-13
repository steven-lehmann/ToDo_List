package ToDo_Server;

import java.time.LocalDate;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;;

public class Client_View {
	
	final private Client_Model model;
	protected static Stage stage;
	
	protected Scene sceneRoot, sceneLogin, sceneRegistration, sceneMainView, sceneChangePW,
		sceneCreateToDo;
	
	protected BorderPane root, registrationView, loginView, myToDoView, changePWView, toDoView;
		
	protected GridPane centerServerConnection, centerRegistration, centerLogin, centerToDo, centerChangePW;
	
	protected HBox spacer, spacer2, bottom;
	
	protected VBox myListCenter;
	
	protected ListView <String> myList;
	
	protected ToolBar toolbarMyView, toolbarPWView, bottombarMyView, bottombarChangePW, toolbarToDo, bottombarToDo;
	
	protected Label lbRegistration, lbUsername, lbUserPassword, lbLogin, lbUsernameLogin, lbUserPasswordLogin,
			lbIpAddress, lbPort, lbPortMyView, lbPortNrMyView, lbPortPWView, lbPortNrPWView,
			lbServerMyView, lbServerIPMyView, lbServerPWView, lbServerIPPWView, lbTitle, lbDescription,
			lbDueDate, lbShare, lbPriority, lbNoAccount, lbChangePW, 
			lbNewPW, lbServer, lbMyListID, lbPflichtfelder;
	
	protected final String SERVERIPMYVIEW = "127.0.0.1", SEREVRIPPWVIEW = "127.0.0.1", PORTNRMYVIEW = "50002",
				PORTNRPWVIEW = "50002"; 
	
	protected Button btServerConnect, btLogin, btRegistration, btCreateAccount, btLoginUser, btLogoutMyView, btChangePassword, btChange, btBack,
		btMyToDo, btBackToDo, btHome, btSave, btDelete, btCreateToDo, btShowMyToDos;
	
	protected TextField txtUsername, txtUsernameLogin, txtIpAddress, txtPort, txtTitle, txtCreator, txtID, txtNewPW;
	
	protected TextArea txtaDescription;
	
	protected DatePicker dpDueDate, dpCreateDate;
	
	protected ChoiceBox <Prio> chbPriority;
	
	protected PasswordField txtpPassword, txtpPasswordLogin;
	
	protected static Image ICONBACK = new Image("/zuruck.png");
	protected static Image ICONPW = new Image("/changepw.png");
	protected static Image ICONLOGOUT = new Image("/ausloggen.png");
	protected static Image ICONADDTODO = new Image("/add.png");
	protected static Image ICONHOME = new Image("/zuruck.png");
	protected static Image ICONHOMETODO = new Image("/zuruck.png");
	protected static Image ICONDONE = new Image("/loschen.png");
	protected static Image ICONSAVE = new Image("/uberpruft.png");
	protected static Image ICONSHOWTODO = new Image("/show.png");
	
	protected ImageView iconBack, iconLogout, iconChangePW, iconAddToDo,
		iconHome, iconHomeToDo, iconDone, iconSave, iconShowToDo;

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
		this.lbServer = new Label("Verbindung herstellen");
		this.lbServer.getStyleClass().add("lbServer");
		
		this.btServerConnect = new Button("Verbinden");
		this.btServerConnect.getStyleClass().add("btServerConnection");
	
		this.txtIpAddress = new TextField();
		this.txtPort = new TextField();
		
		this.centerServerConnection.add(this.lbServer, 0, 0, 2, 1);
		this.centerServerConnection.add(this.lbIpAddress, 0, 1);
		this.centerServerConnection.add(this.txtIpAddress, 1, 1);
		this.centerServerConnection.add(this.lbPort, 0, 2);
		this.centerServerConnection.add(this.txtPort, 1, 2);
		this.centerServerConnection.add(this.btServerConnect, 0, 3, 2, 1);
		
		this.root.setCenter(this.centerServerConnection);
		
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
		this.btBack = new Button();
		this.btBack.getStyleClass().add("btBack");
		
		this.iconBack = new ImageView(ICONBACK);
		this.btBack.setGraphic(this.iconBack);
		this.iconBack.setFitHeight(40);
		this.iconBack.setFitWidth(40);
		
		this.centerRegistration.add(this.lbRegistration, 0, 0);
		this.centerRegistration.add(this.lbUsername, 0, 1);
		this.centerRegistration.add(this.txtUsername, 1, 1);
		this.centerRegistration.add(this.lbUserPassword, 0, 2);
		this.centerRegistration.add(this.txtpPassword, 1, 2);
		this.centerRegistration.add(this.btCreateAccount, 0, 3, 2, 1);
		
		this.registrationView.setTop(this.btBack);
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
		this.myToDoView.getStyleClass().add("myToDoView");
		
		this.myList = new ListView <String>();
		this.myList.getStyleClass().add("myList");
		
		this.myListCenter = new VBox();
		this.myListCenter.getStyleClass().add("myListCenter");
		
		this.toolbarMyView = new ToolBar();
		this.toolbarMyView.getStyleClass().add("toolbarMyView");
		this.bottombarMyView = new ToolBar();
		this.bottombarMyView.getStyleClass().add("bottombar");


		this.btLogoutMyView = new Button();
		this.btLogoutMyView.getStyleClass().add("btMyToDo");
		
		this.iconLogout = new ImageView(ICONLOGOUT);
		this.btLogoutMyView.setGraphic(this.iconLogout);
		this.iconLogout.setFitHeight(40);
		this.iconLogout.setFitWidth(40);
		
		this.btChangePassword = new Button();
		this.btChangePassword.getStyleClass().add("btMyToDo");
		
		this.iconChangePW = new ImageView(ICONPW);
		this.btChangePassword.setGraphic(this.iconChangePW);
		this.iconChangePW.setFitHeight(40);
		this.iconChangePW.setFitWidth(40);
		
		this.btCreateToDo = new Button();
		this.btCreateToDo.getStyleClass().add("btMyToDo");
		
		this.iconAddToDo = new ImageView(ICONADDTODO);
		this.btCreateToDo.setGraphic(this.iconAddToDo);
		this.iconAddToDo.setFitHeight(40);
		this.iconAddToDo.setFitWidth(40);
		

		this.btShowMyToDos = new Button();
		this.btShowMyToDos.getStyleClass().add("btMyToDo");
		
		this.iconShowToDo = new ImageView(ICONSHOWTODO);
		this.btShowMyToDos.setGraphic(this.iconShowToDo);
		this.iconShowToDo.setFitHeight(40);
		this.iconShowToDo.setFitWidth(40);
		
		
		this.lbPortMyView = new Label("Port: ");
		this.lbPortMyView.getStyleClass().add("lbServerPort");
		this.lbPortNrMyView = new Label(this.PORTNRMYVIEW);
		this.lbPortNrMyView.getStyleClass().add("lbServerPort");
		this.lbServerMyView = new Label("Server IP: ");
		this.lbServerMyView.getStyleClass().add("lbServerPort");
		this.lbServerIPMyView = new Label(this.SERVERIPMYVIEW);
		this.lbServerIPMyView.getStyleClass().add("lbServerPort");
		this.lbMyListID = new Label("Meine ToDo ID(s):");
		this.lbMyListID.getStyleClass().add("lbMyListID");
		
		this.myListCenter.getChildren().addAll(this.lbMyListID, this.myList);
		
		this.toolbarMyView.getItems().addAll(this.btLogoutMyView, this.btChangePassword, this.btShowMyToDos, this.btCreateToDo);
		this.bottombarMyView.getItems().addAll(this.lbPortMyView, this.lbPortNrMyView, this.lbServerMyView, this.lbServerIPMyView);	
		
		
		this.myToDoView.setTop(this.toolbarMyView);
		this.myToDoView.setCenter(this.myListCenter);
		this.myToDoView.setBottom(this.bottombarMyView);
		
		
		//View Change PW
		this.changePWView = new BorderPane();
		this.changePWView.getStyleClass().add("changePWView");
		
		this.centerChangePW = new GridPane();
		this.centerChangePW.getStyleClass().add("centerChangePW");
		
		this.toolbarPWView = new ToolBar();
		this.toolbarPWView.getStyleClass().add("toolbarPWView");
		this.bottombarChangePW = new ToolBar();
		this.bottombarChangePW.getStyleClass().add("bottombar");
		
		this.btBackToDo = new Button();
		this.btBackToDo.getStyleClass().add("btMyToDo");
		
		this.iconHome = new ImageView(ICONHOME);
		this.btBackToDo.setGraphic(this.iconHome);
		this.iconHome.setFitHeight(40);
		this.iconHome.setFitWidth(40);
		
		this.btChange = new Button("Speichern");
		this.btChange.getStyleClass().add("btChange");
		
		this.lbPortPWView = new Label("Port: ");
		this.lbPortPWView.getStyleClass().add("lbServerPort");
		this.lbPortNrPWView = new Label(this.PORTNRPWVIEW);
		this.lbPortNrPWView.getStyleClass().add("lbServerPort");
		this.lbServerPWView = new Label("Server IP: ");
		this.lbServerPWView.getStyleClass().add("lbServerPort");
		this.lbServerIPPWView = new Label(this.SEREVRIPPWVIEW);
		this.lbServerIPPWView.getStyleClass().add("lbServerPort");
		this.lbChangePW = new Label("Passwort ändern");
		this.lbChangePW.getStyleClass().add("lbChangePW");
		this.lbNewPW = new Label("Neues Passwort");
		this.lbNewPW.getStyleClass().add("lbNewPW");
		
		this.txtNewPW = new TextField();
		
		this.centerChangePW.add(this.lbChangePW, 0, 0, 2, 1);
		this.centerChangePW.add(this.lbNewPW, 0, 1);
		this.centerChangePW.add(this.txtNewPW, 1, 1);
		this.centerChangePW.add(this.btChange, 0, 2, 2, 1);
		
		
		this.toolbarPWView.getItems().addAll(this.btBackToDo);
		this.bottombarChangePW.getItems().addAll(this.lbPortPWView, this.lbPortNrPWView, this.lbServerPWView, this.lbServerIPPWView);
		
		this.changePWView.setTop(this.toolbarPWView);
		this.changePWView.setCenter(this.centerChangePW);
		this.changePWView.setBottom(this.bottombarChangePW);
		
		
		//View für ToDo's zu erstellen und Detailansicht
		
		this.toDoView = new BorderPane();
		this.toDoView.getStyleClass().add("toDoView");
		
		this.centerToDo = new GridPane();
		this.centerToDo.getStyleClass().add("centerToDo");
		
		this.toolbarToDo = new ToolBar();
		this.toolbarToDo.getStyleClass().add("toolbarToDo");
		this.bottombarToDo = new ToolBar();
		this.bottombarToDo.getStyleClass().add("bottombarToDo");
		
		
		this.btHome = new Button();
		this.btHome.getStyleClass().add("btHome");
		
		this.iconHomeToDo = new ImageView(ICONHOMETODO);
		this.btHome.setGraphic(this.iconHomeToDo);
		this.iconHomeToDo.setFitHeight(40);
		this.iconHomeToDo.setFitWidth(40);
		
		this.btSave = new Button();
		this.btSave.getStyleClass().add("btSave");
		
		this.iconSave = new ImageView(ICONSAVE);
		this.btSave.setGraphic(this.iconSave);
		this.iconSave.setFitHeight(40);
		this.iconSave.setFitWidth(40);
		
		this.btDelete = new Button();
		this.btDelete.getStyleClass().add("btDelete");
		
		this.iconDone = new ImageView(ICONDONE);
		this.btDelete.setGraphic(this.iconDone);
		this.iconDone.setFitHeight(40);
		this.iconDone.setFitWidth(40);
		
		this.spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		this.spacer2 = new HBox();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		this.bottom = new HBox();
		this.bottom.getChildren().add(this.btDelete);
		this.bottom.getStyleClass().add("bottom");
		
		this.lbTitle = new Label("Titel *");
		this.lbTitle.getStyleClass().add("lbToDoForm");
		this.lbDescription = new Label("Beschreibung *");
		this.lbDescription.getStyleClass().add("lbToDoForm");
		this.lbDueDate = new Label("Fälligkeitsdatum");
		this.lbDueDate.getStyleClass().add("lbToDoForm");
		this.lbPriority = new Label("Priorität *");
		this.lbPriority.getStyleClass().add("lbToDoForm");
		this.lbPflichtfelder = new Label("* Pflichtfeld");
		this.lbPflichtfelder.getStyleClass().add("lbPflichtfeld");
		
		this.txtTitle = new TextField();
		this.txtTitle.getStyleClass().add("fieldsToDo");
		this.txtaDescription = new TextArea();
		this.txtaDescription.getStyleClass().add("fieldsToDo");
		
		this.dpDueDate = new DatePicker();
		this.dpDueDate.getStyleClass().add("fieldsToDo");
		dpDueDate.setDayCellFactory(picker -> new DateCell() {
		        public void updateItem(LocalDate date, boolean empty) {
		            super.updateItem(date, empty);
		            LocalDate today = LocalDate.now();

		            setDisable(empty || date.compareTo(today) < 0 );
		        }
		        
		    });
		
		dpDueDate.setEditable(false);
		
		this.txtID = new TextField();
		this.txtID.setVisible(false);
		
		this.chbPriority = new ChoiceBox <Prio>();
		this.chbPriority.getStyleClass().add("fieldsToDo");
		this.chbPriority.getItems().addAll(Prio.values());
		
		this.centerToDo.add(this.lbTitle, 0, 0);
		this.centerToDo.add(this.txtTitle, 1, 0);
		this.centerToDo.add(this.lbDescription, 0, 1);
		this.centerToDo.add(this.txtaDescription, 1, 1);
		this.centerToDo.add(this.lbPriority, 0, 2);
		this.centerToDo.add(this.chbPriority, 1, 2);
		this.centerToDo.add(this.lbDueDate, 0, 3);
		this.centerToDo.add(this.dpDueDate, 1, 3);
		this.centerToDo.add(this.txtID, 1, 6);
		this.centerToDo.add(this.lbPflichtfelder, 0, 7);
		
		
		this.toolbarToDo.getItems().addAll(this.btHome, this.spacer, this.btSave);
		this.bottombarToDo.getItems().addAll(this.spacer2, this.bottom);
		
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
		
		sceneChangePW = new Scene(changePWView, 700, 550);
		sceneChangePW.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(sceneChangePW);
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
		myList.getItems().clear();
		stage.show();
		
	}


	public void changeViewPW() {
		stage.setScene(sceneChangePW);
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


	public void changeViewMyToDOs() {
		stage.setScene(sceneMainView);
		stage.show();
		
	}


	public void changeToRootView() {
		stage.setScene(sceneRoot);
		stage.show();
		
	}


	

}
