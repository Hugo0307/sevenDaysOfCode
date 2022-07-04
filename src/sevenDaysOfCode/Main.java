package sevenDaysOfCode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		String apiKey = JOptionPane.showInputDialog("Digite a chave da api IMDb");
		
		String json = new ImdbApiClient(apiKey).getBody();
		
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(new ImdbMovieJsonParser(json).getListMovies());
		writer.close();
		
	}
	
}
