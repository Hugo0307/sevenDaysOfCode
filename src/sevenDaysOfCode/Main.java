package sevenDaysOfCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sevenDaysOfCode.apiClient.ImdbApiClient;
import sevenDaysOfCode.apiClient.marvelApiClient;
import sevenDaysOfCode.interfaces.Content;
import sevenDaysOfCode.parser.ImdbMovieJsonParser;
import sevenDaysOfCode.parser.MarvelJsonParser;
import sevenDaysOfCode.utils.KeysUtils;
import sevenDaysOfCode.view.HtmlGenerator;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		/* IMDB */
		String json = new ImdbApiClient(KeysUtils.getApiKeyImDb()).getBody();
		
		List<? extends Content> movieList = new ImdbMovieJsonParser(json).parse();
		
		System.out.println("===== IMDB - Ordenação de inserção =====");
		movieList.forEach(System.out::println);
		
		Collections.sort(movieList);
		//Collections.sort(contentList, Comparator.comparing(Content::title));
		//Collections.sort(contentList, Comparator.comparing(Content::year));
		
		System.out.println("===== IMDB - Ordenação natural =====");
		movieList.forEach(System.out::println);
		
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(movieList);
		writer.close();
		
		/* MARVEL */
		String jsonMarvelString = new marvelApiClient().getBody();
		
		List<? extends Content> seriesList = new MarvelJsonParser(jsonMarvelString).parse();
		
		System.out.println("===== Series da Marvel - Ordenação de inserção =====");
		seriesList.forEach(System.out::println);
		
		//Collections.sort(contentList);
		//Collections.sort(contentList, Comparator.comparing(Content::title));
		Collections.sort(seriesList, Comparator.comparing(Content::year));
		
		System.out.println("===== Series da Marvel - Ordenação natural =====");
		seriesList.forEach(System.out::println);
		
	}
	
}
