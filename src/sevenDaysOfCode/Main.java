package sevenDaysOfCode;

import java.awt.Desktop;
import java.io.FileNotFoundException;
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
		
		showContentByOrderInsertion(movieList);
		
		showContentByInitialLetter(movieList, "R");
		
		showContentByTitleOrder(movieList);
		
		showContentByYearOrder(movieList);
		
		generateHtmlFileWithListContent(movieList);
		
		openBrowserWithContentHtml("content.html");
		
		/* MARVEL */
		String jsonMarvelString = new marvelApiClient().getBody();
		
		List<? extends Content> seriesList = new MarvelJsonParser(jsonMarvelString).parse();
		
		showContentByOrderInsertion(seriesList);

		showContentByInitialLetter(seriesList, "F");
		
		showContentByTitleOrder(seriesList);
		
		showContentByYearOrder(seriesList);
		
		generateHtmlFileWithListContent(seriesList);
		
		openBrowserWithContentHtml("content.html");
		
	}

	private static void generateHtmlFileWithListContent(List<? extends Content> movieList)
			throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(movieList);		
		writer.close();
	}

	private static void openBrowserWithContentHtml(String htmlFileName) {
		
		if(Desktop.isDesktopSupported())
			try {
				Desktop desktop = Desktop.getDesktop();
				
				desktop.browse(new URI(htmlFileName));	//abrindo o html gerado com java no navegador padr�o
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
	}
	
	private static void showContentByTitleOrder(List<? extends Content> contentList) {
		
		System.out.println("===== Ordenação natural por Título =====");
		Collections.sort(contentList, Comparator.comparing(Content::title));
		contentList.forEach(System.out::println);
		System.out.println();
	}

	private static void showContentByYearOrder(List<? extends Content> contentList) {

		System.out.println("===== Ordenação natural por Ano =====");
		Collections.sort(contentList, Comparator.comparing(c -> c.year()));
		contentList.forEach(System.out::println);
		System.out.println();
	}

	private static void showContentByOrderInsertion(List<? extends Content> contentList) {
		
		System.out.println("===== Ordenação de inserção =====");
		contentList.forEach(System.out::println);
		System.out.println();
	}

	private static void showContentByInitialLetter(List<? extends Content> contentList, String initialLetter) {
		
		System.out.println("===== Ordenação por letra inical do título =====");
		contentList.stream()
			.filter(m -> m.title().startsWith(initialLetter))
			.forEach(t -> System.out.println(t.title()));
		System.out.println();
	}
	
}
