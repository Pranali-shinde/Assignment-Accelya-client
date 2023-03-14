package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

public interface LocationOperations {
	
	 @GetMapping("/getlocation")
	public Map<String,Double> getCoordinatesOfSpaceStation() throws Exception;

}
