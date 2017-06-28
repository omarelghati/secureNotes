package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Aj implements Initializable {
	@FXML private ComboBox<String> modeAjout;
	@FXML private ComboBox<String> tacheOuNote;

	@FXML private Button buttonValiderChoix;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		modeAjout.getItems().add("OCR");
		modeAjout.getItems().add("SpeechToText");
		modeAjout.getItems().add("Remplissage de champs");
		tacheOuNote.getItems().add("Note");
		tacheOuNote.getItems().add("Tache");
	}
	public void ajouterNotes() throws Exception{
		Stage st = new Stage();
		Stage a= new Stage();
		a= (Stage) buttonValiderChoix.getScene().getWindow();
		a.close();
		String choix =modeAjout.getSelectionModel().getSelectedItem();
		String tacheonote =tacheOuNote.getSelectionModel().getSelectedItem();

			if(choix=="OCR" && tacheonote=="Note" )
			{

				Parent rt = FXMLLoader.load(getClass().getResource("NoteOCR.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="OCR" && tacheonote=="Tache"  )
			{
				Parent rt = FXMLLoader.load(getClass().getResource("TacheOCR.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="Remplissage de champs" && tacheonote=="Note")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("NoteRemp.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="Remplissage de champs" && tacheonote=="Tache")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("TacheRemp.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="SpeechToText" && tacheonote=="Note")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("NoteSpeech.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="SpeechToText" && tacheonote=="Tache")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("TacheSpeech.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}

			st.initModality(Modality.APPLICATION_MODAL);
			st.initOwner(buttonValiderChoix.getScene().getWindow());
			st.show();


	}


}
