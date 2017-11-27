package interfaces;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.omg.PortableServer.ServantRetentionPolicyValue;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.Article;
import dao.Facture;
import dao.StockQte;
import dataBase.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private TableView<TokenData> factureTable;

	@FXML
	private TableColumn<TokenData, String> factureArticle;

	@FXML
	private TableColumn<TokenData, Integer> factureQuantity;

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
	@FXML
	private JFXButton searchArticle;

	private ObservableList<StockQte> stockTableData;
	private ObservableList<TokenData> FactureTableData;

	public static Facture facture;
	// Hors Taxes ==0 and Exonory
	private int Type = -1;
	// The Stock Of The Current facture
	private String Stock = "";

	public static Article currentArticle;
	public static String CIN = "";
	public static String ADDRESS = "";

	@FXML
	void onClick(MouseEvent event) throws Exception {
		if (event.getSource() == detaills) {
			Action("AddFactureDetails", "Plus d'information");

		} else {
			if (event.getSource() == createFacture) {
				if (facture.getIds() != -1) {
					Main.database.addFacture(facture);
					clearAll();
				} else {
					alert("Creation Imossible",
							"Facture Peut Pas Etre Créé \n assurez vous que vous avez que tous ce que f");
				}
			} else {
				if (event.getSource() == seeFacture) {

				} else {
					if (event.getSource() == addArticle) {
						// TODO test if Its not Taxable
						if (qst.getValue() != 0 && Article.getText() != ""
								&& !checkIfAlreadyinTheTable(currentArticle.getEv())) {

							System.out.println("Type ==+> " + Type);
							if (Type == 1 && CIN == "" && ADDRESS == "") {
								Action("factureMoreData", "Plus D'information Nécessaire");
							} else {
								AddToTableFacture();
							}

						} else {
							alert("Problem !!!", "Cette Article est Deja  ajouté au Facture");
						}
					} else {
						if (event.getSource() == cancel) {
							clearAll();
						} else {
							if (event.getSource() == searchArticle) {
								try {

									stockTable.getItems().clear();

									if (qst.getValue() != 0) {
										// System.out.println(Article.getText());
										currentArticle = Main.database.getArticles(Article.getText()).get(0);
										// System.out.println(currentArticle);
										fillTableStock(Article.getText());
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
					}
				}
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		facture = new Facture();
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
		factureArticle.setCellValueFactory(new PropertyValueFactory<>("ev"));
		factureQuantity.setCellValueFactory(new PropertyValueFactory<>("qte"));
		stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
		stockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));

		stockTableData = FXCollections.observableArrayList();
		FactureTableData = FXCollections.observableArrayList();
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
			if (xmlFile.equals("factureMoreData")) {
				A.setOnHidden(e -> {
					// process input here...
					AddToTableFacture();
				});
			}
			A.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void fillTableStock(String ev) {
		// The Stock qst return by Database ,
		ArrayList<StockQte> stockQte = null;
		try {
			stockQte = Main.database.getArticlesStocks(ev);
			stockQte = dataBasesThatCanSatisfactTheQuantityOrdered(stockQte);
			stockTableData.addAll(FXCollections.observableArrayList(stockQte));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stockTable.setItems(stockTableData);

	}

	public void fillTableFacture(TokenData article) {
		// The Stock qst return by Database ,
		ArrayList<TokenData> factureArticles = new ArrayList<>();
		factureArticles.add(article);
		FactureTableData.addAll(FXCollections.observableArrayList(factureArticles));
		factureTable.setItems(FactureTableData);

	}

	// Monitor The databases and return only those who will satisfact the
	// quantity
	private ArrayList<StockQte> dataBasesThatCanSatisfactTheQuantityOrdered(ArrayList<StockQte> stockQt) {
		stockQt = OrdrStocks(stockQt);
		ArrayList<StockQte> listFinal = new ArrayList<StockQte>();

		// Save The Stock Type For FuTure Use
		Stock = stockQt.get(0).getStock();
		Type = currentArticle.getType();
		// for collecting and add all The quantities togather
		int size = 0;
		for (int i = 0; i < stockQt.size(); i++) {
			listFinal.add(stockQt.get(i));
			size += stockQt.get(i).getQte();
			// System.out.println(stockQt.get(i));
			if (stockQt.get(i).getQte() >= qst.getValue()) {
				// System.out.println(stockQt.get(i));
				// System.out.println("The Spinner Value =+>" + qst.getValue());
				return listFinal;
			}
			// when The Size of the Article in the mentioned ataBase after
			// adding them together become more then the qte mentioned
			if (size >= qst.getValue()) {
				return listFinal;
			}

		}
		return listFinal;
	}

	// ordring the list in ordre to make the traitment much easier
	private ArrayList<StockQte> OrdrStocks(ArrayList<StockQte> stockQt) {
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

	private void clearAll() {
		factureTable.getItems().clear();
		stockTable.getItems().clear();
		Article.setText("");
		qst.getValueFactory().setValue(0);
		currentArticle = null;
		Stock = "";
		Type = -1;
		CIN = "";
		ADDRESS = "";
		facture = new Facture();
	}

	// After all the Tests This methode gonna add the desired Article to The
	// Table
	private void AddToTableFacture() {
		TokenData Tmp = new TokenData(currentArticle.getEv(), qst.getValue() < stockQuantityColumn.getCellData(0)
				? qst.getValue() : stockQuantityColumn.getCellData(0), Stock);
		fillTableFacture(Tmp);
		facture.setIds(DataBase.mappingStock(Stock));
		facture.addArticle(currentArticle);
		facture.addTokenData(Tmp);
		facture.setAddress(ADDRESS);
		facture.setCin(CIN);
		Date A = new Date();
		DateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd");
		facture.setDate_Facture(simpleDate.format(A));

		// not Tested Yet
		// currentArticle = null;
	}

	// check if the item You wanna add is already
	private boolean checkIfAlreadyinTheTable(String EV) {
		for (TokenData A : factureTable.getItems()) {
			if (A.ev.equals(EV)) {
				return true;
			}
		}
		return false;
	}

	private void alert(String title, String conetent) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(conetent);
		alert.showAndWait();
	}

	// This class serves one Purpose filling the table Facture
	public class TokenData {
		private String ev;
		private int qte;
		private String stock;

		public TokenData(String ev, int qte, String stock) {
			super();
			this.ev = ev;
			this.qte = qte;
			this.stock = stock;
		}

		public String getStock() {
			return stock;
		}

		public void setStock(String stock) {
			this.stock = stock;
		}

		public String getEv() {
			return ev;
		}

		public void setEv(String ev) {
			this.ev = ev;
		}

		public int getQte() {
			return qte;
		}

		public void setQte(int qte) {
			this.qte = qte;
		}

	}

}
