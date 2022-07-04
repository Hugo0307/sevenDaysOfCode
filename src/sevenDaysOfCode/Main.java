package sevenDaysOfCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

	private static String APIKEY;
	private static String URLBASE = "https://imdb-api.com/en/API/Top250Movies/";

	public static void main(String[] args) throws IOException, InterruptedException {

		APIKEY = JOptionPane.showInputDialog("Digite a chave da api IMDb");
		String json = requestDataMovies(URLBASE, APIKEY);
		String[] jsonArray = parseJson(json);
		
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(newListMovies(jsonArray));
		writer.close();
	}
	
	/* Obtendo as listas dos atributos title, image, year, imDbRating e adicionando a uma lista de filmes */
	private static List<Movie> newListMovies(String[] jsonArray) {
		List<String> listTitleMovies = newList(jsonArray, "title");
		List<String> listUrlImagesMovies = newList(jsonArray, "image");
		List<String> listYearMovies = newList(jsonArray, "year");
		List<String> listImDbRatingMovies = newList(jsonArray, "imDbRating");
		
		List<Movie> movies = new ArrayList<>();
		int cont = 0;
		while (cont < jsonArray.length) {
			movies.add(new Movie(listTitleMovies.get(cont), listUrlImagesMovies.get(cont), listYearMovies.get(cont),
					listImDbRatingMovies.get(cont)));
			cont++;
		}
		return movies;
	}

	/* Realizando a requisição e obtendo o json dos top 250 filmes do imdb */
	private static String requestDataMovies(String urlApi, String apiKey) throws IOException, InterruptedException {
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(urlApi + apiKey)).GET().build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, BodyHandlers.ofString());

		return response.body();
	}

	/* Retirando os colchetes gerais, chaves e vírgulas entre os filmes */
	private static String[] parseJson(String json) {

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

	/* Obtendo as listas de valores dos atributos */
	private static List<String> newList(String[] jsonArray, String key) {

		List<String> listValues = new ArrayList<>();

		for (int i = 0; i < jsonArray.length; i++) {
			int lastIndexkey = jsonArray[i].indexOf(key);
			int indexTwoPoints = jsonArray[i].indexOf(":", lastIndexkey);
			int indexStartValue = indexTwoPoints + 2;
			int indexQuotes = jsonArray[i].indexOf("\"", indexStartValue);
			String valueAttribute = jsonArray[i].substring(indexStartValue, indexQuotes);
			listValues.add(valueAttribute);
		}
		return listValues;
	}

}
