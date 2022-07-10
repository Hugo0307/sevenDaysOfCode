package sevenDaysOfCode;

import java.util.ArrayList;
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
		
		List<Serie> series = new ArrayList<>();
		
		for (int i = 0; i < array.length; i++) {
			series.add(new Serie(parseTitle(array[i]), parseUrlImage(array[i]), parseRating(array[i]), parseYear(array[i])));
			series.forEach(System.out::println);
		}
		
		return null;
	}
	
	private String parseTitle(String lineJson) {
		int beginTitle = lineJson.indexOf("title");
		int endTitle = lineJson.indexOf("\"", beginTitle + 8);
		String title = lineJson.substring(beginTitle + 8, endTitle).trim();
		
		return title;
	}
	
	private String parseUrlImage(String lineJson) {
		int beginTitle = lineJson.indexOf("path");
		int endTitle = lineJson.indexOf("\"", beginTitle + 7);
		String urlImage = lineJson.substring(beginTitle + 7, endTitle).trim();
		String urlImageFull = urlImage.concat(".").concat(getExtUrlImage(lineJson));
		
		return urlImageFull;
	}
	
	private String getExtUrlImage(String lineJson) {
		int beginTitle = lineJson.indexOf("extension");
		int endTitle = lineJson.indexOf("\"", beginTitle + 12);
		String extension = lineJson.substring(beginTitle + 12, endTitle).trim();
		
		return extension;
	}
	
	private String parseYear(String lineJson) {
		int beginTitle = lineJson.indexOf("startYear");
		int endTitle = lineJson.indexOf(",", beginTitle + 11);
		String year = lineJson.substring(beginTitle + 11, endTitle).trim();
		
		return year;
	}
	
	private String parseRating(String lineJson) {
		int beginTitle = lineJson.indexOf("rating");
		int endTitle = lineJson.indexOf("\"", beginTitle + 8);
		String rating = lineJson.substring(beginTitle + 8, endTitle).trim();
		
		if(rating.isEmpty()) rating = "Sem Avaliação";
		
		return rating;
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
