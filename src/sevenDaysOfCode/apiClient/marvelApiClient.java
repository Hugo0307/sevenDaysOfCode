package sevenDaysOfCode.apiClient;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sevenDaysOfCode.interfaces.APIClient;
import sevenDaysOfCode.utils.KeysUtils;

public class marvelApiClient implements APIClient{

	@Override
	public String getBody() {
		
		String ts = String.valueOf(System.currentTimeMillis());
		String hash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			BigInteger hashBytes = new BigInteger(1, messageDigest.digest(ts.concat(KeysUtils.getPrivateKeyMarvel()).concat(KeysUtils.getPublicKeyMarvel()).getBytes()));
			hash = hashBytes.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(String.format("https://gateway.marvel.com/v1/public/series?ts=%s&hash=%s&apikey=%s", ts, hash, KeysUtils.getPublicKeyMarvel())))
				.build();
		
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient()
					.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response.body();
	}
	

}