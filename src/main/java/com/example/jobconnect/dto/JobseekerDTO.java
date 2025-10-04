package com.example.jobconnect.dto;

import org.springframework.web.multipart.MultipartFile;

public class JobseekerDTO {
	
	
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private String location;

	    private String jobTitle;
	    private String industry;
	    private String experienceLevel;
	    private String skills;

	    private String username;
	    private String password;
	    
	    private String fp;

	    private MultipartFile resume;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public String getIndustry() {
			return industry;
		}

		public void setIndustry(String industry) {
			this.industry = industry;
		}

		public String getExperienceLevel() {
			return experienceLevel;
		}

		public void setExperienceLevel(String experienceLevel) {
			this.experienceLevel = experienceLevel;
		}

		public String getSkills() {
			return skills;
		}

		public void setSkills(String skills) {
			this.skills = skills;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public MultipartFile getResume() {
			return resume;
		}

		public void setResume(MultipartFile resume) {
			this.resume = resume;
		}

		public String getFp() {
			return fp;
		}

		public void setFp(String fp) {
			this.fp = fp;
		}
	    
	    
	    
	    
	    

}
