package com.example.jobconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobconnect.model.JobseekerEntity;

@Repository
public interface JobseekerRepository extends JpaRepository<JobseekerEntity, Long> {

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	JobseekerEntity findByVerificationToken(String token);

	JobseekerEntity findByEmail(String email);

}
