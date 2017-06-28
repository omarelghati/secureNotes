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


public class NoteDetails implements Initializable {

	@FXML private Label title;
	@FXML private Button close;
	@FXML private Label body;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hey");
		title.setText("Titre : "+Acceuil.selectedNote.getNom());
		body.setText(Acceuil.selectedNote.getDescription());
	}
	@FXML
	public void close(){
		try{
			Stage st= null;
			//////
			st = (Stage)close.getScene().getWindow();
			st.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@FXML
	public void delete() throws SQLException {
		Connection connection = DBConnection.connector();
		if(connection == null) System.out.println("erreur de connexion");
		connection.createStatement().executeUpdate("delete from note where nom='"+Acceuil.selectedNote.getNom()+"' and user='"+FXController.maVar+"'");
		this.close();
	}
}
