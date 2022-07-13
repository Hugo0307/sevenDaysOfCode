package sevenDaysOfCode.domain;

import sevenDaysOfCode.interfaces.Content;

public record Movie (String title, String urlImage, String year, String rating) implements Content{
	
	@Override
	public int compareTo(Content content) {
		return this.rating().compareToIgnoreCase(content.rating());
	}
}