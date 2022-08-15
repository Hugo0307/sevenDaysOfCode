package sevenDaysOfCode.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sevenDaysOfCode.domain.Serie;
import sevenDaysOfCode.interfaces.JsonParser;

/**
 * Classe respons�vel por receber json da api da Marvel, tratar os dados e devolver uma lista de s�ries.
 * @author Hugo
 * @since 07/2022
 */
public class MarvelJsonParser implements JsonParser {

	private String jsonString;

	/**
	 * O construtor MarvelJsonParser recebendo argumento tipado como <code>string</code> inicializa a vari�vel de
	 * inst�ncia com o valor do argumento recebido.
	 * @param jsonString o json obtido no corpo da resposta da requisi��o feita � API.
	 */
	public MarvelJsonParser(String jsonString) {
		this.jsonString = jsonString;
	}

	/**
	 * M�todo respons�vel por gerar a lista de series.
	 * @return lista de series da marvel
	 */
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

	/**
	 * M�todo respons�vel por realizar o parse do atributo title.
	 * @param lineJson o conte�do de cada posi��o do array do json ap�s parse.
	 * @return <code>string</code> com value do atributo title.
	 */
	private String parseTitle(String lineJson) {
		return parseAttribute(lineJson, "title");
	}

	/**
	 * M�todo respons�vel por realizar o parse do atributo urlImage e adicionar a extens�o.
	 * @param lineJson o conte�do de cada posi��o do array do json ap�s parse.
	 * @return <code>string</code> com value do atributo urlImage com extens�o.
	 */
	private String parseUrlImage(String lineJson) {

		String urlImage = parseAttribute(lineJson, "path");
		String urlImageFull = urlImage.concat(".").concat(getExtUrlImage(lineJson));

		return urlImageFull;
	}

	/**
	 * M�todo respons�vel por recuperar a extens�o da url da imagem da s�rie.
	 * @param lineJson o conte�do de cada posi��o do array do json ap�s parse.
	 * @return <code>string</code> com value da extens�o do atributo extension.
	 */
	private String getExtUrlImage(String lineJson) {
		return parseAttribute(lineJson, "extension").trim();
	}

	/**
	 * M�todo respons�vel por realizar o parse do atributo year.
	 * @param lineJson o conte�do de cada posi��o do array do json ap�s parse.
	 * @return <code>string</code> com value do atributo startYear que representa o ano da s�rie.
	 */
	private String parseYear(String lineJson) {

		int beginTitle = lineJson.indexOf("startYear");
		int endTitle = lineJson.indexOf(",", beginTitle + 11);
		String year = lineJson.substring(beginTitle + 11, endTitle).trim();

		return year;
	}

	/**
	 * M�todo respons�vel por realizar o parse do atributo rating.
	 * @param lineJson o conte�do de cada posi��o do array do json ap�s parse.
	 * @return <code>string</code> com value do atributo rating que representa a avalia��o da s�rie.
	 */
	private String parseRating(String lineJson) {

		String rating = parseAttribute(lineJson, "rating");

		if (rating.isEmpty())
			rating = "Sem Avalia��o";

		return rating;
	}

	/**
	 * M�todo respons�vel por realizar parse de atributos das s�ries.
	 * @param lineJson o conte�do de cada posi��o do array do json ap�s parse.
	 * @param attribute o nome do atributo do json a ser recuperado o value.
	 * @return <code>string</code> com value do atributo informado na assinatura do m�todo.
	 */
	private String parseAttribute(String lineJson, String attribute) {

		Matcher beginAttribute = Pattern.compile(".*\"" + attribute + "\":").matcher(lineJson);
		beginAttribute.find();
		int begin = beginAttribute.end();
		int end = lineJson.indexOf("\"", begin + 1);

		String attributeValue = lineJson.substring(begin + 1, end).trim();

		return attributeValue;
	}

	/**
	 * M�todo respons�vel por realizar o parse do json recebido da requisi��o feita � API da Marvel.
	 * @param json o corpo da resposta da requisi��o feita � API.
	 * @return array de <code>string</code> com o json ap�s parse.
	 */
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
