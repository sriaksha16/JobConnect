package com.example.jobconnect.model;

import java.util.Arrays;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "employers")
public class EmployerEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    
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
    
    private String fp;

    
    
    
    @Lob
    private byte[] logo;
    private String fileName;
    private String fileType;
    
    @Lob
    private byte[] idproof;
    private String fileName1;
    private String fileType1;
    
    private boolean enabled = false;
    private String verificationToken;
    
    
    
    
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
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
	public byte[] getIdproof() {
		return idproof;
	}
	public void setIdproof(byte[] idproof) {
		this.idproof = idproof;
	}
	public String getFileName1() {
		return fileName1;
	}
	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	public String getFileType1() {
		return fileType1;
	}
	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
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
		return "EmployerEntity [id=" + id + ", companyname=" + companyname + ", website=" + website + ", industry="
				+ industry + ", companysize=" + companysize + ", companydescription=" + companydescription
				+ ", employername=" + employername + ", employeremail=" + employeremail + ", jobtitle=" + jobtitle
				+ ", mobile=" + mobile + ", password=" + password + ", fp=" + fp + ", logo=" + Arrays.toString(logo)
				+ ", fileName=" + fileName + ", fileType=" + fileType + ", idproof=" + Arrays.toString(idproof)
				+ ", fileName1=" + fileName1 + ", fileType1=" + fileType1 + ", enabled=" + enabled
				+ ", verificationToken=" + verificationToken + "]";
	}
	public EmployerEntity() {
		super();
		this.id = id;
		this.companyname = companyname;
		this.website = website;
		this.industry = industry;
		this.companysize = companysize;
		this.companydescription = companydescription;
		this.employername = employername;
		this.employeremail = employeremail;
		this.jobtitle = jobtitle;
		this.mobile = mobile;
		this.password = password;
		this.fp = fp;
		this.logo = logo;
		this.fileName = fileName;
		this.fileType = fileType;
		this.idproof = idproof;
		this.fileName1 = fileName1;
		this.fileType1 = fileType1;
		this.enabled = enabled;
		this.verificationToken = verificationToken;
	}

    
    
    

	
    

}
