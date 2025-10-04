package com.example.jobconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobconnect.model.JobApplicationEntity;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {

	

	List<JobApplicationEntity> findByJobIdIn(List<Long> jobIds);

}
