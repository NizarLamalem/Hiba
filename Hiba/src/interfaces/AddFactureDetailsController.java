package interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddFactureDetailsController implements Initializable {

	@FXML
	private JFXButton exit;

	@FXML
	private TextField EV;

	@FXML
	private TextField type;

	@FXML
	private TextField prixNette;

	@FXML
	private TextField tva;

	@FXML
	private TextField prixTarif;

	@FXML
	private TextField ht;

	@FXML
	private TextField designation;

	@FXML
	private TextField refference;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		if (AddFactureController.currentArticle != null) {
			designation.setText(AddFactureController.currentArticle.getDesignation());
			EV.setText(AddFactureController.currentArticle.getEv());
			type.setText(""+AddFactureController.currentArticle.getType());
			prixNette.setText(""+AddFactureController.currentArticle.getPrixNette());
			tva.setText(""+AddFactureController.currentArticle.getTva());
			prixTarif.setText(""+AddFactureController.currentArticle.getPrixTarif());
			ht.setText(""+AddFactureController.currentArticle.getHt());
			refference.setText(AddFactureController.currentArticle.getReference());

		}

	}

	@FXML
	void onClick(MouseEvent event) {
		((Stage) exit.getScene().getWindow()).close();
	}

}
