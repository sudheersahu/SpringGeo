package com.interview.restservice.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;


public class IPLocationService {

	
	 private DatabaseReader dbReaderCity;
	 
	 public IPLocationService() throws IOException {
		 
		 ClassLoader classLoader = getClass().getClassLoader();
		 File cityDatabase = new File(classLoader.getResource("GeoLite2-City.mmdb").getFile());
		 dbReaderCity = new DatabaseReader.Builder(cityDatabase).build();
	    }
	 
	 public List<String> getLocation(List<String> ip) 
		      throws IOException, GeoIp2Exception {
		 
		 List<String> countryList = new ArrayList<String>();
		 
		          for( String ipdetail : ip) {
		        	  InetAddress ipAddress = InetAddress.getByName(ipdetail);
		        	 
		        	   CityResponse cityresponse = dbReaderCity.city(ipAddress);
		        	   if(cityresponse.getLocation().getLatitude() > 0.00000) {
		        		   countryList.add(cityresponse.getCountry().getName()); 
		        	   }
		            }
		        
		       return countryList.stream().distinct().sorted().collect(Collectors.toList());
		    }
	 
}
