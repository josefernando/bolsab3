package br.com.recatalog.bolsab3.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/b3")
public class QuoteResource {
	@GetMapping("quotes")
	public String quotes() {
		return "Quotes";
	}
}