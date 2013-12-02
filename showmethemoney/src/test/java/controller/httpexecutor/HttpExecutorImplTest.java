package controller.httpexecutor;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import controller.dto.Location;

public class HttpExecutorImplTest
{
	HttpExecutor httpExec = new HttpExecutorImpl();
	
	
	@Test
	public void test_get() throws MalformedURLException
	{
		URL url = new URL("http://esapi.where.com/geocoding/v1/geolocation/from_address_text?address=1881+Robert+C+Byrd+Dr%2C+Mac+Arthur%2C+WV%2C+25873%2CUSA");
	
		Location resp = httpExec.getLocation(url);
		
		System.out.println(resp);
		
	}
}
