package com.interview.restservice.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.restservice.service.IPLocationService;



@RestController
public class IPRestController {
	
      IPLocationService  locationService;
	    
	  public IPRestController() throws IOException {
	        locationService = new IPLocationService();
	    }
	  
	 @GetMapping("/northcountries")
	    public List<String> getLocation(
	      @RequestParam(value="ip", required=true) List<String> ipAddress) throws Exception {
	        return locationService.getLocation(ipAddress);
	    }
	 
}
