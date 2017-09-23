package anchorExtractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkUtility {
	/**
	 * Accepts a resource URI, fetches and parses it to identify all the linked unique domains. 
	 * 
	 * @param url An absolute input resource URI that we need to fetch from 
	 * @return Map<String, Integer> with unique base URIs and corresponding count in the resource identified by the input URI
	 * 
	 */
	public Map<String,Integer> getAvailableURLs(URL url){
		Document htmlDoc;
		try {
			htmlDoc = Jsoup.connect(url.toString()).get();
		} catch (IOException e) {
			htmlDoc = new Document(url.toString());
			e.printStackTrace();
		}
		Map<String, Integer> availableURLs = new HashMap<String, Integer>();
		Elements links = htmlDoc.getElementsByTag("a");
		for(Element link:links){
			URL current_url;
			try {
				current_url = new URL(link.absUrl("href"));
			} catch (MalformedURLException e) {
				// Do nothing as it is not a valid URL
				continue;
			}
			if(current_url.getHost().equalsIgnoreCase(url.getHost()) || current_url.getHost().trim().isEmpty())
				continue;
			String domain = current_url.getHost();
			if(domain.startsWith("www.")){
				domain = domain.substring(4);
			}
			if(availableURLs.containsKey(domain)){
				// if URL already available
				Integer count = availableURLs.get(domain);
				count++;
				availableURLs.put(domain, count);
			}
			else{
				// new URL
				Integer count = 1;
				availableURLs.put(domain, count);
			}
		}
		return availableURLs;
	}

}
