package sevenDaysOfCode.utils;

import java.util.List;

import sevenDaysOfCode.interfaces.Content;

public class SearchByFilter {
	
	public static List<? extends Content> getContentByInitialLetter(List<? extends Content> contentList, String initialLetter) {
		
		return contentList.stream()
			.filter(m -> m.title().startsWith(initialLetter))
			.toList();
	}

}
