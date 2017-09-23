package anchorExtractor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MainAnchorExtractor {
	/**
	 * Main function to run this anchor extractor project. 
	 *  
	 * @param args Input URI should be in args[0]
	 * @return nothing
	 * 
	 */
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL(args[0]);
		} catch (MalformedURLException e) {
			System.out.println("URL Format is wrong. Please check!");
			return;
		}
		LinkUtility utility = new LinkUtility();
		Map<String, Integer> availableURLs = utility.getAvailableURLs(url);
		for (String curr_url : availableURLs.keySet()) {
			System.out.println(curr_url + "  --  " + availableURLs.get(curr_url));
		}
	}

}
