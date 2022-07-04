package sevenDaysOfCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por receber json da api externa, tratar os dados e devolver uma lista de filmes
 * @author Hugo Almeida
 * @since 07/2022
 */
public class ImdbMovieJsonParser {
	
	private String json;
	
	public ImdbMovieJsonParser(String json) {
		this.json = json;
	}
	
	/* Obtendo as listas dos atributos title, image, year, imDbRating e adicionando a uma lista de filmes */
	/**
	 * Método responsável por criar uma lista de filmes com as listas dos atributos
	 * @return lista de filmes 
	 */
	public List<Movie> getListMovies() {
		
		parseJson();
		
		List<String> listTitleMovies = newListAtributes(parseJson(), "title");
		List<String> listUrlImagesMovies = newListAtributes(parseJson(), "image");
		List<String> listYearMovies = newListAtributes(parseJson(), "year");
		List<String> listImDbRatingMovies = newListAtributes(parseJson(), "imDbRating");
		
		List<Movie> movies = new ArrayList<>();
		int cont = 0;
		while (cont < parseJson().length) {
			movies.add(new Movie(listTitleMovies.get(cont), listUrlImagesMovies.get(cont), listYearMovies.get(cont),
					listImDbRatingMovies.get(cont)));
			cont++;
		}
		return movies;
	}

	/**
	 * Método responsável por buscar no json todos os values de determinado atributo e os atribui a uma lista
	 * @param jsonArray
	 * @param keyAttribute
	 * @return lista de string de values do atributo chave passado como parâmetro
	 */
	private List<String> newListAtributes(String[] jsonArray, String keyAttribute) {

		List<String> listValues = new ArrayList<>();

		for (int i = 0; i < jsonArray.length; i++) {
			int lastIndexkey = jsonArray[i].indexOf(keyAttribute);
			int indexTwoPoints = jsonArray[i].indexOf(":", lastIndexkey);
			int indexStartValue = indexTwoPoints + 2;
			int indexQuotes = jsonArray[i].indexOf("\"", indexStartValue);
			String valueAttribute = jsonArray[i].substring(indexStartValue, indexQuotes);
			listValues.add(valueAttribute);
		}
		return listValues;
	}
	
	/**
	 * Método responsável por realizar parse do json recebido da api externa
	 * @return array de string sem colchetes, chaves e virgulas entre as chaves
	 */
	private String[] parseJson() {
		
		int indiceColchete1 = json.indexOf("[");
		int indiceColchete2 = json.indexOf("]");
		String jsonArrayString = json.substring(indiceColchete1 + 1, indiceColchete2);

		String[] jsonArray = jsonArrayString.split("\\},\\{");
		jsonArray[0] = jsonArray[0].substring(1);

		int lastIndex = jsonArray.length - 1;
		int lastChar = jsonArray[lastIndex].indexOf("}");
		jsonArray[lastIndex] = jsonArray[lastIndex].substring(0, lastChar - 1);

		return jsonArray;
	}

}
