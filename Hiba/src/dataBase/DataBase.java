package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import dao.Article;
import dao.Facture;

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
	public LinkedList<Article> getArticles(String ev) throws Exception {
		LinkedList<Article> articls = new LinkedList<>();
		if (ev.equals("-1")) {
			SQL = "SELECT * FROM `article`";

		} else {
			SQL = "SELECT * FROM `article` WHERE `Ev`='"+ ev+"'";
		}
		
		//Adding The Articles to The LinkedList
		rs = executeStatements(SQL);
		while (rs.next()) {
			articls.add(new Article(rs.getString("Ev"),rs.getInt("Type"),
					rs.getDouble("PrixTarif"), rs.getDouble("PrixNette"), 
					rs.getDouble("Ht"), rs.getDouble("TVA"), 
					rs.getString("Desig"), rs.getString("ref"))) ;
		}
		cleanVariables();
		//Check If The Articles Are Empty
		return articls ;
	}
	
	// This Function Will Returns Articls with The Specific Ids ==>if Id equals
		// -1 Then its Gooing return a all the Article
		public LinkedList<Facture> getFactures(int id) throws Exception {
			LinkedList<Facture> Factures = new LinkedList<>();
			if (id == -1) {
				SQL = "SELECT * FROM `facture`";

			} else {
				SQL = "SELECT * FROM `facture` WHERE `IDF` = '"+ id+"'";
			}
			
			//Adding The factures to The LinkedList
			rs = executeStatements(SQL);
			while (rs.next()) {
				Factures.add(new Facture(rs.getInt("IDS"),rs.getInt("IDF"),
						rs.getDate("Date_Facture"), rs.getDouble("PriceHT"), 
						rs.getDouble("PriceTTC"), 
						rs.getString("CIN"), rs.getString("Address"))) ;
			}
			cleanVariables();
			//Check If The Factures Are Empty
			return Factures ;
		}
		
		
		

	// The Executing Query Task is a repetitive code
	private ResultSet executeStatements(String sql) throws Exception {
		stmt = con.createStatement();
		return stmt.executeQuery(sql);
	}

	
	// Cleaning The Variables in case of a future Bugs or something
	private void cleanVariables() throws Exception {
		rs.close();
		stmt.close();
	}

	public Connection getConnection() {
		return this.con;
	}
}
