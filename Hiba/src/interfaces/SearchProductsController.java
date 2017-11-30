package interfaces;


import dataBase.*;
import dao.Article;
import application.Main;
import java.net.URL;

import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import dao.Article;
import org.controlsfx.control.textfield.TextFields;

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

public class SearchProductsController implements Initializable{

    @FXML
    private TextField chercherArticle;
    
    @FXML
    private TableView<Article> tableProducts;

    @FXML
    private TableColumn<?, ?> columnEV;

    @FXML
    private TableColumn<?, ?> columnRef;

    @FXML
    private TableColumn<?, ?> columnDsg;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnTarif;

    @FXML
    private TableColumn<?, ?> columnNet;

    @FXML
    private TableColumn<?, ?> columnHT;

    @FXML
    private TableColumn<?, ?> columnTVA;
        
	private ObservableList<Article> data = FXCollections.observableArrayList();
	FilteredList<Article> filteredData = new FilteredList<>(data, e->true);
	DataBase database;
    
    private void setCellTable() {
    	columnEV.setCellValueFactory(new PropertyValueFactory<>("ev"));
    	columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
    	columnDsg.setCellValueFactory(new PropertyValueFactory<>("designation"));
    	columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
    	columnTarif.setCellValueFactory(new PropertyValueFactory<>("prixTarif"));
    	columnNet.setCellValueFactory(new PropertyValueFactory<>("prixNette"));
    	columnHT.setCellValueFactory(new PropertyValueFactory<>("ht"));
    	columnTVA.setCellValueFactory(new PropertyValueFactory<>("tva"));
    }
    
	private void loadProducts() throws Exception{
		data.addAll(database.getArticles("-1"));
		tableProducts.setItems(data);
	}

    public void searchEV() {
    	chercherArticle.textProperty().addListener((ObservableValue,oldValue,newValue)->{
    		filteredData.setPredicate((Predicate<? super Article>)article->{
    			if(newValue==null || newValue.isEmpty()) {
    				return true;
    			}
    			String lowerCaseFilter = newValue.toLowerCase();
    			if(article.getEv().toLowerCase().contains(lowerCaseFilter)) {
    				return true;
    			}
    			return false;
    		});
    	});
    	SortedList<Article> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(tableProducts.comparatorProperty());
    	tableProducts.setItems(sortedData);
    }
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			database = new DataBase();
			setCellTable();
			loadProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
}
