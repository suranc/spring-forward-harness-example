package io.harness.springforward;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorPageController {

	@GetMapping("/error")
	public String index() {
		System.err.println("ERROR:  There was an error!");
		return "Oops, there was an error!";
	}

}