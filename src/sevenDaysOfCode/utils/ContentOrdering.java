package sevenDaysOfCode.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sevenDaysOfCode.interfaces.Content;

/**
 * Classe responsável por ordenar o conteúdo
 * @author Hugo
 * @since 08/2022
 */
public class ContentOrdering {
	
	/**
	 * Método que ordena por Título
	 * @param contentList do tipo <code>List</code>
	 * @return <code>List</code> de conteúdo ordenada por título
	 */
	public static List<? extends Content> orderContentByTitle(List<? extends Content> contentList) {

		Collections.sort(contentList, Comparator.comparing(Content::title));
		
		return contentList;
	}
	
	/**
	 * Método que ordena por ano de lançamento
	 * @param contentList do tipo <code>List</code>
	 * @return <code>List</code> de conteúdo ordenada por ano de lançamento
	 */
	public static List<? extends Content> orderContentByYear(List<? extends Content> contentList) {

		Collections.sort(contentList, Comparator.comparing(c -> c.year()));
		
		return contentList;
	}
	
	/**
	 * Método que ordena por avaliação do maior para o menor
	 * @param contentList do tipo <code>List</code>
	 * @return <code>List</code> de conteúdo ordenada por avaliação do maior para o menor
	 */
	public static List<? extends Content> orderContentByRating(List<? extends Content> contentList) {

		Collections.sort(contentList, Comparator.comparing(c -> c.rating()));
		Collections.reverse(contentList);
		
		return contentList;
	}

}
