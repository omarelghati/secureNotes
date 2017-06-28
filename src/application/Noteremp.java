package application;

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
import javafx.scene.control.TextField;

public class Noteremp implements Initializable {
	@FXML private TextField nom;
	@FXML private javafx.scene.control.TextArea description ;
	@FXML private Button enregistrer;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	public void inserer(){
		try{
			Connection connection = DBConnection.connector();
			 String query ="INSERT INTO `note`(`nom`, `description`, `importance`,`user`) VALUES('"+nom.getText()+"','"+description.getText()+"','1','"+FXController.maVar+"')";
			 connection.createStatement().executeUpdate(query);


		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
