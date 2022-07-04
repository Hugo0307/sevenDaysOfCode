package sevenDaysOfCode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * Classe responsável por receber uma chave para acessar a api de filmes e obter um json de filmes
 * @author Hugo Almeida
 * @since 06/2022
 */
public class ImdbApiClient {
	
	private String apiKey;
	
	public ImdbApiClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	/**
	 * Método responsável por se conectar com a api externa e requisitar os dados
	 * @return o json com os dados dos filmes devolvido no corpo da resposta
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String getBody() throws IOException, InterruptedException {
		String urlApi = "https://imdb-api.com/en/API/Top250Movies/";
		
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(urlApi + apiKey)).GET().build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, BodyHandlers.ofString());

		return response.body();
	}

}
