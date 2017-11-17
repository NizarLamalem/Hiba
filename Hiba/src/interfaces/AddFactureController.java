package interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.Article;
import dao.StockQte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddFactureController implements Initializable {
	@FXML
	private TextField Article;;

	@FXML
	private Spinner<Integer> qst;

	@FXML
	private TableView<StockQte> stockTable;

	@FXML
	private TableColumn<StockQte, String> stockColumn;

	@FXML
	private TableColumn<StockQte, Integer> stockQuantityColumn;

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
	private JFXButton detaills;

	private ObservableList<StockQte> stockTableData;

	@FXML
	void onClick(MouseEvent event) {
		if (event.getSource() == detaills) {
			Action("AddFactureDetails", "Plus d'information");

		} else {
			if (event.getSource() == createFacture) {

			} else {
				if (event.getSource() == seeFacture) {

				} else {
					if (event.getSource() == addArticle) {
						// TODO test if Its not Taxable
						Action("factureMoreData", "Plus D'information Nécessaire");
					} else {
						if (event.getSource() == cancel) {

						}
					}
				}
			}
		}

	}

	@FXML
	void OnAction(ActionEvent event) {
		if (qst.getValue() != 0) {
			fillTable(Article.getText());
		}
	}

	@FXML
	void OnActionEvent(MouseEvent event) {
		if (qst.getValue() != 0 && Article.getText() != "") {
			fillTable(Article.getText());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// When ever We are Using Articles Or Factures We Gonna check
		// if we alredy have the data stores in our App
		if (Main.Articles == null) {
			try {
				Main.refrech_Articles();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		stockTableData = FXCollections.observableArrayList();
		// Value factory takes the Min value , The Max Value , and The First
		// Value to appear
		SpinnerValueFactory<Integer> valueFactory = //
		new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		// Getting All The EV From
		LinkedList<String> Tmp = new LinkedList<String>();
		for (Article A : Main.Articles) {
			Tmp.add(A.getEv());
		}
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(Article, Tmp);
		qst.setValueFactory(valueFactory);

	}

	public void Action(String xmlFile, String Title) {
		Stage A;
		Scene Sc;
		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/interfaces/" + xmlFile + ".fxml"));
			Sc = new Scene(root);
			Sc.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			A = new Stage();
			A.initOwner((Stage) cancel.getScene().getWindow());
			A.setTitle(Title);
			A.setScene(Sc);
			A.setResizable(false);
			A.initModality(Modality.WINDOW_MODAL);
			A.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void fillTable(String ev) {
		stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
		stockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
		//The Stock qst return by Database ,
		ArrayList<StockQte> stockQte =null;
		try {
			stockQte =Main.database.getArticlesStocks(ev) ;
			stockQte=tableCheckData(stockQte) ;
			stockTableData.addAll(FXCollections.observableArrayList(stockQte));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stockTable.setItems(stockTableData);

	}

	// Monitore The databases and return only those who will satisfact the
	// cquantity
	private ArrayList<StockQte> tableCheckData(ArrayList<StockQte> stockQt) {
		stockQt=OrdrStocks(stockQt) ;
		for(StockQte A : stockQt){
			System.out.println(A);
		}
		return stockQt;
	}
	
	// ordring the list in ordre to make the traitment much easier
	private  ArrayList<StockQte> OrdrStocks(ArrayList<StockQte> stockQt) {
			Collections.sort(stockQt, new Comparator<StockQte>() {
				@Override
				public int compare(StockQte o1, StockQte o2) {
					if (o1.getId() > o2.getId())
						return 1;
					if (o2.getId() > o1.getId())
						return -1;
					return 0;
				}
			});
			return stockQt;
	}

}
