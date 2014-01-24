package CentralAnalyticsTool.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import CentralAnalyticsTool.gui.customControls.AufgabenTabAuswahl;
import CentralAnalyticsTool.gui.customControls.ImportVorgang;

;

public class WorkBoosterController implements Initializable {

	@FXML
	private ComboBox<AufgabenTabAuswahl> aufgabenAuswahl;
	
	@FXML
	private Button aufgabenOKButton;
	
	@FXML
	private TabPane aufgabenTabbedPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert this.aufgabenAuswahl != null : "fx:id=\"aufgabenAuswahl\" was not injected: check your FXML file 'WorkBooster.fxml'.";
		assert this.aufgabenOKButton != null : "fx:id=\"aufgabenOKButton\" was not injected: check your FXML file 'WorkBooster.fxml'.";
		assert this.aufgabenTabbedPane != null : "fx:id=\"aufgabenTabbedPane\" was not injected: check your FXML file 'WorkBooster.fxml'.";
		this.initializeComboBox();
	}

	private void initializeComboBox() {
		ObservableList<AufgabenTabAuswahl> listAufgabenTabAuswahl = FXCollections
				.observableArrayList(AufgabenTabAuswahl.values());

		this.aufgabenAuswahl.setItems(listAufgabenTabAuswahl);
		this.aufgabenAuswahl.setValue(AufgabenTabAuswahl.values()[0]);
	}
	
	public void fuegeTabHinzu(){
		Tab tab = new Tab();
		switch(this.aufgabenAuswahl.getValue()){
			case Import: 	this.fuegeImportVorgangHinzu(tab); break;
			default: System.out.println(this.aufgabenAuswahl.getValue());
		}
		this.aufgabenTabbedPane.getTabs().add(tab);
	}
	
	private void fuegeImportVorgangHinzu(Tab tab){
		ImportVorgang grid = new ImportVorgang();
		tab.setContent(grid);
		tab.setText("Neuer Importvorgang");
		this.aufgabenTabbedPane.getSelectionModel().select(tab);
	}
}
