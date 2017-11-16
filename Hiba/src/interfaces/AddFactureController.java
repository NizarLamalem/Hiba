package interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddFactureController implements Initializable {
	@FXML
	private TextField Article;;

	@FXML
	private Spinner<Integer> qst;

	@FXML
	private TableView<?> stockTable;

	@FXML
	private TableColumn<?, ?> stockColumn;

	@FXML
	private TableColumn<?, ?> stockQuantityColumn;

	@FXML
	private TableView<?> factureTable;

	@FXML
	private TableColumn<?, ?> factureArticle;

	@FXML
	private TableColumn<?, ?> factureQuantity;

	@FXML
	private TableColumn<?, ?> factureAction;

	@FXML
	private JFXButton addArticle;

	@FXML
	private JFXButton cancel;

	@FXML
	private JFXButton seeFacture;

	@FXML
	private JFXButton createFacture;

	@FXML
	void onClick(MouseEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(Article,"Zin","Titiz","Drafa","Khayba") ;
	}

}
