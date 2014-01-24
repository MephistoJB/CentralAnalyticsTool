/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CentralAnalyticsTool.gui.customControls;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * 
 * 
 * @author ROBT
 */
public class test extends VBox {

	@FXML
	private AnchorPane myTestButton;

	public test() {
		//FXMLLoader fxmlLoader = new FXMLLoader(
		
		//getClass().getResource("/C:/Users/Anna/workspace/WorkBooster3/src/WorkBooster/gui/customControls/test.fxml"));

		FXMLLoader fxmlLoader = new FXMLLoader();
		URL url;
		try {
			url = getClass().getResource("test.fxml");
			File f = new File(url.toURI());
			System.out.println(f.exists());
			fxmlLoader.setLocation(f.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}