package com.example.jobconnect.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jobconnect.dto.JobApplicationDTO;
import com.example.jobconnect.dto.JobseekerDTO;

import com.example.jobconnect.model.JobEntity;
import com.example.jobconnect.model.JobseekerEntity;
import com.example.jobconnect.service.Jobseekerservice;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class JobseekerController {
	
	
	@Autowired
    private Jobseekerservice jobSeekerService;
    
    
	


	    @PostMapping("/jobseeker/register")
	    @Operation(summary = "Register Jobseeker", description = "Register a new jobseeker account. Redirects to login page with appropriate status.")
	    public String register(@ModelAttribute JobseekerDTO dto) throws IOException {
	        String result = jobSeekerService.registerJobSeeker(dto);

	        if ("email_exists".equals(result)) {
	            return "redirect:/jobseekerlogin?error=email_exists";
	        }
	        if ("username_exists".equals(result)) {
	            return "redirect:/jobseekerlogin?error=username_exists";
	        }

	        return "redirect:/jobseekerlogin?verify_email";
	    }

	    @GetMapping("/jobseeker/verify")
	    @Operation(summary = "Verify Jobseeker Account", description = "Verify jobseeker account using the verification token sent via email")
	    public String verifyAccount(@RequestParam("token") String token) {
	        boolean verified = jobSeekerService.verifyAccount(token);
	        if (verified) {
	            return "redirect:/jobseekerlogin?verified";
	        } else {
	            return "redirect:/jobseekerlogin?invalid_token";
	        }
	    }

	    @PostMapping("/jobseeker/login")
	    @Operation(summary = "Jobseeker Login", description = "Authenticate jobseeker using email and password. Redirects to jobseeker home if successful.")
	    public String jobseekerLogin(@RequestParam("email") String email,
	                                 @RequestParam("password") String password,
	                                 Model model, HttpSession session) {

	        JobseekerEntity jobseeker = jobSeekerService.validatejobseekerLogin(email, password);

	        if (jobseeker != null) {
	            model.addAttribute("jobseeker", jobseeker);
	            session.setAttribute("email", email);
	            return "jobseekerhome";
	        } else {
	            model.addAttribute("error", "Invalid username or password, or account not verified!");
	            return "jobseekerlogin";
	        }
	    }

	

	        @GetMapping("/jobseeker/forgotpassword")
	        @Operation(summary = "Forgot Password Page (Jobseeker)", description = "Displays the jobseeker forgot password page.")
	        public String jobseekerForgotPassword() {
	            return "jobseekerforgotpassword";
	        }

	        @PostMapping("/jobseeker/otp")
	        @Operation(summary = "Send OTP (Jobseeker)", description = "Generates and sends OTP to the jobseeker’s registered email address.")
	        public String sendOtp(@RequestParam("email") String email,
	                              RedirectAttributes redirectAttributes,
	                              HttpSession session) {

	            boolean otpSent = jobSeekerService.generateAndSendOtp(email);

	            if (!otpSent) {
	                redirectAttributes.addFlashAttribute("error", "Email not found!");
	                return "redirect:/jobseeker/forgotpassword";
	            }

	            session.setAttribute("resetEmail", email);
	            redirectAttributes.addFlashAttribute("success", "OTP sent to your email!");
	            return "redirect:/jobseeker/verify-otp";
	        }

	        @GetMapping("/jobseeker/verify-otp")
	        @Operation(summary = "Verify OTP Page (Jobseeker)", description = "Displays the OTP verification page for jobseekers.")
	        public String showVerifyOtpPage() {
	            return "jobseekerverifyotp";
	        }

	        @PostMapping("/jobseeker/verify-otp")
	        @Operation(summary = "Verify OTP (Jobseeker)", description = "Validates the OTP entered by the jobseeker and redirects to reset password page if valid.")
	        public String verifyOtp(@RequestParam("otp") String otp,
	                                HttpSession session,
	                                RedirectAttributes redirectAttributes) {

	            String email = (String) session.getAttribute("resetEmail");
	            if (email == null) {
	                return "redirect:/jobseeker/forgotpassword";
	            }

	            boolean isValid = jobSeekerService.verifyOtp(email, otp);

	            if (!isValid) {
	                redirectAttributes.addFlashAttribute("error", "Invalid OTP!");
	                return "redirect:/jobseeker/verify-otp";
	            }

	            return "redirect:/jobseeker/reset-password";
	        }

	 

	            @GetMapping("/jobseeker/reset-password")
	            @Operation(summary = "Reset Password Page (Jobseeker)", 
	                       description = "Displays the reset password form for jobseekers.")
	            public String showResetPasswordPage() {
	                return "jobseekerresetpassword";
	            }

	            @PostMapping("/jobseeker/reset-password")
	            @Operation(summary = "Reset Password (Jobseeker)", 
	                       description = "Resets the password for a jobseeker after successful OTP verification.")
	            public String resetPassword(@RequestParam("newPassword") String newPassword,
	                                        HttpSession session,
	                                        RedirectAttributes redirectAttributes) {

	                String email = (String) session.getAttribute("resetEmail");
	                if (email == null) {
	                    return "redirect:/jobseeker/forgotpassword";
	                }

	                jobSeekerService.updatePassword(email, newPassword);
	                session.removeAttribute("resetEmail");

	                redirectAttributes.addFlashAttribute("success", "Password reset successfully! Please login.");
	                return "redirect:/jobseekerlogin";
	            }

	
	
	
	            
	                @GetMapping("/jobseeker/profile")
	                @Operation(summary = "Jobseeker Profile", 
	                           description = "Displays the logged-in jobseeker’s profile details.")
	                public String myProfile(Model model, HttpSession session) {
	                    
	                    String jobseekeremail = (String) session.getAttribute("email");

	                    if (jobseekeremail == null) {
	                        return "redirect:/jobseekerlogin";
	                    }

	                    JobseekerEntity profile = jobSeekerService.getProfileByJobsekerEmail(jobseekeremail);
	                    model.addAttribute("profile", profile);

	                    return "jobseekermyprofile";
	                }

	                @GetMapping("/jobseeker/findjobs")
	                @Operation(summary = "Find Jobs", 
	                           description = "Fetches and displays all available jobs for jobseekers.")
	                public String jobseekerFindJobs(Model model) {
	                    List<JobEntity> jobs = jobSeekerService.getAllJobs();
	                    model.addAttribute("jobs", jobs);
	                    return "jobseekerfindjobs";
	                }

	  

	                    @PostMapping("/jobseeker/applyjob")
	                    @Operation(summary = "Apply for a Job", 
	                               description = "Allows a jobseeker to apply for a job by submitting job application details.")
	                    public String saveJobApplication(@ModelAttribute JobApplicationDTO dto) throws IOException {
	                        String result = jobSeekerService.saveJobApplication(dto);

	                        if ("success".equals(result)) {
	                            return "redirect:/jobseeker/findjobs?success";
	                        } else {
	                            return "redirect:/jobseeker/findjobs?error";
	                        }
	                    }

	                    @GetMapping("/jobseeker/back")
	                    @Operation(summary = "Back to Jobseeker Home", 
	                               description = "Navigates the jobseeker back to the home page.")
	                    public String jobseekerBack() {
	                        return "jobseekerhome";
	                    }

	                    @GetMapping("/jobseeker/logout")
	                    @Operation(summary = "Jobseeker Logout", 
	                               description = "Logs out the jobseeker by invalidating the session and redirects to login page.")
	                    public String jobseekerLogout(HttpServletRequest request, HttpServletResponse response) {
	                        HttpSession session = request.getSession(false);
	                        if (session != null) {
	                            session.invalidate();
	                        }
	                        return "redirect:/jobseekerlogin?logout";
	                    }




}
