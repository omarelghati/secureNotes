package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.asprise.ocr.Ocr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TacheOcr implements Initializable {
	@FXML private TextField nom;
	@FXML private TextField url;
	@FXML private ComboBox<String>degre;
	@FXML private Button ajouter;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		degre.getItems().add("Fort");
		degre.getItems().add("Moyen");
		degre.getItems().add("Faible");

	}
	@FXML
	public void close(){
		try{
			Stage st= null;
			//////
			st = (Stage)degre.getScene().getWindow();
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

			 String query ="INSERT INTO `tache`(`nom`, `description`, `degre`,`user`) VALUES('"+nom.getText()+"','"+s+"','"+degre.getSelectionModel().getSelectedItem()+"','"+FXController.maVar+"')";
			 int r = connection.createStatement().executeUpdate(query);
			 close();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
