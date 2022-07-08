package sevenDaysOfCode;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class marvelApiClient implements APIClient{

	@Override
	public String getBody() {
		
		String ts = String.valueOf(System.currentTimeMillis());
		String apikey = "239e9d84cfdfaeb54a70d3424c6389e7";
		String hashString = "e3b6fa7e170c07114507039c8d8d690a74031d6d";
		String hash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			BigInteger hashBytes = new BigInteger(1, messageDigest.digest(ts.concat(hashString).concat(apikey).getBytes()));
			hash = hashBytes.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(String.format("https://gateway.marvel.com/v1/public/characters?ts=%s&hash=%s&apikey=%s", ts, hash, apikey)))
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
		System.out.println(httpRequest.uri().toString());	
		return response.body();
	}
	

}
