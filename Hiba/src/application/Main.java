package application;

import java.util.LinkedList;

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
	static public LinkedList<Article> Articles;
	static public LinkedList<Facture> Factures;

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
}
