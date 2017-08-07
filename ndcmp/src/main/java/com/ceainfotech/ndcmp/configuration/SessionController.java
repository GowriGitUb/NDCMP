package com.ceainfotech.ndcmp.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/**")
public class SessionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

	@RequestMapping(value = "/resetSession", method = RequestMethod.POST)
	public void resetSessionTimer() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("resetting session timer dummy function");
		}
	}

}
