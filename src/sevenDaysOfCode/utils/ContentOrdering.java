package sevenDaysOfCode.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sevenDaysOfCode.interfaces.Content;

/**
 * Classe respons�vel por ordenar o conte�do
 * @author Hugo
 * @since 08/2022
 */
public class ContentOrdering {
	
	/**
	 * M�todo que ordena por T�tulo
	 * @param contentList do tipo <code>List</code>
	 * @return <code>List</code> de conte�do ordenada por t�tulo
	 */
	public static List<? extends Content> orderContentByTitle(List<? extends Content> contentList) {

		Collections.sort(contentList, Comparator.comparing(Content::title));
		
		return contentList;
	}
	
	/**
	 * M�todo que ordena por ano de lan�amento
	 * @param contentList do tipo <code>List</code>
	 * @return <code>List</code> de conte�do ordenada por ano de lan�amento
	 */
	public static List<? extends Content> orderContentByYear(List<? extends Content> contentList) {

		Collections.sort(contentList, Comparator.comparing(c -> c.year()));
		
		return contentList;
	}
	
	/**
	 * M�todo que ordena por avalia��o do maior para o menor
	 * @param contentList do tipo <code>List</code>
	 * @return <code>List</code> de conte�do ordenada por avalia��o do maior para o menor
	 */
	public static List<? extends Content> orderContentByRating(List<? extends Content> contentList) {

		Collections.sort(contentList, Comparator.comparing(c -> c.rating()));
		Collections.reverse(contentList);
		
		return contentList;
	}

}
