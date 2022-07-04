package sevenDaysOfCode;

import java.io.PrintWriter;
import java.util.List;

/**
 * Classe responsável por gerar o html para renderizar a lista de filmes
 * @author Hugo Almeida
 * @since 07/2022
 */
public class HtmlGenerator {

	private final PrintWriter printWriter;

	public HtmlGenerator(PrintWriter writer) {
		this.printWriter = writer;
	}

	/**
	 * Método responsável por gerar o html através de text blocks
	 * @param movies
	 */
	public void generate(List<Movie> movies) {
		printWriter.println(
	"""
	<html>
		<head>
			<meta charset=\"utf-8\">
			<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
			<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
						+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
						
		</head>
		<body>
	""");

		for (Movie movie : movies) {
			String divTemplate =
			"""
			<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
				<h4 class=\"card-header\">%s</h4>
				<div class=\"card-body\">
					<img class=\"card-img\" src=\"%s\" alt=\"%s\">
					<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
				</div>
			</div>
			""";
			printWriter.println(String.format(divTemplate, movie.title(), movie.urlImage(), movie.title(), movie.rating(), movie.year()));
		}
				
		printWriter.println(
		"""
			</body>
		</html>
		""");
	}
}

