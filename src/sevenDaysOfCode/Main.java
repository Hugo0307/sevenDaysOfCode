package sevenDaysOfCode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.swing.JOptionPane;

public class Main {
	
	private static String APIKEY;

	public static void main(String[] args) throws IOException, InterruptedException {
		
		APIKEY = JOptionPane.showInputDialog("Digite a chave da api IMDb");
	
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + APIKEY))
					.GET()
					.build();
			
				 HttpResponse<String> response = HttpClient.newHttpClient()
					     .send(httpRequest, BodyHandlers.ofString()); 
		
				 System.out.println(response.body());
				 
	}

}
