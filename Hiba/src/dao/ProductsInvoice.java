package dao;

public class ProductsInvoice {
	private String Ref;
	private String design;
	private String qte;
	private String prixTarifU;
	private String prixTarifT;
	private String netU;
	private String netT;
	
	public ProductsInvoice(String ref, String design, String qte, String prixTarifU, String prixTarifT, String netU,
			String netT) {
		super();
		this.Ref = ref;
		this.design = design;
		this.qte = qte;
		this.prixTarifU = prixTarifU;
		this.prixTarifT = prixTarifT;
		this.netU = netU;
		this.netT = netT;
	}

	public String getRef() {
		return Ref;
	}

	public void setRef(String ref) {
		this.Ref = ref;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getQte() {
		return qte;
	}

	public void setQte(String qte) {
		this.qte = qte;
	}

	public String getPrixTarifU() {
		return prixTarifU;
	}

	public void setPrixTarifU(String prixTarifU) {
		this.prixTarifU = prixTarifU;
	}

	public String getPrixTarifT() {
		return prixTarifT;
	}

	public void setPrixTarifT(String prixTarifT) {
		this.prixTarifT = prixTarifT;
	}

	public String getNetU() {
		return netU;
	}

	public void setNetU(String netU) {
		this.netU = netU;
	}

	public String getNetT() {
		return netT;
	}

	public void setNetT(String netT) {
		this.netT = netT;
	}
	
	@Override
	public String toString() {
		return "FactproductsInvoiceure [ids=" + Ref + ", idf=" + design + ", date_Facture=" + qte + ", priceHT=" + prixTarifU
				+ ", priceTTC=" + prixTarifT + ", cin=" + netU + ", address=" + netT + "]";
	}
}