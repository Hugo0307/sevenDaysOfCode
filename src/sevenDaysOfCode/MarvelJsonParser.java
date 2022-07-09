package sevenDaysOfCode;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarvelJsonParser implements JsonParser{
	
	private String jsonString;
	
	public MarvelJsonParser(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public List<Serie> parse() {

		//exibindo o json após parse
		String[] array = parseJson(this.jsonString);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
		return null;
	}
	
	
	private String[] parseJson(String json) {
		
		Matcher beginArray = Pattern.compile(".*\"results\":\\[").matcher(json);
		beginArray.find();
		int begin = beginArray.end();
		
		Matcher endArray = Pattern.compile(".*\\]}}").matcher(json);
		endArray.find();
		int end = endArray.end();
		
		json = json.substring(begin, end);
		
		String[] jsonSerieStrings = json.split("\\},\\{\"id\"");
		
		return jsonSerieStrings;
	}

}
