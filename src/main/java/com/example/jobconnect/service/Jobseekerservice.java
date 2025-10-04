package com.example.jobconnect.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.jobconnect.dto.JobApplicationDTO;
import com.example.jobconnect.dto.JobseekerDTO;

import com.example.jobconnect.model.JobApplicationEntity;
import com.example.jobconnect.model.JobEntity;
import com.example.jobconnect.model.JobseekerEntity;
import com.example.jobconnect.repository.JobApplicationRepository;
import com.example.jobconnect.repository.JobRepository;
import com.example.jobconnect.repository.JobseekerRepository;

@Service
public class Jobseekerservice {

    @Autowired
    private JobseekerRepository repository;
    
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	private JobApplicationRepository jobApplicationRepository;

   
    public String registerJobSeeker(JobseekerDTO dto) throws IOException {
        if (repository.existsByEmail(dto.getEmail())) {
            return "email_exists";
        }
        if (repository.existsByUsername(dto.getUsername())) {
            return "username_exists";
        }

        JobseekerEntity jobSeeker = new JobseekerEntity();
        jobSeeker.setFirstName(dto.getFirstName());
        jobSeeker.setLastName(dto.getLastName());
        jobSeeker.setEmail(dto.getEmail());
        jobSeeker.setPhone(dto.getPhone());
        jobSeeker.setLocation(dto.getLocation());
        jobSeeker.setJobTitle(dto.getJobTitle());
        jobSeeker.setIndustry(dto.getIndustry());
        jobSeeker.setExperienceLevel(dto.getExperienceLevel());
        jobSeeker.setSkills(dto.getSkills());
        jobSeeker.setUsername(dto.getUsername());
        jobSeeker.setFp(dto.getFp());

        
        jobSeeker.setPassword(passwordEncoder.encode(dto.getPassword()));

        
        MultipartFile resumeFile = dto.getResume();
        if (resumeFile != null && !resumeFile.isEmpty()) {
            jobSeeker.setResume(resumeFile.getBytes());
            jobSeeker.setFileName(resumeFile.getOriginalFilename());
            jobSeeker.setFileType(resumeFile.getContentType());
        }

        
        String token = UUID.randomUUID().toString();
        jobSeeker.setVerificationToken(token);
        jobSeeker.setEnabled(false);

        repository.save(jobSeeker);

        
        String verificationLink = "http://localhost:8080/jobseeker/verify?token=" + token;
        emailService.sendVerificationEmail(dto.getEmail(), dto.getFirstName(), verificationLink);

        return "success";
    }

    
    public boolean verifyAccount(String token) {
        JobseekerEntity jobSeeker = repository.findByVerificationToken(token);
        if (jobSeeker == null) {
            return false;
        }
        jobSeeker.setEnabled(true);
        jobSeeker.setVerificationToken(null);
        repository.save(jobSeeker);
        return true;
    }

    
    public JobseekerEntity validatejobseekerLogin(String email, String rawPassword) {
        JobseekerEntity jobseeker = repository.findByEmail(email);

        if (jobseeker != null && jobseeker.isEnabled()) {
            
            if (passwordEncoder.matches(rawPassword, jobseeker.getPassword())) {
                return jobseeker;
            }
        }
        return null;
    }

	public List<JobEntity> getAllJobs() {
		
		return jobRepository.findAll();
	}
	public String saveJobApplication(JobApplicationDTO dto) {
        try {
            JobApplicationEntity entity = new JobApplicationEntity();
            
            
            entity.setApplicantName(dto.getApplicantName());
            entity.setApplicantEmail(dto.getApplicantEmail());
            entity.setApplicantPhone(dto.getApplicantPhone());
            entity.setApplicantAddress(dto.getApplicantAddress());
            entity.setApplicantEducation(dto.getApplicantEducation());
            entity.setApplicantExperience(dto.getApplicantExperience());
            entity.setApplicantSkills(dto.getApplicantSkills());
            entity.setCoverLetter(dto.getCoverLetter());

            
            if (dto.getResumeFile() != null && !dto.getResumeFile().isEmpty()) {
                entity.setResumeFile(dto.getResumeFile().getBytes());
                entity.setResumeFileName(dto.getResumeFile().getOriginalFilename());
                entity.setResumeFileType(dto.getResumeFile().getContentType());
            }

            
            if (dto.getCoverLetterFile() != null && !dto.getCoverLetterFile().isEmpty()) {
                entity.setCoverLetterFile(dto.getCoverLetterFile().getBytes());
                entity.setCoverLetterFileName(dto.getCoverLetterFile().getOriginalFilename());
                entity.setCoverLetterFileType(dto.getCoverLetterFile().getContentType());
            }

            
            entity.setJobId(dto.getJobId());
            entity.setJobTitle(dto.getJobTitle());
            entity.setCompany(dto.getCompany());
            entity.setDepartment(dto.getDepartment());
            entity.setJobType(dto.getJobType());
            entity.setLocation(dto.getLocation());
            entity.setSalary(dto.getSalary());
            entity.setDescription(dto.getDescription());

            jobApplicationRepository.save(entity);
            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

	public JobseekerEntity getProfileByJobsekerEmail(String jobseekeremail) {
		
		return repository.findByEmail(jobseekeremail);
	}
	
	
	public boolean generateAndSendOtp(String email) {
	    JobseekerEntity jobseeker = repository.findByEmail(email);
	    if (jobseeker == null) {
	        return false;
	    }

	    String otp = String.valueOf(100000 + new Random().nextInt(900000));
	    jobseeker.setFp(otp);
	    repository.save(jobseeker);

	    
	    emailService.sendOtpEmail(email, otp);

	    return true;
	}
	
	
	 public boolean verifyOtp(String email, String otp) {
	        JobseekerEntity jobseeker = repository.findByEmail(email);
	        return jobseeker != null && otp.equals(jobseeker.getFp());
	    }
	 
	 
	 
	 public void updatePassword(String email, String newPassword) {
		 JobseekerEntity jobseeker =repository.findByEmail(email);
	        if (jobseeker != null) {
	            String hashedPassword = passwordEncoder.encode(newPassword);
	            jobseeker.setPassword(hashedPassword);
	            jobseeker.setFp(null); 
	            repository.save(jobseeker);
	        }
	    }
	

	
}
