package hu.java.project.alvicomdz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Hello world!
 *
 */
public class App {
	
	private static List<SzamlaBean> szamlaList;
	private static MessageService service;
	
	static List<SzamlaBean> createSzamlaList() {
		List<SzamlaBean> result = new ArrayList<SzamlaBean>();
		result.add(new SzamlaBean("11111111-22222222", PenznemEnum.HUF, 150000D));
		result.add(new SzamlaBean("22222222-33333333", PenznemEnum.USD, 1230D));
		return result;
	}

	public static void main(String[] args) {
		String file = "";
		int size = 0;
		if (args != null && args.length != 0 && args[0] == null) {
			file = "c:/Temp/json/tranzakciok.json";
		} else {
			file = args[0];
		}
		if (args != null && args.length != 0 && args[1] == null) {
			size = 1000;
		} else {
			size = Integer.valueOf(args[1]);
		}
		szamlaList = createSzamlaList();
		service = new MessageServiceImpl();
		List<ResultBean> resultList = new ArrayList<ResultBean>();
		int sorszam = 0;
		Map<String, List<ResultBean>> resultMap = createEmptyResultMap();
		List<TranzakcioBean> tranzakciok = null;
		service.createValues(file, size);
		tranzakciok = Arrays.asList(service.readJson(file).getTranzakciok());
		while (sorszam < tranzakciok.size()) {
			int j = 0;
			while (j < szamlaList.size() && !szamlaList.get(j).getSzamlaszam().equals(tranzakciok.get(sorszam).getSzamlaszam())) {
				j++;
			}
			ResultBean item = null;
			if (j < szamlaList.size()) {
				item = service.addTranzakcioToSzamla(szamlaList.get(j), tranzakciok.get(sorszam), sorszam);
				szamlaList.get(j).setEgyenleg(szamlaList.get(j).getEgyenleg() + item.getTranzakcioValue());
				List<ResultBean> list = resultMap.get(szamlaList.get(j).getSzamlaszam());
				list.add(item);
				resultMap.put(szamlaList.get(j).getSzamlaszam(), list);
			} else {
				item = service.addTranzakcioToError(sorszam);
				resultMap.get("FAILED").add(item);
			}
			resultList.add(item);
			sorszam++;
			if (sorszam % 10 == 0) {
				System.err.println("Tranzakciók (" + (sorszam-10) + "-" + (sorszam-1) + "):");
				System.out.println(service.getResultList(resultMap, sorszam));
				resultMap = createEmptyResultMap();
			} 
		}
	}

	private static HashMap<String, List<ResultBean>> createEmptyResultMap() {
		HashMap<String, List<ResultBean>> map = new HashMap<String, List<ResultBean>>();
		for (SzamlaBean szamla : szamlaList) {
			ArrayList<ResultBean> list = new ArrayList<ResultBean>();
			map.put(szamla.getSzamlaszam(), list);
		}
		ArrayList<ResultBean> list = new ArrayList<ResultBean>();
		map.put("FAILED", list);
		return map;
	}
	
	
}
