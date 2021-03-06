package dao;

import java.util.Date;
import java.util.LinkedList;

import interfaces.AddFactureController;
import interfaces.AddFactureController.TokenData;

public class Facture {

	private int ids;
	private int idf;
	private String date_Facture;
	private double priceHT;
	private double priceTTC;
	private String cin;
	private String address;

	private LinkedList<Article> articleList;
	private LinkedList<AddFactureController.TokenData> tokenList;

	public Facture(int ids, int idf, String date_Facture, double priceHT, double priceTTC, String cin, String address) {
		super();
		this.ids = ids;
		this.idf = idf;
		this.date_Facture = date_Facture;
		this.priceHT = priceHT;
		this.priceTTC = priceTTC;
		this.cin = cin;
		this.address = address;
		articleList = new LinkedList<>();
		tokenList = new LinkedList<>();
	}

	public Facture() {
		articleList = new LinkedList<>();
		tokenList = new LinkedList<>();
		this.cin = "";
		this.address = "";
		this.priceHT = 0;
		this.priceTTC = 0;
		this.ids = -1;
	}

	public int getIds() {
		return ids;
	}

	public void setIds(int ids) {
		this.ids = ids;
	}

	public int getIdf() {
		return idf;
	}

	public void setIdf(int idf) {
		this.idf = idf;
	}

	public String getDate_Facture() {
		return date_Facture;
	}

	public void setDate_Facture(String date_Facture) {
		this.date_Facture = date_Facture;
	}

	public double getPriceHT() {
		return priceHT;
	}

	public void setPriceHT(double priceHT) {
		this.priceHT = priceHT;
	}

	public double getPriceTTC() {
		return priceTTC;
	}

	public void setPriceTTC(double priceTTC) {
		this.priceTTC = priceTTC;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Facture [ids=" + ids + ", idf=" + idf + ", date_Facture=" + date_Facture + ", priceHT=" + priceHT
				+ ", priceTTC=" + priceTTC + ", cin=" + cin + ", address=" + address + "]";
	}

	public void addArticle(Article e) {
		if (!articleList.contains(e))
			articleList.add(e);
	}

	public LinkedList<Article> getArticleList() {

		return articleList;
	}

	public void addTokenData(TokenData e) {
		if (!tokenList.contains(e))
			tokenList.add(e);
	}

	public LinkedList<AddFactureController.TokenData> getTokenData() {
		return tokenList;
	}

}
