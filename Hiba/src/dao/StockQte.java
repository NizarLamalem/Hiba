package dao;

public class StockQte {

	private String stock;
	private String ev;
	private int qte;


	public StockQte(String stock, String ev, int qte,int id) {
		super();
		this.stock = stock;
		this.ev = ev;
		this.qte = qte;
		this.id=id ;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id ;
	
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

	@Override
	public String toString() {
		return "StockQte [id=" + stock + ", ev=" + ev + ", qte=" + qte + "]";
	}

}
