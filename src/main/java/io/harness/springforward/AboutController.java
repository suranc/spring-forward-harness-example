package io.harness.springforward;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.harness.cf.client.api.*;
import io.harness.cf.client.dto.Target;

@RestController
public class AboutController {

	CfClient cfClient;

	public AboutController () {
		String apiKey = "35adfc78-ea47-4290-a51d-9515f4c7e930";
		this.cfClient = new CfClient(apiKey, Config.builder().build());
	}

	@GetMapping("/about")
	public String index() {
		/**
		* Define you target on which you would like to evaluate
		* the featureFlag
		*/
		final Target target = Target.builder()
                    .identifier("Test")
                    .name("Test")
                    .attribute("location", "test env")
                    .build();

		System.out.println(cfClient.boolVariation("ABOUT_PAGE", target, false));

		if (cfClient.boolVariation("ABOUT_PAGE", target, false)) {
			return "FF is on!";
		} else {
			return "FF is off!";
		}
	}

}