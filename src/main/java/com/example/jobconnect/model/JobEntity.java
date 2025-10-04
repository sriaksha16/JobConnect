package com.example.jobconnect.model;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="jobs")
public class JobEntity {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String jobTitle;
	    @Column(length = 2000)
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
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		@Override
		public String toString() {
			return "JobEntity [id=" + id + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription
					+ ", jobType=" + jobType + ", department=" + department + ", companyname=" + companyname
					+ ", employeremail=" + employeremail + ", country=" + country + ", city=" + city
					+ ", workArrangement=" + workArrangement + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary
					+ ", benefits=" + benefits + ", applicationDeadline=" + applicationDeadline + ", applyMethod="
					+ applyMethod + ", applicationUrl=" + applicationUrl + "]";
		}
		public JobEntity() {
			super();
			this.id = id;
			this.jobTitle = jobTitle;
			this.jobDescription = jobDescription;
			this.jobType = jobType;
			this.department = department;
			this.companyname = companyname;
			this.employeremail = employeremail;
			this.country = country;
			this.city = city;
			this.workArrangement = workArrangement;
			this.minSalary = minSalary;
			this.maxSalary = maxSalary;
			this.benefits = benefits;
			this.applicationDeadline = applicationDeadline;
			this.applyMethod = applyMethod;
			this.applicationUrl = applicationUrl;
		}

	    
	    
	    
	    
	    
	    
	    

}
