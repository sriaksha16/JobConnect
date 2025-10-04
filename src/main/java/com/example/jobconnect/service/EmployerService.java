package com.example.jobconnect.service;

import java.io.IOException;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.jobconnect.dto.EmployerDTO;

import com.example.jobconnect.dto.JobDTO;
import com.example.jobconnect.model.EmployerEntity;
import com.example.jobconnect.model.JobApplicationEntity;
import com.example.jobconnect.model.JobEntity;
import com.example.jobconnect.model.JobseekerEntity;
import com.example.jobconnect.repository.EmpoyerRepository;
import com.example.jobconnect.repository.JobApplicationRepository;
import com.example.jobconnect.repository.JobRepository;
import com.example.jobconnect.repository.JobseekerRepository;

@Service
public class EmployerService {

	
		 @Autowired
		 private EmpoyerRepository repository;

		 @Autowired
		 private EmailService emailService;

		 @Autowired
		 private PasswordEncoder passwordEncoder;
		 
		 @Autowired
		 private JobRepository jobRepository;
		 
		 @Autowired
		 private TwilioService twilioService;
		 
		 @Autowired
		 private JobseekerRepository jobSeekerRepository;
		 
		 @Autowired
		 private JobApplicationRepository jobApplicationRepository;

		   
		 public String registerEmployer(EmployerDTO dto) throws IOException {
			    if (repository.existsByEmployeremail(dto.getEmployeremail())) {
			        return "employeremail_exists";
			    }
			    if (repository.existsByEmployername(dto.getEmployername())) {
			        return "employername_exists";
			    }

			    EmployerEntity employer = new EmployerEntity();
			    employer.setCompanyname(dto.getCompanyname());
			    employer.setWebsite(dto.getWebsite());
			    employer.setIndustry(dto.getIndustry());
			    employer.setCompanysize(dto.getCompanysize());
			    employer.setCompanydescription(dto.getCompanydescription());

			    employer.setEmployername(dto.getEmployername());
			    employer.setEmployeremail(dto.getEmployeremail());
			    employer.setJobtitle(dto.getJobtitle());
			    employer.setMobile(dto.getMobile());

			    employer.setPassword(passwordEncoder.encode(dto.getPassword()));
			    employer.setFp(dto.getFp());

			    
			    MultipartFile logoFile = dto.getLogo();
			    if (logoFile != null && !logoFile.isEmpty()) {
			        employer.setLogo(logoFile.getBytes());
			        employer.setFileName(logoFile.getOriginalFilename());
			        employer.setFileType(logoFile.getContentType());
			    }

			   
			    MultipartFile idProofFile = dto.getIdproof();
			    if (idProofFile != null && !idProofFile.isEmpty()) {
			        employer.setIdproof(idProofFile.getBytes());
			        employer.setFileName1(idProofFile.getOriginalFilename());
			        employer.setFileType1(idProofFile.getContentType());
			    }

			    
			    String token = UUID.randomUUID().toString();
			    employer.setVerificationToken(token);
			    employer.setEnabled(false);

			    repository.save(employer);

			    
			    String verificationLink = "http://localhost:8080/employer/verify?token=" + token;
			    emailService.sendVerificationEmail(dto.getEmployeremail(), dto.getEmployername(), verificationLink);

			    return "success";
			}
		 
		 
		
		    public boolean verifyAccount(String token) {
		    	EmployerEntity employer = repository.findByVerificationToken(token);
		        if (employer == null) {
		            return false;
		        }
		        employer.setEnabled(true);
		        employer.setVerificationToken(null);
		        repository.save(employer);
		        return true;
		    }

		    
		    
		
		    public EmployerEntity validateemployerLogin(String email, String rawPassword) {
		        EmployerEntity employer = repository.findByEmployeremail(email);

		        if (employer != null && employer.isEnabled()) {
		            if (passwordEncoder.matches(rawPassword, employer.getPassword())) {
		                return employer;
		            }
		        }
		        return null;
		    }
		    
		    
		    
