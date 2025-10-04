package com.example.jobconnect.model;

import java.util.Arrays;

import jakarta.persistence.*;

@Entity
@Table(name = "jobseekers")
public class JobseekerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @Lob
    private byte[] resume;
    private String fileName;
    private String fileType;

    
    private boolean enabled = false;
    private String verificationToken;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getVerificationToken() {
		return verificationToken;
	}
	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	@Override
	public String toString() {
		return "JobseekerEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", location=" + location + ", jobTitle=" + jobTitle + ", industry=" + industry
				+ ", experienceLevel=" + experienceLevel + ", skills=" + skills + ", username=" + username
				+ ", password=" + password + ", fp=" + fp + ", resume=" + Arrays.toString(resume) + ", fileName="
				+ fileName + ", fileType=" + fileType + ", enabled=" + enabled + ", verificationToken="
				+ verificationToken + "]";
	}
	public JobseekerEntity() {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.jobTitle = jobTitle;
		this.industry = industry;
		this.experienceLevel = experienceLevel;
		this.skills = skills;
		this.username = username;
		this.password = password;
		this.fp = fp;
		this.resume = resume;
		this.fileName = fileName;
		this.fileType = fileType;
		this.enabled = enabled;
		this.verificationToken = verificationToken;
	}
	
    
    
    
    
	

    


}
