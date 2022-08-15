package sevenDaysOfCode.domain;

import sevenDaysOfCode.interfaces.Content;

/**
 * Classe que modela um filme
 * @author Hugo
 *
 */
public record Movie (String title, String urlImage, String year, String rating) implements Content{
	
	/**
	 * Método compareTo sobrescrito que possibilita ordenar os filmes por avaliação
	 * @return inteiro usado para a ordenação
	 */
	@Override
	public int compareTo(Content content) {
		return this.rating().compareToIgnoreCase(content.rating());
	}
}