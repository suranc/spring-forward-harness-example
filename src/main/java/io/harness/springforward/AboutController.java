package io.harness.springforward;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.harness.cf.client.api.*;
import io.harness.cf.client.dto.Target;

@RestController
public class AboutController {

	private final String apiKey = (System.getenv("FF_API_KEY") != null) ? System.getenv("FF_API_KEY") : "no-api-key";
	private CfClient cfClient = new CfClient(apiKey);

	@GetMapping("/about")
	public String index() {
		/**
		* Define you target on which you would like to evaluate
		* the featureFlag
		*/
		final Target target = Target.builder()
                    .identifier("account1")
                    .name("Account 1")
                    .attribute("Region", "EMEA")
                    .build();

		Boolean ABOUT_PAGE = null;
		try {
			ABOUT_PAGE = cfClient.boolVariation("ABOUT_PAGE", target, false);
		} catch (Exception e) {
			ABOUT_PAGE = false;
		}

		if (ABOUT_PAGE) {
			return "ABOUT_PAGE is enabled!";
		} else {
			throw new ResponseStatusException(
			HttpStatus.NOT_FOUND, "entity not found"
			);
		}
	}
}