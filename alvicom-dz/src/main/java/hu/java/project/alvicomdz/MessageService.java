package hu.java.project.alvicomdz;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author deakz
 *
 */
public interface MessageService {

	public TranzakcioList readJson(String file);
	
	public ResultBean addTranzakcioToSzamla(SzamlaBean szamla, TranzakcioBean tranzakcio, int sorszam);
	
	public ResultBean addTranzakcioToError(int sorszam);
	
	public String getResultList(Map<String, List<ResultBean>> resultMap, int sorszam);
	
	public void createValues(String file, int size);
}
