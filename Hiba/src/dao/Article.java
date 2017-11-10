package dao;

public class Article {

	private String ev;
	private int type;
	private double prixTarif;
	private double prixNette;
	private double ht;
	private double tva;
	private String designation;
	private String reference;

	public Article(String ev, int type, double prixTarif, double prixNette, double ht, double tva, String designation,
			String reference) {
		super();
		this.ev = ev;
		this.type = type;
		this.prixTarif = prixTarif;
		this.prixNette = prixNette;
		this.ht = ht;
		this.tva = tva;
		this.designation = designation;
		this.reference = reference;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getPrixTarif() {
		return prixTarif;
	}

	public void setPrixTarif(double prixTarif) {
		this.prixTarif = prixTarif;
	}

	public double getPrixNette() {
		return prixNette;
	}

	public void setPrixNette(double prixNette) {
		this.prixNette = prixNette;
	}

	public double getHt() {
		return ht;
	}

	public void setHt(double ht) {
		this.ht = ht;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEv() {
		return ev;
	}

	public void setEv(String ev) {
		this.ev = ev;
	}

	@Override
	public String toString() {
		return "Article [ev=" + ev + ", type=" + type + ", prixTarif=" + prixTarif + ", prixNette=" + prixNette
				+ ", ht=" + ht + ", tva=" + tva + ", designation=" + designation + ", reference=" + reference + "]";
	}

}