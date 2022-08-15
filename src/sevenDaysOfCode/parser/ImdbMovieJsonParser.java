package sevenDaysOfCode.parser;

import java.util.ArrayList;
import java.util.List;

import sevenDaysOfCode.domain.Movie;
import sevenDaysOfCode.interfaces.JsonParser;

/**
 * Classe respons�vel por receber json da api externa, tratar os dados e devolver uma lista de filmes.
 * @author Hugo Almeida
 * @since 07/2022
 */
public class ImdbMovieJsonParser implements JsonParser {
	
	private String json;
	
	/**
	 * O construtor ImdbMovieJsonParser recebendo argumento tipado como <code>string</code> inicializa a vari�vel
	 * de inst�ncia com o valor do argumento recebido.
	 * @param json o corpo da resposta da requisi��o feita � API.
	 */
	public ImdbMovieJsonParser(String json) {
		this.json = json;
	}
	
	/**
	 * M�todo respons�vel por criar uma lista de filmes com as listas dos atributos.
	 * @return lista de filmes.
	 */
	@Override
	public List<Movie> parse() {
		
		parseJson();
		
		List<Movie> movies = new ArrayList<>();
		
		int cont = 0;
		while (cont < parseJson().length) {
			movies.add(new Movie(
					newValueAttributeList(parseJson(), "title").get(cont), 
					newValueAttributeList(parseJson(), "image").get(cont), 
					newValueAttributeList(parseJson(), "year").get(cont),
					newValueAttributeList(parseJson(), "imDbRating").get(cont)
					));
			cont++;
		}
		return movies;
	}

	/**
	 * M�todo respons�vel por buscar no json todos os values de determinado atributo e os atribui a uma lista.
	 * @param jsonArray o array de <code>string</code> ap�s parse do json recebido da API.
	 * @param keyAttribute o nome do atributo.
	 * @return lista de <code>string</code> de values do atributo chave passado como par�metro.
	 */
	private List<String> newValueAttributeList(String[] jsonArray, String keyAttribute) {

		List<String> valueAttributeList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length; i++) {
			int lastIndexKey = jsonArray[i].indexOf(keyAttribute);
			int indexTwoPoints = jsonArray[i].indexOf(":", lastIndexKey);
			int indexValueBegin = indexTwoPoints + 2;
			int indexQuotes = jsonArray[i].indexOf("\"", indexValueBegin);
			String valueAttribute = jsonArray[i].substring(indexValueBegin, indexQuotes);
			valueAttributeList.add(valueAttribute);
		}
		return valueAttributeList;
	}
	
	/**
	 * M�todo respons�vel por realizar parse do json recebido da api externa.
	 * @return array de <code>string</code> sem colchetes, chaves e virgulas entre as chaves.
	 */
	private String[] parseJson() {
		
		String jsonArrayString = removeBrackets(this.json);

		String[] jsonArray = jsonArrayString.split("\\},\\{");
		jsonArray[0] = jsonArray[0].substring(1);

		int lastIndex = jsonArray.length - 1;
		int lastChar = jsonArray[lastIndex].indexOf("}");
		jsonArray[lastIndex] = jsonArray[lastIndex].substring(0, lastChar - 1);

		return jsonArray;
	}

	/**
	 * M�todo respons�vel por remover os colchetes do json
	 * @param <code>string</code> json
	 * @return <code>string</code> novo json sem os colchetes
	 */
	private String removeBrackets(String json) {
		
		int bracketsBegin = json.indexOf("[");
		int bracketsEnd = json.indexOf("]");
		
		String jsonWithoutBrackets = json.substring(bracketsBegin + 1, bracketsEnd);
		
		return jsonWithoutBrackets;
	}

}
