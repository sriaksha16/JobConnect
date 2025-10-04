package com.example.jobconnect.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "job_applications")
public class JobApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantAddress;
    private String applicantEducation;
    private Integer applicantExperience;

    @Lob
    @Column(length = 5000)
    private String applicantSkills;

    @Lob
    @Column(length = 5000)
    private String coverLetter;

    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resumeFile;

    private String resumeFileName;
    private String resumeFileType;

    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] coverLetterFile;

    private String coverLetterFileName;
    private String coverLetterFileType;

   
    private Long jobId;
    private String jobTitle;
    private String company;
    private String department;
    private String jobType;
    private String location;
    private String salary;

    @Lob
    @Column(length = 5000)
    private String description;

 
    @Column(name = "applied_date", columnDefinition = "DATETIME")
    private LocalDateTime appliedDate = LocalDateTime.now();


    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getApplicantEmail() { return applicantEmail; }
    public void setApplicantEmail(String applicantEmail) { this.applicantEmail = applicantEmail; }

    public String getApplicantPhone() { return applicantPhone; }
    public void setApplicantPhone(String applicantPhone) { this.applicantPhone = applicantPhone; }

    public String getApplicantAddress() { return applicantAddress; }
    public void setApplicantAddress(String applicantAddress) { this.applicantAddress = applicantAddress; }

    public String getApplicantEducation() { return applicantEducation; }
    public void setApplicantEducation(String applicantEducation) { this.applicantEducation = applicantEducation; }

    public Integer getApplicantExperience() { return applicantExperience; }
    public void setApplicantExperience(Integer applicantExperience) { this.applicantExperience = applicantExperience; }

    public String getApplicantSkills() { return applicantSkills; }
    public void setApplicantSkills(String applicantSkills) { this.applicantSkills = applicantSkills; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public byte[] getResumeFile() { return resumeFile; }
    public void setResumeFile(byte[] resumeFile) { this.resumeFile = resumeFile; }

    public String getResumeFileName() { return resumeFileName; }
    public void setResumeFileName(String resumeFileName) { this.resumeFileName = resumeFileName; }

    public String getResumeFileType() { return resumeFileType; }
    public void setResumeFileType(String resumeFileType) { this.resumeFileType = resumeFileType; }

    public byte[] getCoverLetterFile() { return coverLetterFile; }
    public void setCoverLetterFile(byte[] coverLetterFile) { this.coverLetterFile = coverLetterFile; }

    public String getCoverLetterFileName() { return coverLetterFileName; }
    public void setCoverLetterFileName(String coverLetterFileName) { this.coverLetterFileName = coverLetterFileName; }

    public String getCoverLetterFileType() { return coverLetterFileType; }
    public void setCoverLetterFileType(String coverLetterFileType) { this.coverLetterFileType = coverLetterFileType; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
	@Override
	public String toString() {
		return "JobApplicationEntity [id=" + id + ", applicantName=" + applicantName + ", applicantEmail="
				+ applicantEmail + ", applicantPhone=" + applicantPhone + ", applicantAddress=" + applicantAddress
				+ ", applicantEducation=" + applicantEducation + ", applicantExperience=" + applicantExperience
				+ ", applicantSkills=" + applicantSkills + ", coverLetter=" + coverLetter + ", resumeFile="
				+ Arrays.toString(resumeFile) + ", resumeFileName=" + resumeFileName + ", resumeFileType="
				+ resumeFileType + ", coverLetterFile=" + Arrays.toString(coverLetterFile) + ", coverLetterFileName="
				+ coverLetterFileName + ", coverLetterFileType=" + coverLetterFileType + ", jobId=" + jobId
				+ ", jobTitle=" + jobTitle + ", company=" + company + ", department=" + department + ", jobType="
				+ jobType + ", location=" + location + ", salary=" + salary + ", description=" + description
				+ ", appliedDate=" + appliedDate + "]";
	}
	public JobApplicationEntity() {
		super();
		this.id = id;
		this.applicantName = applicantName;
		this.applicantEmail = applicantEmail;
		this.applicantPhone = applicantPhone;
		this.applicantAddress = applicantAddress;
		this.applicantEducation = applicantEducation;
		this.applicantExperience = applicantExperience;
		this.applicantSkills = applicantSkills;
		this.coverLetter = coverLetter;
		this.resumeFile = resumeFile;
		this.resumeFileName = resumeFileName;
		this.resumeFileType = resumeFileType;
		this.coverLetterFile = coverLetterFile;
		this.coverLetterFileName = coverLetterFileName;
		this.coverLetterFileType = coverLetterFileType;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.company = company;
		this.department = department;
		this.jobType = jobType;
		this.location = location;
		this.salary = salary;
		this.description = description;
		this.appliedDate = appliedDate;
	}
    
    
    
    
    
    
    
    
}
