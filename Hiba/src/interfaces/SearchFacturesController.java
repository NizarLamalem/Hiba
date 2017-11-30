package interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import dao.Facture;
import dataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchFacturesController implements Initializable{

	@FXML
	private TextField chercherFacture;

	@FXML
	private TableView<Facture> tableFactures;

	@FXML
	private TableColumn<?, ?> columnIDFacture;

	@FXML
	private TableColumn<?, ?> columnIDStock;

	@FXML
	private TableColumn<?, ?> columnDate;

	@FXML
	private TableColumn<?, ?> columnCIN;

	@FXML
	private TableColumn<?, ?> columnAdr;

	@FXML
	private TableColumn<?, ?> columnPrixHT;

	@FXML
	private TableColumn<?, ?> columnPrixTTC;

	private ObservableList<Facture> data = FXCollections.observableArrayList();
	FilteredList<Facture> filteredData = new FilteredList<>(data, e->true);
	DataBase database;
	
	private void setCellTable() {
		columnIDFacture.setCellValueFactory(new PropertyValueFactory<>("idf"));
		columnIDStock.setCellValueFactory(new PropertyValueFactory<>("ids"));
		columnDate.setCellValueFactory(new PropertyValueFactory<>("date_Facture"));
		columnCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
		columnAdr.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnPrixHT.setCellValueFactory(new PropertyValueFactory<>("priceHT"));
		columnPrixTTC.setCellValueFactory(new PropertyValueFactory<>("priceTTC"));
	}

	private void loadFactures() throws Exception{
		data.addAll(database.getFactures(-1));
		tableFactures.setItems(data);
	}
	
	public void searchIdsCinDate() {
		chercherFacture.textProperty().addListener((ObservableValue,oldValue,newValue)->{
    		filteredData.setPredicate((Predicate<? super Facture>)facture->{
    			if(newValue==null || newValue.isEmpty()) {
    				return true;
    			}
    			String lowerCaseFilter = newValue.toLowerCase();
    			if(facture.getCin().toLowerCase().contains(lowerCaseFilter)) {
    				return true;
    			}/*else if(String.valueOf(facture.getIds()).contains(lowerCaseFilter)) {
    				return true;
    			}else if(facture.getDate_Facture().equals(Date.valueOf(lowerCaseFilter))){
    				return true;
    			}*/
    			return false;
    		});
    	});
    	SortedList<Facture> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(tableFactures.comparatorProperty());
    	tableFactures.setItems(sortedData);
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			database = new DataBase();
			setCellTable();
			loadFactures();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

