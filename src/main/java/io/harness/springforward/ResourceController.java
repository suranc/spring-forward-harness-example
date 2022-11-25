package io.harness.springforward;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

class ConsumeResources {
	private String[] stuff;

	public ConsumeResources() {
		this.stuff = new String[500000000];
		for (int i = 0; i < 500000000; i++) {
			this.stuff[i] = String.valueOf(i);
		}
	}
}


@RestController
public class ResourceController {

	@GetMapping("/resources")
	public String index() {
		ConsumeResources resources = new ConsumeResources();

		return "Consuming Resources!";
	}

}