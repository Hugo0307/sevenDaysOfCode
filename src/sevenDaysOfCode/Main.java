package sevenDaysOfCode;

import java.io.IOException;
import java.util.List;

import sevenDaysOfCode.apiClient.ImdbApiClient;
import sevenDaysOfCode.apiClient.marvelApiClient;
import sevenDaysOfCode.interfaces.Content;
import sevenDaysOfCode.parser.ImdbMovieJsonParser;
import sevenDaysOfCode.parser.MarvelJsonParser;
import sevenDaysOfCode.utils.ContentOrdering;
import sevenDaysOfCode.utils.KeysUtils;
import sevenDaysOfCode.utils.SearchByFilter;
import sevenDaysOfCode.view.helper.HtmlGeneratorViewHelper;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		/* IMDB */
		String json = new ImdbApiClient(KeysUtils.getApiKeyImDb()).getBody();
		
		List<? extends Content> movieList = new ImdbMovieJsonParser(json).parse();
	
		movieList = ContentOrdering.orderContentByRating(movieList);
		
		HtmlGeneratorViewHelper.generateHtmlFileWithListContentAndOpenBrowser(movieList);
		
		System.out.println(SearchByFilter.getContentByInitialLetter(movieList, "S"));
		
		/* MARVEL */
		String jsonMarvelString = new marvelApiClient().getBody();
		
		List<? extends Content> seriesList = new MarvelJsonParser(jsonMarvelString).parse();

		seriesList = ContentOrdering.orderContentByYear(seriesList);
	
		HtmlGeneratorViewHelper.generateHtmlFileWithListContentAndOpenBrowser(seriesList);
		
		System.out.println(SearchByFilter.getContentByInitialLetter(seriesList, "R"));
		
	}

}
