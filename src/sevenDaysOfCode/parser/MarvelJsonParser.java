package sevenDaysOfCode.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sevenDaysOfCode.domain.Serie;
import sevenDaysOfCode.interfaces.JsonParser;

public class MarvelJsonParser implements JsonParser {

	private String jsonString;

	public MarvelJsonParser(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public List<Serie> parse() {

		String[] array = parseJson(this.jsonString);

		List<Serie> series = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			series.add(new Serie(parseTitle(array[i]), parseUrlImage(array[i]), parseRating(array[i]),
					parseYear(array[i])));
		}
		return series;
	}

	private String parseTitle(String lineJson) {
		String title = parseAttribute(lineJson, "title");

		return title;
	}

	private String parseUrlImage(String lineJson) {

		String urlImage = parseAttribute(lineJson, "path");
		String urlImageFull = urlImage.concat(".").concat(getExtUrlImage(lineJson));

		return urlImageFull;
	}

	private String getExtUrlImage(String lineJson) {

		String extension = parseAttribute(lineJson, "extension");

		return extension;
	}

	private String parseYear(String lineJson) {

		int beginTitle = lineJson.indexOf("startYear");
		int endTitle = lineJson.indexOf(",", beginTitle + 11);
		String year = lineJson.substring(beginTitle + 11, endTitle).trim();

		return year;
	}

	private String parseRating(String lineJson) {

		String rating = parseAttribute(lineJson, "rating");

		if (rating.isEmpty())
			rating = "Sem Avaliação";

		return rating;
	}

	private String parseAttribute(String lineJson, String attribute) {

		Matcher beginAttribute = Pattern.compile(".*\"" + attribute + "\":").matcher(lineJson);
		beginAttribute.find();
		int begin = beginAttribute.end();
		int end = lineJson.indexOf("\"", begin + 1);

		String attributeValue = lineJson.substring(begin + 1, end).trim();

		return attributeValue;
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
