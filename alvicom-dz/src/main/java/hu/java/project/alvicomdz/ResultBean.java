package hu.java.project.alvicomdz;

public class ResultBean {

	private int sorszam;
	private ResultTypeEnum resultType;
	private String szamlaszam;
	private PenznemEnum penznem;
	private Double oldEgyenleg;
	private Double tranzakcioValue;
	private Double arfolyam;
	private Double newValue;
	
	public ResultBean() {
	}

	public int getSorszam() {
		return sorszam;
	}

	public void setSorszam(int sorszam) {
		this.sorszam = sorszam;
	}

	public ResultTypeEnum getResultType() {
		return resultType;
	}

	public void setResultType(ResultTypeEnum resultType) {
		this.resultType = resultType;
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

	public Double getOldEgyenleg() {
		return oldEgyenleg;
	}

	public void setOldEgyenleg(Double oldEgyenleg) {
		this.oldEgyenleg = oldEgyenleg;
	}

	public Double getTranzakcioValue() {
		return tranzakcioValue;
	}

	public void setTranzakcioValue(Double tranzakcioValue) {
		this.tranzakcioValue = tranzakcioValue;
	}

	public Double getArfolyam() {
		return arfolyam;
	}

	public void setArfolyam(Double arfolyam) {
		this.arfolyam = arfolyam;
	}

	public Double getNewValue() {
		return newValue;
	}

	public void setNewValue(Double newValue) {
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tranzakció: [sorszam=");
		builder.append(sorszam);
		builder.append("\t resultType=");
		builder.append(resultType);
		if (szamlaszam != null) {
			builder.append("\t szamlaszam=");
			builder.append(szamlaszam);
		}
		if (penznem != null) {
			builder.append("\t penznem=");
			builder.append(penznem);
		}
		if (oldEgyenleg != null) {
			builder.append("\t oldEgyenleg=");
			builder.append(oldEgyenleg);
		}
		if (tranzakcioValue != null) {
			builder.append("\t tranzakcioValue=");
			builder.append(tranzakcioValue);
		}
		if (arfolyam != null) {
			builder.append("\t arfolyam=");
			builder.append(arfolyam);
		}
		if (newValue != null) {
			builder.append("\t newValue=");
			builder.append(newValue);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
