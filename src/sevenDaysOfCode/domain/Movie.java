package sevenDaysOfCode.domain;

import sevenDaysOfCode.interfaces.Content;

public record Movie (String title, String urlImage, String year, String rating) implements Content{

	@Override
	public String title() {
		return title;
	}

	@Override
	public String urlImage() {
		return urlImage;
	}

	@Override
	public String rating() {
		return rating;
	}

	@Override
	public String year() {
		return year;
	}
}