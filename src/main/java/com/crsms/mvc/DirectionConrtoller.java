/**
 * 
 */
package com.crsms.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuri Kucheriavy
 *
 */
@Controller
public class DirectionConrtoller {
	
	private DirectionService directionService;
	
	@RequestMapping(value = {"directions"}, method = RequestMethod.GET)
	
	

}
