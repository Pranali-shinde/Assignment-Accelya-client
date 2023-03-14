
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.core.status.Status;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

@RestController
@RequestMapping("/client")
public class LocationController implements LocationOperations {

	public static final Logger LOGGER = LogManager.getLogger(LocationController.class);

//	@Autowired
//	private LocationService locationService;
//	
	Map<String,Double> map ;

@Override
public Map<String, Double> getCoordinatesOfSpaceStation() throws Exception {
	 final String method = "getCoordinatesOfSpaceStation-controller";
	    LOGGER.info("Entering " + method);
	    
	    try {
	    	//The current location of space station retrieved by using following Public API  
	    	String publicURL = "https://api.wheretheiss.at/v1/satellites/25544";
			
	    		//Storing response of the publicAPI
				RestTemplate restTemplate = new RestTemplate();
				
				JSONObject jsonObject = restTemplate.getForObject(publicURL, JSONObject.class);
				
				Double latitude = (Double) jsonObject.get("latitude");
				Double longitude = (Double) jsonObject.get("longitude");
		          
		         map = new LinkedHashMap<>(2);

				if(latitude != 0 && longitude != 0 ) {
					 map.put("latitude",latitude);
				        map.put("longitude", longitude);
				}else{
					 map.put("latitude",0.0);
				        map.put("longitude", 0.0);
				}
		       
		        LOGGER.info("Moving Out " + method);

				return map;

	    }catch(Exception ex) {
	    	LOGGER.error("Error : ", ex);
	        throw new Exception(ex);
	    }
}

//	 @GetMapping("/getlocation")

//	  public Map<String,Double> getLocationOfSatellite() throws Exception {
//		 final String method = "getLocationOfSatellite-Controller";
//		    LOGGER.info("Entering " + method);
//	    locationMap = locationService.getCoordinatesOfSpaceStation();
//	    LOGGER.info("Moving Out " + method);
//	    return locationMap;
//	  }	
}
