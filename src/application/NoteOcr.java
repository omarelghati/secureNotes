package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.asprise.ocr.Ocr;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class NoteOcr implements Initializable{
	@FXML private TextField nom;
	@FXML private TextField url;
	@FXML private Button ajouter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub


	}
	@FXML
	public void close(){
		try{
			Stage st= null;
			//////
			st = (Stage)nom.getScene().getWindow();
			st.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void addmethod(){
		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
		String s = ocr.recognize(new File[] {new File(url.getText())}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
		System.out.println("Result: " + s);
		// ocr more images here ...
		ocr.stopEngine();
		try{
			Connection connection = DBConnection.connector();

			 String query ="INSERT INTO `note`(`nom`, `description`, `importance`,`user`) VALUES('"+nom.getText()+"','"+s+"','1','"+FXController.maVar+"')";
			 int r = connection.createStatement().executeUpdate(query);
			 close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
