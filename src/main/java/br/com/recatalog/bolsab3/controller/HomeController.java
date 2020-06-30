package br.com.recatalog.bolsab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

	@GetMapping("/")
	public ModelAndView getHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/b3statistics")
	public ModelAndView b3statistics() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("b3statistics");
		return mv;
	}
}
