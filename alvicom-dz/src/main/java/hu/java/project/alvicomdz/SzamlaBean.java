package hu.java.project.alvicomdz;


/**
 * 
 * @author deakz
 *
 */
public class SzamlaBean {

	private String szamlaszam;
	private PenznemEnum penznem;
	private Double egyenleg;
	
	public SzamlaBean() {
	}

	public SzamlaBean(String szamlaszam, PenznemEnum penznem, Double egyenleg) {
		super();
		this.szamlaszam = szamlaszam;
		this.penznem = penznem;
		this.egyenleg = egyenleg;
	}

	public String getSzamlaszam() {
		return szamlaszam;
	}

	public void setSzamlaszam(String szamlaszam) {
		this.szamlaszam = szamlaszam;
	}

	public PenznemEnum getPenznem() {
		return penznem;
	}

	public void setPenznem(PenznemEnum penznem) {
		this.penznem = penznem;
	}

	public Double getEgyenleg() {
		return egyenleg;
	}

	public void setEgyenleg(Double egyenleg) {
		this.egyenleg = egyenleg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SzamlaBean [szamlaszam=");
		builder.append(szamlaszam);
		builder.append(", penznem=");
		builder.append(penznem);
		builder.append(", egyenleg=");
		builder.append(egyenleg);
		builder.append("]");
		return builder.toString();
	}
	
	
}
