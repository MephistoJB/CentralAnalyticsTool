package CentralAnalyticsTool.gui.customControls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

;

public class ImportVorgangController implements Initializable {

	@FXML
	private ComboBox<ImportVorgangAuswahl> importVorgangAuswahl;

	@FXML
	private Button importVorgangOKButton;

	@FXML
	private Text importVorgangText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert this.importVorgangAuswahl != null : "fx:id=\"importVorgangAuswahl\" was not injected: check your FXML file 'WorkBooster.fxml'.";
		assert this.importVorgangOKButton != null : "fx:id=\"importVorgangOKButton\" was not injected: check your FXML file 'WorkBooster.fxml'.";
		assert this.importVorgangText != null : "fx:id=\"importVorgangText\" was not injected: check your FXML file 'WorkBooster.fxml'.";
		this.initializeComboBox();
	}

	private void initializeComboBox() {
		ObservableList<ImportVorgangAuswahl> listImportVorgangAuswahl = FXCollections
				.observableArrayList(ImportVorgangAuswahl.values());

		this.importVorgangAuswahl.setItems(listImportVorgangAuswahl);
		this.importVorgangAuswahl.setValue(ImportVorgangAuswahl.values()[0]);
	}
}
