package sevenDaysOfCode.domain;

import sevenDaysOfCode.interfaces.Content;

/**
 * Classe que modela um filme
 * @author Hugo
 *
 */
public record Movie (String title, String urlImage, String year, String rating) implements Content{
	
	/**
	 * M�todo compareTo sobrescrito que possibilita ordenar os filmes por avalia��o
	 * @return inteiro usado para a ordena��o
	 */
	@Override
	public int compareTo(Content content) {
		return this.rating().compareToIgnoreCase(content.rating());
	}
}