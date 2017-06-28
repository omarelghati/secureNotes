package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Acceuil implements Initializable {

	@FXML private Label lab;
	@FXML private Button deconnexion;
	@FXML private Button notes;
	@FXML private Button taches;
	@FXML private Button ajouter;
	@FXML private TableView<Object> tableview;
	@FXML private TableColumn<Object,String > tableNotes;
	private ObservableList<Object>data ;
	public static String note;
	public static Note selectedNote ;
	public static Tache selectedTache ;




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		notes.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		taches.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		lab.setText("Bonjour "+FXController.maVar);


		tableview.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {

		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		            Node node = ((Node) event.getTarget()).getParent();
		            TableRow row;
		            if (node instanceof TableRow) {
		                row = (TableRow) node;
		            } else {
		                // clicking on text part
		                row = (TableRow) node.getParent();
		            }
		           Object c = row.getItem();
		           System.out.println(c.getClass().toString());
		           if(c.getClass().toString().matches("class application.Note")) {
		               Note v = new Note();
			            v=(Note) row.getItem();
			            selectedNote =v;
		               try{
			    			Connection connection = DBConnection.connector();
			    			if(connection == null) System.out.println("erreur de connexion");
			    			ResultSet res = connection.createStatement().executeQuery("select * from note where nom='"+v.getNom()+"'");
			    			res.next();
			    					try{
			    						Stage st = new Stage();

			    						Parent rt = FXMLLoader.load(getClass().getResource("noteDetails.fxml"));
			    						Scene scene = new Scene(rt);
			    						st.setScene(scene);

			    						st.initModality(Modality.APPLICATION_MODAL);
			    						st.initOwner(deconnexion.getScene().getWindow());
			    						st.show();
			    					}catch(Exception e){
			    						e.printStackTrace();
			    					}

			    		}catch(SQLException e){
			    			System.out.println(e);
			    		}
			        }
		           if(c.getClass().toString().matches("class application.Tache")) {
		               Tache v = new Tache();
			            v=(Tache) row.getItem();
			            selectedTache=v;
		               try{
			    			Connection connection = DBConnection.connector();
			    			if(connection == null) System.out.println("erreur de connexion");
			    			ResultSet res = connection.createStatement().executeQuery("select * from tache where nom='"+v.getNom()+"'");
			    			res.next();
			    					try{
			    						Stage st = new Stage();

			    						Parent rt = FXMLLoader.load(getClass().getResource("tacheDetails.fxml"));
			    						Scene scene = new Scene(rt);
			    						st.setScene(scene);

			    						st.initModality(Modality.APPLICATION_MODAL);
			    						st.initOwner(deconnexion.getScene().getWindow());
			    						st.show();
			    					}catch(Exception e){
			    						e.printStackTrace();
			    					}
			    		}catch(SQLException e){
			    			System.out.println(e);
			    		}
			        }
		           }



		    }
		});

	}
	@FXML
	public void deconnect(ActionEvent event){
		try{
			Stage st= null;
			st = (Stage)deconnexion.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("JFXoverlay.fxml").openStream());
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			st.setScene(scene);
			st.show();

		}catch (Exception e){
			e.printStackTrace();
		}


	}
	public void notesButton(Event event) {
		notes.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
		taches.setStyle("-fx-background-color: black; -fx-text-fill: white;");


		try{
			Connection connection = DBConnection.connector();
			data = FXCollections.observableArrayList();
			ResultSet res = connection.createStatement().executeQuery("select * from note where user='"+FXController.maVar+"'");
			while(res.next()){
				data.add(new Note(res.getString(2),res.getString(3)));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		tableNotes.setCellValueFactory(new PropertyValueFactory<Object,String>("nom"));
		tableview.setItems(null);
		tableview.setItems(data);



	}
	public void tachesButton(Event event) {
		taches.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
		notes.setStyle("-fx-background-color: black; -fx-text-fill: white;");

		try{
			Connection connection = DBConnection.connector();
			data = FXCollections.observableArrayList();
			ResultSet res = connection.createStatement().executeQuery("select * from tache where user='"+FXController.maVar+"'");
			while(res.next()){
				data.add(new Tache(res.getString(2),res.getString(3),res.getString(4)));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		tableNotes.setCellValueFactory(new PropertyValueFactory<Object,String>("nom"));
		tableview.setItems(null);
		tableview.setItems(data);



	}
	@FXML
	public void addElement(ActionEvent event) throws Exception{
		Stage st = new Stage();

		Parent rt = FXMLLoader.load(getClass().getResource("ajout.fxml"));
		Scene scene = new Scene(rt);
		st.setScene(scene);

		st.initModality(Modality.APPLICATION_MODAL);
		st.initOwner(deconnexion.getScene().getWindow());
		st.show();


	}



}
