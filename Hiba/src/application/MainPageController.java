package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainPageController implements Initializable {

	@FXML
	private JFXDrawer drawer;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private AnchorPane root;

	@FXML
	private Label title;
	@FXML
	private AnchorPane rootP;

	public static AnchorPane RootP;
	public static JFXDrawer Drawer;
	public static HamburgerBackArrowBasicTransition transition;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// To Make The Buttons
		rootP.toFront();
		try {
			RootP = rootP;
			Drawer = drawer;
			VBox box = FXMLLoader.load(getClass().getResource("Drawer.fxml"));
			drawer.setSidePane(box);
		} catch (IOException ex) {
			Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
		}

		transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
			
			if (drawer.isShown()) {
				drawer.close();
				rootP.toFront();
			} else {
				drawer.toFront();
				;
				drawer.open();
			}
		});
	}

}
