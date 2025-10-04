package com.example.jobconnect.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.jobconnect.dto.EmployerDTO;

import com.example.jobconnect.dto.JobDTO;
import com.example.jobconnect.model.EmployerEntity;
import com.example.jobconnect.model.JobApplicationEntity;
import com.example.jobconnect.model.JobEntity;
import com.example.jobconnect.repository.JobApplicationRepository;
import com.example.jobconnect.service.EmployerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.util.Optional;


@Controller
@Tag(name = "Employer APIs", description = "Endpoints for employer operations")
public class EmployerController {
	
	@Autowired
    private EmployerService employerService;
	
	@Autowired
    private JobApplicationRepository jobApplicationRepository;
	
	
	@PostMapping("/employer/register")
	@Operation(summary = "Register Employer", description = "Registers a new employer account")
	public String register(@ModelAttribute EmployerDTO dto) throws IOException {
	    String result = employerService.registerEmployer(dto);

	    if ("employeremail_exists".equals(result)) {
	        return "redirect:/employerlogin?error=employeremail_exists";
	    }
	    if ("employername_exists".equals(result)) {
	        return "redirect:/employerlogin?error=employername_exists";
	    }

	    return "redirect:/employerlogin?verify_employeremail";
	}
	
	

	    @GetMapping("/employer/verify")
	    @Operation(summary = "Verify Employer Account", description = "Verify employer account using the verification token sent via email")
	    public String verifyAccount(@RequestParam("token") String token) {
	        boolean verified = employerService.verifyAccount(token);
	        if (verified) {
	            return "redirect:/employerlogin?verified";
	        } else {
	            return "redirect:/employerlogin?invalid_token";
	        }
	    }

	    @PostMapping("/employer/login")
	    @Operation(summary = "Employer Login", description = "Authenticate employer using email and password. Redirects to employer home if successful.")
	    public String employerLogin(@RequestParam("email") String email,
	                                @RequestParam("password") String password,
	                                Model model, HttpSession session) {

	        EmployerEntity employer = employerService.validateemployerLogin(email, password);

	        if (employer != null) {
	            model.addAttribute("employer", employer);
	            session.setAttribute("email", email);
	            return "employerhome";
	        } else {
	            model.addAttribute("error", "Invalid username or password, or account not verified!");
	            return "employerlogin";
	        }
	    }

	
	
	    @GetMapping("/employer/forgotpassword")
	    @Operation(summary = "Forgot Password Page", description = "Display the forgot password page for employer to enter their email")
	    public String employerForgotPassword() {
	        return "forgotpassword";
	    }

	    @PostMapping("/employer/otp")
	    @Operation(summary = "Send OTP", description = "Send OTP to employer's registered email for password reset")
	    public String sendOtp(@RequestParam("email") String email,
	                          RedirectAttributes redirectAttributes,
	                          HttpSession session) {

	        boolean otpSent = employerService.generateAndSendOtp(email);

	        if (!otpSent) {
	            redirectAttributes.addFlashAttribute("error", "Email not found!");
	            return "redirect:/employer/forgot-password";
	        }

	        session.setAttribute("resetEmail", email);

	        redirectAttributes.addFlashAttribute("success", "OTP sent to your email!");
	        return "redirect:/employer/verify-otp";
	    }

	    @GetMapping("/employer/verify-otp")
	    @Operation(summary = "Verify OTP Page", description = "Display the OTP verification page for employer")
	    public String showVerifyOtpPage() {
	        return "employerverifyotp";
	    }

	    @PostMapping("/employer/verify-otp")
	    @Operation(summary = "Verify OTP", description = "Verify the OTP entered by employer before allowing password reset")
	    public String verifyOtp(@RequestParam("otp") String otp,
	                            HttpSession session,
	                            RedirectAttributes redirectAttributes) {

	        String email = (String) session.getAttribute("resetEmail");
	        if (email == null) {
	            return "redirect:/employer/forgotpassword";
	        }

	        boolean isValid = employerService.verifyOtp(email, otp);

	        if (!isValid) {
	            redirectAttributes.addFlashAttribute("error", "Invalid OTP!");
	            return "redirect:/employer/verify-otp";
	        }

	        return "redirect:/employer/reset-password";
	    }

	 
	 

	     @GetMapping("/employer/reset-password")
	     @Operation(summary = "Show Reset Password Page", description = "Display the reset password form for the employer")
	     public String showResetPasswordPage() {
	         return "employerresetpassword";
	     }

	     @PostMapping("/employer/reset-password")
	     @Operation(summary = "Reset Employer Password", description = "Reset employer's password using the new password provided")
	     public String resetPassword(@RequestParam("newPassword") String newPassword,
	                                 HttpSession session,
	                                 RedirectAttributes redirectAttributes) {

	         String email = (String) session.getAttribute("resetEmail");
	         if (email == null) {
	             return "redirect:/employer/forgotpassword";
	         }

	         employerService.updatePassword(email, newPassword);
	         session.removeAttribute("resetEmail");

	         redirectAttributes.addFlashAttribute("success", "Password reset successfully! Please login.");
	         return "redirect:/employerlogin";
	     }

	
	@GetMapping("/employer/profile")
	@Operation(summary = "Get Employer Profile", description = "Returns the profile details of the logged-in employer")
	  public String myProfile(Model model, HttpSession session) {
	      
	      String employeremail = (String) session.getAttribute("email");

	      if (employeremail == null) {
	          return "redirect:/employerlogin";
	      }

	      EmployerEntity jobs = employerService.getProfileByEmployerEmail(employeremail);
	      
	      model.addAttribute("jobs", jobs);

	      return "employersmyprofile";
	  }
	
	

