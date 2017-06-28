package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class TacheDetails implements Initializable {

	@FXML private Label title;
	@FXML private Button close;
	@FXML private Label body;
	@FXML private Label degree;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hey");
		title.setText("Titre : "+Acceuil.selectedTache.getNom());
		body.setText(Acceuil.selectedTache.getDescription());
		degree.setText("Degré : "+Acceuil.selectedTache.getDegre());
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
		connection.createStatement().executeUpdate("delete from tache where nom='"+Acceuil.selectedTache.getNom()+"' and user='"+FXController.maVar+"'");
		this.close();
	}
}
