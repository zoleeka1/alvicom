package hu.java.project.alvicomdz;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TranzakcioList {

	@JsonProperty(value = "tranzakciok")
	private TranzakcioBean[] tranzakciok;

	public TranzakcioList() {
	}

	public TranzakcioList(TranzakcioBean[] tranzakciok) {
		this.tranzakciok = tranzakciok;
	}

	public TranzakcioBean[] getTranzakciok() {
		return tranzakciok;
	}

	public void setTranzakciok(TranzakcioBean[] tranzakciok) {
		this.tranzakciok = tranzakciok;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TranzakcioList [tranzakciok=");
		builder.append(Arrays.toString(tranzakciok));
		builder.append("]");
		return builder.toString();
	}
	
	
}