		    public String saveJobs(JobDTO dto) throws IOException {
		        JobEntity job = new JobEntity();

		        job.setJobTitle(dto.getJobTitle());
		        job.setJobDescription(dto.getJobDescription());
		        job.setJobType(dto.getJobType());
		        job.setDepartment(dto.getDepartment());
		        job.setCompanyname(dto.getCompanyname());
		        job.setEmployeremail(dto.getEmployeremail());
		        job.setCountry(dto.getCountry());
		        job.setCity(dto.getCity());

		        job.setWorkArrangement(dto.getWorkArrangement());  
		        job.setMinSalary(dto.getMinSalary());
		        job.setMaxSalary(dto.getMaxSalary());
		        job.setBenefits(dto.getBenefits()); 

		        job.setApplicationDeadline(dto.getApplicationDeadline());
		        job.setApplyMethod(dto.getApplyMethod());
		        job.setApplicationUrl(dto.getApplicationUrl());

		        jobRepository.save(job);
		        
		        
		        List<JobseekerEntity> seekers = jobSeekerRepository.findAll();
		        for (JobseekerEntity seeker : seekers) {
		            if (seeker.getSkills() != null && seeker.getSkills().toLowerCase()
		                    .contains(dto.getDepartment().toLowerCase())) {

		                String message = "Hi " + seeker.getUsername() +
		                        ", a new job matching your skills is available: " +
		                        dto.getDepartment() + " at " + dto.getCompanyname() +
		                        ". Apply before " + dto.getApplicationDeadline() + ".";

		               
		                twilioService.sendSms(seeker.getPhone(), message);
		            }
		        }

		        return "success";
		    }


			
			
		    public List<JobEntity> getJobsByEmployerEmail(String employerEmail) {
		        System.out.println("Fetching jobs for employer: " + employerEmail);
		        List<JobEntity> jobs = jobRepository.findByEmployeremail(employerEmail);

		        if (jobs == null || jobs.isEmpty()) {
		            System.out.println("No jobs found for employer: " + employerEmail);
		            return List.of(); 
		        }

		        return jobs;
		    }


			
			
			public JobEntity getJobById(Long id) {
				System.out.println("Fetching job with ID: " + id);
				return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));
			}
			
			
		    public void updateJob(Long id, JobEntity updatedJob) {
		        JobEntity existingJob = getJobById(id);

		        existingJob.setJobTitle(updatedJob.getJobTitle());
		        existingJob.setDepartment(updatedJob.getDepartment());
		        existingJob.setJobType(updatedJob.getJobType());
		        existingJob.setCity(updatedJob.getCity());
		        existingJob.setCountry(updatedJob.getCountry());
		        existingJob.setWorkArrangement(updatedJob.getWorkArrangement());
		        existingJob.setMinSalary(updatedJob.getMinSalary());
		        existingJob.setMaxSalary(updatedJob.getMaxSalary());
		        existingJob.setApplicationDeadline(updatedJob.getApplicationDeadline());
		        existingJob.setApplyMethod(updatedJob.getApplyMethod());
		        existingJob.setApplicationUrl(updatedJob.getApplicationUrl());

		        jobRepository.save(existingJob);
		    }

		    public void deleteJob(Long id) {
		        jobRepository.deleteById(id);
		    }
		    
		    
		    public List<JobApplicationEntity> getJobApplicationsByEmployerEmail(String employerEmail) {
		        
		        List<JobEntity> jobs = jobRepository.findByEmployeremail(employerEmail);

		        if (jobs.isEmpty()) {
		            return List.of(); 
		        }

		        
		        List<Long> jobIds = jobs.stream()
		                .map(JobEntity::getId)
		                .collect(Collectors.toList());

		        
		        return jobApplicationRepository.findByJobIdIn(jobIds);
		    }


			public EmployerEntity getProfileByEmployerEmail(String employeremail) {
				
				return repository.findByEmployeremail(employeremail);
			}
			
			
			
			public boolean generateAndSendOtp(String email) {
			    EmployerEntity employer = repository.findByEmployeremail(email);
			    if (employer == null) {
			        return false;
			    }

			    String otp = String.valueOf(100000 + new Random().nextInt(900000));
			    employer.setFp(otp);
			    repository.save(employer);

			    
			    emailService.sendOtpEmail(email, otp);

			    return true;
			}



			 public boolean verifyOtp(String email, String otp) {
			        EmployerEntity employer = repository.findByEmployeremail(email);
			        return employer != null && otp.equals(employer.getFp());
			    }


			 public void updatePassword(String email, String newPassword) {
			        EmployerEntity employer =repository.findByEmployeremail(email);
			        if (employer != null) {
			            String hashedPassword = passwordEncoder.encode(newPassword);
			            employer.setPassword(hashedPassword);
			            employer.setFp(null); 
			            repository.save(employer);
			        }
			    }
			
	

}
