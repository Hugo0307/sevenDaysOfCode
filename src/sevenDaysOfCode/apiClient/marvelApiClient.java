package sevenDaysOfCode.apiClient;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sevenDaysOfCode.enums.UrlApi;
import sevenDaysOfCode.interfaces.APIClient;
import sevenDaysOfCode.utils.KeysUtils;

public class marvelApiClient implements APIClient{

	/**
	 * Método responsável por se conectar com a api externa e requisitar os dados.
	 * @return <code>string</code> json com dados dos filmes devolvido no corpo da resposta.
	 */
	@Override
	public String getBody() {
		
		String ts = String.valueOf(System.currentTimeMillis());
		
		String hash = parseHash(ts);
		
		HttpResponse<String> response = null;
		
		try {
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.GET()
					.uri(URI.create(String.format(UrlApi.MARVEL.getUrl(), ts, hash, KeysUtils.getPublicKeyMarvel())))
					.build();
			
			response = HttpClient.newHttpClient()
					.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response.body();
	}

	/**
	 * Método responsável por realizar o parse do hash utilizado na uri da requisição
	 * @param <code>string</code> ts com data no momento atual para compor o hash
	 * @return <code>string</code> hash utilizado na uri da requisição
	 */
	private String parseHash(String ts) {
		
		String hash = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			BigInteger hashBytes = new BigInteger(1, messageDigest
					.digest(ts
							.concat(KeysUtils.getPrivateKeyMarvel())
							.concat(KeysUtils.getPublicKeyMarvel()).getBytes()));
			
			hash = hashBytes.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
	

}
