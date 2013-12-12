package controller.cm2client;

import java.io.IOException;

import org.junit.Test;

import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.service.CM2ServiceImpl;
import com.paypal.showmethemoney.service.HttpServiceImpl;

public class CM2TestClient
{
	CM2ServiceImpl cm2Serv = new CM2ServiceImpl(new HttpServiceImpl(), new MongoDaoImpl());
	
	@Test
	public void test() throws IllegalArgumentException, IllegalStateException, IOException
	{
		System.out.println(cm2Serv.getOfferDataFromCM2("51097"));
	}
	
}
