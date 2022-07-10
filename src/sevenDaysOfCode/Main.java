package sevenDaysOfCode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

//		String json = new ImdbApiClient(Utils.getApiKeyImDb()).getBody();
//		
//		PrintWriter writer = new PrintWriter("content.html");
//		new HtmlGenerator(writer).generate(new ImdbMovieJsonParser(json).parse());
//		writer.close();
//		
//		new ImdbMovieJsonParser(json).parse().forEach(System.out::println);
		
		String jsonMarvelString = new marvelApiClient().getBody();
		
		new MarvelJsonParser(jsonMarvelString).parse();
		
		System.out.println(jsonMarvelString.substring(0, 2000));
		
	}
	
}
