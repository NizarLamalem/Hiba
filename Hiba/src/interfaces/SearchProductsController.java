package interfaces;


import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchProductsController {

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
    
    @FXML
    private TextField chercherArticle;
    
	private ObservableList<Article> data;
    //private ResultSet rs = null;
    //private PreparedStatement pst = null;
    //private Connection conn = null;
    
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
    
    private void loadProducts() throws Exception {
    	data.addAll(FXCollections.observableArrayList(Main.database.getArticles("-1")));
    	//try {
    		//pst = conn.prepareStatement("SELECT * FROM article");
    		//rs = pst.executeQuery();
    		//while(rs.next()) {
    		//	data.add(new Article(rs.getString(1), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getString(3), rs.getString(2)));
    		//}
    	//}catch(SQLException ex) {
            //ex.printStackTrace();
    	//}
    	tableProducts.setItems(data);
    }
    
    public void initialize(URL url, ResourceBundle rb) throws Exception {
    	setCellTable();
    	loadProducts();
    } 
}
