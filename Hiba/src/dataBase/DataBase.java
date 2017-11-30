package dataBase;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import application.Main;
import dao.Article;
import dao.Facture;
import dao.ProductsInvoice;
import dao.StockQte;
import interfaces.AddFactureController.TokenData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class DataBase {
	// The DataVase Connection
	private Connection con;
	// The Variable That Going To Hold Statements
	private String SQL;
	// The Variable That Gonna execute Statements
	private Statement stmt;
	// The Variable That Gonna Have The Result of statements
	private ResultSet rs;

	public DataBase() throws Exception {
		try {
			// Loading The Mysql Driver Class
			// connection string, and get a connection
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Succesful");

			String username = "a2d252_hiba";
			String password = "BlaBla123";
			String server = "mysql6001.site4now.net"; // remote server address
			String db = "db_a2d252_hiba";
			String url = "jdbc:mysql://" + server + "/" + db;
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	// This Function Will Returns Articls with The Specific Ids ==>if Id equals
	// -1 Then its Gooing return a all the Article
	public ArrayList<Article> getArticles(String ev) throws Exception {
		ArrayList<Article> articls = new ArrayList<>();
		if (ev.equals("-1")) {
			SQL = "SELECT * FROM `article`";

		} else {
			SQL = "SELECT * FROM `article` WHERE `Ev`='" + ev + "'";
		}
		// System.out.println(SQL);
		// Adding The Articles to The ArrayList
		rs = executeStatements(SQL);
		while (rs.next()) {
			articls.add(new Article(rs.getString("Ev"), rs.getInt("Type"), rs.getDouble("PrixTarif"),
					rs.getDouble("PrixNette"), rs.getDouble("Ht"), rs.getDouble("TVA"), rs.getString("Desig"),
					rs.getString("ref")));
		}
		cleanVariables();
		// Check If The Articles Are Empty
		return articls;
	}

	// This Function Will Returns Articls with The Specific Ids ==>if Id equals
	// -1 Then its Gooing return a all the Article
	public ArrayList<Facture> getFactures(int id) throws Exception {
		ArrayList<Facture> Factures = new ArrayList<>();
		if (id == -1) {
			SQL = "SELECT * FROM `facture`";

		} else {
			SQL = "SELECT * FROM `facture` WHERE `IDF` = '" + id + "'";
		}

		// Adding The factures to The ArrayList
		rs = executeStatements(SQL);
		while (rs.next()) {
			Factures.add(new Facture(rs.getInt("IDS"), rs.getInt("IDF"), rs.getDate("Date_Facture").toString(),
					rs.getDouble("PriceHT"), rs.getDouble("PriceTTC"), rs.getString("CIN"), rs.getString("Address")));
		}
		cleanVariables();
		// Check If The Factures Are Empty
		return Factures;
	}

	public ArrayList<StockQte> getArticlesStocks(String ev) throws Exception {
		ArrayList<StockQte> articlStq = new ArrayList<>();

		SQL = "SELECT * FROM `stock-qte` WHERE `Ev`='" + ev + "'";

		// Adding The Stocks to The ArrayList
		rs = executeStatements(SQL);
		while (rs.next()) {

			articlStq.add(new StockQte(mappingReverseStock(rs.getInt("ID")), rs.getString("EV"), rs.getInt("Qte"),
					rs.getInt("ID")));

		}
		cleanVariables();
		// Check If The Articles Are Empty
		return articlStq;
	}

	// The Executing Query Task is a repetitive code
	private ResultSet executeStatements(String sql) throws Exception {
		stmt = con.createStatement();
		return stmt.executeQuery(sql);
	}

	// The Executing Query Task is a repetitive code
	private int executeUpdateStatements(String sql) throws Exception {
		stmt = con.createStatement();
		return stmt.executeUpdate(sql);
	}

	/*
	 * private int executeInsertStatements(String sql) throws Exception { stmt =
	 * con.createStatement(); return stmt.executeUpdate(sql); }
	 */

	// Cleaning The Variables in case of a future Bugs or something
	private void cleanVariables() throws Exception {
		rs.close();
		stmt.close();
		SQL = "";
	}

	public Connection getConnection() {
		return this.con;
	}

	public void addFacture(Facture facture) throws Exception {
		// Here we add Facture its The Algo whats Missing
		double priceTTc = 0;
		double priceHT = 0;
		for (Article A : facture.getArticleList()) {
			priceHT += A.getHt();
			priceTTc += A.getHt() * (1 + (A.getHt() / 100));
		}
		System.out.println(facture.getDate_Facture());
		SQL = "INSERT INTO `facture`( `IDS`, `Date_Facture`, `PriceHT`, `PriceTTC`, `CIN`, `Address`) VALUES ('"
				+ facture.getIds() + "','" + facture.getDate_Facture() + "'," + priceHT + "," + priceTTc + ",'"
				+ facture.getCin() + "','" + facture.getAddress() + "') ";
		executeUpdateStatements(SQL);

		// Updating The Stock_Qte and The Commande Tables
		System.out.println("getting the Last inserted Id");
		rs = executeStatements("select last_insert_id() as last_id from facture");
		int lastID = 0;
		if (rs.next()) {
			lastID = rs.getInt("last_id");
		}

		SQL = "";
		for (TokenData data : facture.getTokenData()) {
			SQL = "";
			SQL = "UPDATE `stock-qte` SET `Qte`=`Qte`- " + data.getQte() + " WHERE `ID`="
					+ mappingStock(data.getStock()) + " and `EV` = '" + data.getEv() + "'";
			executeUpdateStatements(SQL);
			SQL = "";
			SQL = "INSERT INTO `commande`(`IDF`, `EV`, `QTE`) VALUES (" + lastID + ",'" + data.getEv() + "',"
					+ data.getQte() + ")";
			System.out.println(SQL);
			executeUpdateStatements(SQL);

		}
		Main.database.productsInvoice(lastID, facture.getCin(), DataBase.mappingReverseStock(facture.getIds()),
				facture.getAddress());
		cleanVariables();
	}

	public static int mappingStock(String stock) {

		int Stock = 0;
		switch (stock) {
		case "M":
			Stock = 1;
			break;
		case "D":
			Stock = 2;
			break;
		case "P":
			Stock = 3;
			break;
		case "EX":
			Stock = 4;
			break;
		}
		return Stock;
	}

	public static String mappingReverseStock(int stock) {
		String Stock = null;
		switch (stock) {
		case 1:
			Stock = "M";
			break;
		case 2:
			Stock = "D";
			break;
		case 3:
			Stock = "P";
			break;
		case 4:
			Stock = "EX";
			break;
		}
		return Stock;
	}

	public ArrayList<ProductsInvoice> invoiceProductsList(int id) throws Exception {
		ArrayList<ProductsInvoice> invoiceProducts = new ArrayList<>();

		SQL = "SELECT Ref,Desig,QTE,PrixTarif,PrixNette,PriceHT,PriceTTC" + " FROM facture f,commande c,article a"
				+ " WHERE  f.IDF = '" + id + "' AND f.IDF=c.IDF AND c.EV=a.Ev ";
		// Adding The invoiceProducts to The LinkedList
		rs = executeStatements(SQL);
		while (rs.next()) {
			invoiceProducts.add(new ProductsInvoice(rs.getString("a.Ref"), rs.getString("a.Desig"),
					rs.getString("c.QTE"), rs.getString("a.PrixTarif"), rs.getString("a.PrixNette"),
					rs.getString("f.PriceHT"), rs.getString("f.PriceTTC")));
		}
		cleanVariables();
		// Check If The invoiceProducts Are Empty
		return invoiceProducts;
	}

	public void productsInvoice(int idFacture, String CIN, String stock, String adr) throws Exception {
		String sourceFile = "C:\\Users\\Nizar\\git\\Hiba\\Hiba\\src\\pdf\\Facture.jrxml";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String pdfFile = dateFormat.format(date).toString();
		String destination = "C:\\Users\\Nizar\\Desktop\\Facture\\" + pdfFile + ".pdf";
		try {
			JasperReport jr = JasperCompileManager.compileReport(sourceFile);
			HashMap<String, Object> para = new HashMap<>();
			para.put("CIN", CIN); // put CIN Value
			para.put("Stock", stock); // put Stock Value
			para.put("Address", adr); // put Address Value

			ArrayList<ProductsInvoice> plist = new ArrayList<>();
			plist.addAll(this.invoiceProductsList(idFacture));
			JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
			JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);

			// Create PDF format file
			JasperExportManager.exportReportToPdfFile(jp, destination);
			Desktop.getDesktop().open(new File(destination));
			// JasperViewer.viewReport(jp);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showInvoice(int idFacture, String CIN, String stock, String adr) throws Exception {
		String sourceFile = "C:\\Users\\Nizar\\git\\Hiba\\Hiba\\src\\pdf\\Facture.jrxml";
		try {
			JasperReport jr = JasperCompileManager.compileReport(sourceFile);
			HashMap<String, Object> para = new HashMap<>();
			para.put("CIN", CIN); // put CIN Value
			para.put("Stock", stock); // put Stock Value
			para.put("Address", adr); // put Address Value
			//
			ArrayList<ProductsInvoice> plist = new ArrayList<>();
			plist.addAll(this.invoiceProductsList(idFacture));
			JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(plist);
			JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);

			JasperViewer.viewReport(jp);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
