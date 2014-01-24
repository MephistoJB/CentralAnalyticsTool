package WorkBooster.gui.customControls;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class AufgabenTab extends GridPane {

	public AufgabenTab() {
		try {
		FXMLLoader fxmlLoader = new FXMLLoader();
		URL url = getClass().getResource(getClass().getSimpleName() + ".fxml");
		File f = new File(url.toURI());
		fxmlLoader.setLocation(f.toURI().toURL());
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
