package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DrawerController implements Initializable {

	@FXML
	private JFXButton nouvelleFacture;

	@FXML
	private JFXButton afficherArticles;

	@FXML
	private JFXButton afficherFactures;

	@FXML
	private JFXButton Exite;

	@FXML
	private ImageView back;

	@FXML
	void onClick(MouseEvent event) throws IOException {

		String Destination = "Null";
		Object Tmp = event.getSource();

		MainPageController.transition.setRate(-1);
		MainPageController.transition.setRate(-1);
		MainPageController.transition.play();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		back.setImage(new Image("/Resources/back.jpg"));
	}

}