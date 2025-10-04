package com.example.jobconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobconnect.model.EmployerEntity;

@Repository
public interface EmpoyerRepository extends JpaRepository<EmployerEntity, Long>{

	boolean existsByEmployeremail(String employeremail);

	boolean existsByEmployername(String employername);

	EmployerEntity findByVerificationToken(String token);

	EmployerEntity findByEmployeremail(String email);

	

}