	    @GetMapping("/employer/postjob")
	    @Operation(summary = "Post Job Form", description = "Display the form for employer to post a new job")
	    public String employerPostJob() {
	        return "postjob";
	    }

	    @GetMapping("/employer/back")
	    @Operation(summary = "Back to Employer Home", description = "Navigate back to employer home page")
	    public String employerBack() {
	        return "employerhome";
	    }

	    @PostMapping("/employer/savejob")
	    @Operation(summary = "Save Job", description = "Save a new job posted by the employer")
	    public String saveJob(@ModelAttribute JobDTO dto) throws IOException {
	        String result = employerService.saveJobs(dto);

	        if ("success".equals(result)) {
	            return "redirect:/employer/postjob?success";
	        } else {
	            return "redirect:/employer/postjob?error";
	        }
	    }

	    @GetMapping("/employer/myjob")
	    @Operation(summary = "List Employer Jobs", description = "Retrieve all jobs posted by the currently logged-in employer")
	    public String myJob(Model model, HttpSession session) {
	        String employeremail = (String) session.getAttribute("email");

	        if (employeremail == null) {
	            return "redirect:/employerlogin";
	        }

	        List<JobEntity> jobs = employerService.getJobsByEmployerEmail(employeremail);

	        model.addAttribute("jobs", jobs);

	        return "employersmyjobs";
	    }

	 
	 

	     @GetMapping("/employer/jobs/{id}/view")
	     @Operation(summary = "View Job Details", description = "Get detailed information about a specific job by its ID")
	     public String viewJob(@PathVariable Long id, Model model) {
	         JobEntity job = employerService.getJobById(id);
	         model.addAttribute("job", job);
	         return "employerviewjob";
	     }

	     @GetMapping("/employer/jobs/{id}/edit")
	     @Operation(summary = "Edit Job Form", description = "Retrieve job details to pre-fill the edit form for the employer")
	     public String editJobForm(@PathVariable Long id, Model model) {
	         JobEntity job = employerService.getJobById(id);
	         model.addAttribute("job", job);
	         return "employereditjob";
	     }

	     @PostMapping("/employer/jobs/{id}/edit")
	     @Operation(summary = "Update Job", description = "Update job details for the given job ID")
	     public String updateJob(@PathVariable Long id,
	                             @ModelAttribute("job") JobEntity updatedJob,
	                             RedirectAttributes redirectAttributes) {
	         employerService.updateJob(id, updatedJob);
	         redirectAttributes.addFlashAttribute("success", "Job updated successfully!");
	         return "redirect:/employer/myjob";
	     }

	     @GetMapping("/employer/jobs/{id}/delete")
	     @Operation(summary = "Delete Job", description = "Delete a job by its ID for the employer")
	     public String deleteJob(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	         employerService.deleteJob(id);
	         redirectAttributes.addFlashAttribute("success", "Job deleted successfully!");
	         return "redirect:/employer/myjob";
	     }

	
	   
	    @GetMapping("/employer/jobapplications")
	    @Operation(summary = "List Job Applications", description = "Get all job applications for the employer")
	    public String listApplications(Model model, HttpSession session) {
	        String employerEmail = (String) session.getAttribute("email");

	        if (employerEmail == null) {
	            return "redirect:/employerlogin";
	        }

	        List<JobApplicationEntity> applications = employerService.getJobApplicationsByEmployerEmail(employerEmail);

	        model.addAttribute("applications", applications);
	        return "employersmyjobapplications";
	    }
	    
	    
	    
	    @GetMapping("/employer/download/resume/{id}")
	    @Operation(summary = "Download Resume", description = "Download the resume file for a given job application")
	    public ResponseEntity<byte[]> downloadResume(@PathVariable Long id) {
	        Optional<JobApplicationEntity> optionalApp = jobApplicationRepository.findById(id);
	        if (optionalApp.isEmpty() || optionalApp.get().getResumeFile() == null) {
	            return ResponseEntity.notFound().build();
	        }

	        JobApplicationEntity app = optionalApp.get();

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resume_" + app.getId() + ".pdf\"")
	                .body(app.getResumeFile());
	    }

	    @GetMapping("/employer/download/coverletter/{id}")
	    @Operation(summary = "Download Cover Letter", description = "Download the cover letter file for a given job application")
	    public ResponseEntity<byte[]> downloadCoverLetter(@PathVariable Long id) {
	        Optional<JobApplicationEntity> optionalApp = jobApplicationRepository.findById(id);
	        if (optionalApp.isEmpty() || optionalApp.get().getCoverLetterFile() == null) {
	            return ResponseEntity.notFound().build();
	        }

	        JobApplicationEntity app = optionalApp.get();

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"coverletter_" + app.getId() + ".pdf\"")
	                .body(app.getCoverLetterFile());
	    }
	    
	    
	    @GetMapping("/employer/logout")
	    @Operation(summary = "Employer Logout", description = "Securely logout your session")
	    public String employerLogout(HttpServletRequest request, HttpServletResponse response) {
	        
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }

	        return "redirect:/employerlogin?logout";
	    }

}
