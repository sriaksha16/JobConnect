package com.example.jobconnect.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.jobconnect.dto.EmployerDTO;
import com.example.jobconnect.dto.JobDTO;
import com.example.jobconnect.model.EmployerEntity;
import com.example.jobconnect.model.JobEntity;
import com.example.jobconnect.model.JobApplicationEntity;
import com.example.jobconnect.repository.EmpoyerRepository;
import com.example.jobconnect.repository.JobApplicationRepository;
import com.example.jobconnect.repository.JobRepository;
import com.example.jobconnect.repository.JobseekerRepository;
import com.example.jobconnect.service.EmailService;
import com.example.jobconnect.service.EmployerService;
import com.example.jobconnect.service.TwilioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

class EmployerServiceTest {

    @Mock
    private EmpoyerRepository employerRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobseekerRepository jobSeekerRepository;

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private TwilioService twilioService;

    @InjectMocks
    private EmployerService employerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterEmployer_Success() throws IOException {
        EmployerDTO dto = new EmployerDTO();
        dto.setEmployeremail("test@example.com");
        dto.setEmployername("Test Employer");
        dto.setPassword("password123");

        when(employerRepository.existsByEmployeremail(anyString())).thenReturn(false);
        when(employerRepository.existsByEmployername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String result = employerService.registerEmployer(dto);

        assertEquals("success", result);
        verify(employerRepository, times(1)).save(any(EmployerEntity.class));
        verify(emailService, times(1)).sendVerificationEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testRegisterEmployer_EmailExists() throws IOException {
        EmployerDTO dto = new EmployerDTO();
        dto.setEmployeremail("test@example.com");

        when(employerRepository.existsByEmployeremail(anyString())).thenReturn(true);

        String result = employerService.registerEmployer(dto);
        assertEquals("employeremail_exists", result);
    }

    @Test
    void testValidateEmployerLogin_Success() {
        EmployerEntity employer = new EmployerEntity();
        employer.setEnabled(true);
        employer.setPassword("encodedPassword");

        when(employerRepository.findByEmployeremail(anyString())).thenReturn(employer);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        EmployerEntity result = employerService.validateemployerLogin("test@example.com", "password123");
        assertNotNull(result);
    }

    @Test
    void testSaveJobs_Success() throws IOException {
        JobDTO dto = new JobDTO();
        dto.setJobTitle("Java Developer");
        dto.setDepartment("IT");
        dto.setCompanyname("TestCompany");

        when(jobSeekerRepository.findAll()).thenReturn(List.of());

        String result = employerService.saveJobs(dto);
        assertEquals("success", result);
        verify(jobRepository, times(1)).save(any(JobEntity.class));
    }

    @Test
    void testGetJobsByEmployerEmail_NoJobs() {
        when(jobRepository.findByEmployeremail(anyString())).thenReturn(List.of());
        List<JobEntity> jobs = employerService.getJobsByEmployerEmail("test@example.com");
        assertTrue(jobs.isEmpty());
    }

    @Test
    void testVerifyAccount_Success() {
        EmployerEntity employer = new EmployerEntity();
        when(employerRepository.findByVerificationToken(anyString())).thenReturn(employer);

        boolean result = employerService.verifyAccount("token");
        assertTrue(result);
        assertNull(employer.getVerificationToken());
        assertTrue(employer.isEnabled());
        verify(employerRepository).save(employer);
    }

}
