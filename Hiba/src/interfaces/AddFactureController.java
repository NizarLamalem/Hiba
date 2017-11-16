package interfaces;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import dao.* ;
import application.Main;
import application.MainPageController;
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
		//When ever We are Using Articles Or Factures We Gonna check 
		//if we alredy have the data stores in our App 
		
		if(Main.Articles ==null){
			try {
				Main.Articles=Main.database.getArticles("-1") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Getting All The EV From
		LinkedList<String> Tmp  =new LinkedList<String>() ;
		for(Article A:Main.Articles){
			Tmp.add(A.getEv()) ;
		}
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(Article,Tmp);
	}

}
