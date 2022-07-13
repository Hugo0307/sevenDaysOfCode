package sevenDaysOfCode.domain;

import sevenDaysOfCode.interfaces.Content;

public record Serie(String title, String urlImage, String rating, String year) implements Content{

	@Override
	public int compareTo(Content content) {
		return this.rating().compareToIgnoreCase(content.rating());
	}

}
