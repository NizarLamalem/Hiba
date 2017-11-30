package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DrawerController {

	@FXML
	private JFXButton nouvelleFacture;

	@FXML
	private JFXButton afficherArticles;

	@FXML
	private JFXButton afficherFactures;

	@FXML
	private JFXButton Exite;

	@FXML
	void onClick(MouseEvent event) throws IOException {

		String Destination = "Null";
		Object Tmp = event.getSource();
		
		MainPageController.transition.setRate(MainPageController.transition.getRate() * -1);
		MainPageController.transition.play();
		
		if (Tmp == nouvelleFacture) {
			// todo Tratment Nouvelle Facture ;
			Destination = "AddFacture";

		} else {
			if (Tmp == afficherArticles) {
				// todo article Tretment
				Destination = "SearchProducts";
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
		
		MainPageController.Drawer.close();
		MainPageController.RootP.toFront();
		if (Destination != null && MainPageController.RootP != null) {
			MainPageController.RootP.getChildren().setAll(
					(AnchorPane) FXMLLoader.load(getClass().getResource("/interfaces/" + Destination + ".fxml")));
			Destination = "null";
			
		}

	}

}