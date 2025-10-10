package com.example.jobconnect.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.jobconnect.dto.EmployerDTO;
import com.example.jobconnect.model.EmployerEntity;
import com.example.jobconnect.service.EmployerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class EmployerControllerTest {

    @Mock
    private EmployerService employerService;

    @InjectMocks
    private EmployerController employerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employerController).build();
    }

    @Test
    void testRegisterEmployer_EmailExists() throws Exception {
        EmployerDTO dto = new EmployerDTO();
        when(employerService.registerEmployer(any())).thenReturn("employeremail_exists");

        mockMvc.perform(post("/employer/register")
                        .param("employeremail", "test@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employerlogin?error=employeremail_exists"));
    }

    @Test
    void testVerifyAccount_Success() throws Exception {
        when(employerService.verifyAccount("token")).thenReturn(true);

        mockMvc.perform(get("/employer/verify")
                        .param("token", "token"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employerlogin?verified"));
    }

    @Test
    void testEmployerLogin_Success() throws Exception {
        EmployerEntity employer = new EmployerEntity();
        when(employerService.validateemployerLogin("test@example.com", "password"))
                .thenReturn(employer);

        mockMvc.perform(post("/employer/login")
                        .param("email", "test@example.com")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("employerhome"));
    }

    @Test
    void testEmployerLogin_Failure() throws Exception {
        when(employerService.validateemployerLogin("test@example.com", "wrongpassword"))
                .thenReturn(null);

        mockMvc.perform(post("/employer/login")
                        .param("email", "test@example.com")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("employerlogin"))
                .andExpect(model().attributeExists("error"));
    }
}
