package com.example.jobconnect.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.jobconnect.service.Jobseekerservice;

@WebMvcTest(JobseekerController.class)
public class JobseekerControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private Jobseekerservice jobSeekerService;

    @InjectMocks
    private JobseekerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testJobseekerRegister_EmailExists() throws Exception {
        when(jobSeekerService.registerJobSeeker(any())).thenReturn("email_exists");

        mockMvc.perform(MockMvcRequestBuilders.post("/jobseeker/register")
                .param("email", "test@example.com")
                .param("username", "testuser"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jobseekerlogin?error=email_exists"));
    }

    @Test
    void testJobseekerRegister_Success() throws Exception {
        when(jobSeekerService.registerJobSeeker(any())).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/jobseeker/register")
                .param("email", "test@example.com")
                .param("username", "testuser"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jobseekerlogin?verify_email"));
    }

    @Test
    void testJobseekerVerifyAccount() throws Exception {
        when(jobSeekerService.verifyAccount("token")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/jobseeker/verify")
                .param("token", "token"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jobseekerlogin?verified"));
    }

    @Test
    void testJobseekerLogin_Invalid() throws Exception {
        when(jobSeekerService.validatejobseekerLogin(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/jobseeker/login")
                .param("email", "wrong@example.com")
                .param("password", "wrong"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"));
    }
}
