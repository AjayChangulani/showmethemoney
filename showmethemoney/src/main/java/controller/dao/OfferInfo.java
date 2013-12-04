package controller.dao;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({ "_id" })
public class OfferInfo
{
	@JsonProperty("zipcode")
	private String zipcode;
	
	@JsonProperty("offerIdList")
	private List<String> offerIdList;
	
	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public List<String> getOfferIdList()
	{
		return offerIdList;
	}

	public void setOfferIdList(List<String> offerId)
	{
		this.offerIdList = offerId;
	}

}
