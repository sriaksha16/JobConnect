package com.example.jobconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobconnect.model.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

	List<JobEntity> findByEmployeremail(String employeremail);

}
