package sevenDaysOfCode;

import java.io.IOException;
import java.io.PrintWriter;

import sevenDaysOfCode.apiClient.ImdbApiClient;
import sevenDaysOfCode.apiClient.marvelApiClient;
import sevenDaysOfCode.parser.ImdbMovieJsonParser;
import sevenDaysOfCode.parser.MarvelJsonParser;
import sevenDaysOfCode.utils.KeysUtils;
import sevenDaysOfCode.view.HtmlGenerator;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		//IMDB
		String json = new ImdbApiClient(KeysUtils.getApiKeyImDb()).getBody();
		
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(new ImdbMovieJsonParser(json).parse());
		writer.close();
		
		new ImdbMovieJsonParser(json).parse().forEach(System.out::println);
		
		//MARVEL
		String jsonMarvelString = new marvelApiClient().getBody();
		
		new MarvelJsonParser(jsonMarvelString).parse().forEach(System.out::println);
		
	}
	
}
