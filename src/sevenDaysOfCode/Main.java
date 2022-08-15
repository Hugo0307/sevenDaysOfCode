package sevenDaysOfCode;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
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
		
		movieList.stream()
			.filter(m -> m.title().startsWith("R"))
			.forEach(t -> System.out.println(t.title()));
		
//		System.out.println("===== IMDB - Ordenação de inserção =====");
//		movieList.forEach(System.out::println);
		
		//Collections.sort(movieList);
		//Collections.sort(movieList, Comparator.comparing(Content::title));
		//Collections.sort(movieList, Comparator.comparing(Content::year));
		Collections.sort(movieList, Comparator.comparing(c -> c.year()));
		
//		System.out.println("===== IMDB - Ordenação natural =====");
//		movieList.forEach(System.out::println);
		
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(movieList);
		
		Desktop desktop = Desktop.getDesktop();
		
		if(Desktop.isDesktopSupported())
			try {
				desktop.browse(new URI("content.html"));	//abrindo o html gerado com java no navegador padrão
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		
		writer.close();
		
		/* MARVEL */
//		String jsonMarvelString = new marvelApiClient().getBody();
//		
//		List<? extends Content> seriesList = new MarvelJsonParser(jsonMarvelString).parse();
//		
//		System.out.println("===== Series da Marvel - Ordenação de inserção =====");
//		seriesList.forEach(System.out::println);
//		System.out.println();
//		
//		Collections.sort(seriesList);
//		Collections.sort(seriesList, Comparator.comparing(Content::title));
//		Collections.sort(seriesList, Comparator.comparing(Content::year));
//		
//		System.out.println("===== Series da Marvel - Ordenação natural =====");
//		seriesList.forEach(System.out::println);
		
	}
	
}
