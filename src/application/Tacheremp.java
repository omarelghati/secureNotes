package application;

import java.awt.TextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Tacheremp implements Initializable{
	@FXML private TextField nom;
	@FXML private javafx.scene.control.TextArea description ;
	@FXML private ComboBox<String>degre;
	@FXML private Button enregistrer;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		degre.getItems().add("Fort");
		degre.getItems().add("Faible");
		degre.getItems().add("moyen");
	}
	public void inserer(){
		try{
			Connection connection = DBConnection.connector();
			 String query ="INSERT INTO `tache`(`nom`, `description`, `degre`,`user`) VALUES('"+nom.getText()+"','"+description.getText()+"','"+(String) degre.getValue()+"','"+FXController.maVar+"')";
			 int r = connection.createStatement().executeUpdate(query);
			 Stage s = (Stage) degre.getScene().getWindow();
			 s.close();


		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
