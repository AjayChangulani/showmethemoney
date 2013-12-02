package controller.httpexecutor;

import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import controller.dto.Location;

public class HttpExecutorImpl implements HttpExecutor
{
	public final static String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=utf-8";
	ObjectMapper objMapper = new ObjectMapper();
	
	@SuppressWarnings("deprecation")
	public Location getLocation(URL url)
	{
		if(url == null)
		{
			throw new IllegalArgumentException("URL is null");
		}
		
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url.toURI());
			
			get.addHeader("Accept", CONTENT_TYPE_JSON_UTF8);
			HttpResponse response = client.execute(get);
			
			Location l = objMapper.readValue(response.getEntity().getContent(), Location.class);
			
			return l;
		}
		
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}
