package application;

import java.util.ArrayList;

import dao.*;
import dataBase.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	// DataBase Instance For All Program Use
	static public DataBase database;
	static public ArrayList<Article> Articles;
	static public ArrayList<Facture> Factures;

	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/application/FirstPage.fxml"));
			Scene Sc = new Scene(root);
			Sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(Sc);
			primaryStage.setTitle("Hiba Agricole");
			primaryStage.setResizable(false);
			primaryStage.show();
			database = new DataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	static public void refrech_Articles(){
		try {
			Articles = database.getArticles("-1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void refrech_Factures(){
		try {
			Factures = database.getFactures(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
