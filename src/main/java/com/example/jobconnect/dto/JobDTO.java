package com.example.jobconnect.dto;

import java.time.LocalDate;

public class JobDTO {
	    private String jobTitle;
	    private String jobDescription;
	    private String jobType;
	    private String department;
	    private String companyname;
	    private String employeremail;
	   
		private String country;
	    private String city;
	    private String workArrangement; 
	    private String minSalary;
	    private String maxSalary;
	    private String benefits;
	    private LocalDate applicationDeadline;
	    private String applyMethod;
	    private String applicationUrl;
	    
	    
	    
	    public String getCompanyname() {
			return companyname;
		}
		public void setCompanyname(String companyname) {
			this.companyname = companyname;
		}
		public String getEmployeremail() {
			return employeremail;
		}
		public void setEmployeremail(String employeremail) {
			this.employeremail = employeremail;
		}
		public String getJobTitle() {
			return jobTitle;
		}
		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
		public String getJobDescription() {
			return jobDescription;
		}
		public void setJobDescription(String jobDescription) {
			this.jobDescription = jobDescription;
		}
		public String getJobType() {
			return jobType;
		}
		public void setJobType(String jobType) {
			this.jobType = jobType;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getWorkArrangement() {
			return workArrangement;
		}
		public void setWorkArrangement(String workArrangement) {
			this.workArrangement = workArrangement;
		}
		public String getMinSalary() {
			return minSalary;
		}
		public void setMinSalary(String minSalary) {
			this.minSalary = minSalary;
		}
		public String getMaxSalary() {
			return maxSalary;
		}
		public void setMaxSalary(String maxSalary) {
			this.maxSalary = maxSalary;
		}
		public String getBenefits() {
			return benefits;
		}
		public void setBenefits(String benefits) {
			this.benefits = benefits;
		}
		public LocalDate getApplicationDeadline() {
			return applicationDeadline;
		}
		public void setApplicationDeadline(LocalDate applicationDeadline) {
			this.applicationDeadline = applicationDeadline;
		}
		public String getApplyMethod() {
			return applyMethod;
		}
		public void setApplyMethod(String applyMethod) {
			this.applyMethod = applyMethod;
		}
		public String getApplicationUrl() {
			return applicationUrl;
		}
		public void setApplicationUrl(String applicationUrl) {
			this.applicationUrl = applicationUrl;
		}
	    
	    
	    
    
    
    
}

