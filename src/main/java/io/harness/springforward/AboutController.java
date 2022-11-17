package io.harness.springforward;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.harness.cf.client.api.*;
import io.harness.cf.client.dto.Target;

@RestController
public class AboutController {

	@GetMapping("/about")
	public String index() {
		/**
		* Put the API Key here from your environment
		*/
		String apiKey = '35adfc78-ea47-4290-a51d-9515f4c7e930';

		CfClient cfClient = new CfClient(apiKey, Config.builder().build());

		/**
		* Define you target on which you would like to evaluate
		* the featureFlag
		*/
		Target target = Target.builder()
							.name("User1")
							.attributes(new HashMap<String, Object>())
							.identifier("user1@example.com")
							.build();

		if cfClient.boolVariation("ABOUT_PAGE", target, false) {
			return "FF is on!";
		} else {
			return "FF is off!";
		}
	}

}