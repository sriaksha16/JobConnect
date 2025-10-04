package com.example.jobconnect.dto;

import org.springframework.web.multipart.MultipartFile;

public class JobApplicationDTO {
	
	 private String applicantName;
	    private String applicantEmail;
	    private String applicantPhone;
	    private String applicantAddress;
	    private String applicantEducation;
	    private Integer applicantExperience;
	    private String applicantSkills;
	    private String coverLetter; 

	    
	    private MultipartFile resumeFile;      
	    private MultipartFile coverLetterFile;  

	   
	    private Long jobId;
	    private String jobTitle;
	    private String company;
	    private String department;
	    private String jobType;
	    private String location;
	    private String salary;
	    private String description;
	    
	    
	    
	    private String resumeUrl;
	    private String coverLetterUrl;
	    
	    
	    
		public String getApplicantName() {
			return applicantName;
		}
		public void setApplicantName(String applicantName) {
			this.applicantName = applicantName;
		}
		public String getApplicantEmail() {
			return applicantEmail;
		}
		public void setApplicantEmail(String applicantEmail) {
			this.applicantEmail = applicantEmail;
		}
		public String getApplicantPhone() {
			return applicantPhone;
		}
		public void setApplicantPhone(String applicantPhone) {
			this.applicantPhone = applicantPhone;
		}
		public String getApplicantAddress() {
			return applicantAddress;
		}
		public void setApplicantAddress(String applicantAddress) {
			this.applicantAddress = applicantAddress;
		}
		public String getApplicantEducation() {
			return applicantEducation;
		}
		public void setApplicantEducation(String applicantEducation) {
			this.applicantEducation = applicantEducation;
		}
		public Integer getApplicantExperience() {
			return applicantExperience;
		}
		public void setApplicantExperience(Integer applicantExperience) {
			this.applicantExperience = applicantExperience;
		}
		public String getApplicantSkills() {
			return applicantSkills;
		}
		public void setApplicantSkills(String applicantSkills) {
			this.applicantSkills = applicantSkills;
		}
		public String getCoverLetter() {
			return coverLetter;
		}
		public void setCoverLetter(String coverLetter) {
			this.coverLetter = coverLetter;
		}
		public MultipartFile getResumeFile() {
			return resumeFile;
		}
		public void setResumeFile(MultipartFile resumeFile) {
			this.resumeFile = resumeFile;
		}
		public MultipartFile getCoverLetterFile() {
			return coverLetterFile;
		}
		public void setCoverLetterFile(MultipartFile coverLetterFile) {
			this.coverLetterFile = coverLetterFile;
		}
		public Long getJobId() {
			return jobId;
		}
		public void setJobId(Long jobId) {
			this.jobId = jobId;
		}
		public String getJobTitle() {
			return jobTitle;
		}
		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getJobType() {
			return jobType;
		}
		public void setJobType(String jobType) {
			this.jobType = jobType;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getSalary() {
			return salary;
		}
		public void setSalary(String salary) {
			this.salary = salary;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		
		public String getResumeUrl() {
			return resumeUrl;
		}
		public void setResumeUrl(String resumeUrl) {
			this.resumeUrl = resumeUrl;
		}
		public String getCoverLetterUrl() {
			return coverLetterUrl;
		}
		public void setCoverLetterUrl(String coverLetterUrl) {
			this.coverLetterUrl = coverLetterUrl;
		}
	    
	    
	    
	    
	    
	    
	    
	    

}
