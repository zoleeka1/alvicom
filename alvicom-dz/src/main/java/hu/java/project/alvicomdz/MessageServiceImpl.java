package hu.java.project.alvicomdz;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

/**
 * @author deakz
 *
 */
public class MessageServiceImpl implements MessageService {

	@Override
	public void createSchema(String folder) {
		try {
			documentJsonV3Schema(TranzakcioList.class, folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void documentJsonV3Schema(Class<?> documentedClass, String folder) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Files.createDirectories(Paths.get(folder));
		SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
		objectMapper.acceptJsonFormatVisitor(objectMapper.constructType(documentedClass), visitor);
		JsonSchema jsonSchema = visitor.finalSchema();
		try (BufferedWriter writer = Files.newBufferedWriter(
			Paths.get(folder, documentedClass.getSimpleName() + ".json"), StandardCharsets.UTF_8)) {
			writer.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
		}
	}

	@Override
	public void createValues(String file, int size) {
		try (  PrintWriter out = new PrintWriter( file );
				StringWriter stringEmp = new StringWriter()  ){
			ObjectMapper objectMapper = new ObjectMapper();
			TranzakcioList emp1 = genTranzakcioList(size);
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(stringEmp, emp1);
		    out.println( stringEmp );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private TranzakcioList genTranzakcioList(int size) {
		List<TranzakcioBean> list = new ArrayList<>();
		Random r = new Random();
		String szamlaszam = "";
		PenznemEnum penznem = null;
		for (int i = 0; i < size; i++) {
			int ran = r.nextInt(7);
			if (ran <= 2) {
				szamlaszam = "11111111-22222222";
			} else if (ran > 2 && ran <= 5){
				szamlaszam = "22222222-33333333";
			} else {
				szamlaszam = "33333333-44444444";
			}
			switch (r.nextInt(4)) {
			case 0:
				penznem = PenznemEnum.CAD;
				break;
			case 1:
				penznem = PenznemEnum.EUR;
				break;
			case 2:
				penznem = PenznemEnum.HUF;
				break;
			case 3:
				penznem = PenznemEnum.USD;
				break;
				
			default:
				break;
			}
			double value = kerekitesTizedesre((double) r.nextInt(10000),1);
			value = r.nextBoolean() ? value : value * (-1);
			list.add(new TranzakcioBean(szamlaszam, penznem, value, kerekitesTizedesre(100 * r.nextDouble(), 2)));
		}
		TranzakcioBean [] array = new TranzakcioBean[list.size()];
		array = list.toArray(array);
		TranzakcioList t = new TranzakcioList(array);
		return t;
	}

	@Override
	public TranzakcioList readJson(String file) {
		TranzakcioList tranzakcioList = null;
		try {
			byte[] jsonData = Files.readAllBytes(Paths.get(file));
			ObjectMapper objectMapper = new ObjectMapper();
			tranzakcioList = objectMapper.readValue(jsonData, TranzakcioList.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tranzakcioList;
	}

	@Override
	public ResultBean addTranzakcioToSzamla(SzamlaBean szamla, TranzakcioBean tranzakcio, int sorszam) {
		ResultBean result = new ResultBean();
		result.setSorszam(sorszam);
		result.setSzamlaszam(szamla.getSzamlaszam());
		Double egyenleg = szamla.getEgyenleg();
		result.setOldEgyenleg(egyenleg);
		result.setPenznem(tranzakcio.getPenznem());
		Double tranzakcioValue = null; 
		if (szamla.getPenznem().equals(tranzakcio.getPenznem())) {
			tranzakcioValue = tranzakcio.getOsszeg(); 
		} else {
			tranzakcioValue = tranzakcio.getOsszeg() * tranzakcio.getArfolyam();
			result.setArfolyam(tranzakcio.getArfolyam());
		}
		egyenleg += tranzakcioValue;
		result.setTranzakcioValue(tranzakcioValue);
		result.setNewValue(egyenleg);
		result.setResultType(ResultTypeEnum.SUCCESS);
		return result;
	}

	@Override
	public ResultBean addTranzakcioToError(int sorszam) {
		ResultBean result = new ResultBean();
		result.setSorszam(sorszam);
		result.setResultType(ResultTypeEnum.FAILED);
		return result;
	}

	@Override
	public String getResultList(Map<String, List<ResultBean>> resultMap, int sorszam) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, List<ResultBean>> entry : resultMap.entrySet()) {
			builder.append("Számla - ");
			builder.append(entry.getKey());
			builder.append(":\n");
			List<ResultBean> list = entry.getValue();
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					builder.append(list.get(i).toString());
					builder.append("\n");
				}				
			} else {
				builder.append("Nincs tranzakció!\n");
			}
				
		}
		return builder.toString();
	}
	
	/**
	 * Kerekítés 2 tizedes jegyre, double hibák kiküszöbölése végett
	 * @param value
	 * @param places
	 * @return
	 */
	public double kerekitesTizedesre(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}


}
