package controller.dao;

import org.codehaus.jackson.annotate.JsonProperty;

public class StoreInfo
{
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getContact_person()
	{
		return contact_person;
	}

	public void setContact_person(String contact_person)
	{
		this.contact_person = contact_person;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getLat()
	{
		return lat;
	}

	public void setLat(String lat)
	{
		this.lat = lat;
	}

	public String getLng()
	{
		return lng;
	}

	public void setLng(String lng)
	{
		this.lng = lng;
	}

	@JsonProperty("name")
	private String name;

	@JsonProperty("street")
	private String street;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("zip")
	private String zip;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("contact_person")
	private String contact_person;
	
	@JsonProperty("phone")
	private String phone;

	@JsonProperty("lat")
	private String lat;
	
	@JsonProperty("lng")
	private String lng;

	@Override
	public String toString()
	{
		return "StoreInfo [name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + ", contact_person=" + contact_person + ", phone=" + phone + ", lat=" + lat + ", lng=" + lng + "]";
	}
}
