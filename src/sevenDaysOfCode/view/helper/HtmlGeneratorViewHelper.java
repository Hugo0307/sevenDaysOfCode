package sevenDaysOfCode.view.helper;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import sevenDaysOfCode.interfaces.Content;
import sevenDaysOfCode.view.HtmlGenerator;

public class HtmlGeneratorViewHelper {
	
	private static final String HTML_FILE_NAME = "content.html";
	
	public static void generateHtmlFileWithListContent(List<? extends Content> contentList) {
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(HTML_FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		new HtmlGenerator(writer).generate(contentList);		
		writer.close();
	}
	
	public static void generateHtmlFileWithListContentAndOpenBrowser(List<? extends Content> contentList) {
		
		generateHtmlFileWithListContent(contentList);
		openBrowserWithContentHtml();
	}

	private static void openBrowserWithContentHtml() {
		
		if(Desktop.isDesktopSupported())
			try {
				Desktop desktop = Desktop.getDesktop();
				
				desktop.browse(new URI(HTML_FILE_NAME));	//abrindo o html gerado com java no navegador padrão
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
	}

}
