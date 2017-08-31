package hu.java.project.alvicomdz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TranzakcioBean {

	@JsonProperty(value = "szamlaszam")
	private String szamlaszam;
	@JsonProperty(value = "penznem")
	private PenznemEnum penznem;
	@JsonProperty(value = "osszeg")
	private Double osszeg;
	@JsonProperty(value = "arfolyam")
	private Double arfolyam;
	
	public TranzakcioBean() {
	}

	public TranzakcioBean(String szamlaszam, PenznemEnum penznem, Double osszeg, Double arfolyam) {
		super();
		this.szamlaszam = szamlaszam;
		this.penznem = penznem;
		this.osszeg = osszeg;
		this.arfolyam = arfolyam;
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

	public Double getOsszeg() {
		return osszeg;
	}

	public void setOsszeg(Double osszeg) {
		this.osszeg = osszeg;
	}

	public Double getArfolyam() {
		return arfolyam;
	}

	public void setArfolyam(Double arfolyam) {
		this.arfolyam = arfolyam;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TranzakcioBean [szamlaszam=");
		builder.append(szamlaszam);
		builder.append(", penznem=");
		builder.append(penznem);
		builder.append(", osszeg=");
		builder.append(osszeg);
		builder.append(", arfolyam=");
		builder.append(arfolyam);
		builder.append("]");
		return builder.toString();
	}
	
	
}
