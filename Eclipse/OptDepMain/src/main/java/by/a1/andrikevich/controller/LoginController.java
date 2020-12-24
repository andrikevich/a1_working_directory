package by.a1.andrikevich.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {
	
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "fancy-login";
		
	}
	
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	
	@GetMapping("/dtgroup/dt-main-page")
	public String homePageForDtGroup() {
		return "dt-group-homepage";
	}
	

}
