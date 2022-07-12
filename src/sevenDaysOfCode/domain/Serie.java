package sevenDaysOfCode.domain;

import sevenDaysOfCode.interfaces.Content;

public record Serie(String title, String urlImage, String rating, String year) implements Content{}
