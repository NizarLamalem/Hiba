package interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FactureMoreDataController implements Initializable {

	@FXML
	private TextField address;

	@FXML
	private TextField cin;

	@FXML
	private Label notificationAddresse;

	@FXML
	private Label notificationCin;

	@FXML
	private JFXButton Ajoute;

	private boolean wrong = false;
	// this labels are concerning the Notification
	private Label[] Notifications;
	private TextField[] Fields;

	@FXML
	void onClick(MouseEvent event) {
		wrong = false;
		hideNotification();
		for (int i = 0; i < 2; i++) {
			if (Fields[i].getText().equals("")) {
				Notifications[i].setOpacity(1);
				if (wrong == false)
					wrong = true;
			}
		}
		// If All The Data Is Set
		// Insert Give The Data To The Main Interface ;
		if (!wrong) {
			// TODO Do Treatment
			AddFactureController.ADDRESS = address.getText();
			AddFactureController.CIN = address.getText();
			((Stage) Ajoute.getScene().getWindow()).close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Notifications = new Label[2];
		Notifications[0] = notificationCin;
		Notifications[1] = notificationAddresse;

		Fields = new TextField[2];
		Fields[0] = cin;
		Fields[1] = address;
	}

	private void hideNotification() {
		notificationCin.setOpacity(0);
		notificationAddresse.setOpacity(0);
	}

}
