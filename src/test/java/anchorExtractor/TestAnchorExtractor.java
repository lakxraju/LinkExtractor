package anchorExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestAnchorExtractor {
	LinkUtility utility;
	URL inputUrl;
	Map<String,Integer> expectedOutput;
	
	@Before
	public void setup(){
		utility = new LinkUtility();
		expectedOutput = new HashMap<String, Integer>();
		expectedOutput.put("dajsygnal.pl", 1);
		expectedOutput.put("pogodnie.pl", 1);
		expectedOutput.put("onas.o2.pl", 1);
		expectedOutput.put("film.wp.pl", 1);
		try {
			inputUrl = new URL("http://wawalove.pl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testLinkUtility(){
		Map<String, Integer> availableURLs = utility.getAvailableURLs(inputUrl);
		assertThat(expectedOutput, equalTo(availableURLs));
	}

}
