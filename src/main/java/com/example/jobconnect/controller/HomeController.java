package com.example.jobconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;

@Controller
public class HomeController {
	
	

	    @GetMapping("/index")
	    @Operation(summary = "Home Page", description = "Display the application home/index page")
	    public String index() {
	        return "index";
	    }

	    @GetMapping("/employerlogin")
	    @Operation(summary = "Employer Login Page", description = "Display the login page for employers")
	    public String employerLogin() {
	        return "employerlogin";
	    }

	    @GetMapping("/employerregister")
	    @Operation(summary = "Employer Register Page", description = "Display the registration page for employers")
	    public String employerRegister() {
	        return "employerregister";
	    }

	    @GetMapping("/jobseekerlogin")
	    @Operation(summary = "Jobseeker Login Page", description = "Display the login page for jobseekers")
	    public String jobseekerLogin() {
	        return "jobseekerlogin";
	    }

	    @GetMapping("/jobseekerregister")
	    @Operation(summary = "Jobseeker Register Page", description = "Display the registration page for jobseekers")
	    public String jobseekerRegister() {
	        return "jobseekerreg";
	    }

	    

	    

}
