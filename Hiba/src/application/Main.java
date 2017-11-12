package application;

import dataBase.*;	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	static public Database database;
	@Override
	public void start(Stage primaryStage) {
		try{
			database = new Database();
			Parent root=FXMLLoader.load(getClass().getResource("/application/FirstPage.fxml"));
			Scene Sc=new Scene(root) ;
			Sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm()) ;
			primaryStage.setScene(Sc);
			primaryStage.setTitle("Hiba Agricole");
			primaryStage.setResizable(false);
			primaryStage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
