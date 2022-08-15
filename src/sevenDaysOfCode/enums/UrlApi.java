package sevenDaysOfCode.enums;

public enum UrlApi {
	
	IMDB("https://imdb-api.com/en/API/Top250TVs/"),
	MARVEL("https://gateway.marvel.com/v1/public/series?ts=%s&hash=%s&apikey=%s");
	
	private String url;

	UrlApi(String url) {
		this.url= url;
	}
	
	public String getUrl() {
		return url;
	}
	
}
