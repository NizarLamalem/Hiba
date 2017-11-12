package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DrawerController {

	@FXML
	private JFXButton nouvelleFacture;

	@FXML
	private JFXButton afficherArticles;

	@FXML
	private JFXButton afficherFactures;

	@FXML
	private JFXButton Exite;

	public void onClick(ActionEvent e) throws IOException {
		String Destination = "Null";
		Object Tmp = e.getSource();
		if (Tmp == nouvelleFacture) {
			// todo Tratment Nouvelle Facture ;
			Destination = "AddFacture";
		} else {
			if (Tmp == afficherArticles) {
				// todo article Tretment
				Destination = "SearchFactures";
			} else {
				if (Tmp == afficherFactures) {
					// ToDo Facture Treatment
					Destination = "SearchFactures";
				} else {
					if (Tmp == Exite) {
						System.exit(-1);
						System.out.println("Exit");
					}
				}
			}
		}
		/*if (Destination != null&&MainPageController.rootP!=null) {
			MainPageController.rootP.getChildren().setAll(
					(AnchorPane) FXMLLoader.load(getClass().getResource("Drawer.fxml")));
			Destination = "null";
			System.out.println("Destination and RootP are Not Null");
		}*/
	}

}