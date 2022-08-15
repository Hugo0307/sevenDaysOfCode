package sevenDaysOfCode.apiClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import sevenDaysOfCode.enums.UrlApi;

/**
 * Classe responsável por receber uma chave para acessar a api de filmes e obter um json de filmes.
 * @author Hugo Almeida
 * @since 06/2022
 */
public class ImdbApiClient {
	
	private String apiKey;
	
	/**
	 * O construtor ImdbApiClient recebendo argumento tipado como <code>string</code> inicializa a variável
	 * de instância com o valor do arqumento recebido.
	 * @param apiKey a chave de acesso à API.
	 */
	public ImdbApiClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	/**
	 * Método responsável por se conectar com a api externa e requisitar os dados.
	 * @return o json com os dados dos filmes devolvido no corpo da resposta.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String getBody() {

		HttpResponse<String> response = null;
		
		try {
			HttpRequest httpRequest = HttpRequest
					.newBuilder()
					.uri(URI.create(UrlApi.IMDB.getUrl() + apiKey))
					.GET()
					.build();
			
			response = HttpClient.newHttpClient().send(httpRequest, BodyHandlers.ofString());
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return response.body();
	}

}
