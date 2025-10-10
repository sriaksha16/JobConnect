package com.example.jobconnect.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.example.jobconnect.dto.JobseekerDTO;
import com.example.jobconnect.model.JobseekerEntity;
import com.example.jobconnect.repository.JobseekerRepository;
import com.example.jobconnect.service.EmailService;
import com.example.jobconnect.service.Jobseekerservice;

public class JobseekerServiceTest {

    @InjectMocks
    private Jobseekerservice jobseekerService;

    @Mock
    private JobseekerRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterJobSeeker_Success() throws Exception {
        JobseekerDTO dto = new JobseekerDTO();
        dto.setEmail("test@example.com");
        dto.setUsername("testuser");
        dto.setPassword("password");
        dto.setFirstName("Test");

        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repository.existsByUsername(dto.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("hashedPassword");

        String result = jobseekerService.registerJobSeeker(dto);
        assertEquals("success", result);

        verify(repository, times(1)).save(any(JobseekerEntity.class));
        verify(emailService, times(1)).sendVerificationEmail(eq(dto.getEmail()), eq(dto.getFirstName()), anyString());
    }

    @Test
    void testRegisterJobSeeker_EmailExists() throws Exception {
        JobseekerDTO dto = new JobseekerDTO();
        dto.setEmail("test@example.com");

        when(repository.existsByEmail(dto.getEmail())).thenReturn(true);

        String result = jobseekerService.registerJobSeeker(dto);
        assertEquals("email_exists", result);
    }

    @Test
    void testVerifyAccount_Success() {
        JobseekerEntity entity = new JobseekerEntity();
        entity.setEnabled(false);

        String token = UUID.randomUUID().toString();
        when(repository.findByVerificationToken(token)).thenReturn(entity);

        boolean verified = jobseekerService.verifyAccount(token);
        assertTrue(verified);
        assertTrue(entity.isEnabled());
        verify(repository, times(1)).save(entity);
    }

    @Test
    void testValidateJobseekerLogin_Success() {
        JobseekerEntity entity = new JobseekerEntity();
        entity.setEnabled(true);
        entity.setPassword("hashedPassword");

        when(repository.findByEmail("test@example.com")).thenReturn(entity);
        when(passwordEncoder.matches("password", "hashedPassword")).thenReturn(true);

        JobseekerEntity result = jobseekerService.validatejobseekerLogin("test@example.com", "password");
        assertNotNull(result);
    }

    @Test
    void testGenerateAndVerifyOtp() {
        JobseekerEntity entity = new JobseekerEntity();
        when(repository.findByEmail("test@example.com")).thenReturn(entity);

        boolean sent = jobseekerService.generateAndSendOtp("test@example.com");
        assertTrue(sent);
        assertNotNull(entity.getFp());

        boolean valid = jobseekerService.verifyOtp("test@example.com", entity.getFp());
        assertTrue(valid);
    }

    @Test
    void testUpdatePassword() {
        JobseekerEntity entity = new JobseekerEntity();
        when(repository.findByEmail("test@example.com")).thenReturn(entity);
        when(passwordEncoder.encode("newPassword")).thenReturn("hashedNewPassword");

        jobseekerService.updatePassword("test@example.com", "newPassword");

        assertEquals("hashedNewPassword", entity.getPassword());
        assertNull(entity.getFp());
        verify(repository, times(1)).save(entity);
    }
}
