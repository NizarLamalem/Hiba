package dataBase;

import java.util.LinkedList;

import Traitment.Article;

public class Main {

	public static void main(String[] args) throws Exception {
		//connecting test
		DataBase base = new DataBase();
		// Fetching Database Test
		LinkedList<Article> articles = base.getArticles("EV-30");
		
		
		for (Article A : articles) {
			System.out.println(A);
		}

	}

}
