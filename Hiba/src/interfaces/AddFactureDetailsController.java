package interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddFactureDetailsController implements Initializable{

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
		
	}
    
	 @FXML
	    void onClick(MouseEvent event) {
		 System.exit(-1);
	    } 

}
