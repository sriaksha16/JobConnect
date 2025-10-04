package com.example.jobconnect.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmployerDTO {
	
	
	    private MultipartFile logo;
	    private String companyname;
	    private String website;
	    private String industry;
	    private String companysize;

	    private String companydescription;
	    private String employername;
	    private String employeremail;
	    private String jobtitle;

	    private String mobile;
	    private String password;

	    private MultipartFile idproof;
	    
	    private String fp;

		public MultipartFile getLogo() {
			return logo;
		}

		public void setLogo(MultipartFile logo) {
			this.logo = logo;
		}

		public String getCompanyname() {
			return companyname;
		}

		public void setCompanyname(String companyname) {
			this.companyname = companyname;
		}

		public String getWebsite() {
			return website;
		}

		public void setWebsite(String website) {
			this.website = website;
		}

		public String getIndustry() {
			return industry;
		}

		public void setIndustry(String industry) {
			this.industry = industry;
		}

		public String getCompanysize() {
			return companysize;
		}

		public void setCompanysize(String companysize) {
			this.companysize = companysize;
		}

		public String getCompanydescription() {
			return companydescription;
		}

		public void setCompanydescription(String companydescription) {
			this.companydescription = companydescription;
		}

		public String getEmployername() {
			return employername;
		}

		public void setEmployername(String employername) {
			this.employername = employername;
		}

		public String getEmployeremail() {
			return employeremail;
		}

		public void setEmployeremail(String employeremail) {
			this.employeremail = employeremail;
		}

		public String getJobtitle() {
			return jobtitle;
		}

		public void setJobtitle(String jobtitle) {
			this.jobtitle = jobtitle;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public MultipartFile getIdproof() {
			return idproof;
		}

		public void setIdproof(MultipartFile idproof) {
			this.idproof = idproof;
		}

		public String getFp() {
			return fp;
		}

		public void setFp(String fp) {
			this.fp = fp;
		}
	    
	    
	    
	    
	    
	    

}
