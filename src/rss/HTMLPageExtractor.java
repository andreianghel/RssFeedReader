package rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.NetworkParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.xml.sax.ContentHandler;

public class HTMLPageExtractor {
	
	//public static String getArticleText(String url) {}
	
	
	public static void main(String[] args) throws Exception{
		URL url = new URL("http://www.basicsbehind.com/stack-data-structure/");
	    InputStream input = url.openStream();
	    LinkContentHandler linkHandler = new LinkContentHandler();
	    ContentHandler textHandler = new BodyContentHandler();
	    ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
	    TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
	    Metadata metadata = new Metadata();
	    ParseContext parseContext = new ParseContext();
	    AbstractParser parser = new org.apache.tika.parser.html.HtmlParser();
	    parser.parse(input, teeHandler, metadata, parseContext);
	    System.out.println("title:\n" + metadata.get("title"));
	    System.out.println("links:\n" + linkHandler.getLinks());
	    System.out.println("text:\n" + textHandler.toString());
	    System.out.println("html:\n" + toHTMLHandler.toString());
	}
	
}